/*
 * Created on 13 nov 2016 ( Time 20:13:49 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.salondeventas.cliente.desktop.modelo;
import java.io.Serializable;


public class UsuarioperfilesKey implements Serializable {
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY KEY ATTRIBUTES 
    //----------------------------------------------------------------------
    private Integer    idusuario    ;
    
    private Integer    idperfil     ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTORS
    //----------------------------------------------------------------------
    public UsuarioperfilesKey() {
        super();
    }

    public UsuarioperfilesKey( Integer idusuario, Integer idperfil ) {
        super();
        this.idusuario = idusuario ;
        this.idperfil = idperfil ;
    }
    
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR KEY FIELDS
    //----------------------------------------------------------------------
    public void setIdusuario( Integer value ) {
        this.idusuario = value;
    }
    public Integer getIdusuario() {
        return this.idusuario;
    }

    public void setIdperfil( Integer value ) {
        this.idperfil = value;
    }
    public Integer getIdperfil() {
        return this.idperfil;
    }


    //----------------------------------------------------------------------
    // equals METHOD
    //----------------------------------------------------------------------
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		UsuarioperfilesKey other = (UsuarioperfilesKey) obj; 
		//--- Attribute idusuario
		if ( idusuario == null ) { 
			if ( other.idusuario != null ) 
				return false ; 
		} else if ( ! idusuario.equals(other.idusuario) ) 
			return false ; 
		//--- Attribute idperfil
		if ( idperfil == null ) { 
			if ( other.idperfil != null ) 
				return false ; 
		} else if ( ! idperfil.equals(other.idperfil) ) 
			return false ; 
		return true; 
	} 


    //----------------------------------------------------------------------
    // hashCode METHOD
    //----------------------------------------------------------------------
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		
		//--- Attribute idusuario
		result = prime * result + ((idusuario == null) ? 0 : idusuario.hashCode() ) ; 
		//--- Attribute idperfil
		result = prime * result + ((idperfil == null) ? 0 : idperfil.hashCode() ) ; 
		
		return result; 
	} 


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
		StringBuffer sb = new StringBuffer(); 
		sb.append(idusuario); 
		sb.append("|"); 
		sb.append(idperfil); 
        return sb.toString();
    }
}