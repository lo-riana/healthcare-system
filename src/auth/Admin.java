package auth;

public class Admin extends User {

    private final int    adminLevel;
    private final String department;

    public Admin(int id, String email, String passwordHash,
                 String firstName, String lastName,
                 int adminLevel, String department) {
        super(id, email, passwordHash, Role.ADMIN, firstName, lastName);
        this.adminLevel = adminLevel;
        this.department = department;
    }

    public void manageUsers(User target, String action) {
        if (action.equalsIgnoreCase("DEACTIVATE")) {
            target.deactivate();
            System.out.println("[Admin] " + getFirstName() + " a désactivé : " + target.getEmail());
        }
    }

    public void assignRole(User target, Role newRole) {
        System.out.println("[Admin] " + getFirstName()
            + " assigne le rôle " + newRole + " à " + target.getEmail());
    }

    @Override
    public boolean isProfileComplete() { return department != null; }

    public int    getAdminLevel() { return adminLevel; }
    public String getDepartment() { return department; }
}
