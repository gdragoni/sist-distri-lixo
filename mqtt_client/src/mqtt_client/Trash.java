package mqtt_client;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Trash implements Serializable {
	private String rfid;
	private int idLixeira;
	private String desc;
	private Date data;
	private Time hora;
	
	public Trash(String rfid, int idLixeira,String desc, Date data, Time hora) {
		this.rfid = rfid;
		this.idLixeira = idLixeira;
		this.desc = desc;
		this.data = data;
		this.hora = hora;
	}

	public String getRfid() {
		return rfid;
	}
	
	public int getIdLixeira() {
		return idLixeira;
	}

	public String getDesc() {
		return desc;
	}

	public Date getData() {
		return data;
	}

	public Time getHora() {
		return hora;
	}

}
