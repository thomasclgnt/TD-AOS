package fr.insa.soap;
import java.util.ArrayList;
import java.util.List;

public class DemandeService {
    private List<Demande> demandes;

    public DemandeService() {
        this.demandes = new ArrayList<>();
    }

    // Créer une nouvelle demande
    public Demande creerDemande(int id, String description, User demandeur) {
    	
        Demande demande = new Demande(id, description, demandeur, Statut.CREEE);
        demandes.add(demande);
        return demande;
        
    }
    
    
    // Valider une demande
    public boolean validerDemande(int idDemande, User valideur) {
    	
        for (Demande demande : demandes) {
        	
            if (demande.getId() == idDemande && demande.getStatut() == Statut.CREEE) {
            	
                demande.setValideur(valideur);
                demande.setStatut(Statut.VALIDEE);
                return true;
                
            }
        }
        return false; // Échec si la demande n'est pas trouvée ou déjà traitée
        
    }
    
    // Refuser une demande
    public boolean refuserDemande(int idDemande, User valideur, String motifRefus) {
    	
        for (Demande demande : demandes) {
        	
            if (demande.getId() == idDemande && demande.getStatut() == Statut.CREEE) {
            	
                demande.setValideur(valideur);
                demande.setMotifRefus(motifRefus);
                demande.setStatut(Statut.REFUSEE);
                
                return true;
                
            }
        }
        return false; // Échec si la demande n'est pas trouvée ou déjà traitée
    }

    // Assigner un travailleur à une demande
    public boolean assignerBenevole(int idDemande, User benevole) {
    	
        for (Demande demande : demandes) {
            if (demande.getId() == idDemande && demande.getStatut() == Statut.CREEE) {
                
            	demande.setBenevole(benevole);
            	
                demande.setStatut(Statut.PRISE_EN_CHARGE);
                
                return true;
            }
        }
        return false; // Échec si la demande n'est pas trouvée ou non assignable
        
    }


    // Annuler une demande
    public boolean annulerDemande(int idDemande) {
    	
        for (Demande demande : demandes) {
        	
            if (demande.getId() == idDemande) {
            	
                demande.setStatut(Statut.ANNULEE);
                return true;
            }
        }
        return false; // Échec si la demande n'est pas trouvée ou non annulable
    }

    // Obtenir toutes les demandes
    public List<Demande> getDemandes() {
    	
        return demandes;
        
    }

    // Rechercher une demande par ID
    public Demande rechercherDemandeParId(int idDemande) {
    	
        for (Demande demande : demandes) {
        	
            if (demande.getId() == idDemande) {
            	
                return demande;
            }
        }
        return null; // Retourne null si la demande n'est pas trouvée
    }
}
