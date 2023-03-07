package gui;

import autentificacion.Encriptacion;
import gestor.GestorBBDD;
import model.*;

import java.util.*;

public class Control {
    private final Scanner sc = new Scanner(System.in);
    private static final GestorBBDD gestorBBDD = GestorBBDD.getInstance();
    private final Encriptacion encriptacion = new Encriptacion();
    private static boolean methodInitialized = false;
    static {
        initializeMethod();
    }
    
    private static Control instance = null;
    private Control() {}

    public static Control getInstance() {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }
    public static void initializeMethod() {
        if (!methodInitialized) {
        	try {
                
                Afiliacion afiliacion = new Afiliacion("SuperAfiliado", null);
                Set<Afiliacion> af = new HashSet<>();
                af.add(afiliacion);
                gestorBBDD.insertarAfiliacion(afiliacion);

                Cliente cliente = new Cliente("12345678A", "Naim", 12, 'H', 12.0, "Avenida Faje", "1234",af);
                gestorBBDD.insertarCliente(cliente);
                Empleado empleado = new Empleado("12345689B", "Benja", 12, 'H', "Avenida fake", 123.0, "tinkywinky");
                gestorBBDD.insertarEmpleado(empleado);
                Descuentos descuento = new Descuentos(0.12, "Afiliados", af);
                Producto producto = new Producto("Coca-Cola",0.3 , 0.5, 12);
                Producto productso = new Producto("Galleta dinosaurio",1 , 4, 30);
                gestorBBDD.insertarProducto(producto);
                gestorBBDD.insertarProducto(productso);

                gestorBBDD.insertarDescuento(descuento);
                Set<Descuentos> descuentos = new HashSet<>();
                descuentos.add(descuento);

                Set<Cliente> clientes = new HashSet<>();
                clientes.add(cliente);
                afiliacion.setDescuentos(descuentos);
                afiliacion.setClientes(clientes);


                gestorBBDD.insertarDescuento(descuento);
                gestorBBDD.insertarAfiliacion(afiliacion);
                gestorBBDD.insertarCliente(cliente);
            } catch (Exception e) {
                System.out.println("Error al insertar clienteLogin");
            }
            methodInitialized = true;
        }
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

    boolean loginCliente(String dni, String contrasena) throws Exception {

        if (gestorBBDD.selectClienteLoginByDni(dni).getContrasena().equals(Encriptacion.encriptar(contrasena))) {
            return true;
        } else {
            System.out.println("DNI o contraseña incorrectos.");
            return false;
        }
    }
    
    Cliente cliente(String dni) {
		return gestorBBDD.selectClienteByDni(dni);
    }

    private void Cliente(Cliente cliente) throws Exception {
        System.out.println("----- Login cliente -----");
        System.out.println("1. Realizar compra");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                realizarCompra(cliente);
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
        }
    }

    private boolean realizarCompra(Cliente cliente) throws Exception {
        Map listaCompraLocal = new HashMap();
        int opcion;
        do {
            System.out.println("----- Realizar compra -----");
            System.out.println("1. Ver productos");
            System.out.println("2. Añadir producto");
            System.out.println("3. Eliminar producto");
            if (!listaCompraLocal.isEmpty()) {
                System.out.println("4. Pagar");
                System.out.println("0. Salir");
                System.out.println("----- Lista de la compra -----");
                System.out.println("----- Total: " + calcularPrecioTotal(listaCompraLocal) + "€ -----");
            }else {
                System.out.println("0. Salir");
            }

            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();
            if (!listaCompraLocal.isEmpty()) {
                switch (opcion) {
                    case 1:
                        System.out.println("----- Productos -----");
                        System.out.println(gestorBBDD.verProductos());
                        break;
                    case 2:
                        listaCompraLocal = anadirProducto(listaCompraLocal);
                        break;
                    case 3:
                        listaCompraLocal = eliminarProducto(listaCompraLocal);
                        break;
                    case 0:
                        System.out.println("Gracias por utilizar el sistema.");
                        break;
                    case 4:
                        pagar(cliente, listaCompraLocal);
                        break;
                    default:
                        System.out.println("Opción inválida, seleccione una opción válida.");
                }
            } else {
                switch (opcion) {
                    case 1:
                        System.out.println("----- Productos -----");
                        System.out.println(gestorBBDD.verProductos());
                        break;
                    case 2:
                        listaCompraLocal = anadirProducto(listaCompraLocal);
                        break;
                    case 3:
                        listaCompraLocal = eliminarProducto(listaCompraLocal);
                        break;
                    case 4:
                        System.out.println("Gracias por utilizar el sistema.");
                        break;
                    default:
                        System.out.println("Opción inválida, seleccione una opción válida.");
                }
            }

        } while (opcion != 4);
        return true;
}

