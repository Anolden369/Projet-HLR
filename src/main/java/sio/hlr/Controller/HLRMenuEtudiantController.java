package sio.hlr.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Material;
import javafx.stage.Stage;
import sio.hlr.Entities.Competences;
import sio.hlr.Entities.Demandes;
import sio.hlr.Entities.Matiere;
import sio.hlr.Entities.User;
import sio.hlr.HLRApplication;
import sio.hlr.Tools.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.scene.control.TreeTableView;


import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class HLRMenuEtudiantController implements Initializable{


    @javafx.fxml.FXML
    private Button btnMesCompetences;
    @javafx.fxml.FXML
    private Button btnLesDemandes;
    @javafx.fxml.FXML
    private Button btnMesStatistiques;
    @javafx.fxml.FXML
    private TableView<Demandes> tvMesDemandes;
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
    private AnchorPane apModifierDemande;
    @javafx.fxml.FXML
    private ComboBox cboModifierDemande;
    @javafx.fxml.FXML
    private AnchorPane apMesCompetences;
    @javafx.fxml.FXML
    private Button btnMenuCreerCompetence;
    @javafx.fxml.FXML
    private AnchorPane apCreerCompetence;
    @javafx.fxml.FXML
    private Button btnCreerCompetence;
    @javafx.fxml.FXML
    private Button btnMesDemandes;
    @javafx.fxml.FXML
    private AnchorPane apStatistiques;
    @javafx.fxml.FXML
    private AnchorPane apLesDemandes;
    @javafx.fxml.FXML
    private DatePicker dpDateFin;
    ServicesMatieres servicesMatieres;
    ServicesSousMatieres servicesSousMatieres;
    ServicesMesDemandes servicesMesDemandes;
    ServicesMesCompetences servicesMesCompetences;
    ServicesLesDemandes servicesLesDemandes;
    ServicesUsers servicesUsers;
    ConnexionBDD maCnx;

    @FXML
    private TableView<Matiere> tvCreerMatiereCompetence;
    @FXML
    private TableColumn tcCreerMatiereCompetence;
    @FXML
    private TableView<Matiere> tvCreerMatiereDemande;
    @FXML
    private TableColumn tcCreerMatiereDemande;
    @FXML
    private TableView<Matiere> tvModifMatiereDemande;
    @FXML
    private TableColumn tcModifMatiereDemande;
    @FXML
    private TableView<Matiere> tvModifSMatiereDemande;
    @FXML
    private TableColumn tcModifSMatiereDemande;
    @FXML
    private TableView<Matiere> tvCreerSMatiereCompetence;
    @FXML
    private TableView<Matiere> tvCreerSMatiereDemande;
    @FXML
    private TableColumn tcCreerSMatiereDemande;
    @FXML
    private TableView<Demandes> tvLesDemandes;
    @FXML
    private TableColumn tcLesDemandesDemandeurs;
    @FXML
    private TableColumn tcLesDemandesMatiere;
    @FXML
    private TableColumn tcLesDemandesSMatiere;
    @FXML
    private TableColumn tcLesDemandesDateLimite;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private TableColumn tcNumVoirMesDemandes;
    @FXML
    private TableColumn tcDateVoirMesDemandes;
    @FXML
    private TableColumn tcSousMatiereVoirMesDemandes;
    @FXML
    private TableColumn tcStatutVoirMesDemandes;
    @FXML
    private TableColumn tcMatiereVoirMesDemandes;
    @FXML
    private TableColumn tcChoixSMatiereCreerDemande;
    LocalDate DateActuelle = LocalDate.now();
    @FXML
    private DatePicker dpModifierDemandeDateFin;
    @FXML
    private TableColumn tcChoixSMatiereModifDemande;
    @FXML
    private TableView<Competences> tvMesCompetences;
    @FXML
    private TableColumn tcVoirMesCompetencesMatiere;
    @FXML
    private TableColumn tcVoirMesCompetencesSMatiere;
    @FXML
    private TableColumn tcCreerSMatiereComptence;
    @FXML
    private TableColumn tcChoixSMatiereCreerCompetence;
    @FXML
    private Button btnMenuSupprimerCompetence;
    @FXML
    private AnchorPane apLesDemandes2;
    @FXML
    private TextField txtValidationCommentaire;
    @FXML
    private Button btnValidationDemande;
    @FXML
    private TextField txtNomValidation;
    @FXML
    private TextField txtMatiereValidation;
    @FXML
    private TextField txtSousMatieres;
    @FXML
    private TextField idValidation;
    @FXML
    private DatePicker dpDateUpdateSoutien;
    @FXML
    private TextField dateLimiteValidationSoutien;
    @FXML
    private PieChart graph2;
    @FXML
    private LineChart graph1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apMesDemandes.toFront();
        try {
            maCnx = new ConnexionBDD();

            servicesMatieres = new ServicesMatieres();
            servicesSousMatieres = new ServicesSousMatieres();
            servicesMesDemandes = new ServicesMesDemandes();
            servicesMesCompetences = new ServicesMesCompetences();
            servicesLesDemandes = new ServicesLesDemandes();


            //Afficher toutes mes demandes en cours
            tcNumVoirMesDemandes.setCellValueFactory(new PropertyValueFactory<Demandes, Integer>("id"));
            tcMatiereVoirMesDemandes.setCellValueFactory(new PropertyValueFactory<Demandes, String>("matiere"));
            tcSousMatiereVoirMesDemandes.setCellValueFactory(new PropertyValueFactory<Demandes, String>("sousMatiere"));
            tcDateVoirMesDemandes.setCellValueFactory(new PropertyValueFactory<Demandes, Date>("dateFinDemande"));
            tcStatutVoirMesDemandes.setCellValueFactory(new PropertyValueFactory<Demandes, Integer>("status"));
            tcStatutVoirMesDemandes.setCellFactory(column -> {
                return new TableCell<Demandes, Integer>() {
                    @Override
                    protected void updateItem(Integer status, boolean empty) {
                        super.updateItem(status, empty);
                        if (empty || status == null) {
                            setText(null);
                        } else {
                            if (status == 1) {
                                setText("En cours");
                            } else if(status == 2){
                                setText("Acceptée - En attente d'attribution d'une salle");
                            } else if(status == 3){
                                setText("Validée - Salle attribuée");
                            } else {
                                setText("Aucun statut");
                            }
                        }
                    }
                };
            });


            tvMesDemandes.setItems(servicesMesDemandes.GetAllMesDemandes());

            //Afficher toutes mes compétences
            tcVoirMesCompetencesMatiere.setCellValueFactory(new PropertyValueFactory<Competences, String>("matiere"));
            tcVoirMesCompetencesSMatiere.setCellValueFactory(new PropertyValueFactory<Competences, String>("sousMatiere"));

            tvMesCompetences.setItems(servicesMesCompetences.GetAllMesCompetences());


            //Creer une matiere dans Demande
            tvCreerMatiereDemande.setItems(servicesMatieres.GetAllMatiere());
            tcCreerMatiereDemande.setCellValueFactory(new PropertyValueFactory<Matiere, String>("designation"));

            //Modifier une matiere dans Demande
            tvModifMatiereDemande.setItems(servicesMatieres.GetAllMatiere());
            tcModifMatiereDemande.setCellValueFactory(new PropertyValueFactory<Matiere, String>("designation"));


            //Creer une matiere dans Compétences
            tvCreerMatiereCompetence.setItems(servicesMatieres.GetAllMatiere());
            tcCreerMatiereCompetence.setCellValueFactory(new PropertyValueFactory<Matiere, String>("designation"));

            //Les demandes des autres
            tcLesDemandesDemandeurs.setCellValueFactory(new PropertyValueFactory<Demandes, String>("nomUser"));
            tcLesDemandesMatiere.setCellValueFactory(new PropertyValueFactory<Demandes, String>("matiere"));
            tcLesDemandesSMatiere.setCellValueFactory(new PropertyValueFactory<Demandes, String>("sousMatiere"));
            tcLesDemandesDateLimite.setCellValueFactory(new PropertyValueFactory<Demandes, Date>("dateFinDemande"));
            dpDateFin.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.isBefore(DateActuelle));
                }
            });
            dpModifierDemandeDateFin.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.isBefore(DateActuelle));
                }
            });
            dpDateUpdateSoutien.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate dateLimite = LocalDate.parse(dateLimiteValidationSoutien.getText());
                    setDisable(empty || date.isBefore(DateActuelle) || date.isAfter(dateLimite));
                }
            });
            tvLesDemandes.setItems(servicesLesDemandes.GetAllLesDemandes());
            //a continuer


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void tvChoixMatiereDemandeClicked(Event event) throws SQLException {
        servicesMatieres = new ServicesMatieres();
        servicesSousMatieres = new ServicesSousMatieres();
        String designation = tvCreerMatiereDemande.getSelectionModel().getSelectedItem().getDesignation();
        tcCreerSMatiereDemande.setCellValueFactory(new PropertyValueFactory<Matiere,String>("sousMatiere"));
        tcChoixSMatiereCreerDemande.setCellValueFactory(new PropertyValueFactory<Matiere, CheckBox>("uneSelection"));
        tvCreerSMatiereDemande.setItems(servicesSousMatieres.GetAllSousMatieres(designation));
    }

    @FXML
    public void onBtn2CreerDemandeClicked(Event event) throws SQLException {
        // je vérifie s'il y a au moins une sous matière qui a été selectionnée par le user
        boolean AuMoinsUneSelectionSousMatiere = false;
        for (Matiere matiere : tvCreerSMatiereDemande.getItems()) {
            if (matiere.getUneSelection() != null && matiere.getUneSelection().isSelected()) {
                AuMoinsUneSelectionSousMatiere = true;
                break;
            }
        }
        // je gère les erreurs
        if(tvCreerMatiereDemande.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setContentText("Veuillez selectionner une matière !");
            alert.setHeaderText("");
            alert.showAndWait();
        } else if(!AuMoinsUneSelectionSousMatiere) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setContentText("Veuillez selectionner au moins une sous matière !");
            alert.setHeaderText("");
            alert.showAndWait();
        } else if(dpDateFin.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de date");
            alert.setContentText("Veuillez selectionner une date !");
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            servicesMesDemandes = new ServicesMesDemandes();
            ObservableList<Matiere> lesSelectionsSousMatieres = tvCreerSMatiereDemande.getItems();
            ArrayList<String> lesSousMatieres = new ArrayList<>();

            for (Matiere uneMatiere : lesSelectionsSousMatieres) {
                if (uneMatiere.getUneSelection().isSelected()) {
                    lesSousMatieres.add(uneMatiere.getSousMatiere());
                }
            }
            String designation = tvCreerMatiereDemande.getSelectionModel().getSelectedItem().getDesignation();
            servicesMesDemandes.creerDemande(lesSousMatieres, dpDateFin, designation);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Créer demande");
            alert.setContentText("Votre demande a bien été créer!");
            alert.setHeaderText("");
            alert.showAndWait();
            apMesDemandes.toFront();
            tvMesDemandes.setItems(servicesMesDemandes.GetAllMesDemandes());
        }
    }

    @javafx.fxml.FXML
    public void onBtnModifierDemandeClicked(Event event) throws SQLException {
        if(tvMesDemandes.getSelectionModel().getSelectedItem() == null){
            apMesDemandes.toFront();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setContentText("Veuillez selectionner une demande pour la modifier !");
            alert.setHeaderText("");
            alert.showAndWait();
        } else if(tvMesDemandes.getSelectionModel().getSelectedItem().getStatus() == 2){
            apMesDemandes.toFront();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention");
            alert.setContentText("Vous ne pouvez pas modifier une demande qui a déjà été acceptée !");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else if(tvMesDemandes.getSelectionModel().getSelectedItem().getStatus() == 3){
            apMesDemandes.toFront();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention");
            alert.setContentText("Vous ne pouvez pas modifier une demande où la salle a été attribuée !");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else {
            servicesMesDemandes = new ServicesMesDemandes();
            ObservableList<Integer> lesIdDemandesSelectionne = FXCollections.observableArrayList();
            int idDemandesSelectionne = tvMesDemandes.getSelectionModel().getSelectedItem().getId();
            lesIdDemandesSelectionne.add(idDemandesSelectionne);
            cboModifierDemande.setItems(lesIdDemandesSelectionne);
            cboModifierDemande.getSelectionModel().selectFirst();
            dpModifierDemandeDateFin.setValue(tvMesDemandes.getSelectionModel().getSelectedItem().getDateFinDemande());
            apModifierDemande.toFront();
        }
    }
    @FXML
    public void tvModifMatiereDemandeClicked(Event event) throws SQLException {
        servicesMatieres = new ServicesMatieres();
        servicesSousMatieres = new ServicesSousMatieres();
        String designation = tvModifMatiereDemande.getSelectionModel().getSelectedItem().getDesignation();
        tcModifSMatiereDemande.setCellValueFactory(new PropertyValueFactory<Matiere,String>("sousMatiere"));
        tcChoixSMatiereModifDemande.setCellValueFactory(new PropertyValueFactory<Matiere, CheckBox>("uneSelection"));
        tvModifSMatiereDemande.setItems(servicesSousMatieres.GetAllSousMatieres(designation));
    }

    @FXML
    public void onBtn2ModifierDemandeClicked(Event event) throws SQLException {
        // je vérifie s'il y a au moins une sous matière qui a été selectionnée par le user
        boolean AuMoinsUneSelectionSousMatiere = false;
        for (Matiere matiere : tvModifSMatiereDemande.getItems()) {
            if (matiere.getUneSelection() != null && matiere.getUneSelection().isSelected()) {
                AuMoinsUneSelectionSousMatiere = true;
                break;
            }
        }
        // je gère les erreurs
        if(tvModifMatiereDemande.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setContentText("Veuillez selectionner une matière !");
            alert.setHeaderText("");
            alert.showAndWait();
        } else if(!AuMoinsUneSelectionSousMatiere) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setContentText("Veuillez selectionner au moins une sous matière !");
            alert.setHeaderText("");
            alert.showAndWait();
        } else if(dpModifierDemandeDateFin.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de date");
            alert.setContentText("Veuillez selectionner une date !");
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            servicesMesDemandes = new ServicesMesDemandes();
            ObservableList<Matiere> lesSelectionsSousMatieres = tvModifSMatiereDemande.getItems();
            ArrayList<String> lesSousMatieres = new ArrayList<>();

            for (Matiere uneMatiere : lesSelectionsSousMatieres) {
                if (uneMatiere.getUneSelection().isSelected()) {
                    lesSousMatieres.add(uneMatiere.getSousMatiere());
                }
            }
            String designation = tvModifMatiereDemande.getSelectionModel().getSelectedItem().getDesignation();
            servicesMesDemandes.modifierDemande(Integer.parseInt(cboModifierDemande.getSelectionModel().getSelectedItem().toString()),lesSousMatieres, dpModifierDemandeDateFin, designation);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modifier demande");
            alert.setContentText("Votre demande a bien été modifier!");
            alert.setHeaderText("");
            alert.showAndWait();
            apMesDemandes.toFront();
            tvMesDemandes.setItems(servicesMesDemandes.GetAllMesDemandes());
        }
    }

    // Partie mes Competences
    @FXML
    public void tvChoixMatiereCompetenceClicked(Event event) throws SQLException {
        servicesMatieres = new ServicesMatieres();
        servicesSousMatieres = new ServicesSousMatieres();
        servicesMesCompetences = new ServicesMesCompetences();
        servicesUsers =new ServicesUsers();

        String designation = tvCreerMatiereCompetence.getSelectionModel().getSelectedItem().getDesignation();
        tcCreerSMatiereComptence.setCellValueFactory(new PropertyValueFactory<Matiere,String>("sousMatiere"));
        tcChoixSMatiereCreerCompetence.setCellValueFactory(new PropertyValueFactory<Matiere, CheckBox>("uneSelection"));
        tvCreerSMatiereCompetence.setItems(servicesSousMatieres.GetAllSousMatieres(designation));
        for (Competences competence : servicesMesCompetences.GetAllMesCompetences()) {
            if (competence.getIdUser() == servicesUsers.getIdUser()) {
                String[] sousMatieres = competence.getSousMatiere().split("#");

                for (Matiere matiere : tvCreerSMatiereCompetence.getItems()) {
                    for (String sousMatiere : sousMatieres) {
                        if (matiere.getSousMatiere().equals(sousMatiere)) {
                            matiere.getUneSelection().setSelected(true);
                            break;
                        }
                    }
                }
            }
        }
    }

    @FXML
    public void onBtn2CreerCompetenceClicked(Event event) throws SQLException {
        // je vérifie s'il y a au moins une sous matière qui a été selectionnée par le user
        boolean AuMoinsUneSelectionSousMatiere = false;
        for (Matiere matiere : tvCreerSMatiereCompetence.getItems()) {
            if (matiere.getUneSelection() != null && matiere.getUneSelection().isSelected()) {
                AuMoinsUneSelectionSousMatiere = true;
                break;
            }
        }
        // je gère les erreurs
        if(tvCreerMatiereCompetence.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setContentText("Veuillez selectionner une matière !");
            alert.setHeaderText("");
            alert.showAndWait();
        } else if(!AuMoinsUneSelectionSousMatiere) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setContentText("Veuillez selectionner au moins une sous matière !");
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            ObservableList<Matiere> lesSelectionsSousMatieres = tvCreerSMatiereCompetence.getItems();
            ArrayList<String> lesSousMatieres = new ArrayList<>();

            for (Matiere uneMatiere : lesSelectionsSousMatieres) {
                if (uneMatiere.getUneSelection().isSelected()) {
                    lesSousMatieres.add(uneMatiere.getSousMatiere());
                }
            }
            String designation = tvCreerMatiereCompetence.getSelectionModel().getSelectedItem().getDesignation();
            servicesMesCompetences.creerCompetence(lesSousMatieres,designation);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Créer compétence");
            alert.setContentText("Votre compétence a bien été créer !");
            alert.setHeaderText("");
            alert.showAndWait();
            apMesCompetences.toFront();
            tvMesCompetences.setItems(servicesMesCompetences.GetAllMesCompetences());
        }

    }

    @FXML
    public void onBtnSupprimerCompetenceClicked(Event event) throws SQLException {
        if(tvMesCompetences.getSelectionModel().getSelectedItem() == null){
            apMesCompetences.toFront();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setContentText("Veuillez selectionner une compétence pour supprimer la matière de vos compétences!");
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            servicesMesCompetences = new ServicesMesCompetences();
            String nomMatiereMesCompetencesSelectionne = tvMesCompetences.getSelectionModel().getSelectedItem().getMatiere();
            servicesMesCompetences.supprimerCompetence(nomMatiereMesCompetencesSelectionne);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression compétence");
            alert.setContentText("Votre compétence a bien été supprimer !");
            alert.setHeaderText("");
            alert.showAndWait();
            apMesCompetences.toFront();
            tvMesCompetences.setItems(servicesMesCompetences.GetAllMesCompetences());
        }
    }


    // Les demandes des autres
    @javafx.fxml.FXML
    public void onBtnLesDemandesClicked(Event event) throws SQLException {
        servicesLesDemandes = new ServicesLesDemandes();
        apLesDemandes.toFront();
        tvLesDemandes.setItems(servicesLesDemandes.getDemandesCorrespondantesCompetences());
    }

    @FXML
    public void tvLesDemandesClicked(Event event) throws SQLException {
        if(tvLesDemandes.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setContentText("Veuillez selectionner une demande !");
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            apLesDemandes2.toFront();
            String nom = tvLesDemandes.getSelectionModel().getSelectedItem().getNomUser();
            String matiere = tvLesDemandes.getSelectionModel().getSelectedItem().getMatiere();
            LocalDate date = tvLesDemandes.getSelectionModel().getSelectedItem().getDateFinDemande();
            String sousMatiere = tvLesDemandes.getSelectionModel().getSelectedItem().getSousMatiere();
            int id = tvLesDemandes.getSelectionModel().getSelectedItem().getId();


            txtNomValidation.setText(nom);
            dateLimiteValidationSoutien.setText(String.valueOf(date));
            txtMatiereValidation.setText(matiere);
            txtSousMatieres.setText(sousMatiere);
            idValidation.setText(String.valueOf(id));
        }
    }

    @FXML
    public void btnValidationDemandeClicked(Event event) throws SQLException {
        if(dpDateUpdateSoutien.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de date");
            alert.setContentText("Veuillez selectionner une date pour le soutien !");
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            String nom = txtNomValidation.getText();
            //String matiere = txtMatiereValidation.getText();
            LocalDate date = dpDateUpdateSoutien.getValue();
            String sousMatiere = txtSousMatieres.getText();
            int id = tvLesDemandes.getSelectionModel().getSelectedItem().getId();
            String matiere;
            matiere = tvLesDemandes.getSelectionModel().getSelectedItem().getMatiere();
            int idCompetence = servicesMesCompetences.getIdCompetenceByUser(matiere);
            String commentaire = txtValidationCommentaire.getText();
            servicesLesDemandes.ajoutSoutien(id, idCompetence, date, commentaire);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acceptation Soutien");
            alert.setContentText("Merci pour votre soutien !");
            alert.setHeaderText("");
            alert.showAndWait();
            apLesDemandes.toFront();
            tvLesDemandes.setItems(servicesLesDemandes.getDemandesCorrespondantesCompetences());
        }
    }



    @FXML
    public void btnSMDemande(Event event) throws SQLException {
    }

    @javafx.fxml.FXML
    public void onBtnMesDemandesClicked(Event event) throws SQLException {
        tvMesDemandes.setItems(servicesMesDemandes.GetAllMesDemandes());
        apMesDemandes.toFront();
    }

    @javafx.fxml.FXML
    public void onBtnMesCompetencesClicked(Event event) throws SQLException {
        tvMesCompetences.setItems(servicesMesCompetences.GetAllMesCompetences());
        apMesCompetences.toFront();
    }

    @javafx.fxml.FXML
    public void onBtnMesStatistiquesClicked(Event event) {
        apStatistiques.toFront();
    }

    @FXML
    public void onBtnCreerDemandeClicked(Event event) {
        apCreerDemande.toFront();
    }

    @javafx.fxml.FXML
    public void onBtnCreerCompetenceClicked(Event event) {
        apCreerCompetence.toFront();
    }

    @FXML
    public void deconnexion(ActionEvent actionEvent) throws IOException {
        HLRApplication.LoginScene();
    }

}