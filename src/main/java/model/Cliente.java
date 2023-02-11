package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona {

    // Atributos de la clase
    @Id
    @Column(name = "dni",length = 10)

    private String dni;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "edad")
    private int edad;
    @Column(name = "sexo")
    private char sexo;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "dinero")
    private double dinero;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idAfiliacion")
    private Afiliacion afiliacion;

    // Constructor
    public Cliente(String dni, String nombre, Integer edad, Character sexo, Double dinero, String direccion) {
        super();

    }



    // Getter y setters

    public double getdinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    // Método toString

    @Override
    public String toString() {
        return super.toString() + ", dinero = " + dinero;
    }

    //Suma dinero al cliente
    public void sumDinero(double dineroIngresado) {
        this.dinero += dineroIngresado;
    }

    //Resta dinero al cliente
    public void restaDinero(double dineroExtraido) {
        this.dinero += dineroExtraido;
    }

    public boolean pagarPedido(Double pago) {
        return !(dinero - pago < 0);
    }
}