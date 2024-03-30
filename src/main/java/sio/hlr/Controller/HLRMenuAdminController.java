package sio.hlr.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sio.hlr.Entities.Demandes;
import sio.hlr.Entities.Matiere;
import sio.hlr.Entities.Salle;
import sio.hlr.HLRApplication;
import sio.hlr.Tools.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

public class HLRMenuAdminController implements Initializable {
    //ServicesMatieres servicesMatieres; //marche pas
    //ServicesSalles servicesSalles;
    ConnexionBDD maCnx;
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
    private ComboBox cboModifNumSalle;
    @javafx.fxml.FXML
    private ComboBox cboModifEtageSalle;
    @javafx.fxml.FXML
    private TableView<Demandes> tvSoutien;
    @javafx.fxml.FXML
    private TableColumn tcSoutienDemandes;
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
    ServicesLesDemandes servicesLesDemandes;
    ServicesSalles servicesSalles;
    ServicesMatieres servicesMatieres;
    ServicesSousMatieres servicesSousMatieres;
    ServicesStatistiques servicesStatistiques;

    @javafx.fxml.FXML
    private AnchorPane apVisuSoutients2;
    @javafx.fxml.FXML
    private TableView<Salle> tvAffectationSalleSelection;
    @javafx.fxml.FXML
    private TextArea txtAffectationInfo1;
    @javafx.fxml.FXML
    private TextArea txtAffectationInfo2;
    @javafx.fxml.FXML
    private Button btnValidationAffection;
    @javafx.fxml.FXML
    private TableColumn tcNumSalle;
    LocalDate DateActuelle = LocalDate.now();
    @javafx.fxml.FXML
    private TableColumn tcSoutienAides;
    @javafx.fxml.FXML
    private Button btnGraphique1to2;
    @javafx.fxml.FXML
    private Button btnGraphique1to3;
    @javafx.fxml.FXML
    private Button btnGraphique2to1;
    @javafx.fxml.FXML
    private Button btnGraphique2to3;
    @javafx.fxml.FXML
    private Button btnGraphique3to1;
    @javafx.fxml.FXML
    private Button btnGraphique3to2;
    @javafx.fxml.FXML
    private AnchorPane apStatistiques1;
    @javafx.fxml.FXML
    private AnchorPane apStatistiques2;
    @javafx.fxml.FXML
    private AnchorPane apStatistiques3;
    @javafx.fxml.FXML
    private BarChart graph1;
    @javafx.fxml.FXML
    private PieChart graph2;
    @javafx.fxml.FXML
    private LineChart graph3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apCreerMatieres.toFront();
        try{
            maCnx = new ConnexionBDD();
            servicesSalles = new ServicesSalles();
            cboNomMatiere.setValue("Choix de la matière");

            servicesLesDemandes = new ServicesLesDemandes();
            servicesMatieres = new ServicesMatieres();
            servicesSalles = new ServicesSalles();
            servicesSousMatieres = new ServicesSousMatieres();

            tcSoutienDemandes.setCellValueFactory(new PropertyValueFactory<Demandes, String>("nomUser"));
            tcSoutienMatiere.setCellValueFactory(new PropertyValueFactory<Demandes,String>("matiere"));
            tcSoutienDateLimite.setCellValueFactory(new PropertyValueFactory<Demandes, Date>("dateFinDemande"));
            tcSoutienSMatiere.setCellValueFactory(new PropertyValueFactory<Demandes,String>("id"));
            tcSoutienStatut.setCellValueFactory(new PropertyValueFactory<Demandes,String>("status"));
            tvSoutien.setItems(servicesLesDemandes.GetAllLesDemandesAdmin());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /*
    * Affiche la page de création des matières.
    */
    @javafx.fxml.FXML
    public void btnCreerMatiereClicked(Event event){apCreerMatieres.toFront();}

    /*
     * Affiche la page de modification des matières.
     */
    @javafx.fxml.FXML
    public void btnModifMatiereClicked(Event event) throws SQLException {
        refreshModifMatiere();
    }

    /*
    * Liste déroulante de matières de la BDD, permet de sélectionner une matière.
    */
    @javafx.fxml.FXML
    public void cboNomMatiereClicked(Event event) throws SQLException {
         servicesSousMatieres = new ServicesSousMatieres();
         servicesMatieres = new ServicesMatieres();

        String matiereCbo = cboNomMatiere.getSelectionModel().getSelectedItem().toString(); // récupération du nom de la matière du cbo pour le mettre dnas une variable String qui sera paramètre
        txtModifNomSousMatiere.setText(servicesSousMatieres.GetSousMatiere(matiereCbo)); // remplissage de zone texte des sous matières par
        txtModifNomMatiere.setText(servicesMatieres.getNomMatiere(matiereCbo));

    }

    /*
     * Affiche la page de création des salles de classes.
     */
    @javafx.fxml.FXML
    public void btnCreerSallesClicked(Event event) throws SQLException {
        apCreerSalles.toFront();
        ServicesSalles servicesSalles = new ServicesSalles();
        txtCreerNumSalle.setText(String.valueOf(servicesSalles.getSalle()));
    }

    /*
     * Affiche la page de visualisation des soutiens des élèves.
     */
    @javafx.fxml.FXML
    public void btnVisuSoutiensClicked(Event event) {
        apVisuSoutients.toFront();
    }

    /*
     * Affiche la page des statistiques, affiche le graphique n°1.
     */
    @javafx.fxml.FXML
    public void btnStatistiquesAdminClicked(Event event) {
        apStatistiques1.toFront();
    }

    /*
     * Valide la saisie de la nouvelle matière,
     * insertion dans la table “matière” de la BDD la nouvelle matière (Nom, sous-matières, id est auto-incrémenté)
     */
    @javafx.fxml.FXML
    public void btnValiderCreerMatiere(Event event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
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
            ServicesMatieres servicesMatieres = new ServicesMatieres();
            servicesMatieres.ajoutMatiereSousMatiere(txtCreerNomMatiere.getText(),txtCreerNomSousMatiere.getText());
            alert1.setTitle("Ajout affectué");
            alert1.setHeaderText("");
            alert1.setContentText("La matière a bien été ajouté !");
            alert1.showAndWait();
        }
    }

