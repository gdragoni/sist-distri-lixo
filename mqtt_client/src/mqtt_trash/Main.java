package mqtt_trash;

import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import mqtt_client.MqttCliente;

public class Main {
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

	public static void main(String[] args) throws MqttException, IOException {
		Scanner scan = new Scanner(System.in);
		
		// Register in topic
		System.out.println("-----------------------");
		System.out.println("Cadastro da Lixeira 📝 ");
		System.out.println("-----------------------");
		System.out.print("Digite o hash(id) da lixeira 🗑: ");
		String trashId = scan.next();
		System.out.print("\n");
		clearConsole();
		
		
		// Output hash trash
		System.out.println("--------------------------------------------------------");
		System.out.println(" HASH(id) da lixeira 🔑 -> " + trashId);
		System.out.println("-------------------------------------------------------- \n");
		
		MqttCliente cliente = new MqttCliente("tcp://test.mosquitto.org:1883", trashId);
		
		Esvaziar esvaziar = new Esvaziar();
		Timer t1 = new Timer();
		Timer t2 = new Timer();
		
		// Simulation send action of the trash cover
    	// Sensor pode enviar a acao da tampa aqui substituindo o 0 "Segundo parametro" da funcao HistoricoTampa(trashId, valor sensor, cliente)
		t1.scheduleAtFixedRate(new HistoricoTampa(Integer.valueOf(trashId), false, cliente), 0, 120000); // 2min
		
		// Simulation send capacity of the trash
		// Sensor pode enviar a capacidade aqui substituindo o 0 "Segundo parametro" da funcao HistoricoCapacidade(trashId, valor sensor, cliente)
		t2.scheduleAtFixedRate(new HistoricoCapacidade(Integer.valueOf(trashId), 0, cliente), 0, 600000); // 10 min
		
		while (true) {		
			esvaziar.esvaziarComRfid(Integer.valueOf(trashId), cliente);		
		}
	}
}
