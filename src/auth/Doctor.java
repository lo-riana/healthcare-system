package auth;

import appointment.Appointment;
import appointment.Schedule;
import appointment.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {

    private String       specialty;
    private final String       licenseNumber;
    private Schedule schedule;
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor(int id, String email, String passwordHash,
                  String firstName, String lastName,
                  String specialty, String licenseNumber) {
        super(id, email, passwordHash, Role.DOCTOR, firstName, lastName);
        this.specialty     = specialty;
        this.licenseNumber = licenseNumber;
        this.schedule      = new Schedule(this);
        this.appointments = new ArrayList<>();
    }

    public void setAvailability(TimeSlot timeSlot) {
        schedule.addTimeSlot(timeSlot);
        System.out.println("[Doctor] Dr. " + getLastName() + " — créneau ajouté : " + timeSlot);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public Schedule getAgenda() {
        return schedule;
    }

    @Override
    public boolean isProfileComplete() {
        return specialty != null && licenseNumber != null;
    }

    public String getSpecialty()     { return specialty;     }
    public String getLicenseNumber() { return licenseNumber; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
}
