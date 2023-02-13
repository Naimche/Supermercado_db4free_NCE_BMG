package gui;

import autentificacion.Encriptacion;
import gestor.GestorBBDD;
import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Gui {
    private Scanner sc = new Scanner(System.in);
    private GestorBBDD gestorBBDD = new GestorBBDD();
    private Encriptacion encriptacion = new Encriptacion();

    public void start() {
        try {
            Cliente cliente = new Cliente("12345678A","Naim",12,'H',12.0,"adas","1234");
            gestorBBDD.insertarCliente(cliente);
        }catch (Exception e){
            System.out.println("Error al insertar clienteLogin");
        }

        int opcion = 0;
        do {
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Login cliente");
            System.out.println("2. Login empleado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    try {
                        loginCliente();
                    } catch (Exception e) {
                        System.out.println("Error al iniciar sesión.");
                    }
                    break;
                case 2:
                    Empleado();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 3);
    }

    private boolean loginCliente() throws Exception {

        System.out.println("----- Login cliente -----");
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        System.out.println("Comprobando credenciales...");
        if (gestorBBDD.selectClienteLoginByDni(dni).getContrasena().equals(Encriptacion.encriptar(contrasena))) {
            Cliente();
            return true;
        } else {
            System.out.println("DNI o contraseña incorrectos.");
            return false;
        }
    }

    private void Cliente() {
        System.out.println("----- Login cliente -----");
        System.out.println("1. Realizar compra");
        System.out.println("2. Pagar");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                // TODO: 2/12/2023 Realizar compra
                break;
            case 2:
                // TODO: 2/12/2023 Pagar
                break;
            case 3:
                // TODO: 2/12/2023 Salir
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
        }
    }

    private void Empleado() {
        System.out.println("----- Login empleado -----");
        System.out.println("1. Ver historial de compras");
        System.out.println("2. Gestionar inventario");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("Viendo compras anteriores...");
                break;
            case 2:
                System.out.println("Gestionando inventario...");
                break;
            case 3:
                System.out.println("Saliendo del menú empleado...");
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
        }
    }
}

