package sio.hlr.Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sio.hlr.HLRApplication;

import java.io.IOException;
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
    private Button btnValiderModifSalles;
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
    private ComboBox cboNomSalle;
    @javafx.fxml.FXML
    private ComboBox cboModifNumSalle;
    @javafx.fxml.FXML
    private ComboBox cboModifEtageSalle;
    @javafx.fxml.FXML
    private TableView tvSoutien;
    @javafx.fxml.FXML
    private TableColumn tcSoutienDemandes;
    @javafx.fxml.FXML
    private TableColumn tcSoutienAides;
    @javafx.fxml.FXML
    private TableColumn tcSoutienMatiere;
    @javafx.fxml.FXML
    private TableColumn tcSoutienSMatiere;
    @javafx.fxml.FXML
    private TableColumn tcSoutienDescription;
    @javafx.fxml.FXML
    private TableColumn tcSoutienStatut;
    @javafx.fxml.FXML
    private TableColumn tcSoutienDateLimite;
    @javafx.fxml.FXML
    private Button btnDeconnexion;

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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (txtCreerNomMatiere.getText().isEmpty())
        {
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("");
            alert.setContentText("Veuillez saisir le nom de la matière");
            alert.showAndWait();
        }
        else if (txtCreerNomSousMatiere.getText().isEmpty())
        {
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("");
            alert.setContentText("Veuillez saisir les sous-matières");
            alert.showAndWait();
        }
        else
        {
            // a compléter
        }
    }

    @javafx.fxml.FXML
    public void btnValiderModifMatiereClicked(Event event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (cboNomMatiere.getSelectionModel().getSelectedItem()==null)
        {
            alert.setTitle("Erreur de choix");
            alert.setHeaderText("");
            alert.setContentText("Veuillez choisir une matière");
            alert.showAndWait();
        }
        else if (txtModifNomMatiere.getText().isEmpty())
        {
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("");
            alert.setContentText("Veuillez saisir le nom de la matière");
            alert.showAndWait();
        }
        else if (txtModifNomSousMatiere.getText().isEmpty())
        {
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("");
            alert.setContentText("Veuillez saisir les sous-matières");
            alert.showAndWait();
        }
        else
        {
            // a compléter
        }

    }

    @javafx.fxml.FXML
    public void btnValiderCreerSalleClicked(Event event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (txtCreerNumSalle.getText().isEmpty())
        {
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("");
            alert.setContentText("Veuillez saisir le numéro de la salle");
            alert.showAndWait();
        }
        else if (txtCreerEtage.getText().isEmpty())
        {
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("");
            alert.setContentText("Veuillez saisir l'étage");
            alert.showAndWait();
        }
        else
        {
            // a compléter
        }
    }

    @javafx.fxml.FXML
    public void btnValiderModifSallesClicked(Event event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (cboModifNumSalle.getValue()==null)
        {
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("");
            alert.setContentText("Veuillez saisir le numéro de la salle");
            alert.showAndWait();
        }
        else if (cboModifEtageSalle.getValue()==null)
        {
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("");
            alert.setContentText("Veuillez saisir l'étage");
            alert.showAndWait();
        }
        else
        {
            // a compléter
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apCreerMatieres.toFront();
    }

    @javafx.fxml.FXML
    public void cboNomSalleClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void cboModifNumSalleClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void cboModifEtageSalleClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void tvSoutienClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void deconnexion(ActionEvent actionEvent) throws IOException {
        HLRApplication.LoginScene();
    }
}
