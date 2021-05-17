package UCn.Message;

import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;

/**
 *
 * @author Luís Laranjeira
 * @author Rafael Sá
 */
public class MessageSerializer implements Serializer<Message>{
    private String encoding = "UTF8";
    
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, Message data) {
        
        int sizeOfMsg;
        byte[] serializedMsg = null;
        
        if (data == null) return null;
        
        serializedMsg = data.getMessage().getBytes();
        return serializedMsg;
    }

    @Override
    public void close() { }
}
