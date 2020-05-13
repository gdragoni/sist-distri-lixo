package mqtt_client;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Capacity implements Serializable {
	private int idTrash;
	private double capacity;
	private Date data;
	private Time hora;
	
	public Capacity(int idTrash,double capacity, Date data, Time hora) {
		this.idTrash = idTrash;
		this.capacity = capacity;
		this.data = data;
		this.hora = hora;
	}

	public int getIdTrash() {
		return idTrash;
	}
	public double getCapacity() {
		return capacity;
	}
	public Date getData() {
		return data;
	}
	public Time getHora() {
		return hora;
	}
	
}
