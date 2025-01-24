package fr.insa.soap;

public class Demande {
    private int id;
    private String description;
    private User demandeur;
    private User benevole;
    private User valideur;
    private Statut statut;
    private String motifRefus;
      
    // Constructeur 
    public Demande(int id, String description, User demandeur, Statut statut) {
        this.id = id;
        this.description = description;
        this.demandeur = demandeur;
        this.benevole = null; // Par défaut, aucun travailleur assigné
        this.valideur = null;    // Par défaut, aucun valideur
        this.statut = statut;    // Statut initial passé au constructeur
        this.motifRefus = null;  // Par défaut, pas de motif de refus
    }
    
    // Getters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public User getDemandeur() {
        return demandeur;
    }

    public User getBenevole() {
        return benevole;
    }

    public User getValideur() {
        return valideur;
    }


    public Statut getStatut() {
        return statut;
    }

    public String getMotifRefus() {
        return motifRefus;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDemandeur(User demandeur) {
        this.demandeur = demandeur;
    }

    public void setBenevole(User benevole) {
        this.benevole = benevole;
    }

    public void setValideur(User valideur) {
        this.valideur = valideur;
    }


    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public void setMotifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
    }
}

