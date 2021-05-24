package UC3.Record;

/**
 * Record to be sent to the Kafka topic.
 * @author Luís Laranjeira
 * @author Rafael Sá
 */
public class Record {
    
    /** Sensor ID. */
    private String sensorId;
    /** Temperature. */
    private float temperature;          
    /** Timestamp. */
    private int timestamp;

    /**
     * Record instantiation.
     */
    public Record() {}
    
    /**
     * Record instantiation.
     * @param sensorId sensor id
     * @param temperature temperature
     * @param timestamp timestamp
     */
    public Record(String sensorId, float temperature, int timestamp) {
        this.sensorId = sensorId;
        this.temperature = temperature;
        this.timestamp = timestamp;
    }
    
    /**
     * Get temperature.
     * @return temperature
     */
    public float getTemperature() {
        return this.temperature;
    }
    /**
     * Get the sensor id.
     * @return sensor id
     */
    public String getSensorId(){
        return this.sensorId;
    }
    /**
     * Get the timestamp.
     * @return timestamp
     */
    public int getTimestamp(){
        return this.timestamp;
    }
    
    @Override
    public String toString(){
        return "Record(" + sensorId + ", " + temperature + ", " + timestamp +")";
    }
}
