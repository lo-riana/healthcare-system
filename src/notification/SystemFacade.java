package notification;

import auth.User;
import auth.Patient;
import auth.Doctor;
import java.time.LocalDateTime;

/**
 * Facade Pattern: Provides a simple API to the notification subsystem.
 * Clients use this class to register channels and send common notifications
 * without coupling to internal classes or workflow.
 */
public class SystemFacade {
    private final NotificationService service;

    public SystemFacade() {
        this.service = new NotificationService();
    }

    // Expose limited operations to keep coupling low and preserve encapsulation
    public void registerChannel(NotificationChannel channel) {
        service.addChannel(channel);
    }

    public void unregisterChannel(NotificationChannel channel) {
        service.removeChannel(channel);
    }

    /**
     * Notify a patient about a scheduled appointment.
     */
    public void notifyAppointmentScheduled(Patient patient, Doctor doctor, LocalDateTime when) {
        String message = "Your appointment is scheduled on " + when.toString();
        NotificationEvent event = new NotificationEvent("AppointmentScheduled", message, (User) patient, when, doctor != null ? doctor.toString() : "Unknown");
        service.notifyNow(event);
    }

    /**
     * Notify a patient about an appointment cancellation.
     */
    public void notifyAppointmentCancelled(Patient patient, Doctor doctor, LocalDateTime when) {
        String message = "Your appointment on " + when.toString() + " has been cancelled.";
        NotificationEvent event = new NotificationEvent("AppointmentCancelled", message, (User) patient, when, doctor != null ? doctor.toString() : "Unknown");
        service.notifyNow(event);
    }

    /**
     * General-purpose notification API for arbitrary users.
     */
    public void notifyUser(User user, String eventType, String message) {
        NotificationEvent event = new NotificationEvent(eventType, message, user, null, null);
        service.notifyNow(event);
    }
}
