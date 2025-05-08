package fr.insa.ms.td.user.tdUser.model;

public class User {
	
	 private int id;
	    private String name;
	    private String email;
	    private Role role; // Roles: "demandeur", "benevole", "valideur"
	   	  
	    // Constructeurs
	    public User(int id, String name, String email, Role role) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.role = role;
	    }
	    
	    public User() {
	    	
	    }
	    
	    public User(int id, String name, String email, String role) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.role = Role.fromString(role);
	    }

	    // Getters et Setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public Role getRole() {
	        return role;
	    }

	    public void setRole(Role role) {
	        this.role = role;
	    }
	    
	    public void setRoleFromString(String roleName) {
	        this.role = Role.fromString(roleName);
	    }


}
