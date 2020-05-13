package mqtt_trash;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import mqtt_client.MqttCliente;
import mqtt_client.Trash;

public class Esvaziar {
	public final static void clearConsole() throws IOException {
	    final String os = System.getProperty("os.name");
	
	    if (os.contains("Windows"))
	    {
	        Runtime.getRuntime().exec("cls");
	    }
	    else
	    {
	        Runtime.getRuntime().exec("clear");
	    }
	}
	
	public void esvaziarComRfid(int trashId, MqttCliente cliente) throws IOException, MqttPersistenceException, MqttException {
		Scanner scan = new Scanner(System.in);

		System.out.print("Para esvaziar a lixeira, digite ou passe o RFID 💳: ");
		String rfid = scan.next();
		String desc = "Esvaziou a lixeira";
		Date data = new Date(System.currentTimeMillis());
		Time hora = new Time(System.currentTimeMillis());
		
		// Object Trash
		Trash trash = new Trash(rfid, trashId, desc, data, hora);
		
		// Request POST
		URL url = new URL("http://localhost:8080/restful/webresources/evento");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		String json = "{\"id_lixeira\":" + trash.getIdTrash() + ", \"descricao\":" + "\"" + trash.getDesc() + "\", \"id_usuario\":" + "\"" + trash.getRfid() + "\"}";

		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		
		OutputStream out = con.getOutputStream();
		out.write(json.getBytes());
		
		if (con.getResponseCode() >= 200 && con.getResponseCode() < 300) {
			System.out.println("Lixeira esvaziada com sucesso! \n");
			
			String topic = "topic/" + String.valueOf(trashId);
			cliente.publicar(topic, trash);
			clearConsole();
		} else {
			System.out.println("Falha ao esvaziar a lixeira. \n");
		}
	}
}
