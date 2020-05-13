package mqtt_client;

import java.io.IOException;
import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {

	public static void main(String[] args) throws MqttException, IOException {
		Scanner scan = new Scanner(System.in);
		Request req = new Request();
		
		// Verify user
		Boolean pass1 = false;
		String clientId = "";
		
		while (!pass1) {
			System.out.print("Passe ou digite seu RFID(id) 🔑 : ");
			clientId = scan.next();
			
			pass1 = req.verifyExistUser(clientId);
		}	
		
		// Verify trash
		Boolean pass2 = false;
		String trashId = "";
		
		while (!pass2) {
			System.out.print("Digite o hash(id) da lixeira que deseja observar 🗑: ");
			trashId = scan.next();
			
			pass2 = req.verifyExistTrash(trashId);
		}
		
		String topic =  "topic/" + trashId;
		
		MqttCliente cliente = new MqttCliente("tcp://test.mosquitto.org:1883", clientId);
		cliente.setCallback();
		
		cliente.inscreverSe(topic);
		System.out.println("Conectado com sucesso! ✅");
		System.out.println("Aguardando eventos da lixeira 🕙 .... \n");
	}

}
