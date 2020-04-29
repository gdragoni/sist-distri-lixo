package modelo;

/**
 *
 * @author gdragoni 
 * 
 */
public class Lixeira {
    private int id;
    private float lat, lng;
    private String tipo, ambiente;
    
    public Lixeira(int id, float lat, float lng, String tipo, String ambiente) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.tipo = tipo;
        this.ambiente = ambiente;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the lat
     */
    public float getLat() {
        return lat;
    }

    /**
     * @return the lng
     */
    public float getLng() {
        return lng;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return the ambiente
     */
    public String getAmbiente() {
        return ambiente;
    }
}
