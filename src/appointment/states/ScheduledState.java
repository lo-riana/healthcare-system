package appointment.states;

import appointment.Appointment;
import appointment.AppointmentState;

public class ScheduledState implements AppointmentState {
    public void confirm(Appointment a) {
        a.setState(new ConfirmedState());
    }
    public void cancel(Appointment a) {
        a.setState(new CancelledState());
    }
    public void complete(Appointment a) {
        throw new IllegalStateException("Cannot complete from SCHEDULED");
    }
}
