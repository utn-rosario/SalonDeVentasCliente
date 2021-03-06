/*
 * Created on 22 nov 2016 ( Time 09:39:30 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.salondeventas.cliente.desktop.modelo;

import java.io.Serializable;
import javax.validation.constraints.* ;
import org.hibernate.validator.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    private Integer    idproducto   ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
	@Size(max = 90)
    private String     nombre       ;
	@Size(max = 90)
    private String     codbarras    ;
    private Integer    mininventario ;
    private BigDecimal precio       ;
    private Integer    cantidadStock ;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    private List<Lineadeventa> listOfLineadeventa;

    private List<ProductoIngreso> listOfProductoIngreso;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Producto() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdproducto( Integer idproducto ) {
        this.idproducto = idproducto ;
    }
    public Integer getIdproducto() {
        return this.idproducto;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : nombre ( VARCHAR ) 
    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return this.nombre;
    }

    //--- DATABASE MAPPING : codBarras ( VARCHAR ) 
    public void setCodbarras( String codbarras ) {
        this.codbarras = codbarras;
    }
    public String getCodbarras() {
        return this.codbarras;
    }

    //--- DATABASE MAPPING : minInventario ( INT ) 
    public void setMininventario( Integer mininventario ) {
        this.mininventario = mininventario;
    }
    public Integer getMininventario() {
        return this.mininventario;
    }

    //--- DATABASE MAPPING : precio ( DECIMAL ) 
    public void setPrecio( BigDecimal precio ) {
        this.precio = precio;
    }
    public BigDecimal getPrecio() {
        return this.precio;
    }

    //--- DATABASE MAPPING : cantidad_stock ( INT ) 
    public void setCantidadStock( Integer cantidadStock ) {
        this.cantidadStock = cantidadStock;
    }
    public Integer getCantidadStock() {
        return this.cantidadStock;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfLineadeventa( List<Lineadeventa> listOfLineadeventa ) {
        this.listOfLineadeventa = listOfLineadeventa;
    }
    public List<Lineadeventa> getListOfLineadeventa() {
        return this.listOfLineadeventa;
    }

    public void setListOfProductoIngreso( List<ProductoIngreso> listOfProductoIngreso ) {
        this.listOfProductoIngreso = listOfProductoIngreso;
    }
    public List<ProductoIngreso> getListOfProductoIngreso() {
        return this.listOfProductoIngreso;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idproducto);
        sb.append("|"); 
        sb.append(nombre);
        sb.append("|");
        sb.append(precio);        
        return sb.toString(); 
    } 

}
