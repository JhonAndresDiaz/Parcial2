
package Controladores;

import Modelos.Cliente;
import Servicios.ServiceClientes;
import java.sql.SQLException;

/**
 *
 * @author diaza
 */
public class ControladorVentanaGestionClientes {
    
    public void agregarCliente(int id_cliente, String nombre, String email) {
        Object[] values = {id_cliente, nombre, email};
        ServiceClientes.getINSTANCE().agregarCliente(values);
    }
    
    public void editarCliente(Cliente cliente) {
        ServiceClientes.getINSTANCE().editarCliente(cliente.getId_cliente(), cliente.getNombre(), cliente.getEmail());;
    }
    
    public void eliminarCliente(int id_cliente) {
        ServiceClientes.getINSTANCE().eliminarClienteId(id_cliente);
    }
    
    public Cliente buscarCliente(int id_cliente){
        Cliente cliente = ServiceClientes.getINSTANCE().buscarCliente(id_cliente);
        return cliente;
    }
}
