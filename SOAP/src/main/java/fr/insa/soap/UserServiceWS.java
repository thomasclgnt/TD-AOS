package fr.insa.soap;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.List;

@WebService(serviceName="UserService")
public class UserServiceWS {

    private UserService userService = new UserService();

    @WebMethod(operationName="addUser")
    public String addUser(
        @WebParam(name = "id") int id, 
        @WebParam(name = "name") String name, 
        @WebParam(name = "email") String email, 
        @WebParam(name = "role") String role
    ) {
        User user = new User(id, name, email, role);
        return userService.addUser(user);
    }


    @WebMethod(operationName="getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @WebMethod(operationName="getUserById")
    public User getUserById(@WebParam(name = "id") int id) {
        return userService.getUserById(id);
    }

    @WebMethod(operationName="deleteUser")
    public String deleteUser(@WebParam(name = "id") int id) {
        return userService.deleteUser(id);
    }
}
