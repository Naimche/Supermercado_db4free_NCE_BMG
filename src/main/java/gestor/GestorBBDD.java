import model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Map;


public class GestorBBDD {
    private EntityManager manager;
    private EntityTransaction transaction=manager.getTransaction();

    public GestorBBDD(EntityManager manager) {
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
    public boolean crearTabla(String nombreTabla, Map<String, String> campos){
        try{
            transaction.begin();
            StringBuilder query = new StringBuilder("CREATE TABLE " + nombreTabla + " (");
            for (Map.Entry<String, String> entry : campos.entrySet()) {
                query.append(entry.getKey()).append(" ").append(entry.getValue()).append(", ");
            }
            query = new StringBuilder(query.substring(0, query.length() - 2));
            query.append(")");
            manager.createNativeQuery(query.toString()).executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }
    public Boolean insertarFila(String nombreTabla,  Object valor){
        try{
            transaction.begin();
            manager.createNativeQuery("INSERT INTO "+nombreTabla+"  VALUES ("+valor+")").executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }
    public List selectAll(String nombreTabla){
        return manager.createNativeQuery("SELECT * FROM "+nombreTabla).getResultList();
    }
   public List selectBy(String nombreTabla, String campo, Object valor){
        return manager.createNativeQuery("SELECT * FROM "+nombreTabla+" WHERE "+campo+" = "+valor).getResultList();
    }
    public Boolean update(String nombreTabla, String campo, Object valor, String campoWhere, Object valorWhere){
        try{
            transaction.begin();
            manager.createNativeQuery("UPDATE "+nombreTabla+" SET "+campo+" = "+valor+" WHERE "+campoWhere+" = "+valorWhere).executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }
    public Boolean delete(String nombreTabla, String campo, Object valor){
        try{
            transaction.begin();
            manager.createNativeQuery("DELETE FROM "+nombreTabla+" WHERE "+campo+" = "+valor).executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
    }

    public void close(){
        manager.close();
    }
}
