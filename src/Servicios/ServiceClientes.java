
package Servicios;

import ConexionDB.ConexionDB;
import Modelos.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.postgresql.util.PSQLException;

/**
 *
 * @author diaza
 */
public class ServiceClientes {
    
    private static ServiceClientes INSTANCE;
    private static Connection conn;
    
    private ServiceClientes() {
        conn = ConexionDB.getINSTANCE().getConnection();
    }
    
    public static ServiceClientes getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new ServiceClientes();
        }
        return INSTANCE;
    }
    
    public static ResultSet buscar_verificar (int id_cliente){
        try{
            String sqlBuscar = "SELECT id_cliente FROM clientes WHERE id_cliente=?";
            PreparedStatement buscarStmt = conn.prepareStatement(sqlBuscar);
            buscarStmt.setInt(1, id_cliente);
            ResultSet resultado = buscarStmt.executeQuery();
            return resultado;         
        }catch( Exception ex){
            Logger.getLogger(ServiceClientes.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return null;
    }
    
    public Cliente buscarCliente(int id_cliente) {
        try {
            String sqlBuscarPedido = "SELECT * FROM clientes WHERE id_cliente = ?";
            PreparedStatement buscarPedidoStmt = conn.prepareStatement(sqlBuscarPedido);
            buscarPedidoStmt.setInt(1, id_cliente);
            ResultSet rs = buscarPedidoStmt.executeQuery();
            if (rs.next()) {
                String email = rs.getString("email");
                String nombre = rs.getString("nombre");
                Cliente cliente = new Cliente(id_cliente, nombre, email);
                return cliente;
            } else {
                JOptionPane.showMessageDialog(null, "El cliente con el id " + id_cliente + " no está registrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(ServicePedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean agregarCliente(Object[] values){
        try{  
            int id_cliente = Integer.parseInt(values[0].toString());
            String nombre = values[1].toString();
            String email = values[2].toString();

            ResultSet resultado1 = buscar_verificar(id_cliente);
            
            if (resultado1.next()   ) {
                JOptionPane.showMessageDialog(null, "El cliente con el numero de id " + id_cliente + " ya está registrado", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            String sql = "INSERT INTO clientes(id_cliente, nombre, email)" +  "VALUES(?, ?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id_cliente);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            JOptionPane.showMessageDialog(null, "Cliente agregado");
            return true;               
        }catch( Exception ex){
            Logger.getLogger(ServiceClientes.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return false;
    }
    
    public static void agregarCategoria (int id_cliente){
        try {
            ResultSet rs = buscar_verificar(id_cliente);
            
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "El cliente con el id: " + id_cliente + " ya está registrado", "Error", JOptionPane.ERROR_MESSAGE);               
            }else{
                try{
                    String sql = "INSERT INTO clientes(id_cliente) VALUES(?);";

                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setInt(1, id_cliente);

                    preparedStatement.executeUpdate();

                    preparedStatement.close();
                    JOptionPane.showMessageDialog(null, "Cliente agregado");
                } catch (Exception ex) {
                    Logger.getLogger(ServiceClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ServiceClientes.class.getName()).log(Level.SEVERE, null, ex);
        } 
    } 
    
    public static void editarCliente(int id_cliente, String nombre, String email) {
        try {
            String sql = "UPDATE clientes SET nombre=?, email=? WHERE id_cliente=?;";          
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(3, id_cliente);
            int rowsUpdated = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Datos actualizados");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un cliente con el id: " + id_cliente, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(ServiceClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void eliminarClienteId(int id_cliente) {
    try {
        String pedidosAsociadosSql = "SELECT COUNT(*) FROM pedidos WHERE id_cliente = ?";
        PreparedStatement pedidosAsociadosStatement = conn.prepareStatement(pedidosAsociadosSql);
        pedidosAsociadosStatement.setInt(1, id_cliente);
        ResultSet pedidosAsociadosResult = pedidosAsociadosStatement.executeQuery();

        if (pedidosAsociadosResult.next()) {
            int cantidadPedidos = pedidosAsociadosResult.getInt(1);

            if (cantidadPedidos > 0) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar el cliente porque hay " + cantidadPedidos + " pedidos asociados a este cliente.");
                return;
            }
        }
        String eliminarClienteSql = "DELETE FROM clientes WHERE id_cliente = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(eliminarClienteSql);
        preparedStatement.setInt(1, id_cliente);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        JOptionPane.showMessageDialog(null, "Cliente eliminado");
    } catch (Exception ex) {
        Logger.getLogger(ServiceClientes.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
//    public static void eliminarClienteId(int id_cliente){
//        try {
//            String sql = "DELETE from clientes where id_cliente=?";
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setInt(1, id_cliente);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            JOptionPane.showMessageDialog(null, "Cliente eliminado");
//        }catch (PSQLException ex) {
//            if (ex.getMessage().contains("violates foreign key constraint \"pedidos_id_cliente_fkey\"")) {
//                JOptionPane.showMessageDialog(null, "No se puede eliminar el cliente porque hay pedidos asignados a esta cliente.");
//            }else {
//                Logger.getLogger(ServiceClientes.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }catch (Exception ex) {
//            Logger.getLogger(ServiceClientes.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public ArrayList obtenerClientes(){
        ArrayList<Cliente> listaClientes = new ArrayList();
        try {
            String sql = "SELECT id_cliente, nombre, email FROM clientes;" ;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id_cliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                Cliente cliente = new Cliente(id_cliente, nombre, email);
                listaClientes.add(cliente);
            }
            preparedStatement.close();
        } catch (Exception ex) {
            Logger.getLogger(ServiceClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClientes;     
    }
    
    public String obtenerCliente(int id_cliente) {
        String cliente = null;
        try {
            String sql = "SELECT nombre, email FROM clientes WHERE id_cliente=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id_cliente);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
//                cliente = "Nombre: " + nombre + ", Email: " + email;
            }
            preparedStatement.close();
        } catch (Exception ex) {
            Logger.getLogger(ServiceClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

}
