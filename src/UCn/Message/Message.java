package UCn.Message;

/**
 *
 * @author Luís Laranjeira
 * @author Rafael Sá
 */
public class Message {
    
    private String sensorId;                                  /* sensor ID */
    private float temperature;                                 /* Temperature */
    private int timestamp;                                  /* Timestamp */
    private String message;                                 /* Toda a mensagem recebida */
    
    /**
     * Função: Message
     * Esta função recebe uma string e converte numa Mensagem.
     * 
     * @param msg: string a converter.
     */
    public Message(String msg){
        // BEFORE DECODE
        this.sensorId = "Unknown";
        this.timestamp = -1;
        this.temperature = (float) 0.0;
        this.message = msg;
        decodeMessage();
    }
    
    /* GETTERS AND SETTERS */
    public float getTemperature() {
        return this.temperature;
    }
    public String getSensorId(){
        return this.sensorId;
    }
    public int getTimestamp(){
        return this.timestamp;
    }
    
    public String getMessage(){
        return this.message;
    }
    
    /**
     * Função: void decodeMessage
     * Esta função descodifica a string da mensagem lida e converte numa Message.
     */
    private void decodeMessage(){
        String[] msg = message.split(" ");
        
        this.sensorId = msg[0];
        this.temperature = Float.valueOf(msg[1]);
        this.timestamp = Integer.parseInt(msg[2]);
    }
    
    @Override
    public String toString(){
        return "Sensor [" + sensorId + "] : " + temperature + " : " + timestamp;
    }
}
