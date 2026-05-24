package appointment;

import auth.Doctor;
import auth.Patient;

import java.util.UUID;

public class AppointmentService {
    public Appointment book(Doctor doctor, Patient patient, TimeSlot slot) {
        if (hasConflict(doctor, slot))
            throw new IllegalStateException("Slot not available");

        Appointment appt = new Appointment(
                UUID.randomUUID().toString(), slot, doctor, patient
        );
        doctor.addAppointment(appt);
        patient.addAppointment(appt);
        return appt;
    }

    public boolean hasConflict(Doctor doctor, TimeSlot slot) {
        return !doctor.getAgenda().isAvailable(slot);
    }

    public void cancel(Appointment appt)  { appt.cancel(); }
    public void confirm(Appointment appt) { appt.confirm(); }
    public void complete(Appointment appt) { appt.complete(); }
}
