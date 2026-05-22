package auth;

public enum Role {
    PATIENT,
    DOCTOR,
    ADMIN;

    public boolean hasPermission(String permission) {
        return switch (this) {
            case ADMIN   -> true;
            case DOCTOR  -> permission.equals("VIEW_AGENDA")
                         || permission.equals("SET_AVAILABILITY")
                         || permission.equals("VIEW_PROFILE");
            case PATIENT -> permission.equals("BOOK_APPOINTMENT")
                         || permission.equals("VIEW_HISTORY")
                         || permission.equals("VIEW_PROFILE");
        };
    }
}
