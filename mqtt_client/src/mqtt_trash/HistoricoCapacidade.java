package mqtt_trash;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.TimerTask;

import org.eclipse.paho.client.mqttv3.MqttException;
import mqtt_client.Capacity;
import mqtt_client.MqttCliente;

public class HistoricoCapacidade extends TimerTask {
	private int trashId;
	private double capacity;
	private MqttCliente client;
	
	HistoricoCapacidade(int trashId, double capacity, MqttCliente client) throws IOException {
		this.trashId = trashId;
		this.capacity = capacity;
		this.client = client;
	}

	@Override
	public void run() {
		URL url;
		
		if (capacity < 1) {
			capacity = capacity + 0.1;
		} else {
			capacity = 0;
		}

		try {
			// Request POST
			url = new URL("http://localhost:8080/restful/webresources/historicoCapacidade");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			String json = "{\"id_lixeira\":" + trashId + ", \"capacidade\":" + capacity + "}";
			
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			
			OutputStream out = con.getOutputStream();
			out.write(json.getBytes());
			
			// Object Capacity
			Date data = new Date(System.currentTimeMillis());
			Time hora = new Time(System.currentTimeMillis());

			Capacity cap = new Capacity(trashId, capacity, data, hora);
			
			
			
			if (con.getResponseCode() >= 200 && con.getResponseCode() < 300) {
//				DEBUG - Habilitar para testar se está enviando corretamente
//				System.out.print("\nCapacidade enviada com sucesso !");
				
				String topic = "topic/" + String.valueOf(trashId);
				client.publicarCapacity(topic, cap);
			} else {
				System.out.print("\nFalha ao enviar a capacidade.");
			}
		} catch (IOException | MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
