package UC3.Record;

/**
 *
 * @author Luís Laranjeira
 * @author Rafael Sá
 */
public class Record {
    
    private String sensorId;                                  /* sensor ID */
    private float temperature;                                 /* Temperature */
    private int timestamp;                                  /* Timestamp */

    public Record() {}
    
    public Record(String sensorId, float temperature, int timestamp) {
        this.sensorId = sensorId;
        this.temperature = temperature;
        this.timestamp = timestamp;
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
    
    @Override
    public String toString(){
        return "Record(" + sensorId + ", " + temperature + ", " + timestamp +")";
    }
}
