package auth;

import java.util.Optional;

public interface UserRepository {
    void           save(User user);
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
    void           delete(int id);
    boolean        existsByEmail(String email);
}
