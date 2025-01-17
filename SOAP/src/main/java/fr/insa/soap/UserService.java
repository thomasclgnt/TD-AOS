package fr.insa.soap;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();

    // Ajouter un utilisateur
    public String addUser(User user) {
        users.add(user);
        return "Utilisateur ajouté avec succès";
    }

    // Récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return users;
    }

    // Récupérer un utilisateur par ID
    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null; // ou lever une exception
    }

    // Supprimer un utilisateur
    public String deleteUser(int id) {
        User userToDelete = getUserById(id);
        if (userToDelete != null) {
            users.remove(userToDelete);
            return "Utilisateur supprimé avec succès";
        }
        return "Utilisateur introuvable";
    }


}
