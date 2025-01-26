package fr.insa.soa.RESTProject.Demande;

import java.util.ArrayList;
import java.util.List;

import fr.insa.soa.RESTProject.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import fr.insa.soa.RESTProject.Demande.Statut;
import fr.insa.soa.RESTProject.User.User;
import fr.insa.soa.RESTProject.User.UserResource;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("demandes")
public class DemandeResource {
	
	private static final ArrayList<Demande> demandes = new ArrayList<>();

	private String getUriforSelf(UriInfo uriInfo, Demande demande) {
		String url=uriInfo.getBaseUriBuilder()
				.path(DemandeResource.class)
				.path(Long.toString(demande.getId()))
				.build()
				.toString();
		return url;
	}
	
	private String getUriforUser(UriInfo uriInfo) {
		String url=uriInfo.getBaseUriBuilder()
				.path(UserResource.class)
				.build()
				.toString();
		return url;
	}
	
	private String getUriforValidate(UriInfo uriInfo, Demande demande) {
		String url=uriInfo.getBaseUriBuilder()
				.path(DemandeResource.class)
				.path(DemandeResource.class, "validerDemande")
				.resolveTemplate("id", demande.getId())
				.build()
				.toString();
		return url;
	}
	
	private String getUriforRefuse(UriInfo uriInfo, Demande demande) {
		String url=uriInfo.getBaseUriBuilder()
				.path(DemandeResource.class)
				.path(DemandeResource.class, "refuserDemande")
				.resolveTemplate("id", demande.getId())
				.build()
				.toString();
		return url;
	}
	
	private String getUriforCancel(UriInfo uriInfo, Demande demande) {
		String url=uriInfo.getBaseUriBuilder()
				.path(DemandeResource.class)
				.path(DemandeResource.class, "annulerDemande")
				.resolveTemplate("id", demande.getId())
				.build()
				.toString();
		return url;
	}
	
	private String getUriforAssignWorker(UriInfo uriInfo, Demande demande) {
		String url=uriInfo.getBaseUriBuilder()
				.path(DemandeResource.class)
				.path(DemandeResource.class, "assignerBenevole")
				.resolveTemplate("id", demande.getId())
				.build()
				.toString();
		return url;
	}
	
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
    public Demande creerDemande(@QueryParam("description") String description, @Context UriInfo uriInfo ) {
    	
    	int id = demandes.size()+1;
    	User usertest = new User(10, "thomas", "thomas@mail.com", "demandeur");
        Demande demande = new Demande(id, description, usertest, Statut.CREEE);
        
        // AJout des liens vers les autres ressources liées à demande
        demande.addLink(getUriforSelf(uriInfo, demande),"self", "GET");
        demande.addLink(getUriforUser(uriInfo), "User", "GET");
        demande.addLink(getUriforValidate(uriInfo, demande),"validate-demand", "PUT");
        demande.addLink(getUriforRefuse(uriInfo, demande),"refuse-demand", "PUT");
        demande.addLink(getUriforCancel(uriInfo, demande),"cancel-demand", "PUT");
        demande.addLink(getUriforAssignWorker(uriInfo, demande),"self", "PUT");
        
        // Demande demande = new Demande(id, description, demandeur, Statut.CREEE);
        demandes.add(demande);
        return demande;
        
    }
    
    // Valider une demande
    @PUT
    @Path("/validate/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Demande validerDemande(@PathParam("id") int idDemande) {
    	
    	User valideur = new User(10, "thomas", "thomas@mail.com", "valideur");
        for (Demande demande : demandes) {
            if (demande.getId() == idDemande && demande.getStatut() == Statut.CREEE) {
                demande.setValideur(valideur);
                demande.setStatut(Statut.VALIDEE);
                return demande;
            }
        }
        
        return null; // Échec si la demande n'est pas trouvée ou déjà traitée
        
    }
    
    // Refuser une demande
    @PUT
    @Path("/refuse/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Demande refuserDemande(@PathParam("id") int idDemande, @QueryParam("motif") String motifRefus) {
    	
    	User valideur = new User(10, "thomas", "thomas@mail.com", "valideur");
    	
        for (Demande demande : demandes) {
            if (demande.getId() == idDemande && demande.getStatut() == Statut.CREEE) {
                demande.setValideur(valideur);
                demande.setMotifRefus(motifRefus);
                demande.setStatut(Statut.REFUSEE);
                
                return demande;
            }
        }
        return null; // Échec si la demande n'est pas trouvée ou déjà traitée
    }
    
    // Annuler une demande
    @PUT
    @Path("/cancel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Demande annulerDemande(@PathParam("id") int idDemande) {
    	
    	User valideur = new User(10, "thomas", "thomas@mail.com", "valideur");
    	
        for (Demande demande : demandes) {
            if (demande.getId() == idDemande) {
            	demande.setValideur(valideur);
                demande.setStatut(Statut.ANNULEE);
                
                return demande;
            }
        }
        return null; // Échec si la demande n'est pas trouvée ou non annulable
    }
    
    // Assigner un travailleur à une demande
    @PUT
    @Path("/assignWorker/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Demande assignerBenevole(@PathParam("id") int idDemande) {
    	
    	User benevole = new User(10, "thomas", "thomas@mail.com", "benevole");
    	
        for (Demande demande : demandes) {
            if (demande.getId() == idDemande && demande.getStatut() == Statut.CREEE) {
            	demande.setBenevole(benevole);
                demande.setStatut(Statut.PRISE_EN_CHARGE);
                return demande;
            }
        }
        return null; // Échec si la demande n'est pas trouvée ou non assignable
        
    }
    
}
