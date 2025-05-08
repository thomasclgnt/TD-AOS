package fr.insa.ms.TdOrchestrator.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.ms.TdOrchestrator.model.User;

@RestController
public class OrchestratorResource {
	
	@Autowired
	private RestTemplate restTemplate ;
	
	private final String Url_UserMS = "http://user";
//	private final String Url_UserMS = "http://localhost:8060";
	private final String Url_DemandeMS = "http://demande";
//	private final String Url_DemandeMS = "http://localhost:8070";
	
	// Récupère les informations de l'User à partir de son id
    private User getUser (int userId) {
    	System.out.println("getUser");
    	
    	ResponseEntity<User> response = restTemplate.getForEntity(Url_UserMS + "/users/" + userId, User.class);
    	System.out.println("Réponse : " + response);
    	
    	if (response.getStatusCode() != HttpStatus.OK) {
    		System.out.println(response);
    		return null;    		
    	} else {
    		User user = response.getBody();
    		return user;
    	}
    }
    
    private boolean checkUserRole (int userId, String expectedRole) {
    	System.out.println("checkUserRole");
        User user = getUser(userId);
        return user != null && expectedRole.equals(user.getRole().getRoleName());
    }
    
    @PostMapping("/addDemande")
    public ResponseEntity<String> addDemande (@RequestBody Map<String, Object> demandeData,
                                             @RequestParam("idUser") int idUser) {

        if (!checkUserRole(idUser, "demandeur")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("L'utilisateur n'est pas un demandeur.");
        }
        System.out.println(demandeData);
        demandeData.put("idDemandeur", idUser);
        System.out.println(demandeData);

        ResponseEntity<String> response = restTemplate.postForEntity(
        		Url_DemandeMS + "/demandes/new", demandeData, String.class);

        return response.getStatusCode().is2xxSuccessful()
                ? ResponseEntity.ok("Demande créée avec succès.")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création de la demande.");
    }
    
    
    @GetMapping("/getCreatedDemandes")
    public ResponseEntity<Object> getCreatedDemandes (@RequestParam("idUser") int idUser) {
    	
    	System.out.println("getCreatedDemandes");
    	
        if (!checkUserRole(idUser, "valideur")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé. Rôle valideur requis.");
        }

        return restTemplate.getForEntity(Url_DemandeMS + "/demandes/statut/creee", Object.class);
    }
    
    @GetMapping("/getValidatedDemandes")
    public ResponseEntity<Object> getValidatedDemandes (@RequestParam("idUser") int idUser) {
        if (!checkUserRole(idUser, "valideur")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé. Rôle valideur requis.");
        }

        return restTemplate.getForEntity(Url_DemandeMS + "/demandes/statut/validee", Object.class);
    }
    
    
    @PutMapping("/validateDemande/{idDemande}")
    public ResponseEntity<String> validateDemande (@PathVariable int idDemande,
                                                  @RequestParam("idValideur") int idValideur) {
        
    	if (!checkUserRole(idValideur, "valideur")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("L'utilisateur n'est pas un valideur.");
        }

        restTemplate.put(Url_DemandeMS + "/demandes/validate/" + idDemande + "?idValideur=" + idValideur, null);

        return ResponseEntity.ok("Demande validée.");
    }
    
    
    @PutMapping("/assignDemande/{idDemande}")
    public ResponseEntity<String> assignDemande (@PathVariable int idDemande,
                                                @RequestParam("idValideur") int idValideur,
                                                @RequestParam("idBenevole") int idBenevole) {
    	
        if (!checkUserRole(idValideur, "valideur")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé. Rôle valideur requis.");          	
            }
        else if(!checkUserRole(idBenevole, "benevole")) {
        	return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La personne assignée n'est pas bénévole. Il faut un bénévole pour remplir une mission.");  
        }

        restTemplate.put(Url_DemandeMS + "/demandes/assignWorker/" + idDemande + "?idBenevole=" + idBenevole, null);
        return ResponseEntity.ok("Demande assignée.");
    }
    
    
    @PutMapping("/refuseDemande/{idDemande}")
    public ResponseEntity<String> refuseDemande (@PathVariable int idDemande,
                                                @RequestParam("idValideur") int idValideur,
                                                @RequestParam("motif") String motif) {
    	
        if (!checkUserRole(idValideur, "valideur")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé. Rôle valideur requis.");
        }

        restTemplate.put(Url_DemandeMS + "/demandes/refuse/" + idDemande + "?idValideur=" + idValideur + "&motif=" + motif, null);
        return ResponseEntity.ok("Demande refusée.");
    }
    
    @PutMapping("/cancelDemande/{idDemande}")
    public ResponseEntity<String> cancelDemande (@PathVariable int idDemande,
                                                @RequestParam("idValideur") int idValideur) {
    	
        if (!checkUserRole(idValideur, "valideur")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé. Rôle valideur requis.");
        }

        restTemplate.put(Url_DemandeMS + "/demandes/cancel/" + idDemande, null);
        return ResponseEntity.ok("Demande annulée.");
    }

}