    /*
     * Valide la saisie de la modification de la matière,
     * mise à jour dans la table “matière” de la BDD, la matière sélectionnée (Nom, sous-matières)
     */
    @javafx.fxml.FXML
    public void btnValiderModifMatiereClicked(Event event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
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
            ServicesMatieres servicesMatieres = new ServicesMatieres();
            String newMatiere = txtModifNomMatiere.getText().toString();
            String newSousMatiere = txtModifNomSousMatiere.getText().toString();
            String cbo = cboNomMatiere.getSelectionModel().getSelectedItem().toString();
            servicesMatieres.ModifMatiereSousMatiere(newMatiere,newSousMatiere,cbo);
            apModifMatiere.toFront();
            alert1.setTitle("Modification de la matière");
            alert1.setHeaderText("");
            alert1.setContentText("Les informations ont été mises à jour");
            alert1.showAndWait();
            refreshModifMatiere();
        }

    }

    /*
     * Valide la création de la salle, ajout dans la table “salle” de la BDD,
     * la nouvelle salle (id, nom_salle, etage)
     */
    @javafx.fxml.FXML
    public void btnValiderCreerSalleClicked(Event event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        int numSalle = Integer.parseInt(txtCreerNumSalle.getText().toString());
        String numSalle2 = txtCreerNumSalle.getText().toString();
        servicesSalles.crerSalle(numSalle,numSalle2);
        apCreerSalles.toFront();
        txtCreerNumSalle.setText(String.valueOf(servicesSalles.getSalle()));
        alert.setTitle("Création de salle");
        alert.setHeaderText("");
        alert.setContentText("Une nouvelle salle a été créée");
        alert.showAndWait();
    }

    /*
     * Affiche la page de visualisation des soutiens en attentes d’affectation d’une salle.
     */
    @javafx.fxml.FXML
    public void tvSoutienClicked(Event event) throws SQLException {
        ObservableList<Integer> lesIdDemandesSelectionne = FXCollections.observableArrayList();

        apVisuSoutients2.toFront();
        int id = tvSoutien.getSelectionModel().getSelectedItem().getId();
        String nom = tvSoutien.getSelectionModel().getSelectedItem().getNomUser();
        txtAffectationInfo1.setText(String.valueOf(id));
        txtAffectationInfo2.setText(nom);
        servicesSalles = new ServicesSalles();
        tcNumSalle.setCellValueFactory(new PropertyValueFactory<Salle,Integer>("codeSalle"));
        tvAffectationSalleSelection.setItems(servicesSalles.getAllSalle());
    }

    /*
     * Valide l'affectation d’une salle pour un soutien sélectionné dans “tvSoutienClicked”,
     * mise à jour de la table “soutien” de la BDD le soutien (id_salle et statut = 3)
     */
    @javafx.fxml.FXML
    public void btnValidationAffectionClicked(Event event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String matiere = tvSoutien.getSelectionModel().getSelectedItem().getMatiere();
        LocalDate dateLimite = tvSoutien.getSelectionModel().getSelectedItem().getDateFinDemande();
        int idDemande = tvSoutien.getSelectionModel().getSelectedItem().getId();
        String codeSalle = tvAffectationSalleSelection.getSelectionModel().getSelectedItem().getCodeSalle();
        int idSalle = servicesSalles.getIdSalle(codeSalle);
        int idCompetence = servicesMatieres.GetIdMatiere(matiere);
        servicesLesDemandes.affectationSoutien(idCompetence,idSalle,DateActuelle,idDemande);
        apVisuSoutients.toFront();
        tvSoutien.setItems(servicesLesDemandes.GetAllLesDemandesAdmin());
        alert.setTitle("Affectation de la salle");
        alert.setHeaderText("");
        alert.setContentText("Une salle a été affectée pour le soutien");
        alert.showAndWait();
    }

    /*
     * Rafraichi la liste déroulante des matières (cboNomMatiere).
     * Fonction rappelée dans “btnValiderModifMatiereClicked”.
     */
    public void refreshModifMatiere() throws SQLException {
        apModifMatiere.toFront();
        // Remplissage de la liste déroulante (cboNomMatiere)
        ServicesMatieres servicesMatieres = new ServicesMatieres();
        ServicesSousMatieres servicesSousMatieres = new ServicesSousMatieres();
        ObservableList<String> lesMatieres = FXCollections.observableArrayList();
        for (Matiere uneMatiere : servicesMatieres.GetAllMatiere()){
            lesMatieres.add(uneMatiere.getDesignation());
        }
        cboNomMatiere.setItems(lesMatieres);
    }

    /*
     * Affiche le graphique n°2 depuis la graphique n°1.
     */
    @javafx.fxml.FXML
    public void onBtnGraphique1to2Clicked(ActionEvent actionEvent) throws SQLException {
        apStatistiques2.toFront();
        graphique2();
    }

    /*
     * Affiche le graphique n°3 depuis la graphique n°1.
     */
    @javafx.fxml.FXML
    public void onBtnGraphique1to3Clicked(ActionEvent actionEvent) throws SQLException {
        apStatistiques3.toFront();
        graphique3();
    }

    /*
     * Affiche le graphique n°1 depuis la graphique n°2.
     */
    @javafx.fxml.FXML
    public void onBtnGraphique2to1Clicked(ActionEvent actionEvent) throws SQLException {
        apStatistiques1.toFront();
        graphique1();
    }

    /*
     * Affiche le graphique n°3 depuis la graphique n°2.
     */
    @javafx.fxml.FXML
    public void onBtnGraphique2to3Clicked(ActionEvent actionEvent) throws SQLException {
        apStatistiques3.toFront();
        graphique3();
    }

    /*
     * Affiche le graphique n°1 depuis la graphique n°3.
     */
    @javafx.fxml.FXML
    public void onBtnGraphique3to1Clicked(ActionEvent actionEvent) throws SQLException {
        apStatistiques1.toFront();
        graphique1();
    }

    /*
     * Affiche le graphique n°2 depuis la graphique n°3.
     */
    @javafx.fxml.FXML
    public void onBtnGraphique3to2Clicked(ActionEvent actionEvent) throws SQLException {
        apStatistiques2.toFront();
        graphique2();
    }

    /*
     * Affiche la page des statistiques, affiche le graphique n°1.
     */
    @javafx.fxml.FXML
    public void onBtnLesStatistiquesClicked(ActionEvent actionEvent) throws SQLException {
        apStatistiques1.toFront();
        graphique1();
    }

    /*
     * Rempli le graphique n°1 (nombre de demande par niveau),
     * récupère les données de la BDD pour constituer le graphique.
     */
    public void graphique1() throws SQLException {
        graph1.getData().clear();
        servicesStatistiques = new ServicesStatistiques();
        HashMap<String, Integer> datasGraphique = servicesStatistiques.GetDatasGraphique4();
        graph1.setTitle("Le nombre de demandes par niveau");

        for (String xValue : datasGraphique.keySet()) {
            int yValue = datasGraphique.get(xValue);

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(xValue);

            series.getData().add(new XYChart.Data<>(xValue, yValue));

            graph1.getData().add(series);
        }
    }

    /*
     * Rempli le graphique n°2 (camembert des étudiants ayant réalisé le plus de soutien),
     * récupère les données de la BDD pour constituer le graphique.
     */
    public void graphique2() throws SQLException {
        graph2.getData().clear();
        servicesStatistiques = new ServicesStatistiques();
        ObservableList<PieChart.Data> datasGraph2 = FXCollections.observableArrayList();
        HashMap<String, Integer> datasGraphique2 = servicesStatistiques.GetDatasGraphique5();
        Iterator var12 = datasGraphique2.keySet().iterator();

        while(var12.hasNext()) {
            String valeur = (String)var12.next();
            datasGraph2.add(new PieChart.Data(valeur, (double)(Integer)datasGraphique2.get(valeur)));
        }

        graph2.setData(datasGraph2);
        graph2.setTitle("Les 10 étudiants qui ont réalisé le plus de soutiens");
        var12 = graph2.getData().iterator();

        while(var12.hasNext()) {
            PieChart.Data entry = (PieChart.Data) var12.next();
            double var16 = entry.getPieValue();
            Tooltip t = new Tooltip("" + var16 + " : " + entry.getName());
            t.setStyle("-fx-background-color:#3D9ADA");
            Tooltip.install(entry.getNode(), t);
        }
    }

    /*
     * Rempli le graphique n°3 (nombre de demande de soutien par matière),
     * récupère les données de la BDD pour constituer le graphique.
     */
    public void graphique3() throws SQLException {
        graph3.getData().clear();
        servicesStatistiques = new ServicesStatistiques();
        HashMap<String, Integer> datasGraphique = servicesStatistiques.GetDatasGraphique6();

        graph3.setTitle("Les 20 sous matières les plus sollicitées");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Sous matières");

        for (String xValue : datasGraphique.keySet()) {
            int yValue = datasGraphique.get(xValue);
            series.getData().add(new XYChart.Data<>(xValue, yValue));
        }
        graph3.getData().add(series);
    }

    /*
     * Permet de se déconnecter du compte.
     */
    @javafx.fxml.FXML
    public void deconnexion(ActionEvent actionEvent) throws IOException {
        HLRApplication.LoginScene();
    }
}
