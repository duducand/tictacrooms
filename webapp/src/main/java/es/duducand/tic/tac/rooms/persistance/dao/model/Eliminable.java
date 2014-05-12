package es.duducand.tic.tac.rooms.persistance.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Eliminable implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4065709825496821318L;
    private Integer id;
    private Date fechaBaja;
    private Usuario usuarioBaja;
    
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "fechaBaja", nullable=true)
    public Date getFechaBaja() {
        return (fechaBaja == null) ? null : (Date) fechaBaja.clone();
    }
    
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = (fechaBaja == null) ? null : (Date) fechaBaja.clone();
    }

    @ManyToOne
    @JoinColumn(name = "usuarioBaja", nullable=true)
    public Usuario getUsuarioBaja() {
        return usuarioBaja;
    }
    public void setUsuarioBaja(Usuario usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }
}
