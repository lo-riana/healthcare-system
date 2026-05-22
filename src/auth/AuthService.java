package auth;

public class AuthService {

    private final UserRepository  userRepo;
    private final PasswordEncoder passwordEncoder;
    private final SessionManager  sessionManager;
    private final UserFactory     userFactory;

    public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo        = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.sessionManager  = SessionManager.getInstance();
        this.userFactory     = UserFactory.getInstance(passwordEncoder);
    }

    /** Authentifie un utilisateur et retourne un token de session */
    public String authenticate(String email, String password) {
        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new AuthenticationException("Aucun compte pour : " + email));

        if (!user.isActive())
            throw new AuthenticationException("Compte désactivé.");

        if (!user.login(password, passwordEncoder))
            throw new AuthenticationException("Mot de passe incorrect.");

        return sessionManager.createSession(user);
    }

    /** Vérifie le token et la permission */
    public void authorize(String token, String permission) {
        SessionManager.Session session = sessionManager.validate(token)
            .orElseThrow(() -> new AuthenticationException("Session invalide ou expirée."));

        if (!session.role().hasPermission(permission))
            throw new AuthorizationException(
                "Permission refusée : " + permission + " pour le rôle " + session.role());
    }

    /** Inscrit un nouvel utilisateur via UserFactory */
    public User register(Role role, String email, String password,
                         String firstName, String lastName, Object... extra) {
        if (userRepo.existsByEmail(email))
            throw new IllegalArgumentException("E-mail déjà utilisé : " + email);

        User user = userFactory.createUser(role, email, password, firstName, lastName, extra);
        userRepo.save(user);
        return user;
    }

    /** Déconnexion */
    public void logout(String token) { sessionManager.invalidate(token); }
}
