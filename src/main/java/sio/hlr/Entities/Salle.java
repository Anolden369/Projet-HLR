package sio.hlr.Entities;

public class Salle {
    private int id;
    private String codeSalle;
    private int etage;

    public Salle(int id, String codeSalle, int etage) {
        this.id = id;
        this.codeSalle = codeSalle;
        this.etage = etage;
    }

    public int getId() {
        return id;
    }

    public String getCodeSalle() {
        return codeSalle;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }
}
