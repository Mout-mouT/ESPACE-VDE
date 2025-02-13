package vde;

public class gestionnairedeContacte {

    public static void main(String[] args) {
        // Créer un objet Contact
        Contact contact = new Contact("Dupont", "Jean", "jean.dupont@example.com", "0123456789");

        // Afficher les informations du contact
        System.out.println(contact); // Affiche "Contact{nom='Dupont', prenom='Jean', email='jean.dupont@example.com', telephone='0123456789'}"

        // Modifier le numéro de telephone
        contact.setTelephone("0987654321");

        // Afficher les informations du contact modifiées
        System.out.println(contact); // Affiche "Contact{nom='Dupont', prenom='Jean', email='jean.dupont@example.com', telephone='0987654321'}"
    }
}

class Contact {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    // Constructeur
    public Contact(String nom, String prenom, String email, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    // Méthode toString() pour une représentation textuelle
    @Override
    public String toString() {
        return "Contact{" +
               "nom='" + nom + '\'' +
               ", prenom='" + prenom + '\'' +
               ", email='" + email + '\'' +
               ", telephone='" + telephone + '\'' +
               '}';
    }
}

class GestionContact {
    // Attributs privés
    private String nom;
    private String prenom;
    private String email;
    private String num;

    // Constructeur pour initialiser les valeurs des attributs
    public GestionContact(String nom, String prenom, String email, String num) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.num = num;
    }

    // Getter pour obtenir le nom
    public String getNom() {
        return nom;
    }

    // Setter pour définir le nom
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter pour obtenir le prénom
    public String getPrenom() {
        return prenom;
    }

    // Setter pour définir le prénom
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // Getter pour obtenir l'email
    public String getEmail() {
        return email;
    }

    // Setter pour définir l'email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter pour obtenir le numéro de téléphone
    public String getNum() {
        return num;
    }

    // Setter pour définir le numéro de téléphone
    public void setNum(String num) {
        this.num = num;
    }
}
