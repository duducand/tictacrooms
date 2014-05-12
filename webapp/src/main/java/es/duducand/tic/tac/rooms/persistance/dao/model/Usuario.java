package es.duducand.tic.tac.rooms.persistance.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "usuario",
	   uniqueConstraints = {@UniqueConstraint(columnNames={"login"})}
)
public class Usuario extends Eliminable{

	private static final long serialVersionUID = 1L;

	
	private String login;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String poblacion;
    private String provincia;
    private String codigoPostal;
    private String claveUsuario;
    private String cuentaBancaria;
    private String email;
    private Boolean administrador;
    private Boolean trabajador;
    private Boolean socio;

	
	
	@Column(name = "login", nullable=false, length=50)
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Usuario: {");
		buffer.append("Id: ").append(this.getId()).append(", ");
		buffer.append("Login: ").append(this.getLogin()).append(", ");
		
		buffer.append("Fecha Baja: ").append(this.getFechaBaja() != null? this.getFechaBaja():"null").append(", ");
		buffer.append("Usuario Baja: ").append(this.getUsuarioBaja() != null? this.getUsuarioBaja():"null").append(", ");
		buffer.append("}");
		return buffer.toString();
	}
	
	@Column(name = "claveUsuario", nullable=false, length=50)
    public String getClaveUsuario() {
        return claveUsuario;
    }
    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }
    
    @Column(name = "nombre", nullable=false, length=50)
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name = "apellidos", nullable=false, length=50)
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    @Column(name = "direccion", nullable=false, length=50)
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    @Column(name = "poblacion", nullable=false, length=50)
    public String getPoblacion() {
        return poblacion;
    }
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }
    
    @Column(name = "provincia", nullable=false, length=50)
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    @Column(name = "codigoPostal", nullable=false, length=50)
    public String getCodigoPostal() {
        return codigoPostal;
    }
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    
    @Column(name = "cuentaBancaria", nullable=false, length=50)
    public String getCuentaBancaria() {
        return cuentaBancaria;
    }
    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
    
    @Column(name = "email", nullable=false, length=50)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    

    public Boolean getAdministrador() {
        return administrador;
    }
    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }
    public Boolean getTrabajador() {
        return trabajador;
    }
    public void setTrabajador(Boolean trabajador) {
        this.trabajador = trabajador;
    }
    public Boolean getSocio() {
        return socio;
    }
    public void setSocio(Boolean socio) {
        this.socio = socio;
    }
    /* BUENAS PRACTICAS PARA METODO EQUALS: (Se puede usar la libreria commons-lang (http://commons.apache.org/lang/))
     * 1. Nunca comparar el id que recomienda usar hibernate, dado que un objeto puede estar persistido y otro objeto igual puede que todavia no.
     * 2. Para saber el tipo del objeto que nos manda, no usar getClass(), sino instanceof. Dado que el proxy de la clase persistene que crea Hibernate no es de la misma clase.
     * 3. Siempre usar los getters y nunca acceder directamente a los atributos. Esto es porque el proxy de hibernate, hasta que no se accede a su getter no se recuperan sus valores de BBDD.
     * 4. No comparar las relaciones de BDD implementadas con collections directamente con un equals, comparar cada objeto internoo uno por uno. Esto es porque el objeto contenedor del proxy de Hibernate recuperado con load no tiene porque ser el mismo que el objeto contenedor de la clase persistente
    */
    @Override
    public boolean equals(Object obj) {
        boolean isEquals = false;
        try {           
            final Usuario other = (Usuario)obj;
            final EqualsBuilder eqb = new EqualsBuilder();
            eqb.append(getClaveUsuario(), other.getClaveUsuario());
            isEquals = eqb.isEquals();
        } catch (Exception e) {
            // Sobre todo si no se puede hacer la conversión de tipos, y en general si se produce cualquier error, devolver que no es igual
            isEquals = false;
        }
        return isEquals;
    }
    
     @Override
     public int hashCode() {
         final HashCodeBuilder hcb = new HashCodeBuilder();
         hcb.append(getClaveUsuario());
         hcb.append(getApellidos());
         hcb.append(getCodigoPostal());
         hcb.append(getCuentaBancaria());
         hcb.append(getDireccion());
         hcb.append(getEmail());
         hcb.append(getNombre());
         hcb.append(getPoblacion());
         hcb.append(getProvincia());
         
         return hcb.toHashCode();
     }
}
