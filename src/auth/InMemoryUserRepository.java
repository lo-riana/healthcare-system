package auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {

    private final Map<Integer, User> byId    = new HashMap<>();
    private final Map<String,  User> byEmail = new HashMap<>();

    @Override
    public void save(User user) {
        byId.put(user.getId(), user);
        byEmail.put(user.getEmail(), user);
    }

    @Override
    public Optional<User> findById(int id)        { return Optional.ofNullable(byId.get(id));    }

    @Override
    public Optional<User> findByEmail(String email) { return Optional.ofNullable(byEmail.get(email)); }

    @Override
    public void delete(int id) {
        User u = byId.remove(id);
        if (u != null) byEmail.remove(u.getEmail());
    }

    @Override
    public boolean existsByEmail(String email) { return byEmail.containsKey(email); }
}
