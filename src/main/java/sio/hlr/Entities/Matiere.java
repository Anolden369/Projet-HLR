package sio.hlr.Entities;

public class Matiere {
    private int id;
    private String designation;
    private int code;
    private String sousMatiere;

    public int getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public int getCode() {
        return code;
    }

    public String getSousMatiere() {
        return sousMatiere;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSousMatiere(String sousMatiere) {
        this.sousMatiere = sousMatiere;
    }
}