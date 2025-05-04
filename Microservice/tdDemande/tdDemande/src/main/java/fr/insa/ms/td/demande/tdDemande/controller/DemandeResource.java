package fr.insa.ms.td.demande.tdDemande.controller;

import fr.insa.ms.td.demande.tdDemande.model.Demande;
import fr.insa.ms.td.demande.tdDemande.model.Statut;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/demandes")
public class DemandeResource {

    private ArrayList<Demande> demandes = new ArrayList<>();
    
    public DemandeResource() {
    	demandes.add(new Demande(1, "Mission 1", 1, Statut.CREEE));
    	demandes.add(new Demande(2, "Mission 2", 1, Statut.CREEE));
	}

    // Obtenir toutes les demandes
    @GetMapping
    public ArrayList<Demande> getAllDemandes() {
        return demandes;
    }
    
    // Obtenir les demandes par statut
    @GetMapping("/statut/{statut}")
    public ArrayList<Demande> getDemandebyStatus(@PathVariable String in_statut) {
    	
    	Statut statut = Statut.fromString(in_statut);
    	ArrayList<Demande> resultat = new ArrayList<Demande>();
    	
    	for (Demande demande : demandes) {
    		
            if (demande.getStatut().equals(statut)) {
                resultat.add(demande);
            }
        }
        return resultat;
    	
    }

    // Rechercher une demande par ID
    @GetMapping("/{id}")
    public Demande getDemandeById(@PathVariable int id) {
    	for (Demande d : demandes) {
            if (d.getId() == id) {
                return d;
            }
    	}
    	return null;
    }

    // Créer une demande
    // On utilise des RequestParam donc avec les paramètres directement dans l'url sous la forme : /demandes/new?description=Mission 1&idDemandeur=1
    @PostMapping("/new")
    public Demande createDemande(@RequestParam String description, @RequestParam int idDemandeur) {
        int id = demandes.size() + 1;
        Demande d = new Demande(id, description, idDemandeur, Statut.CREEE);
        demandes.add(d);
        return d;
    }

    // Valider une demande
    // PUT sous la forme /validate/{id}?idValideur=2
    @PutMapping("/validate/{id}")
    public Demande validateDemande(@PathVariable int id, @RequestParam int idValideur) {
        for (Demande d : demandes) {
            if (d.getId() == id && d.getStatut() == Statut.CREEE) {
                d.setIdValideur(idValideur);
                d.setStatut(Statut.VALIDEE);
                return d;
            }
        }
        return null;
    }

    // Refuser une demande
    @PutMapping("/refuse/{id}")
    public Demande refuseDemande(@PathVariable int id, @RequestParam int idValideur, @RequestParam String motif) {
        for (Demande d : demandes) {
            if (d.getId() == id && d.getStatut() == Statut.CREEE) {
                d.setIdValideur(idValideur);
                d.setMotifRefus(motif);
                d.setStatut(Statut.REFUSEE);
                return d;
            }
        }
        return null;
    }

    // Annuler une demande
    @PutMapping("/cancel/{id}")
    public Demande cancelDemande(@PathVariable int id) {
        for (Demande d : demandes) {
            if (d.getId() == id) {
                d.setStatut(Statut.ANNULEE);
                return d;
            }
        }
        return null;
    }

    // Assigner un bénévole à une demande
    @PutMapping("/assignWorker/{id}")
    public Demande assignWorker(@PathVariable int id, @RequestParam int idBenevole) {
        for (Demande d : demandes) {
            if (d.getId() == id && d.getStatut() == Statut.VALIDEE) {
                d.setIdBenevole(idBenevole);
                d.setStatut(Statut.PRISE_EN_CHARGE);
                return d;
            }
        }
        return null;
    }
    
}