
package UC5.PSource;

import UC5.Communication.CClient;
import UC5.Communication.Message;
import UC5.Communication.MessageTypes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author rafael
 */
public class Data extends Thread{
    
    private final File file;                                /* Ficheiro de leitura */
    CClient cClient;
    
    public Data(String filename, String hostname, int port) {
        file = new File(filename);
        cClient = new CClient(hostname, port); 
    }
    
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

    @Override
    public void run() {
        readFile();
        PSource.endGUI();
    }
}
