
package Servicios;

import ConexionDB.ConexionDB;
import Modelos.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author diaza
 */
public class ServicePedidos {
    
     private static ServicePedidos INSTANCE;
    
    private static Connection conn;
    
    private ServicePedidos() {   
        conn = ConexionDB.getINSTANCE().getConnection();
    }

    public static ServicePedidos getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new ServicePedidos();
        }
        return INSTANCE;
    }

    public Pedido buscarPedido(int pedido_id) {
        try {
            String sqlBuscarPedido = "SELECT * FROM pedidos WHERE pedido_id = ?";
            PreparedStatement buscarPedidoStmt = conn.prepareStatement(sqlBuscarPedido);
            buscarPedidoStmt.setInt(1, pedido_id);
            ResultSet rs = buscarPedidoStmt.executeQuery();
            
            if (rs.next()) {
                String fecha_pedido = rs.getString("fecha_pedido");
                float total = rs.getFloat("total");
                int id_cliente = rs.getInt("id_cliente");
                System.out.println("jajsjasjSII");
                Pedido pedido = new Pedido(pedido_id, fecha_pedido, total, id_cliente);
                return pedido;
            } else {
                JOptionPane.showMessageDialog(null, "El pedido con el id " + pedido_id + " no está registrado", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            Logger.getLogger(ServicePedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ResultSet buscar_verificar(int pedido_id){
         try{
            String sqlBuscarCodigoLibro = "SELECT pedido_id FROM pedidos WHERE pedido_id = ?";            
            PreparedStatement buscarLibroStmt1 = conn.prepareStatement(sqlBuscarCodigoLibro);            
            buscarLibroStmt1.setInt(1, pedido_id);
            ResultSet resultado = buscarLibroStmt1.executeQuery();
            return resultado;
        
        }catch( Exception ex){
            Logger.getLogger(ServicePedidos.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
        
    }
    
    public boolean agregarPedido(Pedido pedido) {
        try {
            
            String fecha_pedido = pedido.getFecha_pedido();
            float total = pedido.getTotal();
            int id = pedido.getId();           
            
            String sql = "INSERT INTO pedidos(fecha_pedido,total,id_cliente) VALUES(?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, fecha_pedido);
            preparedStatement.setFloat(2, total);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            JOptionPane.showMessageDialog(null, "Pedido agregado");
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    

    public boolean editarPedido(Pedido pedido, int pedido_id) {
        try {
            String fecha_pedido = pedido.getFecha_pedido();
            float total = pedido.getTotal();
            int id_cliente = pedido.getId();
            
            ResultSet resultado = buscar_verificar(pedido_id);

            if (resultado.next()) {
                String sql = "UPDATE pedidos SET fecha_pedido=?, total=?, id_cliente=? WHERE pedido_id=?;";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, fecha_pedido);
                preparedStatement.setFloat(2, total);
                preparedStatement.setInt(3, id_cliente);
                preparedStatement.setInt(4, pedido_id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                JOptionPane.showMessageDialog(null, "Pedido actualizado");
                return true;
            }else {
                JOptionPane.showMessageDialog(null, "El pedido con el código " + pedido_id + " no está registrado", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void eliminarPedido(int pedido_id) {
        try {
            ResultSet rs = buscar_verificar(pedido_id);
            if (rs.next()) {
                String sql = "DELETE FROM pedidos WHERE pedido_id = ?;";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, pedido_id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                JOptionPane.showMessageDialog(null, "Pedido eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "El pedido con el código " + pedido_id + " no está registrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList obtenerPedidosFiltro(int id_cliente) {
        ResultSet rs = null;
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            String sql = "SELECT pedido_id, fecha_pedido, total FROM pedidos WHERE id_cliente = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id_cliente);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int pedido_id = rs.getInt("pedido_id");
                String fecha_pedido = rs.getString("fecha_pedido");
                float total = rs.getFloat("total");
                Pedido pedido = new Pedido(pedido_id, fecha_pedido, total, id_cliente);
                pedidos.add(pedido);
            }
            return pedidos;
        } catch (SQLException ex) {
            Logger.getLogger(ServicePedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
}
