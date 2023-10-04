
package Controladores;

import Modelos.Pedido;
import Servicios.ServiceClientes;
import Servicios.ServicePedidos;
import java.util.ArrayList;

/**
 *
 * @author diaza
 */
public class ControladorVentanaGestionPedidos {
    
    public ControladorVentanaGestionPedidos() {
    }
    
    public void aniadirCliente(int id_cliente){
        ServiceClientes.getINSTANCE().agregarCategoria(id_cliente);
    }
    
    public ArrayList traerClientes(){
        ArrayList lista = ServiceClientes.getINSTANCE().obtenerClientes();
        return lista;
    }
    
    public boolean crearPedido(Pedido pedido){
        boolean respuesta = ServicePedidos.getINSTANCE().agregarPedido(pedido);
        return respuesta;
    }
    
    public void eliminarPedido(int pedido_id){
        ServicePedidos.getINSTANCE().eliminarPedido(pedido_id);
    }
    
    public void editarPedido(Pedido pedido, int pedido_id) {
        ServicePedidos.getINSTANCE().editarPedido(pedido, pedido_id);
    }
    
    public Pedido buscarPedido(int pedido_id){
        Pedido pedido = ServicePedidos.getINSTANCE().buscarPedido(pedido_id);
        return pedido;
    }
    
    public String obtenerCliente(int id_cliente){
        String categoria = ServiceClientes.getINSTANCE().obtenerCliente(id_cliente);
        return categoria;
    }
    
    
}
