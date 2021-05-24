package UC5.Communication;

import java.io.Serializable;

/**
 * Message to be sent from the Source to the Producer.
 * @author Luís Laranjeira
 * @author Rafael Sá
 */
public class Message implements Serializable{
    
    /** Sensor ID. */
    private String sensorId; 
    /** Temperature. */
    private float temperature;                               
    /** Timestamp. */
    private int timestamp;      
    /** Type of the message. */
    private int messageType;
    /**
     * Message instantiation.
     * @param messageType type of the message to be sent
     * @param sensorId sensor ID
     * @param temperature temperature
     * @param timestamp timestamp
     */
    public Message(int messageType, String sensorId, float temperature, int timestamp) {
        this.sensorId = sensorId;
        this.temperature = temperature;
        this.timestamp = timestamp;
        this.messageType = messageType;
    }
/**
     * Message instantiation.
     * @param messageType type of the message to be sent
     */
    public Message(int messageType) {
        this.messageType = messageType;
    }
    
    /**
     * Get temperature.
     * @return temperature
     */
    public float getTemperature() {
        return this.temperature;
    }
    /**
     * Get Sensor ID.
     * @return sensor ID
     */
    public String getSensorId(){
        return this.sensorId;
    }
    /**
     * Get Timestamp.
     * @return timestamp
     */
    public int getTimestamp(){
        return this.timestamp;
    }
    /**
     * Get the type of the message.
     * @return message type
     */
    public int getMessageType() {
        return messageType;
    }
}
