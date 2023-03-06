package model;

import interfaces.Descuento;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "descuentos")
public class Descuentos implements Descuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "Porcentaje_Descuento")
    private double porcentajeDescuento;
    @Column(name = "Categoria")
    private String categoria;
    @ManyToMany(mappedBy = "descuentos", cascade = CascadeType.ALL)
    private Set<Afiliacion> afiliaciones = null;
    public Descuentos() {
    }

    public Descuentos(double porcentajeDescuento, String categoria) {
        this.porcentajeDescuento = porcentajeDescuento;
        this.categoria = categoria;
    }

    public Descuentos(double porcentajeDescuento, String categoria, Set<Afiliacion> afiliaciones) {
        this.porcentajeDescuento = porcentajeDescuento;
        this.categoria = categoria;
        this.afiliaciones = afiliaciones;
    }


    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public double aplicarDescuento(double monto) {
        // Aplicando el descuento del porcentaje especificado
        return monto - (monto * porcentajeDescuento / 100);
    }
}
