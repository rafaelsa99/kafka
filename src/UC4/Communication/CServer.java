
package UC4.Communication;

import UC4.PProducer.PProducer;
import UC4.Record.Record;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server to receive messages.
 * @author Rafael Sá (104552), Luís Laranjeira (81526)
 */
public class CServer extends Thread{

    /** Port of the server. */
    private final int portNumber;
    /** Server socket. */
    private ServerSocket serverSocket;
    /**
     * Communication server instantiation.
     * @param portNumber Port of the server
     */
    public CServer(int portNumber) {
        this.portNumber = portNumber;
    }
    
    /**
     * Creates the server socket.
     */
    public void openServer() {
        try {
            this.serverSocket = new ServerSocket(this.portNumber);
        } catch(IOException e){System.out.println(e);}
    }
    
    /**
     * Await new clients while server socket is open.
     */
    public void awaitMessages(){
        boolean end = false;
        try { 
            while (!end) {
	        //new CServerThread(serverSocket.accept()).start(); // -----------> for multi-threaded producers
                Socket socket = serverSocket.accept();
                try (
                    ObjectInputStream in = new ObjectInputStream (socket.getInputStream ());
                    ObjectOutputStream out = new ObjectOutputStream (socket.getOutputStream ());
                ) {
                    Message input = (Message) in.readObject();
                    if(input.getMessageType() == MessageTypes.END){
                        end = true;
                    }
                    if(input.getMessageType() == MessageTypes.RECORD){
                        new ClientThread(input).start();
                    }
                    out.writeObject(new Message(MessageTypes.RESP_OK));
                    out.close();
                    in.close();
                    socket.close();
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(e.toString());
                }
	    }
            
	} catch (IOException e) {}
    }
    
    /**
     * Close the server socket.
     */
    public void closeServer() {
        try {
            this.serverSocket.close();
        } catch(IOException e){System.out.println(e);}
    }  

    /**
     * Life cycle of the server.
     * Await new clients while server socket is open.
     */
    @Override
    public void run() {
        awaitMessages();
    }
    
    
    
    /**
    * Thread to read messages from a client.
    * @author Rafael Sá (104552), Luís Laranjeira (81526)
    */
   class CServerThread extends Thread {
       /** Socket of the client connected to the server. */
       private Socket socket = null;

       /**
        * Thread instantiation.
        * @param socket Socket of the client connected to the server.
        */
       public CServerThread(Socket socket) {
           super("CServerThread");
           this.socket = socket;
       }

       /**
        * Life cycle of the thread.
        * Reads messages from the client.
        */
       @Override
       public void run() {
           awaitMessages();
           closeServer();
       }
   }
   
   class ClientThread extends Thread{
       private Message input;

        public ClientThread(Message input) {
            this.input = input;
        }

        @Override
        public void run() {
            PProducer.appendRecord(input);
            PProducer.sendToTopic(new Record(input.getSensorId(), input.getTemperature(), input.getTimestamp()));
        }
   }
}    
