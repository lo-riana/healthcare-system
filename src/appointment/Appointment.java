package appointment;

import appointment.states.ScheduledState;
import auth.Doctor;
import auth.Patient;
import payment.Paiement;

public class Appointment {
    private String id;
    private TimeSlot slot;
    private AppointmentState state;
    private Patient patient;
    private Doctor doctor;
    private Paiement paiement;

    public void confirm()  { state.confirm(this); }
    public void cancel()   { state.cancel(this); }
    public void complete() { state.complete(this); }

    public Appointment(String id, TimeSlot slot, Doctor doctor, Patient patient) {
        this.id = id;
        this.slot = slot;
        this.doctor = doctor;
        this.patient = patient;
        this.state = new ScheduledState();
    }

    // getters et setters
    public AppointmentState getState() {
        return state;
    }

    public void setState(AppointmentState state) {
        this.state = state;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    public void setSlot(TimeSlot slot) {
        this.slot = slot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='"     + id                                    + '\'' +
                ", doctor=" + doctor.getLastName()                     +
                ", patient=" + patient.getFirstName()+ " " + patient.getLastName()                   +
                ", slot="   + slot                                 +
                ", state="  + state.getClass().getSimpleName()     +
                '}';
    }
}
