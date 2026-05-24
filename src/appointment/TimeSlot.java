package appointment;

import java.time.LocalDateTime;

public class TimeSlot {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TimeSlot(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean overlaps(TimeSlot other) {
        return !this.startTime.isAfter(other.endTime) && !other.startTime.isAfter(this.endTime);
    }

    @Override
    public String toString() {
        return startTime + " → " + endTime;
    }
}
