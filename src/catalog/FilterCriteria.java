package catalog;

public class FilterCriteria {

    private Specialty specialty;
    private String location;
    private boolean availableOnly;

    public FilterCriteria(Specialty specialty, String location, boolean availableOnly) {
        this.specialty = specialty;
        this.location = location;
        this.availableOnly = availableOnly;
    }

    public Specialty getSpecialty()   { return specialty; }
    public String getLocation()       { return location; }
    public boolean isAvailableOnly()  { return availableOnly; }
}
