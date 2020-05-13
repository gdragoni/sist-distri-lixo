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
import mqtt_client.Cover;
import mqtt_client.MqttCliente;

public class HistoricoTampa extends TimerTask {
	private int trashId;
	private boolean value;
	private MqttCliente client;
	
	HistoricoTampa(int trashId, boolean value, MqttCliente client) throws IOException {
		this.trashId = trashId;
		this.value = value;
		this.client = client;
	}

	@Override
	public void run() {
		URL url;
		

		value = !value;

		try {
			// Request POST
			url = new URL("http://localhost:8080/restful/webresources/historicoTampa");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			String json = "{\"id_lixeira\":" + trashId + ", \"valor\":" + value + "}";
			
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			
			OutputStream out = con.getOutputStream();
			out.write(json.getBytes());
			
			// Object Capacity
			Date data = new Date(System.currentTimeMillis());
			Time hora = new Time(System.currentTimeMillis());

			Cover cover = new Cover(trashId, value, data, hora);
			
			
			
			if (con.getResponseCode() >= 200 && con.getResponseCode() < 300) {
//				DEBUG - Habilitar para testar se está enviando corretamente			
//				if (value == false) {
//					System.out.print("\nTampa fechada com sucesso !");
//				} else {
//					System.out.print("\nTampa aberta com sucesso !");
//				}
				
				String topic = "topic/" + String.valueOf(trashId);
				client.publicarCover(topic, cover);
			} else {
				System.out.print("\nFalha ao enviar a acao da tampa.");
			}
		} catch (IOException | MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
