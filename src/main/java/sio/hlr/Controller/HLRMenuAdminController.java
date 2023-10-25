package sio.hlr.Controller;

import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HLRMenuAdminController implements Initializable {


    @javafx.fxml.FXML
    private Button btnCreerMatiere;
    @javafx.fxml.FXML
    private Button btnModifMatiere;
    @javafx.fxml.FXML
    private Button btnCreerSalles;
    @javafx.fxml.FXML
    private Button btnModifSalles;
    @javafx.fxml.FXML
    private Button btnVisuSoutiens;
    @javafx.fxml.FXML
    private Button btnStatistiquesAdmin;
    @javafx.fxml.FXML
    private TextField txtCreerNomMatiere;
    @javafx.fxml.FXML
    private Button btnValiderCreerMatiere;
    @javafx.fxml.FXML
    private TextField txtCreerNomSousMatiere;
    @javafx.fxml.FXML
    private TextField txtModifNomMatiere;
    @javafx.fxml.FXML
    private TextArea txtModifNomSousMatiere;
    @javafx.fxml.FXML
    private Button btnValiderModifMatiere;
    @javafx.fxml.FXML
    private ComboBox cboNomMatiere;
    @javafx.fxml.FXML
    private TextField txtCreerNumSalle;
    @javafx.fxml.FXML
    private TextField txtCreerEtage;
    @javafx.fxml.FXML
    private Button btnValiderCreerSalle;
    @javafx.fxml.FXML
    private TextField txtModfNumSalle;
    @javafx.fxml.FXML
    private TextField txtModifEtage;
    @javafx.fxml.FXML
    private Button btnValiderModifSalles;
    @javafx.fxml.FXML
    private TreeView tvSoutiensAdmin;
    @javafx.fxml.FXML
    private AnchorPane apCreerMatieres;
    @javafx.fxml.FXML
    private AnchorPane apModifMatiere;
    @javafx.fxml.FXML
    private AnchorPane apCreerSalles;
    @javafx.fxml.FXML
    private AnchorPane apModifSalles;
    @javafx.fxml.FXML
    private AnchorPane apVisuSoutients;
    @javafx.fxml.FXML
    private AnchorPane apStatistiques;

    @javafx.fxml.FXML
    public void btnCreerMatiereClicked(Event event) {
        apCreerMatieres.toFront();
    }

    @javafx.fxml.FXML
    public void btnModifMatiereClicked(Event event) {
        apModifMatiere.toFront();
    }

    @javafx.fxml.FXML
    public void btnCreerSallesClicked(Event event) {
        apCreerSalles.toFront();
    }

    @javafx.fxml.FXML
    public void btnModifSallesClicked(Event event) {
        apModifSalles.toFront();
    }

    @javafx.fxml.FXML
    public void btnVisuSoutiensClicked(Event event) {
        apVisuSoutients.toFront();
    }

    @javafx.fxml.FXML
    public void btnStatistiquesAdminClicked(Event event) {
        apStatistiques.toFront();
    }

    @javafx.fxml.FXML
    public void btnValiderCreerMatiere(Event event) {

    }

    @javafx.fxml.FXML
    public void btnValiderModifMatiereClicked(Event event) {

    }

    @javafx.fxml.FXML
    public void btnValiderCreerSalleClicked(Event event) {

    }

    @javafx.fxml.FXML
    public void btnValiderModifSallesClicked(Event event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apCreerMatieres.toFront();
    }
}
