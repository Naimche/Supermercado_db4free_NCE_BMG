package model;

import autentificacion.Encriptacion;

import javax.persistence.*;
import java.util.List;

/**
 * Clase Empleado que hereda de la clase Persona, contiene información adicional de un empleado
 * como su sueldo y su id de empleado.
 */
@Entity
@Table(name = "empleados")
public class Empleado extends Persona {
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
    @Column(name = "sueldo")
    private double sueldo;
    // Sueldo del empleado
    @Column(name = "idEmpleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleado;
    // Id del empleado
    // crear una contraseña para el empleado
    @Column(name = "contrasena")
    private String contrasena;
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
    public Empleado() {
    }

    public Empleado(String dni, String nombre, Integer edad, Character sexo, String direccion, Double sueldo,String contrasena) throws Exception {
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.direccion = direccion;
        this.sueldo = sueldo;
        this.contrasena = Encriptacion.encriptar(contrasena);
    }

    /**
     * Obtener el sueldo del empleado
     *
     * @return sueldo
     */
    public double getSueldo() {
        return sueldo;
    }

    /**
     * Asignar el sueldo del empleado
     *
     * @param sueldo Sueldo del empleado
     */
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * Obtener el id del empleado
     *
     * @return idEmpleado
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * Asignar el id del empleado
     *
     * @param idEmpleado Id del empleado
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Método toString que devuelve información del objeto en forma de String
     *
     * @return String
     */
    @Override
    public String toString() {
        return " DNI = " + dni +
                ", nombre = " + nombre +
                ", edad = " + edad +
                ", sexo = " + sexo +
                ", direccion = " + direccion +
                ", sueldo = " + sueldo +
                ", idEmpleado = " + idEmpleado;
    }

}

