import DB.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Functions {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }


    //достъпване на обекти чрез id-та

    public static Candidate getCandidateByID(int cID){
        final Session session = getSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Candidate> criteriaQuery = builder.createQuery(Candidate.class);
        Root<Candidate> candidateRoot = criteriaQuery.from(Candidate.class);

        criteriaQuery.where(builder.equal(candidateRoot.get("id"), cID));
        Candidate candidate = session.createQuery(criteriaQuery).getSingleResult();

        session.getTransaction().commit();
        session.close();
        return candidate;
    }

    public static Category getCategoryByID(int catID){

        final Session session = getSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = builder.createQuery(Category.class);
        Root<Category> categoryRoot = criteriaQuery.from(Category.class);

        criteriaQuery.where(builder.equal(categoryRoot.get("id"), catID));

        Category category = session.createQuery(criteriaQuery).getSingleResult();

        session.getTransaction().commit();
        session.close();

        return category;
    }

    public static Employer getEmployerByID(int eID){
        final Session session = getSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employer> criteriaQuery = builder.createQuery(Employer.class);
        Root<Employer> employerRoot = criteriaQuery.from(Employer.class);

        criteriaQuery.where(builder.equal(employerRoot.get("id"), eID));
        Employer employer = session.createQuery(criteriaQuery).getSingleResult();

        session.getTransaction().commit();
        session.close();
        return employer;
    }

    public static Offer getOfferByID(int oID){
        final Session session = getSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Offer> criteriaQuery = builder.createQuery(Offer.class);
        Root<Offer> offerRoot = criteriaQuery.from(Offer.class);

        criteriaQuery.where(builder.equal(offerRoot.get("id"), oID));

        Offer offer = session.createQuery(criteriaQuery).getSingleResult();

        session.getTransaction().commit();
        session.close();

        return offer;
    }


    //вмъкване на категория
    public static void insertCategory(String name){
        final Session session = getSession();
        session.beginTransaction();

        Category category = new Category(name);

        session.save(category);
        session.getTransaction().commit();
        session.close();
        System.err.println("A category was inserted: " + name);
    }

    //вмъкване на работодател
    public static boolean insertEmployer(String name){
        final Session session = getSession();
        session.beginTransaction();

        Employer employer = new Employer(name);

        session.save(employer);
        session.getTransaction().commit();
        session.close();
        System.err.println("An employer was inserted: " + name);
        return true;
    }

    //вмъкване на оферта
    public static void insertOffer(int employerID,
                                    String name,
                                    String description,
                                    int categoryID){
        final Session session = getSession();
        session.beginTransaction();

        Employer employer = getEmployerByID(employerID);
        Category category = getCategoryByID(categoryID);

        if(employer.checkOffersLimit(employer.getOffersList())){
            Offer offer = new Offer(employer, name, description, category);
            session.save(offer);
            System.err.println("An offer was inserted: " + name +
                    "\n description: " + description +
                    "\n employer: " + employer.getName() +
                    "\n category: " + category.getName());
        }
        else{
            System.err.println("This employer has reached the limitation of 10 offers");
        }

        session.getTransaction().commit();
        session.close();
    }

    //вмъкване на кандидат
    public static void insertCandidate(String name){
        final Session session = getSession();
        session.beginTransaction();

        Candidate candidate = new Candidate(name);

        session.save(candidate);
        session.getTransaction().commit();
        session.close();
        System.err.println("A candidate was inserted: " + name);
    }

    //кандидатстване за работа
    public static void applyForJob(int candidateID, int offerID){
        final Session session = getSession();
        session.beginTransaction();

        Candidate candidate = getCandidateByID(candidateID);
        Offer offer = getOfferByID(offerID);

        if(!candidate.getOffersList().contains(offer) && offer.getActive().equals(true)) {
            candidate.getOffersList().add(offer);
            offer.getCandidatesList().add(candidate);
            System.err.println("Application successful!");
        }
        else if(candidate.getOffersList().contains(offer)){
            System.err.println("You have already applied for this offer! ");
        }
        else if(offer.getActive().equals(false)){
            System.err.println("This offer is not active!");
        }

        //using .merge because with .update it throws exception
        session.merge(candidate);
        session.merge(offer);
        session.getTransaction().commit();
        session.close();

    }

    //деактивиране на оферта за работа
    public static void deactivateOffer(int offerID){
        final Session session = getSession();
        session.beginTransaction();

        Offer offer = getOfferByID(offerID);
        if(offer.getActive().equals(true)){
            offer.setActive(false);
            session.update(offer);
            System.err.println("The offer '" + offer.getName() + "' is deactivated.");
        }
        else{
            System.err.println("This offer is already not active.");
        }

        session.getTransaction().commit();
        session.close();
    }

//    //справка: колко активни обяви имаме по категории
////    SELECT COUNT(id) FROM Offers
////    WHERE isActive = 1
////
//
//
//    public static void showActiveOffers(){
//        final Session session = getSession();
//        session.beginTransaction();
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
//        Root<Offer> offerRoot = criteriaQuery.from(Offer.class);
//
//        criteriaQuery.select(builder.count(offerRoot));
//        criteriaQuery.where(builder.equal(offerRoot.get("isActive"), 1));
//
//        long count = session.createQuery(criteriaQuery).getSingleResult();
//        System.err.println("Count of active offers: " + count);
//
//        session.getTransaction().commit();
//        session.close();
//    }
//
//    // колко хора са кандидствали по всяка професия
////    SELECT COUNT(candidateID) AS candidatesCount, offerID FROM Candidates_Offers
////    GROUP BY offerID
//    public static void showCountOfCandidates(){
//        final Session session = getSession();
//        session.beginTransaction();
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<CandidatesOffers> criteriaQuery = builder.createQuery(CandidatesOffers.class);
//        CriteriaQuery<Long> longCriteriaQuery = builder.createQuery(Long.class);
//        Root<CandidatesOffers> candidateRoot = criteriaQuery.from(CandidatesOffers.class);
//
//        longCriteriaQuery.select(builder.count(candidateRoot));
//        criteriaQuery.groupBy(candidateRoot.get("offer"));
//
//        List<CandidatesOffers> list = session.createQuery(criteriaQuery).getResultList();
//        for(CandidatesOffers co : list){
//            System.out.println(co);
//        }
//
//        session.getTransaction().commit();
//        session.close();
//    }




}
