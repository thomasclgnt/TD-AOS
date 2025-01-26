package fr.insa.soa.RESTProject.Demande;

import java.util.ArrayList;
import java.util.List;

import fr.insa.soa.RESTProject.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import fr.insa.soa.RESTProject.Demande.Statut;
import fr.insa.soa.RESTProject.User.User;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("demandes")
public class DemandeResource {
	
	private static final ArrayList<Demande> demandes = new ArrayList<>();

	// Obtenir toutes les demandes
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Demande> getDemandes() {
        return demandes;
    }

    // Rechercher une demande par ID
    @GET
    @Path("/{idDemande}")
    @Produces(MediaType.APPLICATION_JSON)
    public Demande rechercherDemandeParId(@PathParam("idDemande") int idDemande) {
    	
        for (Demande demande : demandes) {
            if (demande.getId() == idDemande) {
                return demande;
            }
        }
        
        return null; // Retourne null si la demande n'est pas trouvée
    }
	
    // Créer une nouvelle demande
    @POST
    @Path("/new")
    public Demande creerDemande(@QueryParam("description") String description) {
    	
    	int id = demandes.size()+1;
    	User usertest = new User(10, "thomas", "thomas@mail.com", "demandeur");
        Demande demande = new Demande(id, description, usertest, Statut.CREEE);
        // Demande demande = new Demande(id, description, demandeur, Statut.CREEE);
        demandes.add(demande);
        return demande;
        
    }
    
    // Valider une demande
    @PUT
    @Path("/validate/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean validerDemande(@PathParam("id") int idDemande) {
    	
    	User valideur = new User(10, "thomas", "thomas@mail.com", "valideur");
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
    @PUT
    @Path("/refuse/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean refuserDemande(@PathParam("id") int idDemande, @QueryParam("motif") String motifRefus) {
    	
    	User valideur = new User(10, "thomas", "thomas@mail.com", "valideur");
    	
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
    
    // Annuler une demande
    @PUT
    @Path("/cancel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean annulerDemande(@PathParam("id") int idDemande) {
    	
    	User valideur = new User(10, "thomas", "thomas@mail.com", "valideur");
    	
        for (Demande demande : demandes) {
            if (demande.getId() == idDemande) {
            	demande.setValideur(valideur);
                demande.setStatut(Statut.ANNULEE);
                
                return true;
            }
        }
        return false; // Échec si la demande n'est pas trouvée ou non annulable
    }
    
    // Assigner un travailleur à une demande
    @PUT
    @Path("/assignWorker/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean assignerBenevole(@PathParam("id") int idDemande) {
    	
    	User benevole = new User(10, "thomas", "thomas@mail.com", "benevole");
    	
        for (Demande demande : demandes) {
            if (demande.getId() == idDemande && demande.getStatut() == Statut.CREEE) {
            	demande.setBenevole(benevole);
                demande.setStatut(Statut.PRISE_EN_CHARGE);
                return true;
            }
        }
        return false; // Échec si la demande n'est pas trouvée ou non assignable
        
    }
    
}
