package sio.hlr.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sio.hlr.Entities.Competences;
import sio.hlr.Entities.Demandes;
import sio.hlr.Entities.Matiere;
import sio.hlr.HLRApplication;
import sio.hlr.Tools.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;



import java.util.*;

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
    ServicesStatistiques servicesStatistiques;
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
    private BarChart graph1;
    @FXML
    private AnchorPane apStatistiques2;
    @FXML
    private AnchorPane apStatistiques3;
    @FXML
    private LineChart graph3;
    @FXML
    private Button btnGraphique2to3;
    @FXML
    private Button btnGraphique1to2;
    @FXML
    private Button btnGraphique1to3;
    @FXML
    private Button btnGraphique2to1;
    @FXML
    private Button btnGraphique3to1;
    @FXML
    private Button btnGraphique3to2;


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
            graphique1();
            graphique2();
            graphique3();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////// Mes Demandes  /////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    // Affiche toutes les demandes de l’étudiant.
    @javafx.fxml.FXML
    public void onBtnMesDemandesClicked(Event event) throws SQLException {
        tvMesDemandes.setItems(servicesMesDemandes.GetAllMesDemandes());
        apMesDemandes.toFront();
    }

    // Redirige vers la page de création de demande.
    @FXML
    public void onBtnCreerDemandeClicked(Event event) {
        apCreerDemande.toFront();
    }

    // Affiche la demande de l’étudiant avec la matière et les sous matières.
    @FXML
    public void tvChoixMatiereDemandeClicked(Event event) throws SQLException {
        servicesMatieres = new ServicesMatieres();
        servicesSousMatieres = new ServicesSousMatieres();
        String designation = tvCreerMatiereDemande.getSelectionModel().getSelectedItem().getDesignation();
        tcCreerSMatiereDemande.setCellValueFactory(new PropertyValueFactory<Matiere,String>("sousMatiere"));
        tcChoixSMatiereCreerDemande.setCellValueFactory(new PropertyValueFactory<Matiere, CheckBox>("uneSelection"));
        tvCreerSMatiereDemande.setItems(servicesSousMatieres.GetAllSousMatieres(designation));
    }

    // Affiche la page de création d’une demande avec les matières et sous matières.
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

    // Affiche la page de modification d’une demande avec les matières et sous matières.
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

    // Affiche la page de modification d’une demande avec le nom des matières et des sous matières.
    @FXML
    public void tvModifMatiereDemandeClicked(Event event) throws SQLException {
        servicesMatieres = new ServicesMatieres();
        servicesSousMatieres = new ServicesSousMatieres();
        String designation = tvModifMatiereDemande.getSelectionModel().getSelectedItem().getDesignation();
        tcModifSMatiereDemande.setCellValueFactory(new PropertyValueFactory<Matiere,String>("sousMatiere"));
        tcChoixSMatiereModifDemande.setCellValueFactory(new PropertyValueFactory<Matiere, CheckBox>("uneSelection"));
        tvModifSMatiereDemande.setItems(servicesSousMatieres.GetAllSousMatieres(designation));
    }

    //Applique les modifications faites par l’étudiant concernant sa demande.
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////// Mes Competences  //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    // Affiche toutes les compétences de l’étudiant.
    @javafx.fxml.FXML
    public void onBtnMesCompetencesClicked(Event event) throws SQLException {
        tvMesCompetences.setItems(servicesMesCompetences.GetAllMesCompetences());
        apMesCompetences.toFront();
    }


    // Redirige vers la page de création de compétence.
    @javafx.fxml.FXML
    public void onBtnCreerCompetenceClicked(Event event) {
        apCreerCompetence.toFront();
    }

    // Affiche toutes les compétences de l’étudiant afin de les modifier.
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

    // Créée/Modifie une compétence pour un étudiant dans une matière avec des sous-matières spécifiques.
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////// Les demandes des autres  //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    // Affiche la page des demandes des autres avec les matières et sous matières.
    @javafx.fxml.FXML
    public void onBtnLesDemandesClicked(Event event) throws SQLException {
        servicesLesDemandes = new ServicesLesDemandes();
        apLesDemandes.toFront();
        tvLesDemandes.setItems(servicesLesDemandes.getDemandesCorrespondantesCompetences());
    }

    // Affiche les informations de la demande pour le soutien.
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

    // Valide le soutien après la saisie de la date et du commentaire.
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


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////// Mes Statistiques  /////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////


    // Affiche la page des statistiques de l’étudiant.
    @javafx.fxml.FXML
    public void onBtnMesStatistiquesClicked(Event event) throws SQLException {
        apStatistiques.toFront();
        graphique1();
    }

    // Affiche le graphique n°2 depuis la graphique n°1.
    @FXML
    public void onBtnGraphique1to2Clicked(ActionEvent actionEvent) throws SQLException {
        apStatistiques2.toFront();
        graphique2();
    }

    // Affiche le graphique n°3 depuis la graphique n°1.
    @FXML
    public void onBtnGraphique1to3Clicked(ActionEvent actionEvent) throws SQLException {
        apStatistiques3.toFront();
        graphique3();

    }

    // Affiche le graphique n°1 depuis la graphique n°2.
    @FXML
    public void onBtnGraphique2to1Clicked(ActionEvent actionEvent) throws SQLException {
        apStatistiques.toFront();
        graphique1();
    }

    // Affiche le graphique n°3 depuis la graphique n°2.
    @FXML
    public void onBtnGraphique2to3Clicked(Event event) throws SQLException {
        apStatistiques3.toFront();
        graphique3();
    }

    // Affiche le graphique n°1 depuis la graphique n°3.
    @FXML
    public void onBtnGraphique3to1Clicked(Event event) throws SQLException {
        apStatistiques.toFront();
        graphique1();
    }

    // Affiche le graphique n°2 depuis la graphique n°3.
    @FXML
    public void onBtnGraphique3to2Clicked(ActionEvent actionEvent) throws SQLException {
        apStatistiques2.toFront();
        graphique2();
    }

    // Rempli le graphique n°1 (Nombre de demandes par matière), récupère les données de la BDD pour constituer le graphique.
    public void graphique1() throws SQLException {
        graph1.getData().clear();
        servicesStatistiques = new ServicesStatistiques();
        HashMap<String, Integer> datasGraphique = servicesStatistiques.GetDatasGraphique1();
        graph1.setTitle("Nombre de demandes par matière");

        for (String xValue : datasGraphique.keySet()) {
            int yValue = datasGraphique.get(xValue);

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(xValue);

            series.getData().add(new XYChart.Data<>(xValue, yValue));

            graph1.getData().add(series);
        }
    }

    // Rempli le graphique n°2 (Nombre de soutiens réalisés par matière), récupère les données de la BDD pour constituer le graphique.
    public void graphique2() throws SQLException {
        graph2.getData().clear();
        servicesStatistiques = new ServicesStatistiques();
        ObservableList<PieChart.Data> datasGraph2 = FXCollections.observableArrayList();
        HashMap<String, Integer> datasGraphique2 = servicesStatistiques.GetDatasGraphique2();
        Iterator var12 = datasGraphique2.keySet().iterator();

        while(var12.hasNext()) {
            String valeur = (String)var12.next();
            datasGraph2.add(new PieChart.Data(valeur, (double)(Integer)datasGraphique2.get(valeur)));
        }

        graph2.setData(datasGraph2);
        graph2.setTitle("Nombre de soutiens réalisés par matière");
        var12 = graph2.getData().iterator();

        while(var12.hasNext()) {
            PieChart.Data entry = (PieChart.Data) var12.next();
            double var16 = entry.getPieValue();
            Tooltip t = new Tooltip("" + var16 + " : " + entry.getName());
            t.setStyle("-fx-background-color:#3D9ADA");
            Tooltip.install(entry.getNode(), t);
        }
    }

    // Rempli le graphique n°3 (Nombre de soutiens acceptés par niveau), récupère les données de la BDD pour constituer le graphique.
    public void graphique3() throws SQLException {
        graph3.getData().clear();
        servicesStatistiques = new ServicesStatistiques();
        HashMap<String, Integer> datasGraphique = servicesStatistiques.GetDatasGraphique3();

        graph3.setTitle("Nombre de soutiens acceptés par niveau");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Soutiens acceptés");

        for (String xValue : datasGraphique.keySet()) {
            int yValue = datasGraphique.get(xValue);
            series.getData().add(new XYChart.Data<>(xValue, yValue));
        }
        graph3.getData().add(series);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////// Deconnexion  //////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    // Permet de se déconnecter du compte.
    @FXML
    public void deconnexion(ActionEvent actionEvent) throws IOException {
        HLRApplication.LoginScene();
    }
}