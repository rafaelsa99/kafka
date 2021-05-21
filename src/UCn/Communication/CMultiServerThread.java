
package UCn.Communication;

import java.net.*;
import java.io.*;

/**
 * Thread to read messages from a client connected to the OCC server.
 * @author Rafael Sá (104552), Luís Laranjeira (81526)
 */
public class CMultiServerThread extends Thread {
    /** Socket of the client connected to the server. */
    private Socket socket = null;

    /**
     * Thread instantiation.
     * @param socket Socket of the client connected to the server.
     */
    public CMultiServerThread(Socket socket) {
        super("CMultiServerThread");
        this.socket = socket;
    }
    
    /**
     * Life cycle of the thread.
     * Reads messages from the client.
     */
    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                processInput(inputLine);
            socket.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    
    /**
     * Process a message received from the OIS.
     * @param theInput message to process
     */
    public void processInput(String theInput) {
        System.out.println(theInput);
    }
}