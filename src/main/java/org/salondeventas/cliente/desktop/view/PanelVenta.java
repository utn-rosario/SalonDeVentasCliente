package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.javafx.controls.customs.ComboBoxAutoComplete;
import org.javafx.controls.customs.DecimalField;
import org.javafx.controls.customs.NumberField;
import org.javafx.controls.panels.PanelControlesEdit;
import org.salondeventas.cliente.desktop.PropertyResourceBundleMessageInterpolator;
import org.salondeventas.cliente.desktop.modelo.Lineadeventa;
import org.salondeventas.cliente.desktop.modelo.Producto;
import org.salondeventas.cliente.desktop.modelo.Venta;
import org.salondeventas.cliente.desktop.servicios.ILineadeventaServicio;
import org.salondeventas.cliente.desktop.servicios.IProductoServicio;
import org.salondeventas.cliente.desktop.servicios.impl.LineadeventaServicio;
import org.salondeventas.cliente.desktop.servicios.impl.ProductoServicio;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class PanelVenta extends BorderPane implements EventHandler<ActionEvent>{
	private boolean modoEdit = false;
	private PanelGrillaVenta father;	
	private IProductoServicio productoServicio;
	private ILineadeventaServicio iLineadeventaServicio;
	
	@FXML
	private PanelControlesEdit panelControlesEdit;
	
	@FXML
	private VBox vBoxMsg;


	@FXML
	private NumberField txtidventa;

	@FXML
	private DatePicker dprfecha;

	@FXML
	private DatePicker dprfechaPago;

	@FXML
	private DecimalField txttotal;
	
	@FXML
	private TableView<Lineadeventa> tblLineaDeVentas;
	
	@FXML
	private TableColumn colProducto;
	
	@FXML
	private TableColumn colCantidad;
	
	@FXML
	private TableColumn colImporte;
	
	@FXML
	private ComboBoxAutoComplete<Producto> cbxAgregarProducto;
	
	private ObservableList<Lineadeventa> data ;

	public PanelVenta(PanelGrillaVenta father) {
		this.modoEdit = false;
		this.father = father;
		initComponentes();
    }

	public PanelVenta(PanelGrillaVenta father, int id) {
		this.modoEdit = true;
		this.father = father;
		initComponentes();
		loadEntity(id);		
    }

	private void loadEntity(int id) {
		try {
			Venta unVenta = new Venta();
			unVenta.setIdventa(id);
			unVenta =father.getServicio().load(unVenta);
			loadForm(unVenta);
		} catch (Exception e) {
			Label label = new Label();
	    	label.setText("Se ha producido un error en el servidor. Intente mas tarde.");
	    	vBoxMsg.getChildren().addAll(label);
			e.printStackTrace();
		}
	}

	private void initComponentes(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.getClass().getSimpleName() + ".fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.setResources(ResourceBundle.getBundle("i18n.ValidationMessages"));

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        productoServicio = new ProductoServicio();   
        iLineadeventaServicio = new LineadeventaServicio();
               
        this.setLeft(null);
        this.setRight(null);
        panelControlesEdit.setScene(ControllLogin.getScene());
        panelControlesEdit.getBtnGuardar().setOnAction(this);        
        panelControlesEdit.getBtnCancelar().setOnAction(this);
        father.getTab().setContent(this);       
        
		dprfecha.setValue(LocalDate.now());			
		dprfechaPago.setValue(LocalDate.now());	
		
		cbxAgregarProducto.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {	
				if(event.getCode() == KeyCode.ENTER){
					addProducto();
				}										
			}
		});					
		
		try {
			ObservableList<Producto> productos = FXCollections.observableArrayList (productoServicio.loadAll());
			cbxAgregarProducto.setItems(productos);
			cbxAgregarProducto.reload();				 				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		cbxAgregarProducto.requestFocus();		
		tblLineaDeVentas.setRowFactory(new Callback<TableView<Lineadeventa>, TableRow<Lineadeventa>>() {  
            @Override  
            public TableRow<Lineadeventa> call(TableView<Lineadeventa> tableView) {  
                final TableRow<Lineadeventa> row = new TableRow<>();  
                final ContextMenu contextMenu = new ContextMenu();  
                final MenuItem removeMenuItem = new MenuItem("Eliminar");  
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
                    @Override  
                    public void handle(ActionEvent event) {  
                    	Lineadeventa linea = row.getItem();
                		if(linea.getIdventa() != null){
                			try {
                				if(modoEdit){
                					int stock = 0;
                					iLineadeventaServicio.delete(linea);	                					
                					if(linea.getProducto().getCantidadStock() != null){
                						stock = linea.getProducto().getCantidadStock() + linea.getCantidad();
                						linea.getProducto().setCantidadStock(stock);
                						productoServicio.update(linea.getProducto());
                					}
                				}
    							
    							tblLineaDeVentas.getItems().remove(linea); 
    							calcularTotal();
    						} catch (Exception e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                		}else{
                			tblLineaDeVentas.getItems().remove(linea);  
                		}
                    }  
                });  
                contextMenu.getItems().add(removeMenuItem);  
               // Set context menu on row, but use a binding to make it only show for non-empty rows:  
                row.contextMenuProperty().bind(  
                        Bindings.when(row.emptyProperty())  
                        .then((ContextMenu)null)  
                        .otherwise(contextMenu)  
                );  
                return row ;  
            }  
		}); 
	}
	
	private void addProducto(){		
		int indexProducto = cbxAgregarProducto.getSelectionModel().getSelectedIndex();
		if(indexProducto >= 0){
			Producto prodSelected = cbxAgregarProducto.getItems().get(indexProducto);
			if(prodSelected != null){
				for(Lineadeventa linea: tblLineaDeVentas.getItems()){
					if(linea.getProducto().getIdproducto() == prodSelected.getIdproducto()){
						Alert alert = new Alert(AlertType.INFORMATION);		
						alert.setHeaderText("El producto ya se encuentra ingresado.");				
						alert.showAndWait();
						cbxAgregarProducto.getEditor().setText("");
						return;
					}
				}
				
				TextInputDialog dialog = new TextInputDialog("Ingrese un numero");
				dialog.setTitle("Ingresar la cantidad del Producto");
				dialog.setContentText("Cantidad:");
				// Traditional way to get the response value.
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
				    Lineadeventa nuevaLinea = new Lineadeventa();												
					nuevaLinea.setIdproducto(prodSelected.getIdproducto());
					nuevaLinea.setProducto(prodSelected);
					try{
						nuevaLinea.setCantidad(Integer.valueOf(result.get()));	
					}catch (NumberFormatException e) {
						Alert alert = new Alert(AlertType.ERROR);		
						alert.setHeaderText("Cantidad Incorrecta.");				
						alert.showAndWait();
						return;
					}					
					nuevaLinea.setPrecio(new BigDecimal(nuevaLinea.getCantidad() * prodSelected.getPrecio().doubleValue()));
					tblLineaDeVentas.getItems().add(nuevaLinea);					
					
					calcularTotal();
				}else{					
					return;
				}
				cbxAgregarProducto.setValue(null);
			}
		}		
	}

	public void loadForm(Venta venta){
		if(venta !=null){
			if(venta.getIdventa() != null){
				txtidventa.setText(String.valueOf(venta.getIdventa()));
			}
			if(venta.getFecha() != null){
				dprfecha.setValue(new java.sql.Date(venta.getFecha().getTime()).toLocalDate());		
			}
			if(venta.getFechaPago() != null){
				dprfechaPago.setValue(new java.sql.Date(venta.getFechaPago().getTime()).toLocalDate());		
			}
			if(venta.getTotal() != null){
				txttotal.setValue(venta.getTotal());
			}
						 							
			data = FXCollections.observableArrayList(venta.getListOfLineadeventa());
			tblLineaDeVentas.setItems(data);															
		}
	}

	private Venta getVenta() {
		Venta unVenta = new Venta();
		try{
			unVenta.setIdventa(Integer.valueOf(txtidventa.getText()));
		}catch (NumberFormatException e) {
			unVenta.setIdventa(null);
		}
		unVenta.setFecha(java.sql.Date.valueOf(dprfecha.getValue()));
		unVenta.setFechaPago(java.sql.Date.valueOf(dprfechaPago.getValue()));
		
		calcularTotal();
		unVenta.setTotal(txttotal.getValue());
		//Tiene que haber al menos un producto para que guarde
		if(unVenta.getTotal() == null){
			return null;
		}
		
		Label label = null;	
		vBoxMsg.getChildren().clear();
		Validator validator =PropertyResourceBundleMessageInterpolator.getValidation();
	    Set<ConstraintViolation<Venta>> inputErrors = validator.validate(unVenta); 
	    for(ConstraintViolation<Venta> error: inputErrors){	    	
	    	label = new Label();
	    	label.setText(error.getMessage());
	    	vBoxMsg.getChildren().addAll(label);	    	
	    }
	    if(vBoxMsg.getChildren().size() > 0){
	    	return null;
	    }
		return unVenta;
	}
	
	private List<Lineadeventa> getLineasDeVentas(Venta unaVenta) {
		Label label = null;	
		vBoxMsg.getChildren().clear();
		Validator validator =PropertyResourceBundleMessageInterpolator.getValidation();
		List<Lineadeventa> lineasDeVentas = new ArrayList<Lineadeventa>();
		for(Lineadeventa unaLineaDeVenta: tblLineaDeVentas.getItems()){			
			unaLineaDeVenta.setIdventa(unaVenta.getIdventa());		
			lineasDeVentas.add(unaLineaDeVenta);
			Set<ConstraintViolation<Lineadeventa>> inputErrors = validator.validate(unaLineaDeVenta); 
		    for(ConstraintViolation<Lineadeventa> error: inputErrors){	    	
		    	label = new Label();
		    	label.setText(error.getMessage());
		    	vBoxMsg.getChildren().addAll(label);	    	
		    }
		    if(vBoxMsg.getChildren().size() > 0){
		    	return null;
		    }
		}
		return 	lineasDeVentas;				    
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(panelControlesEdit.getBtnGuardar())){
			Venta unVenta = getVenta();
			if(unVenta != null){
				try {
					if(modoEdit){
						father.getServicio().update(unVenta);
					}else{
						unVenta.setIdventa(Integer.valueOf(father.getServicio().insert(unVenta)));
					}
					
					int stock = 0;
					List<Lineadeventa> lineas=getLineasDeVentas(unVenta);
					for(Lineadeventa linea: lineas){
						linea.setIdventa(unVenta.getIdventa());								
						iLineadeventaServicio.insert(linea);		
						if(linea.getProducto().getCantidadStock() != null){
							stock = linea.getProducto().getCantidadStock() - linea.getCantidad();
							linea.getProducto().setCantidadStock(stock);
							productoServicio.update(linea.getProducto());
						}
					}
					
					father.reLoad();    
				} catch (Exception e) {
					Label label = new Label();
			    	label.setText("Se ha producido un error en el servidor. Intente mas tarde.");
			    	vBoxMsg.getChildren().addAll(label);
					e.printStackTrace();			
				}
			}		
		}
		if(event.getSource().equals(panelControlesEdit.getBtnCancelar())){
			father.reLoad();    
		}			
	}
	
	private void calcularTotal(){
		double total = 0;
		for(Lineadeventa linea: tblLineaDeVentas.getItems()){
			total += linea.getCantidad() * linea.getPrecio().doubleValue();
		}
		txttotal.setValue(new BigDecimal(total));
	}
}