package gestor;

import model.Cliente;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Properties;


public class GestorBBDD {
    public EntityManager initialize(){
        Properties properties = new Properties();
        properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.setProperty(Environment.URL, "jdbc:mysql://db4free.net:3306/equipo7hlc");
        properties.setProperty(Environment.USER, "equipo7hlc");
        properties.setProperty(Environment.PASS, "equipo7hlc");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.SHOW_SQL, "true");
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
    private EntityTransaction transaction=manager.getTransaction();

    public GestorBBDD() {
        this.manager = manager;
    }
    public Boolean insertarCliente(model.Cliente cliente){
        try{
            transaction.begin();
            manager.persist(cliente);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }

    public Boolean insertarEmpleado(model.Empleado empleado){
        try{
            transaction.begin();
            manager.persist(empleado);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }
    public Boolean insertarProducto(model.Producto producto){
        try{
            transaction.begin();
            manager.persist(producto);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }
    public Boolean insertarPedido(model.Pedido pedido){
        try{
            transaction.begin();
            manager.persist(pedido);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }
    public List<Cliente>selectAllClientes(){
        return manager.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }
    public Cliente selectClienteByDni(String dni){
        Cliente cliente = manager.find(Cliente.class, dni);
        if (cliente == null) {
            System.out.println("El cliente con DNI " + dni + " no existe en la base de datos.");
        }
        return cliente;
    }
    public List<model.Empleado>selectAllEmpleados(){
        return manager.createQuery("SELECT e FROM Empleado e", model.Empleado.class).getResultList();
    }
    public model.Empleado selectEmpleadoByDni(String dni){
        return manager.find(model.Empleado.class, dni);
    }
    public List<model.Producto>selectAllProductos(){
        return manager.createQuery("SELECT p FROM Producto p", model.Producto.class).getResultList();
    }
    public model.Producto selectProductoById(String id){
        return manager.find(model.Producto.class, id);
    }
    public List<model.Pedido>selectAllPedidos(){
        return manager.createQuery("SELECT p FROM Pedido p", model.Pedido.class).getResultList();
    }
    public model.Pedido selectPedidoById(String id){
        return manager.find(model.Pedido.class, id);
    }
    public Boolean updateCliente(model.Cliente cliente){
        try{
            transaction.begin();
            manager.merge(cliente);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }
    public Boolean updateEmpleado(model.Empleado empleado){
        try{
            transaction.begin();
            manager.merge(empleado);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }
    public Boolean updateProducto(model.Producto producto){
        try{
            transaction.begin();
            manager.merge(producto);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }
    public Boolean updatePedido(model.Pedido pedido){
        try{
            transaction.begin();
            manager.merge(pedido);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }
    public Boolean deleteCliente(model.Cliente cliente){
        try{
            transaction.begin();
            manager.remove(cliente);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }
    public Boolean deleteEmpleado(model.Empleado empleado){
        try{
            transaction.begin();
            manager.remove(empleado);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }

    //comprobar el cliente login con su contraseña
    public Cliente selectClienteLoginByDni(String dni){
        Cliente cliente = manager.find(Cliente.class, dni);
        if (cliente == null) {
            System.out.println("El cliente con DNI " + dni + " no existe en la base de datos.");
        }
        return cliente;
    }

    public Boolean deleteProducto(model.Producto producto){
        try{
            transaction.begin();
            manager.remove(producto);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }
    public Boolean deletePedido(model.Pedido pedido){
        try{
            transaction.begin();
            manager.remove(pedido);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }

}
