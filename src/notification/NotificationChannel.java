package notification;

/**
 * Strategy Pattern: defines the contract for sending notifications.
 * Concrete implementations (Email, SMS, In-App) provide different
 * sending strategies without changing client code.
 */
public interface NotificationChannel {
    /**
     * Send a notification for the given event.
     * @param event the notification event
     */
    void send(NotificationEvent event);
}
