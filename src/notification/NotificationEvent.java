package notification;

import auth.User;
import java.time.LocalDateTime;

/**
 * Represents a notification event produced by the system.
 * Uses existing auth.User types for the recipient (DIP: depend on abstraction).
 * Fields are immutable to keep the event simple and cohesive (SRP).
 */
public class NotificationEvent {
    private final String eventType;
    private final String message;
    private final User recipient;
    private final LocalDateTime appointmentDate;
    private final String doctorName;

    public NotificationEvent(String eventType, String message, User recipient,
                             LocalDateTime appointmentDate, String doctorName) {
        this.eventType = eventType;
        this.message = message;
        this.recipient = recipient;
        this.appointmentDate = appointmentDate;
        this.doctorName = doctorName;
    }

    public String getEventType() {
        return eventType;
    }

    public String getMessage() {
        return message;
    }

    public User getRecipient() {
        return recipient;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public String getDoctorName() {
        return doctorName;
    }
}
