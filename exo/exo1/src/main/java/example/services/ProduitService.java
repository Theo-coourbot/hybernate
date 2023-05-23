package example.services;

import example.entities.Produit;
import example.interfaces.Repository;

import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class ProduitService extends BaseService implements Repository<Produit> {

    public ProduitService(){
        super();
    }

    @Override
    public boolean create(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Produit o) {

        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        return true;
    }

    public boolean deleteByBrand (String brand){
        List<Produit> produitList = findBrand(brand);
        for (Produit pr: produitList) {
            session.beginTransaction();
            session.delete(pr);
            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public Produit findById(int id) {
        Produit produit = null;

        produit = (Produit) session.get(Produit.class, id);

        return produit;
    }
    public List<Produit> findBrand(String marque) {
        List<Produit> produitList = null;

        Query<Produit> produitQuery = session.createQuery("from Produit where marque like :mot");
        produitQuery.setParameter("mot", marque + "%");
        produitList = produitQuery.list();

        return produitList;
    }



    @Override
    public List<Produit> findAll() {
        List<Produit> produitList = null;
      //  session = sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit");
        produitList = produitQuery.list();
       // session.close();
        return produitList;
    }
    public double produitAvg(){

        double prixMoy = (double)session.createQuery("select avg(prix) from Produit").uniqueResult();

        return prixMoy;
    }

    public List<Produit> filterByPrice(double min) throws Exception{
        if (min >= 0){
         //   session = sessionFactory.openSession();
            Query<Produit> produitQuery = session.createQuery("from Produit where prix >= :min");
            produitQuery.setParameter("min",min);
         //   session.close();
            return produitQuery.list();
        }
        throw new Exception("erreur valeur");
    }
    public List<Produit> filterByStock(int min) throws Exception{
        if (min >= 0){
         //   session = sessionFactory.openSession();
            Query<Produit> produitQuery = session.createQuery("from Produit where stock >= :min");
            produitQuery.setParameter("min",min);
         //   session.close();
            return produitQuery.list();
        }
        throw new Exception("erreur valeur");
    }

    public List<Produit> filterByDate(Date min, Date max) throws Exception{
        if(min.before(max)){
         //   session = sessionFactory.openSession();
            Query<Produit> produitQuery = session.createQuery("from Produit where dateAchat >= :min and dateAchat <= :max ");
            produitQuery.setParameter("min",min);
            produitQuery.setParameter("max",max);
        //    session.close();
            return produitQuery.list();
        }
        throw new Exception("erreur date");
    }

    public void begin(){
        session = sessionFactory.openSession();
    }

    public void end(){
        session.close();
    }
}
