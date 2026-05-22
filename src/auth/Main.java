package auth;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        PasswordEncoder encoder    = new SimplePasswordEncoder();
        UserRepository  repository = new InMemoryUserRepository();
        AuthService     auth       = new AuthService(repository, encoder);

        sep("1 — Inscription des utilisateurs");

        Patient alice = (Patient) auth.register(
            Role.PATIENT, "alice@example.com", "pass123", "Alice", "Martin");
        alice.setInsuranceId("INS-001");
        alice.addMedicalRecord("Allergie pénicilline");

        Doctor smith = (Doctor) auth.register(
            Role.DOCTOR, "dr.smith@hospital.fr", "doc456",
            "John", "Smith", "Cardiologie", "LIC-2024-001");
        smith.setAvailability("Lundi 09:00-12:00");
        smith.setAvailability("Mercredi 14:00-17:00");

        Admin root = (Admin) auth.register(
            Role.ADMIN, "admin@system.fr", "admin789",
            "Super", "Admin", 1, "Système");

        System.out.println("Patient : " + alice);
        System.out.println("Médecin : " + smith);
        System.out.println("Admin   : " + root);

        sep("2 — Profils");

        System.out.println("Alice profil complet ? " + alice.isProfileComplete());
        System.out.println("Historique Alice      : " + alice.viewHistory());
        System.out.println("Agenda Dr Smith       : " + smith.getAgenda());

        sep("3 — Role.hasPermission()");

        System.out.println("PATIENT - BOOK_APPOINTMENT : " + Role.PATIENT.hasPermission("BOOK_APPOINTMENT"));
        System.out.println("PATIENT - VIEW_AGENDA      : " + Role.PATIENT.hasPermission("VIEW_AGENDA"));
        System.out.println("DOCTOR  - SET_AVAILABILITY : " + Role.DOCTOR.hasPermission("SET_AVAILABILITY"));
        System.out.println("ADMIN   - tout             : " + Role.ADMIN.hasPermission("ANYTHING"));

        sep("4 — SessionManager Singleton");

        SessionManager sm1 = SessionManager.getInstance();
        SessionManager sm2 = SessionManager.getInstance();
        System.out.println("sm1 == sm2 : " + (sm1 == sm2));

        sep("5 — authenticate() + authorize()");

        String token = auth.authenticate("alice@example.com", "pass123");
        System.out.println("Token : " + token.substring(0, 8) + "...");

        try {
            auth.authorize(token, "BOOK_APPOINTMENT");
            System.out.println("BOOK_APPOINTMENT → ✅ accordé");
        } catch (AuthorizationException e) {
            System.out.println("BOOK_APPOINTMENT → ❌ " + e.getMessage());
        }

        try {
            auth.authorize(token, "VIEW_AGENDA");
            System.out.println("VIEW_AGENDA      → ✅ accordé");
        } catch (AuthorizationException e) {
            System.out.println("VIEW_AGENDA      → ❌ refusé (attendu)");
        }

        sep("6 — Fragment ALT [échec] du diagramme de séquence");

        try {
            auth.authenticate("alice@example.com", "mauvais");
        } catch (AuthenticationException e) {
            System.out.println("Mauvais mot de passe → ❌ " + e.getMessage());
        }

        auth.logout(token);
        System.out.println("Logout effectué.");

        try {
            auth.authorize(token, "BOOK_APPOINTMENT");
        } catch (AuthenticationException e) {
            System.out.println("Token invalide après logout → ❌ " + e.getMessage());
        }

        sep("7 — Admin : manageUsers + assignRole");

        root.manageUsers(alice, "DEACTIVATE");
        System.out.println("Alice active ? " + alice.isActive());

        root.assignRole(smith, Role.ADMIN);
    }

    private static void sep(String title) {
        System.out.println("\n══ " + title + " ══");
    }
}
