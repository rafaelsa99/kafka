
package UC5.Communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Communication client.
 * @author Rafael Sá (104552), Luís Laranjeira (81526)
 */
public class CClient {
    /** Hostname of the PRODUCER. */
    private final String hostName;
    /** Port of the PRODUCER. */
    private final int portNumber;

    /**
     * CClient instantiation.
     * @param hostName Hostname of the OIS
     * @param portNumber Port of the OIS
     */
    public CClient(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }
    
    /**
     * Connect to the server to test the connection.
     * @return true if the connection succeeded. false otherwise.
     */
    public boolean openServer() {
        try {
            Socket echoSocket = new Socket(this.hostName, this.portNumber);
            echoSocket.close();
            return true;
        } catch(IOException e){
            System.out.println("Couldn't get I/O for the connection to " + hostName);
            return false;
        }
    }
    
    /**
     * Send message.Connection is established and the message is sent.
     * @param obj object to be sent
     */
    public void sendMessageAndWaitForReply(Object obj){
       try (
            Socket kkSocket = new Socket(hostName, portNumber);
            ObjectOutputStream out = new ObjectOutputStream (kkSocket.getOutputStream ());
            ObjectInputStream in = new ObjectInputStream (kkSocket.getInputStream ());
        ) {
           out.writeObject(obj);
           Message reply = (Message)in.readObject();
        } catch (UnknownHostException e) {
            System.out.println("Don't know about host " + hostName);
        } catch (IOException e) {
            System.out.println("Couldn't get I/O for the connection to " + hostName);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }
}
