package sio.hlr.Entities;

import java.util.Date;

public class Demandes {
    private int id;
    private Date dateUpdated;
    private Date dateFinDemande;
    private String matiere;
    private String sousMatiere;
    private int idUser;
    private int idMatiere;
    private int status;

    public Demandes(int id, Date dateUpdated, Date dateFinDemande,String matiere, String sousMatiere, int idUser, int status) {
        this.id = id;
        this.dateUpdated = dateUpdated;
        this.dateFinDemande = dateFinDemande;
        this.sousMatiere = sousMatiere;
        this.idUser = idUser;
        this.matiere = matiere;
        this.status = status;
    }

    public Demandes(int id, Date dateFinDemande, String matiere, String sousMatiere, int status) {
        this.id = id;
        this.dateFinDemande = dateFinDemande;
        this.matiere = matiere;
        this.sousMatiere = sousMatiere;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public Date getDateFinDemande() {
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

    public String getMatiere() {
        return matiere;
    }
    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }
}
