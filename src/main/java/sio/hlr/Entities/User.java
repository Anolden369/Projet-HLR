package sio.hlr.Entities;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String role;
    private int niveau;
    private int sexe;
    private int tel;
    private double pourcentage;

    public User(int id, String nom, String prenom, String email, String password, String role, int niveau, int sexe, int tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.niveau = niveau;
        this.sexe = sexe;
        this.tel = tel;
    }
    public User(int id, String nom, String prenom, String email, String role, int niveau) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        this.niveau = niveau;
    }

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String nom, double pourcentageEleve) {
        this.pourcentage = pourcentageEleve;
        this.nom = nom;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getSexe() {
        return sexe;
    }

    public int getTel() {
        return tel;
    }
}
