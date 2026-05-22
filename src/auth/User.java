package auth;

import java.time.LocalDateTime;

public abstract class User {

    private final int           id;
    private final String        email;
    private       String        passwordHash;
    private final Role          role;
    private       String        firstName;
    private       String        lastName;
    private       boolean       active;
    private final LocalDateTime createdAt;

    protected User(int id, String email, String passwordHash,
                   Role role, String firstName, String lastName) {
        this.id           = id;
        this.email        = email;
        this.passwordHash = passwordHash;
        this.role         = role;
        this.firstName    = firstName;
        this.lastName     = lastName;
        this.active       = true;
        this.createdAt    = LocalDateTime.now();
    }

    public boolean login(String rawPassword, PasswordEncoder encoder) {
        return active && encoder.matches(rawPassword, this.passwordHash);
    }

    public void logout() {}

    public boolean isActive()   { return active; }
    public void deactivate()    { this.active = false; }

    public abstract boolean isProfileComplete();

    public int           getId()        { return id;        }
    public String        getEmail()     { return email;     }
    public Role          getRole()      { return role;      }
    public String        getFirstName() { return firstName; }
    public String        getLastName()  { return lastName;  }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName)   { this.lastName  = lastName;  }

    @Override
    public String toString() {
        return getClass().getSimpleName()
             + "[id=" + id + ", email=" + email + ", role=" + role + "]";
    }
}
