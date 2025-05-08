package fr.insa.ms.TdOrchestrator.model;

public class Demande {
    private int id;
    private String description;
    private int IdDemandeur;
    private int IdBenevole;
    private int IdValideur;
    private Statut statut;
    private String motifRefus;
    
    public Demande() {}
          
    // Constructeur 
    public Demande(int id, String description, int idDemandeur, Statut statut) {
		super();
		this.id = id;
		this.description = description;
		this.IdDemandeur = idDemandeur;
		this.statut = statut;
	}


	// Getters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getIdDemandeur() {
        return IdDemandeur;
    }

    public int getIdBenevole() {
        return IdBenevole;
    }

    public int getIdValideur() {
        return IdValideur;
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

    public void setIdDemandeur(int IdDemandeur) {
        this.IdDemandeur = IdDemandeur;
    }

    public void setIdBenevole(int IdBenevole) {
        this.IdBenevole = IdBenevole;
    }

    public void setIdValideur(int IdValideur) {
        this.IdValideur = IdValideur;
    }


    public void setStatut(Statut statut) {
        this.statut = statut;
    }
    
    public void setStatutfromString(String statutName) {
    	this.statut = Statut.fromString(statutName);
    }

    public void setMotifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
    }
    
}

