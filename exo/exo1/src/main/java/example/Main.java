package example;

import example.entities.Produit;
import example.services.ProduitService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

       private static Scanner sc = new Scanner(System.in);
     private static    ProduitService ps = new ProduitService();

    public static void main(String[] args) throws ParseException {
        int choix;

          ps.begin();
        do {
            System.out.println("Menus");
            System.out.println("1. affichage par date ");
            System.out.println("2. affichage par stock");
            System.out.println("3. moyenne des produit");
            System.out.println("4. recherche par marque");
            System.out.println("5. supprimer les marques");
            System.out.println("6. voir la valeur du stock");
            System.out.println("7. ajout de produit");
            System.out.println("8. supprimer un produit ");
            System.out.println("9. voir tout lrs produits ");
            System.out.println("10. modif d'un produit");
            System.out.println("0.quiter");
            choix= sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case (1) :
                    afichageParDate();
                    break;
                case (2) :
                    affStock();
                    break;
                case (3) :
                    sommeProduit();
                    break;
                case (4) :
                    affParMarque();
                    break;
                case (5) :
                    suppParMarque();
                    break;
                case (6) :
                    valStockMarque();
                    break;
                case (7) :
                    ajoutProduit();
                    break;
                case (8) :
                    deleteProduit();
                    break;
                case (9) :
                    voirProduits();
                    break;
                case (10) :
                    modifProduit();
                    break;
                case (0) :
                    System.out.println("bye");
                    break;
            }

        } while (choix != 0);


        // Exercice 1

        // Creation des produits


        // Informations produit id = 2
//
//        Produit p = ps.findById(2);
//        System.out.println(p);

        // Supprimer le produit id = 3
//        ps.delete(ps.findById(3));

        // Modifier produit id = 1

//        p = ps.findById(1);
//        if(p != null){
//            p.setMarque("HP");
//            p.setReference("MMMMPPP");
//            p.setDateAchat(new Date("2015/09/08"));
//            p.setPrix(5000);
//            ps.update(p);
//        }


        // Exercice 2
//
//        System.out.println("############################");
//        System.out.println("Afficher tous les produits");
//        System.out.println("############################");
//

//        System.out.println("############################");
//        System.out.println("produits avec filtre date");
//        System.out.println("############################");


        ps.end();

    }

    public static void modifProduit() throws ParseException {
        System.out.println("id du produit a modif ");

        int id =sc.nextInt();
        sc.nextLine();
        Produit p = ps.findById(id);
        System.out.println("marque du produit ?");
        String marque = sc.nextLine();
        System.out.println("ref du produit ?");
        String ref = sc.nextLine();
        System.out.println("date de l'ajout du produit 31/12/2999 ?");
        String dateAjoutStr = sc.nextLine();
        Date dateAjout =new SimpleDateFormat("dd/MM/yyyy").parse(dateAjoutStr);
        System.out.println("prix du produit ?");
        double prix = sc.nextDouble();
        sc.nextLine();
        System.out.println("stock dispo du produit ?");
        int stock = sc.nextInt();
        sc.nextLine();
        p.setMarque(marque);
            p.setReference(ref);
            p.setDateAchat(dateAjout);
            p.setPrix(prix);
            p.setStock(stock);
        ps.update(p);
    }
    public static void voirProduits(){


        List<Produit> produits = ps.findAll();
        for (Produit pr: produits) {
            System.out.println(pr.getMarque() + " " + pr.getReference() + " " + pr.getPrix());
        }

    }
    public  static void deleteProduit(){
        System.out.println("id du produit a supp ");
        int id =sc.nextInt();
        ps.delete(ps.findById(id));
    }
    public static void ajoutProduit() throws ParseException {
        System.out.println("marque du produit ?");
        String marque = sc.nextLine();
        System.out.println("ref du produit ?");
        String ref = sc.nextLine();
        System.out.println("date de l'ajout du produit 31/12/2999 ?");
        String dateAjoutStr = sc.nextLine();
        Date dateAjout =new SimpleDateFormat("dd/MM/yyyy").parse(dateAjoutStr);
        System.out.println("prix du produit ?");
        double prix = sc.nextDouble();
        sc.nextLine();
        System.out.println("stock dispo du produit ?");
        int stock = sc.nextInt();
        sc.nextLine();
                ps.create(new Produit(marque,ref,dateAjout,prix,stock));

    }

    public static void afichageParDate() throws ParseException {
        System.out.println("merci de saisir la premiere date dd/MM/yyyy");
        String date1str = sc.nextLine();

        Date date1 =new SimpleDateFormat("dd/MM/yyyy").parse(date1str);
        System.out.println("merci de saisir la deuxieme date dd/MM/yyyy");
        String date2str = sc.nextLine();
        Date date2 =new SimpleDateFormat("dd/MM/yyyy").parse(date2str);

        try{
            List<Produit> produits2 = ps.filterByDate(date1,date2);
            for (Produit pr: produits2) {
                System.out.println(pr.getMarque() + " " + pr.getReference());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void affStock()  {
        System.out.println("nombre de stock minimum");
        int nbr = sc.nextInt();
        System.out.println(nbr);

        try{
            List<Produit> produits = ps.filterByStock(nbr);
            for (Produit pr: produits) {
                System.out.println(pr.getMarque() + " " + pr.getReference());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
       }
    }

    public static void sommeProduit(){
        double moyenneProduit = ps.produitAvg();
        System.out.println("la moyenne des produit est de : " + moyenneProduit);


    }
    public static void affParMarque(){
        System.out.println("nom de la marque rechercher");
        String marqueRechercher = sc.nextLine();
        List<Produit> produits = ps.findBrand(marqueRechercher);
        for (Produit pr: produits) {
            System.out.println(pr.getMarque() + " " + pr.getReference() + " " + pr.getPrix());
        }

    }

    public static void suppParMarque() {
        System.out.println("nom de la marque a supprimer");
        String marqueRechercher = sc.nextLine();
        ps.deleteByBrand(marqueRechercher);
        System.out.println("suppression ok");
    }

    public static void valStockMarque() {
        System.out.println("nom de la marque a rechercher");
        String marqueRechercher = sc.nextLine();
        List<Produit> produits = ps.findBrand(marqueRechercher);
        double valStock = 0;
        String nomMarque = "";

        for (Produit pr: produits) {
             valStock += pr.getPrix() * pr.getStock();
              nomMarque = pr.getMarque();

        }
        System.out.println("la valeur du stock de la marque : " + nomMarque + " est de : " + valStock );
    }
}