package catalog;

import java.util.List;

// Repository Pattern : abstrait l'accès aux données du catalogue
public interface ICatalogRepository {
    List<MedicalService> findAll();
    MedicalService findById(int id);
    List<MedicalService> findBySpecialty(Specialty specialty);
    List<Clinic> findByLocation(String location);
    void addClinic(Clinic clinic);
    void addService(MedicalService service);
}
