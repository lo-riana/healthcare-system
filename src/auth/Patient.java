package auth;

import appointment.Appointment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient extends User {

    private LocalDate    dateOfBirth;
    private List<String> medicalHistory;
    private String       insuranceId;
    private List<Appointment> appointments = new ArrayList<>();

    public Patient(int id, String email, String passwordHash,
                   String firstName, String lastName) {
        super(id, email, passwordHash, Role.PATIENT, firstName, lastName);
        this.medicalHistory = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public void bookAppointment() {
        System.out.println("[Patient] " + getFirstName() + " réserve un rendez-vous.");
    }

    public List<String> viewHistory() {
        return List.copyOf(medicalHistory);
    }

    @Override
    public boolean isProfileComplete() {
        return dateOfBirth != null && insuranceId != null;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public LocalDate    getDateOfBirth()    { return dateOfBirth;  }
    public String       getInsuranceId()    { return insuranceId;  }
    public List<String> getMedicalHistory() { return List.copyOf(medicalHistory); }

    public void setDateOfBirth(LocalDate dateOfBirth)  { this.dateOfBirth = dateOfBirth; }
    public void setInsuranceId(String insuranceId)     { this.insuranceId = insuranceId; }
    public void addMedicalRecord(String record)        { this.medicalHistory.add(record); }
}
