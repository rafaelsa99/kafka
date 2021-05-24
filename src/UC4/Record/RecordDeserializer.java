package UC4.Record;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * Record Deserializer.
 * @author Luís Laranjeira
 * @author Rafael Sá
 */
public class RecordDeserializer implements Deserializer<Record> {

    @Override
    public void configure(Map<String, ?> map, boolean bln) { }
    
    @Override
    public Record deserialize(String topic, byte[] data) {
        Record record = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            record = mapper.readValue(data, Record.class);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return record;
    }

    @Override
    public void close() { }
    
}
