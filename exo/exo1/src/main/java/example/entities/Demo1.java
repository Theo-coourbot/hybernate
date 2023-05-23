package example.entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class Demo1 {
    public static void main(String[] args) {


        StandardServiceRegistry registre = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registre).buildMetadata().buildSessionFactory();


        Session session = sessionFactory.openSession();


            session.getTransaction().begin();
//        Produit p = new Produit();
//        p.setDate("10/12/2014");
//        p.setMarque("bonbon");
//        p.setPrix(14.80);
//        p.setStock(15);
//        p.setRef("xx-177-yy");
//        session.save(p);
//
//      // 2
//        Produit p2 = new Produit();
//        p2.setDate("10/12/2014");
//        p2.setMarque("pomme d'happy");
//        p2.setPrix(9.80);
//        p2.setStock(150);
//        p2.setRef("xx-755-yy");
//        session.save(p2);
//
//        // 3
//        Produit p3 = new Produit();
//        p3.setDate("25/12/2024");
//        p3.setMarque("pere noel");
//        p3.setPrix(100.80);
//        p3.setStock(1005);
//        p3.setRef("xx-252-yy");
//        session.save(p3);
//
//        // 4
//        Produit p4 = new Produit();
//        p4.setDate("05/01/2000");
//        p4.setMarque("choco");
//        p4.setPrix(1000);
//        p4.setStock(2);
//        p4.setRef("xx-999-yy");
//        session.save(p4);
//
//        // 5
//        Produit p5 = new Produit();
//        p5.setDate("10/01/2014");
//        p5.setMarque("or massif");
//        p5.setPrix(1);
//        p5.setStock(99);
//        p5.setRef("xx-150-yy");
//        session.save(p5);


//           session.getTransaction().commit();
//
//
//        session.getTransaction().begin();
//        Produit pSearch = session.load(Produit.class,2);
//        System.out.println(pSearch);

        Query<Produit> produitQuery = session.createQuery("from Produit ");


        List<Produit> produits = produitQuery.list();

        for (Produit p : produits) {
            System.out.println(p);
        }


        System.out.println("petit 2 prix sup a 100");
        Query<Produit> produitQuery2 = session.createQuery("from Produit where prix > 100 ");


        List<Produit> produits2 = produitQuery2.list();

        for (Produit p : produits2) {
            System.out.println(p);
        }

        System.out.println("petit 3 entre 2 date");
        Query<Produit> produitQuery3 = session.createQuery("from Produit where date between '04/01/2000' and '10/06/2014'");


        List<Produit> produits3 = produitQuery3.list();

        for (Produit p : produits3) {
            System.out.println(p);
        }










        session.close();
        sessionFactory.close();

    }
}
