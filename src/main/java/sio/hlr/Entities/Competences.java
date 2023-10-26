package sio.hlr.Entities;

public class Competences {
    private int id;
    private int idMatiere;
    private int idUser;
    private int sousMatiere;
    private int statut;

    public Competences(int id, int idMatiere, int idUser, int sousMatiere, int statut) {
        this.id = id;
        this.idMatiere = idMatiere;
        this.idUser = idUser;
        this.sousMatiere = sousMatiere;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getSousMatiere() {
        return sousMatiere;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
