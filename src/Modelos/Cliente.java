
package Modelos;

/**
 *
 * @author diaza
 */
public class Cliente {
    
    private int id_cliente;
    private String nombre;
    private String email;

    public Cliente(int id_cliente, String nombre, String email) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.email = email;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
