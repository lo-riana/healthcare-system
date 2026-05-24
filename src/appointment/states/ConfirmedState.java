package appointment.states;

import appointment.Appointment;
import appointment.AppointmentState;

public class ConfirmedState implements AppointmentState {
    public void confirm(Appointment a) {
        throw new IllegalStateException("Cannot confirm from CONFIRMED");
    }
    public void cancel(Appointment a) {
        a.setState(new CancelledState());
    }
    public void complete(Appointment a) {
        a.setState(new CompletedState());
    }
}
