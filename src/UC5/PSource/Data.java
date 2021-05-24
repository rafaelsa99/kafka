
package UC5.PSource;

import UC5.Communication.CClient;
import UC5.Communication.Message;
import UC5.Communication.MessageTypes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class to read data from the file and send to the producer via Sockets.
 * @author Rafael Sá (104552), Luís Laranjeira (81526)
 */
public class Data extends Thread{
    
    /** File with the data. */
    private final File file; 
    /** Communication client. */
    private CClient cClient;
    
    /**
     * Data instantiation.
     * @param filename filename
     * @param hostname producer hostname
     * @param port producer port
     */
    public Data(String filename, String hostname, int port) {
        file = new File(filename);
        cClient = new CClient(hostname, port); 
    }
    /**
     * Read lines from the file and send to the producer.
     */
    private void readFile() {
        Scanner myReader;
        String[] line;
        try {
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                line = myReader.nextLine().split(" ");
                Message message = new Message(MessageTypes.RECORD, line[0], Float.parseFloat(line[1]), Integer.parseInt(line[2]));
                cClient.sendMessageAndWaitForReply(message);      
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
