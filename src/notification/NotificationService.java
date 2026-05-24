package notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * NotificationService is an Observer (Observer Pattern) that receives
 * NotificationEvent instances and dispatches them to configured channels.
 *
 * Design notes:
 * - DIP: depends on NotificationChannel abstraction rather than concrete implementations.
 * - SRP: manages dispatch only.
 * - OCP: new channels can be added without modifying this class.
 */
public class NotificationService implements EventObserver {
    private final List<NotificationChannel> channels = Collections.synchronizedList(new ArrayList<>());

    public void addChannel(NotificationChannel channel) {
        if (channel != null) {
            channels.add(channel);
        }
    }

    public void removeChannel(NotificationChannel channel) {
        channels.remove(channel);
    }

    public List<NotificationChannel> getChannels() {
        return new ArrayList<>(channels);
    }

    /**
     * Called by subjects when an event occurs. For each registered channel
     * the service delegates sending the notification.
     */
    @Override
    public void update(NotificationEvent event) {
        // Defensive copy to avoid concurrent modification
        List<NotificationChannel> snapshot = getChannels();
        for (NotificationChannel channel : snapshot) {
            try {
                channel.send(event);
            } catch (RuntimeException ex) {
                System.err.println("Failed to send notification via " + channel.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
    }

    /**
     * Convenience method to send a single event immediately.
     */
    public void notifyNow(NotificationEvent event) {
        update(event);
    }
}
