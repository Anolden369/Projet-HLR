package sio.hlr.Entities;

import javafx.scene.control.CheckBox;

public class Matiere {
    private int id;
    private String designation;
    private String sousMatiere;
    private CheckBox uneSelection;
    private Double pourcentageDemandes;

    public Matiere() {
        this.uneSelection = new CheckBox();
    }

    public Matiere(String designation) {
        this.designation = designation;
        this.uneSelection = new CheckBox();
    }

    public Matiere(int id, String designation, String sousMatiere) {
        this.id = id;
        this.designation = designation;
        this.sousMatiere = sousMatiere;
        this.uneSelection = new CheckBox();
    }

    public Matiere(String designation, Double pourcentageDemandes) {
        this.designation = designation;
        this.pourcentageDemandes = pourcentageDemandes;
    }

    public Double getPourcentageDemandes() {
        return pourcentageDemandes;
    }

    public int getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public CheckBox getUneSelection() {
        return uneSelection;
    }

    public void setUneSelection(CheckBox uneSelection) {
        this.uneSelection = uneSelection;
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