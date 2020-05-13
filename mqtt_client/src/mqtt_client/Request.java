package mqtt_client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Request {
	URL url;

	public Boolean verifyExistUser(String clientId) throws IOException {
		url = new URL("http://localhost:8080/restful/webresources/funcionario/detail/" + clientId);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");
		
		if (con.getResponseCode() != 200) {
			return false;
		}
		
		return true;
	}
	
	public Boolean verifyExistTrash(String trashId) throws IOException {
		url = new URL("http://localhost:8080/restful/webresources/lixeira/detail/" + trashId);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");
		
		if (con.getResponseCode() != 200) {
			return false;
		}
		
		return true;
	}
}
