package catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * ServiceCatalog implémente ICatalogRepository (Repository Pattern),
 * ISearchable et IFilterable (ISP)
 *
 * C'est l'Information Expert du module : il détient toutes les données
 * du catalogue, donc c'est lui qui prend en charge les requêtes dessus
 */
public class ServiceCatalog implements ICatalogRepository, ISearchable, IFilterable {

    private List<MedicalService> services;
    private List<Clinic> clinics;
    private CatalogFilter catalogFilter;

    public ServiceCatalog() {
        this.services = new ArrayList<>();
        this.clinics = new ArrayList<>();
        this.catalogFilter = new CatalogFilter();
    }

    // --- ICatalogRepository ---

    @Override
    public List<MedicalService> findAll() {
        return new ArrayList<>(services);
    }

    @Override
    public MedicalService findById(int id) {
        return services.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<MedicalService> findBySpecialty(Specialty specialty) {
        return catalogFilter.filterBySpecialty(services, specialty);
    }

    @Override
    public List<Clinic> findByLocation(String location) {
        return catalogFilter.filterByLocation(clinics, location);
    }

    @Override
    public void addClinic(Clinic clinic) {
        clinics.add(clinic);
    }

    @Override
    public void addService(MedicalService service) {
        services.add(service);
    }

    // --- ISearchable ---

    @Override
    public List<MedicalService> search(String keyword) {
        String kw = keyword.toLowerCase();
        return services.stream()
                .filter(s -> s.getName().toLowerCase().contains(kw)
                        || s.getDescription().toLowerCase().contains(kw)
                        || s.getSpecialty().getName().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }

    // --- IFilterable ---

    @Override
    public List<MedicalService> filter(FilterCriteria criteria) {
        return catalogFilter.filter(services, criteria);
    }

    // --- Extra ---

    public List<Clinic> getClinics() {
        return new ArrayList<>(clinics);
    }
}
