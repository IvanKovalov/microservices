docker exec broker \
kafka-topics --bootstrap-server broker:9092 \
             --create \
             --topic teacher-service

docker exec broker \
kafka-topics --bootstrap-server broker:9092 \
             --create \
             --topic classroom-service

docker exec broker \
kafka-topics --bootstrap-server broker:9092 \
             --create \
             --topic student-service

