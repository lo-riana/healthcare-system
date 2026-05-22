package auth;

import java.util.concurrent.atomic.AtomicInteger;

public class UserFactory {

    private static UserFactory      instance;
    private static final AtomicInteger counter = new AtomicInteger(1);

    private final PasswordEncoder encoder;

    private UserFactory(PasswordEncoder encoder) { this.encoder = encoder; }

    public static synchronized UserFactory getInstance(PasswordEncoder encoder) {
        if (instance == null) instance = new UserFactory(encoder);
        return instance;
    }

    public User createUser(Role role, String email, String password,
                           String firstName, String lastName, Object... extra) {
        int    id   = counter.getAndIncrement();
        String hash = encoder.encode(password);

        return switch (role) {
            case PATIENT -> new Patient(id, email, hash, firstName, lastName);
            case DOCTOR  -> {
                String specialty  = extra.length > 0 ? (String) extra[0] : "";
                String licenseNum = extra.length > 1 ? (String) extra[1] : "";
                yield new Doctor(id, email, hash, firstName, lastName, specialty, licenseNum);
            }
            case ADMIN -> {
                int    level = extra.length > 0 ? (int) extra[0] : 1;
                String dept  = extra.length > 1 ? (String) extra[1] : "";
                yield new Admin(id, email, hash, firstName, lastName, level, dept);
            }
        };
    }
}
