package sio.hlr.Entities;

public class Demandes {
    private int id;
    private int dateUpdated;
    private int dateFinDemande;
    private String sousMatiere;
    private int idUser;
    private int idMatiere;
    private int status;

    public Demandes(int id, int dateUpdated, int dateFinDemande, String sousMatiere, int idUser, int idMatiere, int status) {
        this.id = id;
        this.dateUpdated = dateUpdated;
        this.dateFinDemande = dateFinDemande;
        this.sousMatiere = sousMatiere;
        this.idUser = idUser;
        this.idMatiere = idMatiere;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getDateUpdated() {
        return dateUpdated;
    }

    public int getDateFinDemande() {
        return dateFinDemande;
    }

    public String getSousMatiere() {
        return sousMatiere;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
