package appointment.states;

import appointment.Appointment;
import appointment.AppointmentState;

public class CompletedState implements AppointmentState {
    public void confirm(Appointment a) {
        throw new IllegalStateException("Cannot confirm from COMPLETED");
    }
    public void cancel(Appointment a) {
        throw new IllegalStateException("Cannot cancel from COMPLETED");
    }
    public void complete(Appointment a) {
        throw new IllegalStateException("Cannot complete from CANCELLED");
    }
}
