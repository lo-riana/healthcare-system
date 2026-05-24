package appointment;

import auth.Doctor;

import java.util.ArrayList;

public class Schedule {
    private Doctor doctor;
    private ArrayList<TimeSlot> timeSlots;

    public Schedule(Doctor doctor) {
        this.doctor = doctor;
        this.timeSlots = new ArrayList<>();
    }

    public void addTimeSlot(TimeSlot timeSlot) {
        timeSlots.add(timeSlot);
    }

    public void removeTimeSlot(TimeSlot timeSlot) {
        timeSlots.remove(timeSlot);
    }

    public boolean isAvailable(TimeSlot timeSlot) {
        return timeSlots.contains(timeSlot);
    }

    public Doctor getDoctor() { return doctor; }
    public ArrayList<TimeSlot> getTimeSlots() { return timeSlots; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Schedule of Dr. ").append(doctor.getLastName()).append(":\n");
        if (timeSlots.isEmpty()) {
            sb.append("  No available slots.");
        } else {
            for (TimeSlot slot : timeSlots) {
                sb.append("  - ").append(slot).append("\n");
            }
        }
        return sb.toString();
    }
}
