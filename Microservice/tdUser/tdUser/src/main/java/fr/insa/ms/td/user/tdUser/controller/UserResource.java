package fr.insa.ms.td.user.tdUser.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ms.td.user.tdUser.model.User;

@RestController
public class UserResource {


	private static final ArrayList<User> users = new ArrayList<>();
		
	public UserResource() {
		users.add(new User(1, "Alice", "alice@example.com", "demandeur"));
		users.add(new User(2, "Bob", "bob@example.com", "benevole"));
		users.add(new User(3, "Pablo", "pablo@example.com", "valideur"));
	}

	
	// Retourne tous les utilisateurs
    @GetMapping("/users")
    public ArrayList<User> getAllUsers() {
        return users;
    }
	
    // Retourne le nombre total d'utilisateurs
    @GetMapping("/users/count")
    public int getUsersCount() {
        return users.size();
    }
    
    // Récupérer un utilisateur par ID
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
	
    // Ajouter un utilisateur via paramètres dans l’URL
    @PostMapping("/users/new")
    public User addUser(@RequestParam String name,
    					@RequestParam String email,
                        @RequestParam String role) {
        int id = users.size() + 1;
        User user = new User(id, name, email, role);
        users.add(user);
        return user;
    }
    
    // Modifier le nom de l'utilisateur
    @PutMapping("/users/update")
    public User updateUser(@RequestParam String param, @RequestParam int id, @RequestParam String newParam) {
        User user = getUserById(id);
        if (user != null) {
        	if (param.equals("name")) {
        		user.setName(newParam);
        	} else if (param.equals("email")) {
        		user.setEmail(newParam);
        	}
        }
        return user;
    }
	
    // Supprimer un utilisateur
    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        User userToRemove = getUserById(id);
        if (userToRemove != null) {
            users.remove(userToRemove);
            return "Utilisateur supprimé : " + userToRemove.getName();
        } else {
            return "Utilisateur non trouvé.";
        }
    }
	
	
}
