
package Modelos;

/**
 *
 * @author diaza
 */
public class Pedido {
    
    private int pedido_id;
    private String fecha_pedido; 
    private float total;
    private int id;

    public Pedido(int pedido_id, String fecha_pedido, float total, int id) {
        this.pedido_id = pedido_id;
        this.fecha_pedido = fecha_pedido;
        this.total = total;
        this.id = id;
    }

    public Pedido(String fecha_pedido, float total, int id) {
        this.fecha_pedido = fecha_pedido;
        this.total = total;
        this.id = id;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public String getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(String fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
