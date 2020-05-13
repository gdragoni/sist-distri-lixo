package mqtt_client;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Cover implements Serializable {
	private int idTrash;
	private boolean value;
	private Date data;
	private Time hora;
	
	public Cover(int idTrash, boolean value, Date data, Time hora) {
		this.idTrash = idTrash;
		this.value = value;
		this.data = data;
		this.hora = hora;
	}

	public int getIdTrash() {
		return idTrash;
	}
	public boolean getValue() {
		return value;
	}
	public Date getData() {
		return data;
	}
	public Time getHora() {
		return hora;
	}
	
}
