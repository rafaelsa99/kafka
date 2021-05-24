/*
 * ver package-info.java
 */
package UC2.PProducer;

import UC2.Communication.CServer;
import UC2.Communication.Message;
import UC2.Record.Record;
import UC2.Record.RecordSerializer;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author omp
 */
public class PProducer extends javax.swing.JFrame {

    public static final int NUM_PRODUCERS = 6;
    public static final int[] SERVER_PORT = {5000, 5001, 5002, 5003, 5004, 5005};
    public static final String TOPIC = "Sensor";
    
    /**
     * Creates new form PProducer
     * @param serverPort server port receive messages from PSOURCE
     */
    public PProducer(int serverPort) {
        initComponents();
        createInterface(6);
        this.setVisible(true);
        startServer(serverPort);
    }
    
    private void startServer(int serverPort) {
        CServer cServer = new CServer(serverPort, this);
        cServer.openServer();
        cServer.start();
    }
    
    /**
    * Função: void sendToTopics Esta função cria o KafkaProducer para enviar as
    * mensagens para os diferentes topicos.É ainda responsável pelo processamento
 e segurança ao enviar as diferentes mensagens.
     * @param record record to be sent
    */
    public void sendToTopic(Record record){

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093, localhost:9094, localhost:9095, localhost:9096, localhost:9097, localhost:9098");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", RecordSerializer.class.getName());
        props.put("acks", "0");
        props.put("buffer.memory", 33554432);
        props.put("batch.size", 100000);
        props.put("linger.ms", 50);
        props.put("max.in.flight.requests.per.connection", 1);
        props.put("compression.type", "gzip");
        props.put("delivery.timeout.ms", 35000);   
        props.put("retries", 0);
        try (Producer<String, Record> producer = new KafkaProducer<>(props)) {
            ProducerRecord<String, Record> pRecord = new ProducerRecord<>(TOPIC, record);
            producer.send(pRecord);
        }
    }
    
    public void appendRecord(Message record){
        appendMessageToInterface(record.getSensorId() + " " + record.getTemperature() + " " + record.getTimestamp());
        updateInterface(Integer.parseInt(record.getSensorId()));
        updateTotalRecords();
    }
    
    public void appendMessageToInterface(String message){
        DefaultListModel model = (DefaultListModel) jListMessages.getModel();
        model.addElement(message);
    }   
    
    public void updateTotalRecords(){
        int total = Integer.parseInt(jLabel_TotalRecords.getText());
        jLabel_TotalRecords.setText(String.valueOf(++total));
    }
    
    public void updateInterface(int sensorId){
        DefaultTableModel model;
        model = (DefaultTableModel)jTable_Records.getModel();
        try {
            SwingUtilities.invokeAndWait(() -> {
                int value = Integer.valueOf(jTable_Records.getValueAt(sensorId, 1).toString());
                jTable_Records.setValueAt(++value, sensorId, 1);
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void createInterface(int numSensor){
        DefaultTableModel model;
        model = (DefaultTableModel)jTable_Records.getModel();
        for(int i = 0; i< numSensor; i++){
            model.addRow(new Object[]{i,0});
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jScrollPane_Records = new javax.swing.JScrollPane();
        jTable_Records = new javax.swing.JTable();
        jLabel_TotalRecords = new javax.swing.JLabel();
        jLabel_TitleTotalRecords = new javax.swing.JLabel();
        jScrollPaneMessages = new javax.swing.JScrollPane();
        jListMessages = new javax.swing.JList<>();
		jListMessages.setModel(new javax.swing.DefaultListModel());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelTitle.setText("Producer");

        jScrollPane_Records.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTable_Records.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable_Records.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SensorId", "Num Records"
            }
        ));
        jScrollPane_Records.setViewportView(jTable_Records);

        jLabel_TotalRecords.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_TotalRecords.setText("0");

        jLabel_TitleTotalRecords.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_TitleTotalRecords.setText("Total Records");

        jScrollPaneMessages.setBorder(javax.swing.BorderFactory.createTitledBorder("Income Messages"));
        jScrollPaneMessages.setViewportView(jListMessages);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel_TitleTotalRecords)
                        .addGap(83, 83, 83)
                        .addComponent(jLabel_TotalRecords))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jLabelTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane_Records, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane_Records, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_TitleTotalRecords)
                    .addComponent(jLabel_TotalRecords))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneMessages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(PProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
       
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            
            int serverPorts[] = {SERVER_PORT[0], SERVER_PORT[1], SERVER_PORT[2], SERVER_PORT[3], SERVER_PORT[4], SERVER_PORT[5]};
            if(args.length == NUM_PRODUCERS){
                for (int i = 0; i < NUM_PRODUCERS; i++) {
                    try{
                        serverPorts[i] = Integer.parseInt(args[i]);
                    }catch (NumberFormatException ex){
                        System.out.println("Invalid parameter!\nParameters: [Optional: serverPort1 serverPort2 serverPort3 serverPort4 serverPort5 serverPort6"
                                + "(Default = " + SERVER_PORT[0] +" to " + SERVER_PORT[NUM_PRODUCERS-1] +")]\n");
                    }
                }
            } else if (args.length > 1){
                System.out.println("Invalid parameter!\nParameters: [Optional: serverPort1 serverPort2 serverPort3 serverPort4 serverPort5 serverPort6"
                            + "(Default = " + SERVER_PORT[0] +" to " + SERVER_PORT[NUM_PRODUCERS -1] +")]\n");
            }
            for (int i = 0; i < serverPorts.length; i++) {
                new PProducer(serverPorts[i]);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabel_TitleTotalRecords;
    private javax.swing.JLabel jLabel_TotalRecords;
    private javax.swing.JList<String> jListMessages;
    private javax.swing.JScrollPane jScrollPaneMessages;
    private javax.swing.JScrollPane jScrollPane_Records;
    private javax.swing.JTable jTable_Records;
    // End of variables declaration//GEN-END:variables
}
