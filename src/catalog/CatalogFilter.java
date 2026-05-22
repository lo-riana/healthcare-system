package catalog;

import java.util.List;
import java.util.stream.Collectors;

public class CatalogFilter {

    public List<MedicalService> filter(List<MedicalService> services, FilterCriteria criteria) {
        List<MedicalService> result = services;

        if (criteria.getSpecialty() != null) {
            result = filterBySpecialty(result, criteria.getSpecialty());
        }

        return result;
    }

    public List<MedicalService> filterBySpecialty(List<MedicalService> services, Specialty specialty) {
        return services.stream()
                .filter(s -> s.getSpecialty().getId() == specialty.getId())
                .collect(Collectors.toList());
    }

    // OCP : on peut ajouter de nouveaux critères de filtre sans modifier filter()
    public List<Clinic> filterByLocation(List<Clinic> clinics, String location) {
        if (location == null || location.isBlank()) return clinics;
        return clinics.stream()
                .filter(c -> c.getAddress().toLowerCase().contains(location.toLowerCase()))
                .collect(Collectors.toList());
    }
}
