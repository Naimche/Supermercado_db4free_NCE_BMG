package model;

import autentificacion.Encriptacion;

import javax.persistence.*;

@Entity
@Table(name = "clienteLogin")
public class ClienteLogin {
    @Id
    @Column(name = "dni",length = 10)
    private String dni;
    @OneToOne
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    private Cliente cliente;
    @Column(name = "contrasena")
    private String contrasena;





    public ClienteLogin() {
    }

    public ClienteLogin(String dni, String contrasena) throws Exception {

        this.dni = dni;
        this.contrasena = Encriptacion.encriptar(contrasena);;
    }

    public String getUsuario() {
        return dni;
    }

    public void setUsuario(String dni) {
        this.dni = dni;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
