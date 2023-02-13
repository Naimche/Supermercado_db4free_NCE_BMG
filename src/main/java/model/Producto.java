package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "inventario")
public class Producto {
    // Atributo para almacenar el nombre del producto
    @Column(name = "nombre")
    private String nombre;
    // Atributo para almacenar el precio de costo del producto
    @Column(name = "precioCosto")
    private double precioCosto;
    // Atributo para almacenar el precio de venta del producto
    @Column(name = "precioVenta")
    private double precioVenta;
    @Column(name = "cantidad")
    private int cantidad;
    @ManyToMany(mappedBy = "productos")
    private List<Pedido> pedidos;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;



    // Constructor que inicializa los atributos del producto
    public Producto (String nombre, double precioCosto, double precioVenta, int cantidad) {
        // Asignando los valores pasados como parámetros a los atributos correspondientes
        this.nombre = nombre;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
    }


    // Método para obtener el nombre del producto
    public String getNombre() {
        return nombre;
    }
    public int getId() {
        return id;
    }

    // Método para obtener el precio de costo del producto
    public double getPrecioCosto() {
        return precioCosto;
    }

    // Método para obtener el precio de venta del producto
    public double getPrecioVenta() {
        return precioVenta;
    }

    // Método para asignar un nuevo nombre al producto
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método para asignar un nuevo precio de costo al producto
    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    // Método para asignar un nuevo precio de venta al producto
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}