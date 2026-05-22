package catalog;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        Specialty cardio  = new Specialty(1, "Cardiologie",  "Maladies cardiovasculaires");
        Specialty dermato = new Specialty(2, "Dermatologie", "Maladies de la peau");

        MedicalService ecg         = new MedicalService(1, "ECG de repos",         "Électrocardiogramme standard", cardio,   30, 45.0, 101);
        MedicalService echo        = new MedicalService(2, "Échocardiographie",    "Échographie cardiaque",        cardio,   45, 80.0, 101);
        MedicalService dermConsult = new MedicalService(3, "Consultation dermato", "Bilan cutané général",         dermato,  20, 35.0, 202);

        Clinic clinicParis = new Clinic(1, "Clinique Pasteur",   "12 rue de la Paix, Paris", "01 23 45 67 89");
        Clinic clinicLyon  = new Clinic(2, "Hôpital Saint-Luc", "5 avenue Berthelot, Lyon",  "04 72 11 22 33");

        clinicParis.addService(ecg);
        clinicParis.addService(echo);
        clinicLyon.addService(dermConsult);


        ServiceCatalog catalog = new ServiceCatalog();
        catalog.addClinic(clinicParis);
        catalog.addClinic(clinicLyon);
        catalog.addService(ecg);
        catalog.addService(echo);
        catalog.addService(dermConsult);

        // Scénario 1 : recherche par mot-clé (ISearchable)

        System.out.println("Recherche : 'cardio'");
        List<MedicalService> results = catalog.search("cardio");
        results.forEach(s -> System.out.println("  " + s.getInfo()));

        // Scénario 2 : filtrage par spécialité (IFilterable)

        System.out.println("\nFiltre : Cardiologie uniquement");
        FilterCriteria criteria = new FilterCriteria(cardio, null, false);
        List<MedicalService> filtered = catalog.filter(criteria);
        filtered.forEach(s -> System.out.println("  " + s.getInfo()));

        // Scénario 3 : cliniques par lieu (ICatalogRepository)

        System.out.println("\nCliniques à Paris");
        List<Clinic> lyonClinics = catalog.findByLocation("Paris");
        lyonClinics.forEach(c -> System.out.println("  " + c));

        // Scénario 4 : accès direct par id

        System.out.println("\nDétail service id=2");
        MedicalService svc = catalog.findById(2);
        if (svc != null) System.out.println("  " + svc.getInfo());
    }
}
