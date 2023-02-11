package model;

/**
 * Clase abstracta Persona que contiene información básica de una persona como su DNI, nombre, edad,
 * sexo y dirección.
 */
public abstract class Persona {

    // Atributos de la clase
    protected String dni; // DNI de la persona
    protected String nombre; // Nombre de la persona
    protected int edad; // Edad de la persona
    protected char sexo; // Sexo de la persona (H o M)
    protected String direccion; // Dirección de la persona


    // Getter y setters

    /**
     * Obtener el DNI de la persona
     *
     * @return dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Asignar el DNI de la persona
     *
     * @param dni DNI de la persona
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtener el nombre de la persona
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asignar el nombre de la persona
     *
     * @param nombre Nombre de la persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtener la edad de la persona
     *
     * @return edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Asignar la edad de la persona
     *
     * @param edad Edad de la persona
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtener el sexo de la persona
     *
     * @return sexo
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * Asignar el sexo de la persona
     *
     * @param sexo Sexo de la persona (H o M)
     */
    public void setSexo(char sexo) {
        if (sexo == 'H' || sexo == 'M') {
            this.sexo = sexo;
        } else sexo = 'H'; // Si no es H o M se asigna H por defecto
    }

    /**
     * Obtener la dirección de la persona
     *
     * @return direccion
     */
    public String getDireccion() {
        return direccion;
    }

/**
 * Asignar la dirección de la persona
 * @param direccion Dirección de la persona
 * */

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
                ", direccion = " + direccion;
    }
}

