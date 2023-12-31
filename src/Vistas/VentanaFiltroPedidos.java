
package Vistas;

import ConexionDB.ConexionDB;
import Controladores.ControladorVentanaFiltroPedidos;
import Modelos.Cliente;
import Modelos.Pedido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diaza
 */
public class VentanaFiltroPedidos extends javax.swing.JFrame {

    private ControladorVentanaFiltroPedidos controlador;

    /**
     * Creates new form VentanaFiltroPedidos
     */
    public VentanaFiltroPedidos() {
        initComponents();
        this.controlador = new ControladorVentanaFiltroPedidos();
        actualizarTabla();
        actualizarComboBox();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cboClientes = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnVolver = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(92, 107, 115));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(222, 226, 230));
        jLabel3.setText("Seleccione el id del cliente:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, 30));

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setAlignmentX(1.0F);
        jSeparator1.setAlignmentY(1.0F);
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 130, 10));

        cboClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboClientesActionPerformed(evt);
            }
        });
        jPanel3.add(cboClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 130, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 100));

        jPanel4.setBackground(new java.awt.Color(92, 107, 115));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaProductos.setBackground(new java.awt.Color(255, 255, 255));
        tablaProductos.setForeground(new java.awt.Color(74, 87, 89));
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id Pedido", "Fecha", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductos.setSelectionBackground(new java.awt.Color(74, 87, 89));
        tablaProductos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tablaProductos);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 550, 150));

        jPanel2.setBackground(new java.awt.Color(222, 226, 230));
        jPanel2.setForeground(new java.awt.Color(222, 226, 230));

        btnVolver.setBackground(new java.awt.Color(53, 79, 82));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(53, 79, 82));
        btnVolver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVolver.setText("Volver");
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVolverMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 90, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 570, 220));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cboClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboClientesActionPerformed
        Object selectedItem = cboClientes.getSelectedItem();
        if (selectedItem != null && selectedItem.toString().equals("-Seleccionar-")) {
            actualizarTabla();
        }else{
            String categoria1 = cboClientes.getSelectedItem().toString();
            int id_cliente = Integer.parseInt(categoria1);
            actualizarTablaFiltro(id_cliente);
        }

    }//GEN-LAST:event_cboClientesActionPerformed

    private void btnVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMouseClicked
        this.dispose();
        VentanaInicio ini = new VentanaInicio();
        ini.setVisible(true);
    }//GEN-LAST:event_btnVolverMouseClicked

    public void actualizarTabla(){
        try{
            DefaultTableModel modelo = new  DefaultTableModel();
            tablaProductos.setModel(modelo);
            
            PreparedStatement ps = null;
            ResultSet rs = null;
            java.sql.Connection conn = ConexionDB.getINSTANCE().getConnection();
            
            String sql = "SELECT P.pedido_id, P.fecha_pedido, P.total, C.id_cliente FROM pedidos AS P INNER JOIN clientes AS C ON P.id_cliente = C.id_cliente;";                    
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            
            int cantidadColumnas = rsMd.getColumnCount();
            
            modelo.addColumn("Id pedido");
            modelo.addColumn("Fecha");
            modelo.addColumn("Total");
            modelo.addColumn("Cliente");

            int anchos[] = {60, 60, 60, 60};
            for (int i = 0; i < tablaProductos.getColumnCount(); i++) {
                tablaProductos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);                
            }
            
            while(rs.next()){
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
            
        }catch(SQLException ex){
            System.err.println(ex.toString());
        }
    }
    
    public void actualizarComboBox() {
        cboClientes.removeAllItems();
        ArrayList<String> lista_str = new ArrayList<>();

        try {
            ArrayList<Cliente> listaClientes = controlador.traerClientes();
            for (int i = 0; i < listaClientes.size(); i++) {
                Cliente clientes = listaClientes.get(i);
                int id_cliente = clientes.getId_cliente();
                lista_str.add(String.valueOf(id_cliente));
            }
            cboClientes.addItem("-Seleccionar-");
            for (int i = 0; i < lista_str.size(); i++) {
                String item = lista_str.get(i);
                cboClientes.addItem(item);
            }
        } catch (Exception e) {
        }
    }
    
    private void actualizarTablaFiltro(int id_cliente) {
        DefaultTableModel modelo = new  DefaultTableModel();
        tablaProductos.setModel(modelo);
            modelo.addColumn("Id pedido");
            modelo.addColumn("Fecha");
            modelo.addColumn("Total");
        try{
            for (int i = 0; i < 3 ; i++) {
                for (int j = 0; j < modelo.getRowCount(); j++) {
                    modelo.removeRow(j);
                }
            }
        }catch(NullPointerException e){
        }
        try{
            ArrayList<Pedido> listaProductos = controlador.obtenerPedidos(id_cliente);
            for (int i = 0; i < listaProductos.size() ; i++) {
                Pedido aux = listaProductos.get(i);
                    Object[] ob = {aux.getPedido_id(), aux.getFecha_pedido(), aux.getTotal()};
                    modelo.addRow(ob);
                
                }
        }catch(Exception ex){        
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaFiltroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaFiltroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaFiltroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaFiltroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaFiltroPedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnVolver;
    private javax.swing.JComboBox<String> cboClientes;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables
}
