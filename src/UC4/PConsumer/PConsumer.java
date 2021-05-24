
package UC4.PConsumer;

import UC4.Record.Record;
import UC4.Record.RecordDeserializer;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * Consumer.
 * @author Rafael Sá (104552), Luís Laranjeira (81526)
 */
public class PConsumer extends javax.swing.JFrame {

    /** Topic Name. */
    private static final String TOPIC = "Sensor";
    /** Shared region of the aggregated data from the eplicas. */
    private static SRDataReplicas srDataReplicas;
    /** Number of planes. */
    private static final int NUM_CONSUMER_REPLICAS = 3;
    /**
     * Creates new form PConsumer.
     */
    public PConsumer() {
        initComponents();
        createInterface(6);
        srDataReplicas = new SRDataReplicas(NUM_CONSUMER_REPLICAS);
        for (int i = 0; i < NUM_CONSUMER_REPLICAS; i++) 
            new Reader().start();
    }
    
    /**
     * Thread to consume records from the topic.
     */
    class Reader extends Thread{

        @Override
        public void run() {
            Properties props = new Properties();
            props.put("bootstrap.servers", "localhost:9093, localhost:9094, localhost:9095, localhost:9096, localhost:9097, localhost:9098");
            props.put("group.id", Thread.currentThread().getName());
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", RecordDeserializer.class.getName());
            props.put("fetch.min.bytes", 100000);
            props.put("enable.auto.commit", true);
            props.put("auto.commit.interval.ms", 10000);
            try( 
                KafkaConsumer<String, Record> consumer = new KafkaConsumer<>(props)) {            
                consumer.subscribe(Collections.singletonList(TOPIC));
                while(true){
                    ConsumerRecords<String, Record> records = consumer.poll(Duration.ofMillis(100));

                    for (ConsumerRecord<String, Record> record : records){
                        new RecordThread(record.value()).start();
                    }
                }
            }
            catch(Exception ex){
                System.out.println(ex);
            }
        }
    }        

    /** Thread to process a record read from the Kafka topic. */
    class RecordThread extends Thread{
        /**
         * Record to be processed.
         */
        private final Record record;

        /**
         * Record thread instantiation.
         * @param record Record to be processed
         */
        public RecordThread(Record record) {
            this.record = record;
        }

        /**
         * Record thread life cycle.
         */
        @Override
        public void run() {
            srDataReplicas.addReplica(record);
        }
    }
    
    /**
     * Update GUI with a new Record.
     * @param record new record
     */
    public static void appendRecord(Record record){
        appendMessageToInterface(record.getSensorId() + " " + record.getTemperature() + " " + record.getTimestamp());
        updateInterface(Integer.parseInt(record.getSensorId()));
        updateTemperatures(record.getTemperature());
        updateTotalRecords();
    }
    /**
     * Update the total number of records.
     */
    public static void updateTotalRecords(){
        int total = Integer.parseInt(jLabel_TotalRecords.getText());
        jLabel_TotalRecords.setText(String.valueOf(++total));
    }
    /**
     * Updates the minimum and maximum temperatures.
     * @param recordTemperature new temperature
     */
    public static void updateTemperatures(Float recordTemperature){
        float min = Float.parseFloat(jLabel_minTemp.getText());
        float max = Float.parseFloat(jLabel_maxTemp.getText());
        if(jLabel_minTemp.getText().equalsIgnoreCase("0") ||recordTemperature < min)
            jLabel_minTemp.setText(String.valueOf(recordTemperature));
        if(jLabel_maxTemp.getText().equalsIgnoreCase("0") ||recordTemperature > max)
            jLabel_maxTemp.setText(String.valueOf(recordTemperature));
    }
    /**
     * Append the record string to the list.
     * @param message record string
     */
    public static void appendMessageToInterface(String message){
        DefaultListModel model = (DefaultListModel) jListMessages.getModel();
        model.addElement(message);
    }   
    /**
     * Update sensor counters.
     * @param sensorId sensor id to increment counter
     */
    public static void updateInterface(int sensorId){
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
    /**
     * Initialize sensors counters.
     * @param numSensor number of sensors
     */
    public static void createInterface(int numSensor){
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
        jScrollPaneMessages = new javax.swing.JScrollPane();
        jListMessages = new javax.swing.JList<>();
        jListMessages.setModel(new javax.swing.DefaultListModel());
        jScrollPane_Records = new javax.swing.JScrollPane();
        jTable_Records = new javax.swing.JTable();
        jLabel_TotalRecords = new javax.swing.JLabel();
        jLabel_TitleTotalRecords = new javax.swing.JLabel();
        jLabel_TitleminTemp = new javax.swing.JLabel();
        jLabel_TitlemaxTemp = new javax.swing.JLabel();
        jLabel_maxTemp = new javax.swing.JLabel();
        jLabel_minTemp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelTitle.setText("Consumer");

        jScrollPaneMessages.setBorder(javax.swing.BorderFactory.createTitledBorder("Income Messages"));
        jScrollPaneMessages.setViewportView(jListMessages);

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

        jLabel_TitleminTemp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_TitleminTemp.setText("Minimum Temperature");

        jLabel_TitlemaxTemp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_TitlemaxTemp.setText("Maximum Temperature");

        jLabel_maxTemp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_maxTemp.setText("0");

        jLabel_minTemp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_minTemp.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jLabelTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane_Records, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPaneMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(jLabel_TitleTotalRecords))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(jLabel_TitleminTemp)))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_TotalRecords)
                            .addComponent(jLabel_minTemp)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel_TitlemaxTemp)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel_maxTemp)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane_Records, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_TitleminTemp)
                    .addComponent(jLabel_minTemp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_TitlemaxTemp)
                    .addComponent(jLabel_maxTemp))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_TitleTotalRecords)
                    .addComponent(jLabel_TotalRecords))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneMessages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(PConsumer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PConsumer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PConsumer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PConsumer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PConsumer().setVisible(true);
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabel_TitleTotalRecords;
    private javax.swing.JLabel jLabel_TitlemaxTemp;
    private javax.swing.JLabel jLabel_TitleminTemp;
    private static javax.swing.JLabel jLabel_TotalRecords;
    private static javax.swing.JLabel jLabel_maxTemp;
    private static javax.swing.JLabel jLabel_minTemp;
    private static javax.swing.JList<String> jListMessages;
    private javax.swing.JScrollPane jScrollPaneMessages;
    private javax.swing.JScrollPane jScrollPane_Records;
    private static javax.swing.JTable jTable_Records;
    // End of variables declaration//GEN-END:variables
}
