package UCn.RebalanceListener;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

/**
 *
 * @author Luís Laranjeira
 * @author Rafael Sá
 */
/**
 *  https://www.learningjournal.guru/courses/kafka/kafka-foundation-training/rebalance-listener/
 */
public class RebalanceListener implements ConsumerRebalanceListener {

    private final KafkaConsumer consumer;
    private final Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap();

    public RebalanceListener(KafkaConsumer con) {
        this.consumer = con;
    }

    public void addOffset(String topic, int partition, long offset) {
        currentOffsets.put(new TopicPartition(topic, partition), new OffsetAndMetadata(offset, "Commit"));
    }

    public Map<TopicPartition, OffsetAndMetadata> getCurrentOffsets() {
        return currentOffsets;
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        //System.out.println("Following Partitions Assigned ....");
        for (TopicPartition partition : partitions) {
            //System.out.println(partition.partition() + ",");
        }
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        //System.out.println("Following Partitions Revoked ....");
        for (TopicPartition partition : partitions) {
            //System.out.println(partition.partition() + ",");
        }

        //System.out.println("Following Partitions commited ....");
        for (TopicPartition tp : currentOffsets.keySet()) {
            //System.out.println(tp.partition());
        }

        consumer.commitSync(currentOffsets);
        currentOffsets.clear();
    }
}
