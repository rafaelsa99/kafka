/**
* This package contains the classes and methods to:
*   - read data from SENSOR.TXT (this file is published on the e-learning platform)
*   - send the data to PProducer
* 
* Depending on the UC and on your implementation:
*   - there can be one or more Java Threads reading data from SENSOR.TXT
*   - there can be one or more Java Threads sending data to PProducer
*   - there can be one or more Java Sockets to send data to PProducer
* Mandatory:
*   - data sent to PProducer relies on Java Sockets
*   - all Java Threads must run inside the process PSource
*   - PSource contains one process only: PSource.java
* 
* 
**/

package UCn.PSource;
