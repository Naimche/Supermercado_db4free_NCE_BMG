package gestor;

import model.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.service.ServiceRegistry;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Map;
import java.util.Properties;


public class GestorBBDD {
    public EntityManager initialize() {
        Properties properties = new Properties();
        properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.setProperty(Environment.URL, "jdbc:mysql://db4free.net:3306/equipo7hlc");
        properties.setProperty(Environment.USER, "equipo7hlc");
        properties.setProperty(Environment.PASS, "equipo7hlc");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.SHOW_SQL, "false");
        properties.setProperty(Environment.HBM2DDL_AUTO, "create-drop");


        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(properties)
                .build();

        EntityManager manager = new HibernatePersistenceProvider()
                .createEntityManagerFactory("supermercado", properties).createEntityManager();


        //Entorno de pruebas

        return manager;
    }

    private EntityManager manager = initialize();
    private EntityTransaction transaction = manager.getTransaction();

    public GestorBBDD() {
        this.manager = manager;
    }

    public Boolean insertarCliente(model.Cliente cliente) {
        try {
            transaction.begin();
            manager.persist(cliente);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public Boolean insertarEmpleado(model.Empleado empleado) {
        try {
            transaction.begin();
            manager.persist(empleado);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public Descuentos selectDescuentoById(int id) {
        Descuentos descuentos = manager.find(Descuentos.class, id);
        return descuentos;
    }

    public Boolean insertarProducto(model.Producto producto) {
        try {
            transaction.begin();
            manager.persist(producto);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public Boolean insertarPedido(model.Pedido pedido) {
        try {
            transaction.begin();
            manager.persist(pedido);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public List<Cliente> selectAllClientes() {
        return manager.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    public Cliente selectClienteByDni(String dni) {
        Cliente cliente = manager.find(Cliente.class, dni);
        if (cliente == null) {
            System.out.println("El cliente con DNI " + dni + " no existe en la base de datos.");
        }
        return cliente;
    }

    public List<model.Empleado> selectAllEmpleados() {
        return manager.createQuery("SELECT e FROM Empleado e", model.Empleado.class).getResultList();
    }

    public model.Empleado selectEmpleadoByDni(String dni) {
        Empleado empleado = manager.find(Empleado.class, dni);
        if (empleado == null) {
            System.out.println("El empleado con DNI " + dni + " no existe en la base de datos.");
        }
        return empleado;
    }

    public List<model.Producto> selectAllProductos() {
        return manager.createQuery("SELECT p FROM Producto p", model.Producto.class).getResultList();
    }

    public model.Producto selectProductoById(int id) {
        return manager.find(model.Producto.class, id);
    }

    public List<model.Pedido> selectAllPedidos() {
        return manager.createQuery("SELECT p FROM Pedido p", model.Pedido.class).getResultList();
    }

    public model.Pedido selectPedidoById(String id) {
        return manager.find(model.Pedido.class, id);
    }

    public Boolean updateCliente(model.Cliente cliente) {
        try {
            transaction.begin();
            manager.merge(cliente);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public Boolean updateEmpleado(model.Empleado empleado) {
        try {
            transaction.begin();
            manager.merge(empleado);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public Boolean updateProducto(model.Producto producto) {
        try {
            transaction.begin();
            manager.merge(producto);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public Boolean updatePedido(model.Pedido pedido) {
        try {
            transaction.begin();
            manager.merge(pedido);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public Boolean deleteCliente(model.Cliente cliente) {
        try {
            transaction.begin();
            manager.remove(cliente);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public Boolean deleteEmpleado(model.Empleado empleado) {
        try {
            transaction.begin();
            manager.remove(empleado);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    //comprobar el cliente login con su contraseña
    public Cliente selectClienteLoginByDni(String dni) {
        Cliente cliente = manager.find(Cliente.class, dni);
        if (cliente == null) {
            System.out.println("El cliente con DNI " + dni + " no existe en la base de datos.");
        }
        return cliente;
    }

    public Boolean deleteProducto(model.Producto producto) {
        try {
            transaction.begin();
            manager.remove(producto);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public Boolean deletePedido(model.Pedido pedido) {
        try {
            transaction.begin();
            manager.remove(pedido);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public boolean crearTabla(String nombreTabla, Map<String, String> campos) {
        try {
            transaction.begin();
            StringBuilder query = new StringBuilder("CREATE TABLE " + nombreTabla + " (");
            for (Map.Entry<String, String> entry : campos.entrySet()) {
                query.append(entry.getKey()).append(" ").append(entry.getValue()).append(", ");
            }
            query = new StringBuilder(query.substring(0, query.length()-2));
            query.append(")");
            manager.createNativeQuery(query.toString()).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public Boolean insertarFila(String nombreTabla, Object valor) {
        try {
            transaction.begin();
            manager.createNativeQuery("INSERT INTO " + nombreTabla + "  VALUES (" + valor + ")").executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public List selectAll(String nombreTabla) {
        return manager.createNativeQuery("SELECT * FROM " + nombreTabla).getResultList();
    }

    public List selectBy(String nombreTabla, String campo, Object valor) {
        return manager.createNativeQuery("SELECT * FROM " + nombreTabla + " WHERE " + campo + " = " + valor).getResultList();
    }

    public Boolean update(String nombreTabla, String campo, Object valor, String campoWhere, Object valorWhere) {
        try {
            transaction.begin();
            manager.createNativeQuery("UPDATE " + nombreTabla + " SET " + campo + " = " + valor + " WHERE " + campoWhere + " = " + valorWhere).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public Boolean delete(String nombreTabla, String campo, Object valor) {
        try {
            transaction.begin();
            manager.createNativeQuery("DELETE FROM " + nombreTabla + " WHERE " + campo + " = " + valor).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }

    }

    public Boolean updateProductoStock(int id, int cantidad) {
        try {
            transaction.begin();
            manager.createNativeQuery("UPDATE productos SET cantidad = cantidad - " + cantidad + " WHERE id = " + id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public void close() {
        manager.close();
    }

    public String verProductos() {
        String productos = "";
        for (Producto p : selectAllProductos()) {
            productos += p.getId() + ".- " + p.getNombre() + ", Precio: " + p.getPrecioVenta() + "€" + "\n";
        }
        return productos;

    }

    public boolean insertarAfiliacion(Afiliacion afiliacion) {
        try {
            transaction.begin();
            manager.persist(afiliacion);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public boolean insertarDescuento(Descuentos descuento) {
        try {
            transaction.begin();
            manager.persist(descuento);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }
}
