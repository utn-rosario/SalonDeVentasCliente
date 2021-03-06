/*
 * Created on 22 nov 2016 ( Time 09:39:30 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a composite Primary Key  


package org.salondeventas.cliente.desktop.modelo;

import java.io.Serializable;
import javax.validation.constraints.* ;
import org.hibernate.validator.constraints.*;
import java.util.Date;

public class ProductoIngreso implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProductoIngresoKey compositePrimaryKey ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
	@NotNull
	@NotBlank
    private Date       fechaingreso ;
	@NotNull
	@NotBlank
    private Integer    cantidad     ;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    private Producto producto    ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public ProductoIngreso() {
		super();
		this.compositePrimaryKey = new ProductoIngresoKey();       
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
    public void setIdproductoIngreso( Integer idproductoIngreso ) {
        this.compositePrimaryKey.setIdproductoIngreso( idproductoIngreso ) ;
    }
    public Integer getIdproductoIngreso() {
        return this.compositePrimaryKey.getIdproductoIngreso() ;
    }
    public void setIdproducto( Integer idproducto ) {
        this.compositePrimaryKey.setIdproducto( idproducto ) ;
    }
    public Integer getIdproducto() {
        return this.compositePrimaryKey.getIdproducto() ;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : fechaIngreso ( DATETIME ) 
    public void setFechaingreso( Date fechaingreso ) {
        this.fechaingreso = fechaingreso;
    }
    public Date getFechaingreso() {
        return this.fechaingreso;
    }

    //--- DATABASE MAPPING : cantidad ( INT ) 
    public void setCantidad( Integer cantidad ) {
        this.cantidad = cantidad;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setProducto( Producto producto ) {
        this.producto = producto;
    }
    public Producto getProducto() {
        return this.producto;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        if ( compositePrimaryKey != null ) {  
            sb.append(compositePrimaryKey.toString());  
        }  
        else {  
            sb.append( "(null-key)" ); 
        }  
        sb.append("]:"); 
        sb.append(fechaingreso);
        sb.append("|");
        sb.append(cantidad);
        return sb.toString(); 
    } 

}
