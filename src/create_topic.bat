cd ../kafka

.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 3 --partitions 6 --topic Sensor --config min.insync.replicas=2
