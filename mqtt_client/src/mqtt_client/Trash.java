package mqtt_client;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Trash implements Serializable {
	private String rfid;
	private float valor;
	private Date data;
	private Time hora;
	
	public Trash(String rfid, float valor, Date data, Time hora) {
		this.rfid = rfid;
		this.valor = valor;
		this.data = data;
		this.hora = hora;
	}

	public String getRfid() {
		return rfid;
	}

	public float getValor() {
		return valor;
	}

	public Date getData() {
		return data;
	}

	public Time getHora() {
		return hora;
	}

}
