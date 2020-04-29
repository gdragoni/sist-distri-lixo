package mqtt_client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		
		Trash trash = (Trash) ois.readObject();
		
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("\nRFID: " + trash.getRfid() + "\nPeso: " + trash.getValor() + "\nData: " + formatador.format(trash.getData()) + " as " + trash.getHora());		
	}	
}
