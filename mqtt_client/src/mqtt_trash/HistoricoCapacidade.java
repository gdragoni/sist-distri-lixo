package mqtt_trash;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.TimerTask;

public class HistoricoCapacidade extends TimerTask {
	public void publicarHistorico(int idLixeira, double capacidade) throws IOException {
		URL url = new URL("http://localhost:8080/restful/webresources/evento");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		Date data = new Date(System.currentTimeMillis());
		Time hora = new Time(System.currentTimeMillis());		
		
		
		String json = "{\"id_lixeira\":" + idLixeira + ", \"capacidade\":" + capacidade + ", \"data\":" + "\"" + data + ", \"hora\":" + "\"" + hora + "\"}";
		
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		
		OutputStream out = con.getOutputStream();
		out.write(json.getBytes());
		
		if (con.getResponseCode() == 400) {
			System.out.println("Requisição salva no banco com sucesso !!");
		}
		
		System.out.println(json);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
