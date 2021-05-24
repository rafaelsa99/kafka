
package UC2.PSource;

import UC2.Communication.CClient;
import UC2.Communication.Message;
import UC2.Communication.MessageTypes;
import UC2.PProducer.PProducer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class to read data from the file and send to the producer via Sockets.
 * @author Rafael Sá (104552), Luís Laranjeira (81526)
 */
public class Data extends Thread{
     /** File with the data. */
    private final File file;                                /* Ficheiro de leitura */
    /** Communication client. */
    private CClient[] cClient;
    
    /**
     * Data instantiation.
     * @param filename filename
     * @param hostname producer hostname
     * @param port producer port
     */
    public Data(String filename, String[] hostname, int[] port) {
        file = new File(filename);
        cClient = new CClient[PProducer.NUM_PRODUCERS];
        for (int i = 0; i < cClient.length; i++) 
            cClient[i] = new CClient(hostname[i], port[i]); 
    }
    /**
     * Read lines from the file and send to the producer.
     * The producer to be sent is according to the sensor id.
     */
    private void readFile() {
        Scanner myReader;
        String[] line;
        try {
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                line = myReader.nextLine().split(" ");
                Message message = new Message(MessageTypes.RECORD, line[0], Float.parseFloat(line[1]), Integer.parseInt(line[2]));
                cClient[Integer.parseInt(message.getSensorId())].sendMessageAndWaitForReply(message);
            }
            myReader.close(); 
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    /**
     * Data reader life cycle.
     */
    @Override
    public void run() {
        readFile();
        PSource.endGUI();
    }
    
    
}
