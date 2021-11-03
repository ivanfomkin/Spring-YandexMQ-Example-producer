package ru.javafom.demo.yandexsqs.service;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.javafom.demo.yandexsqs.dto.Message;

@Slf4j //Аннотация для создания логгера
@Service
@EnableScheduling //Включаем выполнение запланированных задач по расписанию
public class MessageProducer {
    private final QueueMessagingTemplate template; //Bean для отправки сообщений

    @Value("${message.queue.outgoing}")
    private String queueName; //имя очереди, в которую будем отправлять сообщения

    public MessageProducer(QueueMessagingTemplate template) {
        this.template = template;
    }

    @Scheduled(fixedRate = 5000) //Отправляем сообщения каждые 5 секунд
    public void sendMessage() {
        //создаём сообщение с текущим временем в милисекундах
        Message message = new Message("Hello from Java code!", System.currentTimeMillis() / 1000);
        //отправляем сообщение
        template.convertAndSend(queueName, message);
        log.info("Сообщение {} отправлено успешно", message);
    }
}
