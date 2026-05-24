package notification;

/**
 * Observer Pattern: an observer that receives events.
 * Subjects (not part of this module) will call {@code update} when
 * an event occurs.
 */
public interface EventObserver {
    /**
     * Called when a notification event occurs.
     * @param event the notification event
     */
    void update(NotificationEvent event);
}