    private void pagar(Cliente cliente, Map listaCompraLocal) throws Exception {
        System.out.println("----- Pagar -----");
        //Descuento total
        double descuentoTotal = 0;

        if (cliente.getAfiliacion() != null) {
            Set<Afiliacion> afiliaciones = cliente.getAfiliacion();
            for (Afiliacion afiliacion : afiliaciones) {
                descuentoTotal += gestorBBDD.selectDescuentoById(afiliacion.getId()).getPorcentajeDescuento();
            }
        }


        System.out.println("Total sin descuentos: " + calcularPrecioTotal(listaCompraLocal) + "€");
        System.out.println("Descuento de Afiliaciones: " + descuentoTotal + "%");
        System.out.println("Total: " + (calcularPrecioTotal(listaCompraLocal) -(calcularPrecioTotal(listaCompraLocal) * descuentoTotal)) + "€");
        System.out.println("¿Desea pagar? (S/N)");
        String respuesta = sc.nextLine().toUpperCase();
        if (respuesta.equals("S") || respuesta.equals("SI")) {
            System.out.print("Ingrese su contraseña: ");
            String contrasena = sc.nextLine();
            if (cliente.getContrasena().equals(Encriptacion.encriptar(contrasena))) {
                if (cliente.getDinero() >= calcularPrecioTotal(listaCompraLocal) -(calcularPrecioTotal(listaCompraLocal) * descuentoTotal)) {
                    gestorBBDD.updateCliente(cliente);

                    cliente.setDinero(cliente.getDinero() - calcularPrecioTotal(listaCompraLocal) -(calcularPrecioTotal(listaCompraLocal) * descuentoTotal));
                    System.out.println("Compra realizada con éxito.");
                    List productos = new ArrayList();

                    listaCompraLocal.forEach((k, v) -> {
                        try {
                            productos.add(gestorBBDD.selectProductoById((int) k));
                            gestorBBDD.updateProductoStock((int) k, (int) v);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    });
                    try {
                        Pedido ped = new Pedido(cliente, productos);
                        ped.setProductos(productos);
                        ped.setCliente(cliente);
                        gestorBBDD.insertarPedido(ped);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    System.out.println("No tienes saldo suficiente.");
                    realizarCompra(cliente);
                }
            } else {
                System.out.println("Contraseña incorrecta.");
            }

        } else {
            System.out.println("Compra cancelada.");
        }
    }

    private Map eliminarProducto(Map listaCompraLocal) {
        Map<Integer, Integer> productos = listaCompraLocal;
        System.out.println("----- Productos -----");
        System.out.println(gestorBBDD.verProductos());
        System.out.println("----- Eliminar producto -----");
        String respuesta = "S";
        do {
            System.out.print("Introduzca el código del producto: ");
            int codigo = sc.nextInt();
            sc.nextLine();
            productos.remove(codigo);
            StringBuilder listaCompra = new StringBuilder();
            for (Map.Entry<Integer, Integer> entry : productos.entrySet()) {
                int codigoPro = entry.getKey();
                int cantidadPro = entry.getValue();
                Producto producto = gestorBBDD.selectProductoById(codigoPro);
                if (producto != null) {
                    listaCompra.append(producto.getId()).append(".- Nombre: ").append(producto.getNombre()).append(" - Precio: ").append(producto.getPrecioVenta()).append(" - Cantidad: ").append(cantidadPro).append("\n");
                }
            }
            System.out.println(listaCompra);
            System.out.print("¿Desea eliminar otro producto? (S/N): ");
            respuesta = sc.nextLine().toUpperCase();
        } while (respuesta.equals("S") || respuesta.equals("SI"));
        return productos;
    }

    private Map<Integer, Integer> anadirProducto(Map listaCompraLocal) {
        Map<Integer, Integer> productos = listaCompraLocal;
        System.out.println("----- Productos -----");
        System.out.println(gestorBBDD.verProductos());
        System.out.println("----- Añadir producto -----");
        String respuesta = "S";
        do {
            System.out.print("Introduzca el código del producto: ");
            int codigo = sc.nextInt();
            sc.nextLine();
            System.out.print("Introduzca la cantidad: ");

            int cantidad = sc.nextInt();
          
            do {
                if (cantidad >= 0 && cantidad <= gestorBBDD.selectProductoById(codigo).getCantidad()) {
                    break;
                } else {
                    System.out.println("Nuestras existencias para el producto " + gestorBBDD.selectProductoById(codigo).getNombre() + " son de " + gestorBBDD.selectProductoById(codigo).getCantidad() + " unidades.");
                    System.out.print("Introduzca la cantidad: ");
                    cantidad = sc.nextInt();

                }
            } while (true);
            sc.nextLine();
            productos.put(codigo, cantidad);
            StringBuilder listaCompra = new StringBuilder();
            for (Map.Entry<Integer, Integer> entry : productos.entrySet()) {
                int codigoPro = entry.getKey();
                int cantidadPro = entry.getValue();
                Producto producto = gestorBBDD.selectProductoById(codigoPro);
                if (producto != null) {
                    listaCompra.append(" || " + producto.getNombre()).append(", cantidad: ").append(cantidadPro);

                } else {
                    System.out.println("El producto con código " + codigoPro + " no se encuentra en el sistema.");
                }

                System.out.println("La lista de compra es: " + listaCompra + " ||");
                System.out.println("El precio total es: " + calcularPrecioTotal(productos));

                System.out.println("Desea añadir otro producto? (S/N): ");
                respuesta = sc.nextLine().toUpperCase();
                if (respuesta.equals("S") || respuesta.equals("SI")) {
                    continue;
                } else if (respuesta.equals("N") || respuesta.equals("NO")) {
                    break;
                } else {
                    System.out.println("Respuesta inválida, por favor, introduzca una respuesta válida.");
                }
            }

        } while (respuesta.equals("S") || respuesta.equals("SI"));
        return productos;

    }


    private Double calcularPrecioTotal(Map<Integer, Integer> productos) {
        Double precioTotal = 0.0;
        for (Map.Entry<Integer, Integer> entry : productos.entrySet()) {
            int codigoPro = entry.getKey();
            int cantidadPro = entry.getValue();
            Producto producto = gestorBBDD.selectProductoById(codigoPro);
            if (producto != null) {
                precioTotal += producto.getPrecioVenta() * cantidadPro;
            } else {
                System.out.println("El producto con código " + codigoPro + " no se encuentra en el sistema.");
            }
        }
        return precioTotal;
    }

    private boolean empleadoLogin() throws Exception {
        System.out.println("----- Login empleado -----");
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        System.out.println("Comprobando credenciales...");
        if (gestorBBDD.selectEmpleadoByDni(dni).getContrasena().equals(Encriptacion.encriptar(contrasena))) {
            empleado();
            return true;
        } else {
            System.out.println("DNI o contraseña incorrectos.");
            return false;
        }


    }

    private void empleado() {
        int opcion = 0;
        do {

            System.out.println("----- Login empleado -----");
            System.out.println("1. Crear nueva tabla");
            System.out.println("2. Gestionar inventario");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("----- Creando tabla -----");
                    System.out.print("Introduzca el nombre de la tabla: ");
                    String nombre = sc.nextLine();
                    Map<String, String> columnas = new HashMap<>();
                    boolean salir = false;
                    while (!salir) {
                        System.out.print("Introduzca el nombre de la columna: ");
                        String nombreColumna = sc.nextLine();
                        System.out.print("Introduzca el tipo de la columna: ");
                        String tipoColumna = sc.nextLine();
                        columnas.put(nombreColumna, tipoColumna);
                        System.out.print("Desea añadir una fila mas? Y/N:");
                        Character fila = sc.nextLine().toUpperCase().charAt(0);
                        if (fila.equals('Y')) {
                            salir = false;
                        } else if (fila.equals('N')) {
                            salir = true;
                        } else {
                            System.out.println("Respuesta incorrecta.");
                        }
                    }
                    try {
                        gestorBBDD.crearTabla(nombre, columnas);
                        System.out.println("Tabla creada correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error al introducir los datos.");
                    }
                    break;
                case 2:
                    System.out.println("----- Gestionando inventario -----");
                    int opcionInventario = 0;
                    do {
                        System.out.println("1. Agregar producto");
                        System.out.println("2. Eliminar producto");
                        System.out.println("3. Modificar cantidad producto");
                        System.out.println("4. Salir");
                        System.out.print("Seleccione una opción: ");
                        opcionInventario = sc.nextInt();
                        sc.nextLine();
                        switch (opcionInventario) {
                            case 1:
                                addProducto();
                                break;
                            case 2:
                                delProducto();
                                break;
                            case 3:
                                modCantidadProducto();
                                break;
                            default:
                                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        }
                    } while (opcionInventario != 4);
                    break;
                case 3:
                    System.out.println("----- Saliendo del menú empleado -----");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }while (opcion != 3);
    }

    private Boolean modCantidadProducto() {
        System.out.println("----- EModificando la cantidad de stock de un producto -----");
        List<Producto> productos = gestorBBDD.selectAllProductos();
        for (int i = 0; i < productos.size(); i++) {
            System.out.println(productos.get(i).getId() + ".- " + productos.get(i).getNombre() + " " + productos.get(i).getCantidad() + "uds.");
        }
        System.out.print("Selecciona el id del producto que desea modificar su cantidad de stock");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Seleccione una nueva cantidad");
        int cantidad = sc.nextInt();
        sc.nextLine();
        sc.nextLine();
        if (gestorBBDD.selectProductoById(id) != null) {
            gestorBBDD.updateProductoStock(id, cantidad);
            return true;
        } else {
            System.out.println("No se ha encontrado el producto.");
            return false;
        }
    }

    private Boolean delProducto() {
        System.out.println("----- Eliminando producto -----");
        List<Producto> productos = gestorBBDD.selectAllProductos();
        for (int i = 0; i < productos.size(); i++) {
            System.out.println(productos.get(i).getId() + ".- " + productos.get(i).getNombre());
        }
        System.out.print("Selecciona el id del producto que desea eliminar");
        int id = sc.nextInt();
        sc.nextLine();
        if (gestorBBDD.selectProductoById(id) != null) {
            gestorBBDD.insertarProducto(gestorBBDD.selectProductoById(id));
            return true;
        } else {
            System.out.println("No se ha encontrado el producto.");
            return false;
        }
    }

    private Boolean addProducto() {
        System.out.println("----- Agregando producto -----");
        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();
        System.out.print("Costo del producto: ");
        Double costo = sc.nextDouble();
        sc.nextLine();
        System.out.print("Precio de venta del producto: ");
        Double precioVenta = sc.nextDouble();
        sc.nextLine();
        System.out.print("Cantidad de unidades en stock: ");
        int stock = sc.nextInt();
        sc.nextLine();
        try {
            Producto producto = new Producto(nombre, costo, precioVenta, stock);
            gestorBBDD.insertarProducto(producto);
            return true;
        } catch (Exception e) {
            System.out.println("No ha sido posible añadir el producto, asegurate de haber introducido los tipos correspondientes.");
            return false;
        }
    }
}

