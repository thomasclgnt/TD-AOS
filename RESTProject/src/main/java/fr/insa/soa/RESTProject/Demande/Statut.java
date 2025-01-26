package fr.insa.soa.RESTProject.Demande;

public enum Statut {
    CREEE("créée"),
    VALIDEE("benevole"),
    REFUSEE("valideur"),
    ANNULEE("annulée"),
    PRISE_EN_CHARGE("prise en charge");

    private final String valeur;

    Statut(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }
    
    public static Statut fromString(String statutValeur) {
        for (Statut statut : Statut.values()) {
            if (statut.getValeur().equalsIgnoreCase(statutValeur)) {
                return statut;
            }
        }
        throw new IllegalArgumentException("Statut invalide : " + statutValeur + ". Essayez à nouveau.");
    }
}

