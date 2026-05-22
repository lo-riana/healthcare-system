package auth;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {

    private String       specialty;
    private final String       licenseNumber;
    private final List<String> schedule;

    public Doctor(int id, String email, String passwordHash,
                  String firstName, String lastName,
                  String specialty, String licenseNumber) {
        super(id, email, passwordHash, Role.DOCTOR, firstName, lastName);
        this.specialty     = specialty;
        this.licenseNumber = licenseNumber;
        this.schedule      = new ArrayList<>();
    }

    public void setAvailability(String timeSlot) {
        schedule.add(timeSlot);
        System.out.println("[Doctor] Dr. " + getLastName() + " — créneau ajouté : " + timeSlot);
    }

    public List<String> getAgenda() {
        return List.copyOf(schedule);
    }

    @Override
    public boolean isProfileComplete() {
        return specialty != null && licenseNumber != null;
    }

    public String getSpecialty()     { return specialty;     }
    public String getLicenseNumber() { return licenseNumber; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
}
