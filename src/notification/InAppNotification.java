package notification;

import auth.User;

/**
 * Concrete NotificationChannel implementing in-application notifications.
 */
public class InAppNotification implements NotificationChannel {

    @Override
    public void send(NotificationEvent event) {
        User r = event.getRecipient();
        String recipient = (r != null) ? r.getClass().getSimpleName() : "Unknown";

        // Simulated in-app notification
        System.out.println("[IN-APP] To: " + recipient + " - " + event.getEventType() + ": " + event.getMessage());
    }
}
