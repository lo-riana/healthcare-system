package catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Clinic {

    private int id;
    private String name;
    private String address;
    private String phone;
    private List<MedicalService> services;

    public Clinic(int id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.services = new ArrayList<>();
    }

    public int getId()       { return id; }
    public String getName()  { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }

    public List<MedicalService> getServices() {
        return new ArrayList<>(services);
    }

    public void addService(MedicalService service) {
        services.add(service);
    }

    public List<MedicalService> getServicesBySpecialty(Specialty specialty) {
        return services.stream()
                .filter(s -> s.getSpecialty().getId() == specialty.getId())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name + " (" + address + ")";
    }
}
