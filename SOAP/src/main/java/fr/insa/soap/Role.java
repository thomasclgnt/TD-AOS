package fr.insa.soap;

public enum Role {
    DEMANDEUR("demandeur"),
    BENEVOLE("benevole"),
    VALIDEUR("valideur");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static Role fromString(String roleName) {
        for (Role role : Role.values()) {
            if (role.getRoleName().equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Role invalide : " + roleName + ". Essayez à nouveau.");
    }
}
