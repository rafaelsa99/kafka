
package UC4.PConsumer;

import UC4.Record.Record;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author rafael
 */
public class SRDataReplicas {
    
    private final ReentrantLock rl;
    private final int numReplicas;
    private HashMap<Integer, ArrayList<Record>> dataReplicas;
    
    public SRDataReplicas(int numReplicas) {
        this.rl = new ReentrantLock(true);
        this.numReplicas = numReplicas;
        dataReplicas = new HashMap<>();
    }
    
    public void addReplica(Record record){
        try{
            rl.lock();
            if(!dataReplicas.containsKey(record.getTimestamp()))
                dataReplicas.put(record.getTimestamp(), new ArrayList<>());
            dataReplicas.get(record.getTimestamp()).add(record);
            if(dataReplicas.get(record.getTimestamp()).size() == numReplicas){
                PConsumer.appendRecord(getMajority(dataReplicas.get(record.getTimestamp())));
                dataReplicas.remove(dataReplicas.get(record.getTimestamp()));
            }
        } finally{
            rl.unlock();
        }
    }
    
    private Record getMajority(ArrayList<Record> records){
        HashMap<Float, Integer> counters = new HashMap<>();
        for (Record record : records) {
            if(counters.get(record.getTemperature()) == null)
                counters.put(record.getTemperature(), 0);
            else
                counters.replace(record.getTemperature(), counters.get(record.getTemperature()) + 1);
        }
        int max = Collections.max(counters.values());
        for (Entry<Float, Integer> entry : counters.entrySet()) {
            if (entry.getValue()==max) {
                for (Record record : records) {
                    if(record.getTemperature() == entry.getKey())
                        return record;
                }
            }
        }
        return null;
    }
}
