package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "descuentos")
public class Descuentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "Porcentaje_Descuento")
    private double porcentajeDescuento;
    @Column(name = "Categoria")
    private String categoria;

    public Descuentos(double porcentajeDescuento, String categoria) {
        this.porcentajeDescuento = porcentajeDescuento;
        this.categoria = categoria;
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
}
