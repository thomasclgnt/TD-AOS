package fr.insa.ms.TdOrchestrator.model;

public enum Statut {
    CREEE("creee"),
    VALIDEE("validee"),
    REFUSEE("refusee"),
    ANNULEE("annulee"),
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

