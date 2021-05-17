/**
* This package contains the classes and methods to:
*   - read data from the Kafka Cluster
*   - process the received data
* 
* Depending on the UC and on your implementation there can be one or more Kafka Group Consumers
* Mandatory:
*   - each Kafka Consumer must be implemented as Java Thread
*   - all Java Threads must run inside the process PConsumer
*   - PConsumer contains one process only: PConsumer.java
**/

package UCn.PConsumer;
