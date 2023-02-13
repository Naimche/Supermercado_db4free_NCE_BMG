package gui;

import autentificacion.Encriptacion;
import gestor.GestorBBDD;
import model.*;

import java.util.*;

public class Gui {
    private Scanner sc = new Scanner(System.in);
    private GestorBBDD gestorBBDD = new GestorBBDD();
    private Encriptacion encriptacion = new Encriptacion();

    public void start() {
        try {
            Cliente cliente = new Cliente("12345678A", "Naim", 12, 'H', 12.0, "adas", "1234");
            gestorBBDD.insertarCliente(cliente);
            Empleado empleado = new Empleado("12345678A", "Naim", 12, 'H', "persd", 123.0, "tinkywinky");
            gestorBBDD.insertarEmpleado(empleado);

        } catch (Exception e) {
            System.out.println("Error al insertar clienteLogin");
        }

        int opcion = 0;
        do {
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Cliente");
            System.out.println("2. Empleado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    try {
                        do {
                            System.out.println("1. Registrarme");
                            System.out.println("2. Logearme");
                            System.out.println("0. Salir");
                            System.out.print("Seleccione una opción: ");
                            opcion = sc.nextInt();
                            sc.nextLine();
                            switch (opcion) {
                                case 1:
                                    registrarCliente();
                                    break;
                                case 2:
                                    loginCliente();
                                    break;
                                case 0:
                                    System.out.println("Gracias por utilizar el sistema.");
                                    break;
                                default:
                                    System.out.println("Opción inválida, seleccione una opción válida.");
                            }
                        } while (opcion != 0);
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

    private Boolean registrarEmpleado() {
        String dni = "";
        do {
            System.out.print("Ingrese su DNI: ");
            dni = sc.nextLine().toUpperCase();
            if (dni.length() == 9 && dni.matches("[0-9]{8}[A-Z]")) {
                break;
            } else {
                System.out.println("dni inválido, por favor, introduzca un dni válido.");
            }
        } while (true);
        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese su edad: ");
        int edad = sc.nextInt();
        sc.nextLine();
        char sexo = 'o';
        do {
            System.out.print("Ingrese su sexo (M/F): ");
            sexo = sc.nextLine().toUpperCase().charAt(0);
            if (sexo == 'M' || sexo == 'F') {
                break;
            } else {
                System.out.println("Sexo inválido, por favor, introduzca un sexo válido.");
            }
        } while (true);

        System.out.print("Ingrese su cantidad de dinero: ");
        double sueldo = sc.nextDouble();
        sc.nextLine();
        System.out.print("Ingrese su dirección: ");
        String direccion = sc.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = sc.nextLine();
        try {
            Empleado empleado = new Empleado(dni, nombre, edad, sexo, direccion, sueldo, contrasena);

            if (gestorBBDD.insertarEmpleado(empleado)) {
                System.out.println("Cliente registrado con éxito.");
                empleado();
                return true;
            } else {
                System.out.println("No se pudo registrar al cliente.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al registrar al cliente.");
            return false;
        }

    }

    private boolean registrarCliente() {
        String dni = "";
        do {
            System.out.print("Ingrese su DNI: ");
            dni = sc.nextLine().toUpperCase();
            if (dni.length() == 9 && dni.matches("[0-9]{8}[A-Z]")) {
                break;
            } else {
                System.out.println("dni inválido, por favor, introduzca un dni válido.");
            }
        } while (true);
        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese su edad: ");
        int edad = sc.nextInt();
        sc.nextLine();
        char sexo = 'o';
        do {
            System.out.print("Ingrese su sexo (M/F): ");
            sexo = sc.nextLine().toUpperCase().charAt(0);
            if (sexo == 'M' || sexo == 'F') {
                break;
            } else {
                System.out.println("Sexo inválido, por favor, introduzca un sexo válido.");
            }
        } while (true);

        System.out.print("Ingrese su cantidad de dinero: ");
        double dinero = sc.nextDouble();
        sc.nextLine();
        System.out.print("Ingrese su dirección: ");
        String direccion = sc.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = sc.nextLine();
        try {
            Cliente cliente = new Cliente(dni, nombre, edad, sexo, dinero, direccion, contrasena);

            if (gestorBBDD.insertarCliente(cliente)) {
                System.out.println("Cliente registrado con éxito.");
                Cliente(cliente);
                return true;
            } else {
                System.out.println("No se pudo registrar al cliente.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al registrar al cliente.");
            return false;
        }

    }

    private boolean loginCliente() throws Exception {

        System.out.println("----- Login cliente -----");
        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        System.out.println("Comprobando credenciales...");
        if (gestorBBDD.selectClienteLoginByDni(dni).getContrasena().equals(Encriptacion.encriptar(contrasena))) {
            Cliente(gestorBBDD.selectClienteLoginByDni(dni));
            return true;
        } else {
            System.out.println("DNI o contraseña incorrectos.");
            return false;
        }
    }

    private void Cliente(Cliente cliente) throws Exception {
        System.out.println("----- Login cliente -----");
        System.out.println("1. Realizar compra");
        System.out.println("2. Pagar");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                realizarCompra(cliente);
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

    private void empleado() {
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

