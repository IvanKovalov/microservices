# microservices
Для успішної збірки додатку потрібно:
1.	Запустити мінікуб
2.	Зібрати докер імедж сервісів або завантажити з докер хаб
3.	Зібрати додаток за допомогою команди helm install local v1
4.	Увімкнути збір метрик
5.	Відправити запит за таким шляхом http://localhost/schedule\
6.	Обрати пост запит та вставити такі дані 
{
"teacherId": 1,
"studentId": 2,
"classId": 3,
"subject": "4" 
}
7.	Так як наразі присутній лише 1 сервіс, отримання повідомлення від сервісу schedule-service, можна виконавши команду у терміналі  
kubectl exec -it pod/kafka-0 -- /bin/bash
8.	І далі виконати 

kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic student-service --from-beginning

kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic teacher-service --from-beginning

kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic classroom-service --from-beginning

Для виконання використовувались java 11 + spring boot 2.7.8 + kafka 18.3.1

