package fr.insa.ms.td.user.tdUser.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserClientConfigResource {

	@Value("${db.uri}")
	private String dbUri;
	
	@Value("${db.name}")
	private String dbName;
	
	@Value("${db.login}")
	private String dbLogin;
	
	@Value("${db.pwd}")
	private String dbPwd;

	@GetMapping("/dbUri")
	public String getDbUri() {
		return dbUri;
	}

	public void setDbUri(String dbUri) {
		this.dbUri = dbUri;
	}

	@GetMapping("/dbName")
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	@GetMapping("/dbLog")
	public String getDbLogin() {
		return dbLogin;
	}

	public void setDbLogin(String dbLogin) {
		this.dbLogin = dbLogin;
	}

	@GetMapping("/dbPwd")
	public String getDbPwd() {
		return dbPwd;
	}

	public void setDbPwd(String dbPwd) {
		this.dbPwd = dbPwd;
	}
}
