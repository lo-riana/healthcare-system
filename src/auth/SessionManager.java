package auth;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SessionManager {

    private static SessionManager instance;

    private SessionManager() {}

    public static synchronized SessionManager getInstance() {
        if (instance == null) instance = new SessionManager();
        return instance;
    }

    private final Map<String, Session> sessions = new HashMap<>();

    public String createSession(User user) {
        String token = UUID.randomUUID().toString();
        sessions.put(token, new Session(user.getId(), user.getRole(),
                                        LocalDateTime.now().plusHours(8)));
        return token;
    }

    public void invalidate(String token) { sessions.remove(token); }

    public Optional<Session> validate(String token) {
        Session s = sessions.get(token);
        if (s == null || s.isExpired()) { sessions.remove(token); return Optional.empty(); }
        return Optional.of(s);
    }

    public record Session(int userId, Role role, LocalDateTime expiresAt) {
        public boolean isExpired() { return LocalDateTime.now().isAfter(expiresAt); }
    }
}
