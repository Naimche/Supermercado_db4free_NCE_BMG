package model;
import jakarta.persistence.*;
import autentificacion.Encriptacion;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona {

    // Atributos de la clase
    @Id
    @Column(name = "dni",length = 9)

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
    @Column(name = "contrasena")
    private String contrasena;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @ManyToMany(mappedBy = "clientes", cascade = CascadeType.ALL)
    private Set<Afiliacion> afiliaciones = null;

    // Constructor

    public Cliente() {
    }

    public Cliente(String dni, String nombre, Integer edad, Character sexo, Double dinero, String direccion, String contrasena) throws Exception {
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.dinero = dinero;
        this.direccion = direccion;
        this.contrasena = Encriptacion.encriptar(contrasena);


    }
    public Cliente(String dni, String nombre, Integer edad, Character sexo, Double dinero, String direccion, String contrasena, Set<Afiliacion> afiliaciones) throws Exception {
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.dinero = dinero;
        this.direccion = direccion;
        this.contrasena = Encriptacion.encriptar(contrasena);
        this.afiliaciones = afiliaciones;

    }

    public String getUsuario() {
        return dni;
    }

    public Set<Afiliacion> getAfiliacion() {
        return afiliaciones;
    }

    public void setUsuario(String dni) {
        this.dni = dni;
    }

    public void setAfiliaciones(Set<Afiliacion> afiliaciones) {
        this.afiliaciones = afiliaciones;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }



    // Getter y setters

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
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

    public String getDni() {
        return dni;
    }
}