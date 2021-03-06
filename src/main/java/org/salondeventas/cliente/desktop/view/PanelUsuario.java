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

import org.javafx.controls.customs.DecimalField;
import org.javafx.controls.customs.NumberField;
import org.javafx.controls.customs.StringField;
import org.salondeventas.cliente.desktop.PropertyResourceBundleMessageInterpolator;
import org.salondeventas.cliente.desktop.modelo.Usuario;

public class PanelUsuario extends BorderPane implements EventHandler<ActionEvent>{
	private boolean modoEdit = false;
	private PanelGrillaUsuario father;
	private ResourceBundle resource;

	@FXML
	private PanelControlesEdit panelControlesEdit;
	
	@FXML
	private VBox vBoxMsg;


	@FXML
	private NumberField txtidusuario;

	@FXML
	private StringField txtclave;

	@FXML
	private StringField txtnombre;

	public PanelUsuario(PanelGrillaUsuario father) {
		this.modoEdit = false;
		this.father = father;
		initComponentes();
    }

	public PanelUsuario(PanelGrillaUsuario father, int id) {
		this.modoEdit = true;
		this.father = father;
		initComponentes();
		loadEntity(id);		
    }

	private void loadEntity(int id) {
		try {
			Usuario unUsuario = new Usuario();
			unUsuario.setIdusuario(id);
			unUsuario =father.getServicio().load(unUsuario);
			loadForm(unUsuario);
		} catch (Exception e) {
			Label label = new Label();
	    	label.setText("Se ha producido un error en el servidor. Intente mas tarde.");
	    	vBoxMsg.getChildren().addAll(label);
			e.printStackTrace();
		}
	}

	private void initComponentes(){
		this.resource = ResourceBundle.getBundle("i18n.ValidationMessages");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.getClass().getSimpleName() + ".fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.setResources(this.resource);

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

	public void loadForm(Usuario usuario){
		if(usuario !=null){
			if(usuario.getIdusuario() != null){
				txtidusuario.setValue(usuario.getIdusuario());
			}
			if(usuario.getClave() != null){
				txtclave.setValue(usuario.getClave());
			}
			if(usuario.getNombre() != null){
				txtnombre.setValue(usuario.getNombre());
			}
		}
	}

	private Usuario getUsuario() {
		Usuario unUsuario = new Usuario();
		try{
			unUsuario.setIdusuario(Integer.valueOf(txtidusuario.getText()));
		}catch (NumberFormatException e) {
			unUsuario.setIdusuario(null);
		}
		unUsuario.setClave(txtclave.getText());
		unUsuario.setNombre(txtnombre.getText());
		
		Label label = null;	
		vBoxMsg.getChildren().clear();
		Validator validator =PropertyResourceBundleMessageInterpolator.getValidation();
	    Set<ConstraintViolation<Usuario>> inputErrors = validator.validate(unUsuario); 
	    for(ConstraintViolation<Usuario> error: inputErrors){	    	
	    	label = new Label();
	    	label.setText(error.getMessage());
	    	vBoxMsg.getChildren().addAll(label);	    	
	    }
	    if(vBoxMsg.getChildren().size() > 0){
	    	return null;
	    }
		return unUsuario;
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(panelControlesEdit.getBtnGuardar())){
			Usuario unUsuario = getUsuario();
			if(unUsuario != null){
				try {
					if(modoEdit){
						father.getServicio().update(unUsuario);
					}else{
						father.getServicio().insert(unUsuario);
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