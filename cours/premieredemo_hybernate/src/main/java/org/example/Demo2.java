package org.example;

import org.example.entities.Personne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class Demo2 {

    public static void main(String[] args) {
        StandardServiceRegistry registre = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registre).buildMetadata().buildSessionFactory();


        Session session = sessionFactory.openSession();


        Query<Personne> personneQuery = session.createQuery("from Personne ");

        List<Personne> personnes = personneQuery.list();

        for (Personne p : personnes) {
            System.out.println(p.getNom());

        }

        Iterator<Personne> personneIterator = personneQuery.iterate();

        while (personneIterator.hasNext()){
            Personne p = (Personne) personneIterator.next();
            System.out.println(p.getNom());
        }


    }
}
