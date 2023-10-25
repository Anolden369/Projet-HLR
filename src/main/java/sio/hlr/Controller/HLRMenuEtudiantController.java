package sio.hlr.Controller;

import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HLRMenuEtudiantController implements Initializable{



    @javafx.fxml.FXML
    private Button btnMesCompetences;
    @javafx.fxml.FXML
    private Button btnLesDemandes;
    @javafx.fxml.FXML
    private Button btnMesStatistiques;
    @javafx.fxml.FXML
    private TreeView tvMesDemandes;
    @javafx.fxml.FXML
    private Button btnModifierDemande;
    @javafx.fxml.FXML
    private Button btnCreerDemande;
    @javafx.fxml.FXML
    private AnchorPane apMesDemandes;
    @javafx.fxml.FXML
    private Button btnMenuModifierDemande;
    @javafx.fxml.FXML
    private Button btnMenuCreerDemande;
    @javafx.fxml.FXML
    private AnchorPane apCreerDemande;
    @javafx.fxml.FXML
    private ComboBox cboCreerChoixMatiere;
    @javafx.fxml.FXML
    private ComboBox cboCreerChoixSousMatiere;
    @javafx.fxml.FXML
    private AnchorPane apModifierDemande;
    @javafx.fxml.FXML
    private ComboBox cboModifierDemande;
    @javafx.fxml.FXML
    private ComboBox cboModiferChoixMatiere;
    @javafx.fxml.FXML
    private ChoiceBox CceModifierSousMatiere;
    @javafx.fxml.FXML
    private DatePicker DpModifierDateActuelle;
    @javafx.fxml.FXML
    private AnchorPane apMesCompetences;
    @javafx.fxml.FXML
    private TreeView tvMesCompetences;
    @javafx.fxml.FXML
    private Button btnMenuModifierCompetence;
    @javafx.fxml.FXML
    private Button btnMenuCreerCompetence;
    @javafx.fxml.FXML
    private AnchorPane apCreerCompetence;
    @javafx.fxml.FXML
    private ComboBox cboCreerChoixMatiereCompetence;
    @javafx.fxml.FXML
    private ComboBox cboCreerChoixSousMatiereCompetence;
    @javafx.fxml.FXML
    private Button btnCreerCompetence;
    @javafx.fxml.FXML
    private AnchorPane apModifierCompetence;
    @javafx.fxml.FXML
    private ComboBox cboModifierCompetence;
    @javafx.fxml.FXML
    private ComboBox cboModiferChoixSousMatiereCompetence;
    @javafx.fxml.FXML
    private Button btnModifierCompetence;
    @javafx.fxml.FXML
    private ComboBox cboModiferChoixMatiereCompetence;
    @javafx.fxml.FXML
    private Button btnMesDemandes;
    @javafx.fxml.FXML
    private TreeView tvLesDemandes;
    @javafx.fxml.FXML
    private AnchorPane apStatistiques;
    @javafx.fxml.FXML
    private AnchorPane apLesDemandes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apMesDemandes.toFront();
    }

    @javafx.fxml.FXML
    public void onBtnMesDemandesClicked(Event event) {
        apMesDemandes.toFront();
    }

    @javafx.fxml.FXML
    public void onBtnMesCompetencesClicked(Event event) {
        apMesCompetences.toFront();
    }

    @javafx.fxml.FXML
    public void onBtnLesDemandesClicked(Event event) {
        apLesDemandes.toFront();
    }

    @javafx.fxml.FXML
    public void onBtnMesStatistiquesClicked(Event event) {
        apStatistiques.toFront();
    }

    @javafx.fxml.FXML
    public void onBtnModifierDemandeClicked(Event event) {
        apModifierDemande.toFront();
    }

    @javafx.fxml.FXML
    public void onBtnCreerDemandeClicked(Event event) {
        apCreerDemande.toFront();
    }

    @javafx.fxml.FXML
    public void onBtnModifierCompetenceClicked(Event event) {
        apModifierCompetence.toFront();
    }

    @javafx.fxml.FXML
    public void onBtnCreerCompetenceClicked(Event event) {
        apCreerCompetence.toFront();
    }
}
