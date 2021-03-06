/*
 * Created on 9 oct 2016 ( Time 11:29:08 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.salondeventas.cliente.desktop.modelo;

import java.io.Serializable;
import javax.validation.constraints.* ;
import org.hibernate.validator.constraints.*;
import java.util.Date;

public class Licencias implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    private Integer    numero       ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
		    private Date       fechaDesde   ;
		    private Date       fechaHasta   ;
		    private Boolean    usada        ;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Licencias() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setNumero( Integer numero ) {
        this.numero = numero ;
    }
    public Integer getNumero() {
        return this.numero;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : fecha_desde ( DATETIME ) 
    public void setFechaDesde( Date fechaDesde ) {
        this.fechaDesde = fechaDesde;
    }
    public Date getFechaDesde() {
        return this.fechaDesde;
    }

    //--- DATABASE MAPPING : fecha_hasta ( DATETIME ) 
    public void setFechaHasta( Date fechaHasta ) {
        this.fechaHasta = fechaHasta;
    }
    public Date getFechaHasta() {
        return this.fechaHasta;
    }

    //--- DATABASE MAPPING : usada ( BIT ) 
    public void setUsada( Boolean usada ) {
        this.usada = usada;
    }
    public Boolean getUsada() {
        return this.usada;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(numero);
        sb.append("]:"); 
        sb.append(fechaDesde);
        sb.append("|");
        sb.append(fechaHasta);
        sb.append("|");
        sb.append(usada);
        return sb.toString(); 
    } 

}
