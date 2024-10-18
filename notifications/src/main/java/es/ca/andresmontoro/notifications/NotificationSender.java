package es.ca.andresmontoro.notifications;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import es.ca.andresmontoro.notifications.eventos.EventoResponse;

@Component
public class NotificationSender {
  @KafkaListener(
    topics = "new-events-notifications",
    containerFactory = "kafkaListenerContainerFactory",
    groupId = "notifications-group"
  )
  void sendNotification(EventoResponse eventoResponse) {
    System.out.println("New event notification: " + eventoResponse.toString());
  }
}
