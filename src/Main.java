import appointment.Appointment;
import appointment.AppointmentService;
import appointment.TimeSlot;
import auth.*;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        PasswordEncoder encoder    = new SimplePasswordEncoder();
        UserRepository repository = new InMemoryUserRepository();
        AuthService auth       = new AuthService(repository, encoder);

        sep("1 — Inscription des utilisateurs");

        Patient alice = (Patient) auth.register(
            Role.PATIENT, "alice@example.com", "pass123", "Alice", "Martin");
        alice.setInsuranceId("INS-001");
        alice.addMedicalRecord("Allergie pénicilline");

        Doctor smith = (Doctor) auth.register(
            Role.DOCTOR, "dr.smith@hospital.fr", "doc456",
            "John", "Smith", "Cardiologie", "LIC-2024-001");
        smith.setAvailability(new TimeSlot(LocalDateTime.of(2026, 5,24,9, 0),
                LocalDateTime.of(2026, 5,24,17, 0)));
        smith.setAvailability(new TimeSlot(LocalDateTime.of(2026, 5,25,9, 0),
                LocalDateTime.of(2026, 5,25,17, 0)));

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

        sep("8 — AppointmentService : prise de rendez-vous");

        AppointmentService appointmentService = new AppointmentService();

        TimeSlot requestedSlot = new TimeSlot(
                LocalDateTime.of(2026, 5, 24, 10, 0),
                LocalDateTime.of(2026, 5, 24, 11, 0)
        );

        Appointment appt = appointmentService.book(smith, alice, requestedSlot);
        System.out.println("RDV créé     : " + appt);
        System.out.println("État initial : " + appt.getState().getClass().getSimpleName());

        sep("9 — Cycle de vie du RDV (State pattern)");

        appt.confirm();
        System.out.println("Après confirm()  : " + appt.getState().getClass().getSimpleName());

        appt.complete();
        System.out.println("Après complete() : " + appt.getState().getClass().getSimpleName());

        try {
            appt.cancel();
        } catch (IllegalStateException e) {
            System.out.println("Cancel sur COMPLETED → ❌ " + e.getMessage());
        }

        sep("10 — Détection de conflit");

        try {
            appointmentService.book(smith, alice, requestedSlot);
        } catch (IllegalStateException e) {
            System.out.println("Double booking → ❌ " + e.getMessage());
        }

        TimeSlot otherSlot = new TimeSlot(
                LocalDateTime.of(2026, 5, 24, 14, 0),
                LocalDateTime.of(2026, 5, 24, 15, 0)
        );
        Appointment appt2 = appointmentService.book(smith, alice, otherSlot);
        System.out.println("2ème RDV créé : " + appt2);

        sep("11 — Annulation depuis SCHEDULED");

        appt2.cancel();
        System.out.println("Après cancel() : " + appt2.getState().getClass().getSimpleName());

        try {
            appt2.confirm();
        } catch (IllegalStateException e) {
            System.out.println("Confirm sur CANCELLED → ❌ " + e.getMessage());
        }

        sep("12 — Schedule de Smith");

        System.out.println(smith.getAgenda());
    }

    private static void sep(String title) {
        System.out.println("\n══ " + title + " ══");
    }
}
