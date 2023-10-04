
package Controladores;

import Servicios.ServiceClientes;
import Servicios.ServicePedidos;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author diaza
 */
public class ControladorVentanaFiltroPedidos {
    
    public ControladorVentanaFiltroPedidos() {
    }

    public ArrayList traerClientes(){
        ArrayList listaClientes = ServiceClientes.getINSTANCE().obtenerClientes();
        return listaClientes;
    }  
    
    public ArrayList obtenerPedidos(int id_cliente){
        ArrayList listaProductos = ServicePedidos.getINSTANCE().obtenerPedidosFiltro(id_cliente);
        return listaProductos ;
    }
    
}
