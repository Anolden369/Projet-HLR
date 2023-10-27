package sio.hlr.Controller;

import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sio.hlr.Entities.Matiere;
import sio.hlr.Entities.User;
import sio.hlr.Tools.ConnexionBDD;
import sio.hlr.Tools.ServicesMatieres;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.scene.control.TreeTableView;
import sio.hlr.Tools.ServicesSousMatieres;


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
    private ComboBox cboCreerChoixSousMatiere;
    @javafx.fxml.FXML
    private AnchorPane apModifierDemande;
    @javafx.fxml.FXML
    private ComboBox cboModifierDemande;
    @javafx.fxml.FXML
    private DatePicker dpModifierDateActuelle;
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
    @javafx.fxml.FXML
    private DatePicker dpDateCreation;
    @javafx.fxml.FXML
    private DatePicker dpDateFin;
    ServicesMatieres servicesMatieres;
    ConnexionBDD maCnx;

    @FXML
    private TableView tvCreerMatiereCompetence;
    @FXML
    private TableColumn tcCreerMatiereCompetence;
    @FXML
    private TableView tvCreerMatiereDemande;
    @FXML
    private TableColumn tcCreerMatiereDemande;
    @FXML
    private TableView tvModifMatiereDemande;
    @FXML
    private TableColumn tcModifMatiereDemande;
    @FXML
    private TableView tvModifSMatiereDemande;
    @FXML
    private TableColumn tcModifSMatiereDemande;
    @FXML
    private TableView tvCreerSMatiereCompetence;
    @FXML
    private TableColumn tcCreerSMatiereCompetence;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apMesDemandes.toFront();
        try {
            maCnx = new ConnexionBDD();
            LocalDate currentDate = LocalDate.now();
            dpDateCreation.setValue(currentDate);
            dpDateFin.setValue(currentDate.plusDays(7));
            dpModifierDateActuelle.setValue(currentDate);

            //ServicesMatieres servicesMatieres = new ServicesMatieres();
            //ObservableList<Matiere> lesMatieres = servicesMatieres.GetAllMatiere();
            //cboCreerChoixMatiere.setItems(lesMatieres);

            ServicesMatieres servicesMatieres = new ServicesMatieres();
            ServicesSousMatieres servicesSousMatieres = new ServicesSousMatieres();

            tvCreerMatiereDemande.setItems(servicesMatieres.GetAllMatiere());
            tcCreerMatiereDemande.setCellValueFactory(new PropertyValueFactory<Matiere, String>("designation"));

            tvCreerMatiereCompetence.setItems(servicesMatieres.GetAllMatiere());
            tcCreerMatiereCompetence.setCellValueFactory(new PropertyValueFactory<Matiere, String>("designation"));

            tvModifMatiereDemande.setItems(servicesMatieres.GetAllMatiere());
            tcModifMatiereDemande.setCellValueFactory(new PropertyValueFactory<Matiere, String>("designation"));

            tvModifSMatiereDemande.setItems(servicesSousMatieres.GetSousMatiereAnglais());
            tcModifSMatiereDemande.setCellValueFactory(new PropertyValueFactory<Matiere, String>("sousMatiere"));





        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    @FXML
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
