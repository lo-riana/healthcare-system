package catalog;

public class MedicalService {

    private int id;
    private String name;
    private String description;
    private Specialty specialty;
    private int durationMinutes;
    private double fee;
    private int doctorId;

    public MedicalService(int id, String name, String description,
                          Specialty specialty, int durationMinutes,
                          double fee, int doctorId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.specialty = specialty;
        this.durationMinutes = durationMinutes;
        this.fee = fee;
        this.doctorId = doctorId;
    }

    public int getId()              { return id; }
    public String getName()         { return name; }
    public String getDescription()  { return description; }
    public Specialty getSpecialty() { return specialty; }
    public int getDurationMinutes() { return durationMinutes; }
    public double getFee()          { return fee; }
    public int getDoctorId()        { return doctorId; }

    public String getInfo() {
        return String.format("[%s] %s — %d min — %.2f€ (Dr. #%d)",
                specialty.getName(), name, durationMinutes, fee, doctorId);
    }
}
