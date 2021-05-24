package UC5.Communication;

import java.io.Serializable;

/**
 *
 * @author Luís Laranjeira
 * @author Rafael Sá
 */
public class Message implements Serializable{
    
    private String sensorId;                                  /* sensor ID */
    private float temperature;                                 /* Temperature */
    private int timestamp;                                  /* Timestamp */
    private int messageType;
    
    public Message(int messageType, String sensorId, float temperature, int timestamp) {
        this.sensorId = sensorId;
        this.temperature = temperature;
        this.timestamp = timestamp;
        this.messageType = messageType;
    }

    public Message(int messageType) {
        this.messageType = messageType;
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

    public int getMessageType() {
        return messageType;
    }
}
