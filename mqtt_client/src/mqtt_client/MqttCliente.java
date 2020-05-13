package mqtt_client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.TypeVariable;
import java.text.SimpleDateFormat;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;


public class MqttCliente implements MqttCallback{
	private MqttClient client;
	
	public MqttCliente(String uri, String clienteId) throws MqttException {
		// Conexão com o broker
		client = new MqttClient(uri, clienteId);
		client.connect();
	}
	
	public void inscreverSe(String topico) throws MqttException {
		client.subscribe(topico);
	}
	
	public void setCallback() {
		client.setCallback(this);
		
	}
	
	public void publicar(String topico, Trash trash) throws IOException, MqttPersistenceException, MqttException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		oos.writeObject(trash);
		MqttMessage mensagemMqtt = new MqttMessage(bos.toByteArray());
		
		client.publish(topico, mensagemMqtt);
	}
	
	public void publicarCapacity(String topico, Capacity cap) throws IOException, MqttPersistenceException, MqttException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		oos.writeObject(cap);
		MqttMessage mensagemMqtt = new MqttMessage(bos.toByteArray());
		
		client.publish(topico, mensagemMqtt);
	}
	
	public void publicarCover(String topico, Cover cover) throws IOException, MqttPersistenceException, MqttException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		oos.writeObject(cover);
		MqttMessage mensagemMqtt = new MqttMessage(bos.toByteArray());
		
		client.publish(topico, mensagemMqtt);
	}
	

	@Override
	public void connectionLost(Throwable arg0) {
		System.out.println("O Broker caiu...");
		System.out.println(arg0.getMessage());
	}


	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		System.out.println("Id da mensagem: " + arg0.getMessageId());
		System.out.println("Cliente que enviou: " + arg0.getClient().getClientId());
		System.out.println("URI do broker: " + arg0.getClient().getServerURI());		
	}


	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		ByteArrayInputStream bis = new ByteArrayInputStream(arg1.getPayload());
		ObjectInputStream ois = new ObjectInputStream(bis);
		
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		
		Object obj = ois.readObject();
	
		if (obj instanceof Trash) {
			Trash trash = (Trash) obj;
			
			// OUTPUT
			System.out.println("------------------------");
			System.out.println("|       ESVAZIAR ✅️     |");
			System.out.println("-------------------------------------------------");
			System.out.println("| HASH(id) LIXEIRA      | " + trash.getIdTrash());
			System.out.println("-------------------------------------------------");
			System.out.println("RFID(id) DO FUNCIONARIO | " + trash.getRfid());
			System.out.println("-------------------------------------------------");
			System.out.println("DATA                    | " + formatador.format(trash.getData()) + " as " + trash.getHora());
			System.out.println("-------------------------------------------------\n");
		
		}
		
		if (obj instanceof Capacity) {
			Capacity capacity = (Capacity) obj;
			
			// OUTPUT			
			System.out.println("------------------------");
			System.out.println("|      CAPACIDADE ⏳   |");
			System.out.println("-------------------------------------------------");
			System.out.println("| HASH(id) LIXEIRA     | " + capacity.getIdTrash() );
			System.out.println("-------------------------------------------------");
			System.out.println("| CAPACIDADE           | " + capacity.getCapacity());
			System.out.println("-------------------------------------------------");
			System.out.println("| DATA                 | " + formatador.format(capacity.getData()) + " as " + capacity.getHora());
			System.out.println("-------------------------------------------------\n");
		}
		
		if (obj instanceof Cover) {
			Cover cover = (Cover) obj;
			
			String action;
			
			if (cover.getValue() == false) {
				action = "Fechar";
			} else {
				action = "Abrir";
			}
			
			// OUTPUT			
			System.out.println("------------------------");
			System.out.println("|    ACAO DA TAMPA 🚮  |");
			System.out.println("-------------------------------------------------");
			System.out.println("| HASH(id) LIXEIRA     | " + cover.getIdTrash() );
			System.out.println("-------------------------------------------------");
			System.out.println("| ACAO           | " + action );
			System.out.println("-------------------------------------------------");
			System.out.println("| DATA                 | " + formatador.format(cover.getData()) + " as " + cover.getHora());
			System.out.println("-------------------------------------------------\n");
		}
	}	
}
