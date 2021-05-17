/**
* This package contains the classes and methods to:
*   - receive data from PSource
*   - send data to the Kafka Cluster
* 
* Depending on the UC and on your implementation:
*   - there can be one or more sockets
*   - there can be one or more Kafka Producers
* Mandatory:
*   - each Kafka Producer must be implemented as Java Thread
*   - Data received from PSource relies on Java Sockets.
*   - all Java Threads must run inside the process PSource
*   - PProducer contains one process only: PProducer.java
* 
* 
**/

package UCn.PProducer;
