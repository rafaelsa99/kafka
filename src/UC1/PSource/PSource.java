
package UC1.PSource;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

/**
 * Source.
 * @author Rafael Sá (104552), Luís Laranjeira (81526)
 */
public class PSource extends javax.swing.JFrame {
    /** Filename for the sensor data. */
    private static final String FILENAME = "src/Data/sensor.txt";
    /** Port of the socket of the producer. */
    private static final int PORT = 5000;
    /** Host name of the producer. */
    private static final String HOSTNAME = "localhost";
    
    /**
     * Creates new form PSource.
     * @param filename file name
     * @param hostname producer host name
     * @param port producer port
     */
    public PSource(String filename, String hostname, int port) {
        initComponents();
        this.setVisible(true);
        Data data = new Data(filename, hostname, port);
        data.start();
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
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("SOURCE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(jLabel1)
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addContainerGap(252, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** End the GUI. */
    public static void endGUI(){
        try {
            SwingUtilities.invokeAndWait(() -> {
                System.exit(0);
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            System.out.println(ex.toString());
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
            java.util.logging.Logger.getLogger(PSource.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PSource.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PSource.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PSource.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            if((args.length % 2) != 0){
                System.out.println("Optional arguments: "
                        + "\n\t-h <PRODUCER_HOSTNAME>: Producer Hostname (Default = \"" + HOSTNAME + "\")"
                        + "\n\t-p <PRODUCER_PORT>: Producer Server Port (Default = " + PORT + ")"
                        + "\n\t-f <FILENAME>: Sensors Filename (Default = " + FILENAME + ")");
                throw new IllegalArgumentException("Invalid Arguments");
            }
            String filename = FILENAME;
            String hostname = HOSTNAME;
            int port = PORT;
            try{
                for (int i = 0; i < args.length; i+=2) {
                    switch(args[i].toLowerCase()){
                        case "-h": hostname = args[i+1];
                                   break;
                        case "-p": port = Integer.valueOf(args[i+1]);
                                   break;
                        case "-f": filename = args[i+1];
                                   break;
                        default: throw new IllegalArgumentException();
                    }
                }
            } catch(IllegalArgumentException ex){
                System.out.println("Optional arguments: "
                        + "\n\t-h <PRODUCER_HOSTNAME>: Producer Hostname (Default = \"" + HOSTNAME + "\")"
                        + "\n\t-p <PRODUCER_PORT>: Producer Server Port (Default = " + PORT + ")"
                        + "\n\t-f <FILENAME>: Sensors Filename (Default = " + FILENAME + ")");
                throw new IllegalArgumentException("Invalid Arguments");
            }
            new PSource(filename, hostname, port);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}