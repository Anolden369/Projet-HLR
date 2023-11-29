package sio.hlr.Entities;

public class Competences {
    private int id;
    private String matiere;
    private int idUser;
    private String sousMatiere;
    private int statut;

    public Competences(int id, String matiere, int idUser, String sousMatiere, int statut) {
        this.id = id;
        this.matiere = matiere;
        this.idUser = idUser;
        this.sousMatiere = sousMatiere;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public String getMatiere() {
        return matiere;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getSousMatiere() {
        return sousMatiere;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
