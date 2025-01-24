package fr.insa.soap;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.List;

@WebService(serviceName = "DemandeService")
public class DemandeServiceWS {

	private DemandeService demandeService = new DemandeService();

    @WebMethod(operationName = "creerDemande")
    public String creerDemande(
        @WebParam(name = "id") int id,
        @WebParam(name = "description") String description,
        @WebParam(name = "demandeurId") int demandeurId
    ) {
        // Trouver un moyen d'appeler le WS userService pour utiliser la méthode getUserbyID
//        User demandeur = new User(demandeurId, "NomDemandeur", "email@example.com"); 
        Demande demande = demandeService.creerDemande(id, description, demandeur);
        return "Demande créée avec succès. ID: " + demande.getId();
    }

    @WebMethod(operationName = "assignerBenevole")
    public String assignerBenevole(
        @WebParam(name = "idDemande") int idDemande,
        @WebParam(name = "benevoleId") int benevoleId
    ) {
        // Trouver un moyen d'appeler le WS userService pour utiliser la méthode getUserbyID
//        User benevole = new User(benevoleId, "NomBenevole", "email@example.com");
        boolean success = demandeService.assignerBenevole(idDemande, benevole);
        return success ? "benevole assigné avec succès." : "Échec de l'assignation du benevole.";
    }

    @WebMethod(operationName = "validerDemande")
    public String validerDemande(
        @WebParam(name = "idDemande") int idDemande,
        @WebParam(name = "valideurId") int valideurId
    ) {
        // Trouver un moyen d'appeler le WS userService pour utiliser la méthode getUserbyID
//        User valideur = new User(valideurId, "NomValideur", "email@example.com");
        boolean success = demandeService.validerDemande(idDemande, valideur);
        return success ? "Demande validée avec succès." : "Échec de la validation de la demande.";
    }

    @WebMethod(operationName = "refuserDemande")
    public String refuserDemande(
        @WebParam(name = "idDemande") int idDemande,
        @WebParam(name = "valideurId") int valideurId,
        @WebParam(name = "motifRefus") String motifRefus
    ) {
        // Trouver un moyen d'appeler le WS userService pour utiliser la méthode getUserbyID
//        User valideur = new User(valideurId, "NomValideur", "email@example.com");
        boolean success = demandeService.refuserDemande(idDemande, valideur, motifRefus);
        return success ? "Demande refusée avec succès." : "Échec du refus de la demande.";
    }

    @WebMethod(operationName = "annulerDemande")
    public String annulerDemande(
        @WebParam(name = "idDemande") int idDemande
    ) {
        boolean success = demandeService.annulerDemande(idDemande);
        return success ? "Demande annulée avec succès." : "Échec de l'annulation de la demande.";
    }

    @WebMethod(operationName = "getAllDemandes")
    public List<Demande> getAllDemandes() {
        return demandeService.getDemandes();
    }

    @WebMethod(operationName = "rechercherDemandeParId")
    public Demande rechercherDemandeParId(
        @WebParam(name = "idDemande") int idDemande
    ) {
        return demandeService.rechercherDemandeParId(idDemande);
    }
	
}
