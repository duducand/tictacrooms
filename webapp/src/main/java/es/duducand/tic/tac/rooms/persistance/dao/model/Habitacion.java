package es.duducand.tic.tac.rooms.persistance.dao.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


@Entity
@Table(name = "HABITACION")
public class Habitacion implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String precio;
	private String direccion;
	

	
	@Id
	@GeneratedValue
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "precio", nullable=false, length=50)
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	@Column(name = "direccion", nullable=false, length=200)
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
            final Habitacion other = (Habitacion)obj;
            final EqualsBuilder eqb = new EqualsBuilder();
            eqb.append(getPrecio(), other.getPrecio());
            eqb.append(getDireccion(), other.getDireccion());
            isEquals = eqb.isEquals();
        } catch (Exception e) {
            // Sobre todo si no se puede hacer la conversion de tipos, y en general si se produce cualquier error, devolver que no es igual
            isEquals = false;
        }
        return isEquals;
    }
	
	 @Override
     public int hashCode() {
         final HashCodeBuilder hcb = new HashCodeBuilder();
         hcb.append(getPrecio());
         hcb.append(getDireccion());
         
         return hcb.toHashCode();
     }
	 
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Habitacion: {");
		buffer.append("id: ").append(this.getId()).append(", ");
		buffer.append("precio: ").append(this.getPrecio()).append(", ");
		buffer.append("direccion: ").append(this.getDireccion());
		
		buffer.append("}");
		return buffer.toString();
	}

}
