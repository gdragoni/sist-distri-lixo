package mqtt_client;

import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {

	public static void main(String[] args) throws MqttException {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Digite seu ID: ");
		String clientId = scan.next();
		System.out.print("Digite topico que deseja se inscrever: ");
		String topico = scan.next();
		
		MqttCliente cliente = new MqttCliente("tcp://test.mosquitto.org:1883", clientId);
		cliente.setCallback();
		cliente.inscreverSe(topico);
	}

}
