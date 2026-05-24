package appointment.states;

import appointment.Appointment;
import appointment.AppointmentState;

public class CancelledState implements AppointmentState {
    public void confirm(Appointment a) {
        throw new IllegalStateException("Cannot confirm from CANCELLED");
    }
    public void cancel(Appointment a) {
        throw new IllegalStateException("Cannot cancel from CANCELLED");
    }
    public void complete(Appointment a) {
        throw new IllegalStateException("Cannot complete from CANCELLED");
    }
}
