package ru.javafom.demo.yandexsqs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    /**
     * Текст сообщения
     */
    private String text;
    /**
     * Unix-time создания сообщения
     */
    private Long time;
}
