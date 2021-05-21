# scripts to create the topic
cd ../kafka

bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 --partitions 6 --topic Sensor --config min.insync.replicas=2
