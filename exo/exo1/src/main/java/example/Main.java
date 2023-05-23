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
                case (0) :
                    System.out.println("bye");
                    break;
            }

        } while (choix != 0);


        // Exercice 1

        // Creation des produits

//        ps.create(new Produit("HP","EER678",new Date("2016/02/09"),2000,10));
//        ps.create(new Produit("SONY","AQWZSX",new Date("2016/09/23"),6000,5));
//        ps.create(new Produit("DELL","AZERTY",new Date("2016/02/12"),6000,1));
//        ps.create(new Produit("SONY","qsdERT",new Date("2016/02/02"),6000,100));

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