package UC4.Record;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;

/**
 *
 * @author Luís Laranjeira
 * @author Rafael Sá
 */
public class RecordSerializer implements Serializer<Record>{
    private String encoding = "UTF8";
    
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, Record data) {
        
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] serializedMsg = null;
        try {
            serializedMsg = objectMapper.writeValueAsString(data).getBytes();
        } catch (JsonProcessingException ex) {
            System.out.println(ex.toString());
        }
        return serializedMsg;
    }

    @Override
    public void close() { }
}
