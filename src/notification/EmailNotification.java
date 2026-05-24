package notification;

import java.time.format.DateTimeFormatter;
import auth.User;

/**
 * Concrete NotificationChannel implementing email sending strategy.
 * This class adheres to OCP: new channels can be added without modifying
 * existing code. It has a single responsibility (SRP): sending emails.
 */
public class EmailNotification implements NotificationChannel {
    private final String senderAddress;

    public EmailNotification(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    @Override
    public void send(NotificationEvent event) {
        User r = event.getRecipient();
        String name = (r != null) ? r.getClass().getSimpleName() + "(" + r.hashCode() + ")" : "Unknown";
        String when = (event.getAppointmentDate() != null)
                ? event.getAppointmentDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                : "N/A";

        // Simulated email send
        System.out.println("[EMAIL] From: " + senderAddress + " To: " + name);
        System.out.println("[EMAIL] Subject: " + event.getEventType());
        System.out.println("[EMAIL] Body: " + event.getMessage());
        System.out.println("[EMAIL] Appointment: " + when + " with Dr. " + event.getDoctorName());
    }
}
