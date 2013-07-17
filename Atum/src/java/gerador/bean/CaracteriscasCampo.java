/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gerador.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author GCI
 */
@Entity
public class CaracteriscasCampo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Atributos atributos;
    @OneToOne
    private Caracteristica caracteristica;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Atributos getAtributos() {
        return atributos;
    }

    public void setAtributos(Atributos atributos) {
        this.atributos = atributos;
    }

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }
}
