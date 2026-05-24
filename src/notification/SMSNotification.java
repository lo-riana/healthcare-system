package notification;

import auth.User;

/**
 * Concrete NotificationChannel implementing SMS sending strategy.
 */
public class SMSNotification implements NotificationChannel {
    private final String providerName;

    public SMSNotification(String providerName) {
        this.providerName = providerName;
    }

    @Override
    public void send(NotificationEvent event) {
        User r = event.getRecipient();
        String recipientInfo = (r != null) ? r.getClass().getSimpleName() : "Unknown";

        // Simulated SMS send
        System.out.println("[SMS - " + providerName + "] To: " + recipientInfo);
        System.out.println("[SMS] Message: " + event.getMessage());
    }
}
