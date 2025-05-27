
* How to setup Kafka
```
wget http://mirror.cogentco.com/pub/apache/kafka/3.7.2/kafka_2.12-3.7.2.tgz
tar -xzf kafka_2.12-3.7.2.tgz
cd kafka_2.12-3.7.2
./bin/zookeeper-server-start.sh config/zookeeper.properties
./bin/kafka-server-start.sh config/server.properties
./bin/kafka-topics.sh --create --bootstrap-server fyre1:9092 --replication-factor 1 --partitions 1 --topic test
./bin/kafka-topics.sh --create --bootstrap-server fyre1:9092 --replication-factor 1 --partitions 1 --topic test2
./bin/kafka-topics.sh --list --bootstrap-server fyre1:9092
```

* Test if Kafka works
```
bin/kafka-console-producer.sh --bootstrap-server fyre1:9092 --topic test
bin/kafka-console-consumer.sh --bootstrap-server fyre1:9092 --topic test --from-beginning
```

* Check the detail of a topic
```
./bin/kafka-topics.sh --describe --topic test --bootstrap-server fyre1:9092
```

* Check which client is connected to the partition.  
```
  ./bin/kafka-consumer-groups.sh --bootstrap-server fyre1:9092 --describe --group test
```

* The way to increase the number of partition.
```
./bin/kafka-topics.sh --bootstrap-server fyre1:9092 --alter --topic test --partitions 2
```
Note: the number of partistion can't be decreased. 


* How to distribute messages across partitions
```
./bin/kafka-console-producer.sh --bootstrap-server fyre1:9092 --topic test --property "parse.key=true" --property "key.separator=:"
```

* bootstrap.properties
```
mp.messaging.outgoing.test.connector=liberty-kafka
mp.messaging.outgoing.test.bootstrap.servers=fyre1:9092
mp.messaging.outgoing.test.group.id=test
mp.messaging.outgoing.test.key.serializer=org.apache.kafka.common.serialization.StringSerializer
mp.messaging.outgoing.test.value.serializer=org.apache.kafka.common.serialization.StringSerializer

mp.messaging.incoming.test2.connector=liberty-kafka
mp.messaging.incoming.test2.bootstrap.servers=fyre1:9092
mp.messaging.incoming.test2.group.id=test2
mp.messaging.incoming.test2.key.serializer=org.apache.kafka.common.serialization.StringSerializer
mp.messaging.incoming.test2.value.serializer=org.apache.kafka.common.serialization.StringSerializer
```


server.xml
```
        <feature>mpReactiveMessaging-1.0</feature>
```


* How the program works
a message will be sent every 10 seconds from liberty to Kafka
```
[root@ladings1 kafka_2.12-2.5.0]# bin/kafka-console-consumer.sh --bootstrap-server fyre1:9092 --topic test                 
Hello Jun 22,2020 01:31:36
Hello Jun 22,2020 01:31:46
```

send a message from Kafka to liberty
```
[root@ladings1 kafka_2.12-2.5.0]# bin/kafka-console-producer.sh --bootstrap-server fyre1:9092 --topic test2
>Hello from Kafka
>
```

```
[AUDIT   ] CWWKF0011I: The defaultServer server is ready to run a smarter planet. The defaultServer server started in 21.974 seconds.
Hello from Kafka
HELLO FROM KAFKA
```

* Reference
https://github.com/eclipse/microprofile-reactive-messaging/blob/ca6ce67da9b280ccb3e19f3c8e5e68cdf1a628d3/spec/src/main/asciidoc/architecture.asciidoc
