package UCn.Message;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;

/**
 *
 * @author Luís Laranjeira
 * @author Rafael Sá
 */
public class MessageDeserializer implements Deserializer<Message> {

    @Override
    public void configure(Map<String, ?> map, boolean bln) { }
    
    @Override
    public Message deserialize(String topic, byte[] data) {
        
        String s = new String(data, StandardCharsets.UTF_8);
        Message msg = new Message(s);
        return msg;
    }

    @Override
    public void close() { }
    
}
