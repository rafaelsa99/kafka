/*
 * ver package-info.java
 */
package UCn.PConsumer;

import UCn.Communication.Message;
import UCn.RebalanceListener.RebalanceListener;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 *
 * @author omp
 */
public class PConsumer extends javax.swing.JFrame {

    private final String sensorTopic = "SensorTopic";         /* Topico de leitura */
    private final String groupName = "SensorTopicGroup";     /* Consumer Group */
    /**
     * Creates new form PConsumer
     */
    public PConsumer() {
        initComponents();
    }
    
    private void readFromTopic(){
        
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        props.put("group.id", groupName);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "Message.MessageDeserializer");
        
        KafkaConsumer<String, Message> consumer = new KafkaConsumer<>(props);
        RebalanceListener rebalanceListener = new RebalanceListener(consumer);
        consumer.subscribe(Arrays.asList(sensorTopic), rebalanceListener);
        
        ConsumerRecords<String, Message> records = consumer.poll(100);
        try{
            for (ConsumerRecord<String, Message> record : records){
                System.out.println(record.value().toString());

            }
            consumer.commitSync();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        finally {
            consumer.close();
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
        jScrollPane_Records = new javax.swing.JScrollPane();
        jTable_Records = new javax.swing.JTable();
        jLabel_TotalRecords = new javax.swing.JLabel();
        jLabel_TitleTotalRecords = new javax.swing.JLabel();

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
                            .addComponent(jScrollPane_Records, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPaneMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PConsumer().setVisible(true);
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