package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.javafx.controls.panels.PanelControlesEdit;
import javafx.scene.image.ImageView;



import org.salondeventas.cliente.desktop.PropertyResourceBundleMessageInterpolator;
import org.salondeventas.cliente.desktop.modelo.Empresa;

public class PanelEmpresa extends BorderPane implements EventHandler<ActionEvent>{
	private boolean modoEdit = false;
	private PanelGrillaEmpresa father;

	@FXML
	private PanelControlesEdit panelControlesEdit;
	
	@FXML
	private VBox vBoxMsg;


	@FXML
	private TextField txtidempresa;

	@FXML
	private TextField txtdescripcion;

	@FXML
	private TextField txtdireccion;

	@FXML
	private ImageView bytlogo;

	@FXML
	private TextField txtnombre;

	@FXML
	private TextField txttelefono;

	public PanelEmpresa(PanelGrillaEmpresa father) {
		this.modoEdit = false;
		this.father = father;
		initComponentes();
    }

	public PanelEmpresa(PanelGrillaEmpresa father, int id) {
		this.modoEdit = true;
		this.father = father;
		initComponentes();
		loadEntity(id);		
    }

	private void loadEntity(int id) {
		try {
			Empresa unEmpresa = new Empresa();
			unEmpresa.setIdempresa(id);
			unEmpresa =father.getServicio().load(unEmpresa);
			loadForm(unEmpresa);
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
                
        this.setLeft(null);
        this.setRight(null);
        panelControlesEdit.getBtnGuardar().setOnAction(this);        
        panelControlesEdit.getBtnCancelar().setOnAction(this);
        father.getTab().setContent(this);
        
	}

	public void loadForm(Empresa empresa){
		if(empresa !=null){
			if(empresa.getIdempresa() != null){
				txtidempresa.setText(String.valueOf(empresa.getIdempresa()));
			}
			if(empresa.getDescripcion() != null){
				txtdescripcion.setText(empresa.getDescripcion());
			}
			if(empresa.getDireccion() != null){
				txtdireccion.setText(empresa.getDireccion());
			}
			if(empresa.getLogo() != null){
			}
			if(empresa.getNombre() != null){
				txtnombre.setText(empresa.getNombre());
			}
			if(empresa.getTelefono() != null){
				txttelefono.setText(empresa.getTelefono());
			}
		}
	}

	private Empresa getEmpresa() {
		Empresa unEmpresa = new Empresa();
		try{
			unEmpresa.setIdempresa(Integer.valueOf(txtidempresa.getText()));
		}catch (NumberFormatException e) {
			unEmpresa.setIdempresa(null);
		}
		unEmpresa.setDescripcion(txtdescripcion.getText());
		unEmpresa.setDireccion(txtdireccion.getText());
		unEmpresa.setNombre(txtnombre.getText());
		unEmpresa.setTelefono(txttelefono.getText());
		
		Label label = null;	
		vBoxMsg.getChildren().clear();
		Validator validator =PropertyResourceBundleMessageInterpolator.getValidation();
	    Set<ConstraintViolation<Empresa>> inputErrors = validator.validate(unEmpresa); 
	    for(ConstraintViolation<Empresa> error: inputErrors){	    	
	    	label = new Label();
	    	label.setText(error.getMessage());
	    	vBoxMsg.getChildren().addAll(label);	    	
	    }
	    if(vBoxMsg.getChildren().size() > 0){
	    	return null;
	    }
		return unEmpresa;
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(panelControlesEdit.getBtnGuardar())){
			Empresa unEmpresa = getEmpresa();
			if(unEmpresa != null){
				try {
					if(modoEdit){
						father.getServicio().update(unEmpresa);
					}else{
						father.getServicio().insert(unEmpresa);
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
}