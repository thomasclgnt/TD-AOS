package fr.insa.soa.RESTProject.User;

import fr.insa.soa.RESTProject.Demande.DemandeResource;
import java.util.ArrayList;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("users")
public class UserResource {
	
	private static final ArrayList<User> UserList = new ArrayList<>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getUsers (@Context UriInfo uriInfo) {
		return UserList;
	}
	
	@POST
	@Path("new")
	@Produces(MediaType.APPLICATION_JSON)
	public User addUser(@QueryParam("name") String name,
					    @QueryParam("email") String email,
					    @QueryParam("role") String role,
    					@Context UriInfo uriInfo) {
		
		// Vérification des paramètres
        if (name == null || email == null || role == null) {
            throw new BadRequestException("Les paramètres 'name', 'email' et 'role' sont obligatoires.");
        } else if (!role.equals("demandeur") && !role.equals("benevole") && !role.equals("valideur")) {
        	throw new BadRequestException("Les roles possibles sont 'demandeur', 'benevole' et 'valideur'.");
        }
		
        // Création de l'utilisateur via le service
        // Attribution d'un ID basé sur la place de l'user dans la liste
		int id = UserList.size() + 1; // L'ID commence à 1
        User user = new User(id, name, email, role);
        UserList.add(user) ;
        
        // rajouteraddLinks et URI Builder
        user.addLink(getUriForSelf(uriInfo, user), "self", "GET");
        user.addLink(getUriForAllUsers(uriInfo), "all-users", "GET");
        user.addLink(getUriForDemande(uriInfo), "Demande", "GET");
        user.addLink(getUriForDelete(uriInfo, user), "delete-user", "DELETE");

        return user;
    }
	
	@GET
	@Path("/{idUser}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("idUser") int id, @Context UriInfo uriInfo) {
		
		for (User user : UserList) {
	        if (user.getId() == id) {
	            return user; // Retourner l'utilisateur s'il est trouvé
	        }
	    }
		
		throw new NotFoundException("Utilisateur avec l'ID " + id + " non trouvé.");
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("delete/{idUser}")
	public String deleteUser(@PathParam("idUser") int id) {
	    User userToRemove = null;
	    
	    for (User user : UserList) {
	        if (user.getId() == id) {
	            userToRemove = user;
	            break;
	        }
	    }

	    // Si l'utilisateur existe, on le supprime
	    if (userToRemove != null) {
	        UserList.remove(userToRemove);
	        return "Utilisateur " + userToRemove.getName() + " supprimé." ;
	    } else {
	        throw new NotFoundException("Utilisateur avec l'ID " + id + " non trouvé.");
	    }
	}
	
	
	// Méthodes pour générer les URI (HATEOAS)
    
    private String getUriForSelf(UriInfo uriInfo, User user) {
        String url = uriInfo.getBaseUriBuilder()
                      .path(UserResource.class)
                      .path(Long.toString(user.getId()))
                      .build()
                      .toString();
        return url;
    }
    
    private String getUriForDemande(UriInfo uriInfo) {
    	String url = uriInfo.getBaseUriBuilder()
                      .path(DemandeResource.class)
                      .build()
                      .toString();
    	return url;
    }
	
    private String getUriForDelete(UriInfo uriInfo, User user) {
    	String url = uriInfo.getBaseUriBuilder()
                      .path(UserResource.class)
                      .path(UserResource.class, "deleteUser")
                      .resolveTemplate("idUser", user.getId())
                      .build()
                      .toString();
    	return url;
    }
    
    private String getUriForAllUsers(UriInfo uriInfo) {
    	String url = uriInfo.getBaseUriBuilder()
                      .path(UserResource.class)
                      .build()
                      .toString();
        return url;
    }
}
