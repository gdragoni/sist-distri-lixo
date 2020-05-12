package mqtt_trash;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;
import java.util.Timer;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import mqtt_client.Trash;

public class Main {

	public static void main(String[] args) throws MqttException, IOException {
		MqttClient client = new MqttClient("tcp://test.mosquitto.org:1883", "trash1");
		client.connect();
		
		int idLixeira = 12345;
		
		Timer t = new Timer();
		
		
		while (true) {
			for (int i = 0; i < 6; i++) {
				HistoricoCapacidade historico = new HistoricoCapacidade();
				
//				t.schedule(historico.publicarHistorico(idLixeira, i + 1.66665), 600000); FAltou implementar o timer
			}
			
			
			Scanner scan = new Scanner(System.in);

			System.out.println("Passe o RFID...");
			String rfid = scan.next();
			System.out.print("\n");
			String desc = "Esvaziou a lixeira";
			Date data = new Date(System.currentTimeMillis());
			Time hora = new Time(System.currentTimeMillis());
			
			Trash trash = new Trash(rfid, idLixeira, desc, data, hora);
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			
			oos.writeObject(trash);
			MqttMessage mensagemMqtt = new MqttMessage(bos.toByteArray());
			
			client.publish("topic/trash1", mensagemMqtt);
		}
	}

}
