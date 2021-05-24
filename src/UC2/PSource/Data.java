
package UC2.PSource;

import UC2.Communication.CClient;
import UC2.Communication.Message;
import UC2.Communication.MessageTypes;
import UC2.PProducer.PProducer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author rafael
 */
public class Data extends Thread{
    
    private final File file;                                /* Ficheiro de leitura */
    CClient[] cClient;
    
    public Data(String filename, String[] hostname, int[] port) {
        file = new File(filename);
        cClient = new CClient[PProducer.NUM_PRODUCERS];
        for (int i = 0; i < cClient.length; i++) 
            cClient[i] = new CClient(hostname[i], port[i]); 
    }
    
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

    @Override
    public void run() {
        readFile();
        PSource.endGUI();
    }
    
    
}
