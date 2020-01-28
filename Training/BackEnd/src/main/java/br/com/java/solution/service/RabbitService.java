package br.com.java.solution.service;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class RabbitService {

    public static void sendRequest(String queueName, String body) {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {

            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, body.getBytes(StandardCharsets.UTF_8));

            // System.out.println(" [x] Sent '" + body + "'");
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void receiveRequest(String queueName) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection;
        Channel channel;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(queueName, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("[" + queueName + "] Recebida: " + message);
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

}
