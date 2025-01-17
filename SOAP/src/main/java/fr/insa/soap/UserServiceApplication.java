package fr.insa.soap;

import java.net.MalformedURLException;	
import javax.xml.ws.Endpoint;

public class UserServiceApplication {
	
	public static String host="localhost";
	public static short port=8090;
	
	public void demarrerService() {
		String url="http://"+host+":"+port+"/";
		Endpoint.publish(url, new UserServiceWS());
	}
	
    public static void main(String[] args) throws MalformedURLException {
    	new UserServiceApplication().demarrerService();
    	System.out.println("Le service a démarré");
    }

}
