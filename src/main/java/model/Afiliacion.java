package model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "afiliacion")
public class Afiliacion {
    @Id
    @Column(name = "idAfiliacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToMany()
    @JoinTable(name = "afilicacion_descuento", joinColumns = @JoinColumn(name = "idAfiliacion"), inverseJoinColumns = @JoinColumn(name = "idDescuento"))
    private Set<Descuentos> descuentos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "afilicacion_clientes", joinColumns = @JoinColumn(name = "idAfiliacion"), inverseJoinColumns = @JoinColumn(name = "idCliente"))
    private Set<Cliente> clientes;

    public Afiliacion() {
    }

    public Afiliacion(String nombre) {
        this.nombre = nombre;
    }

    public Afiliacion(String nombre, Set<Descuentos> descuentos) {
        this.nombre = nombre;
        this.descuentos = descuentos;

    }

    public int getId() {
        return id;
    }

    public Set<Descuentos> getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(Set<Descuentos> descuentos) {
        this.descuentos = descuentos;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }
}
