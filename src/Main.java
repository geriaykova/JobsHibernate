import DB.Candidate;
import DB.Category;
import DB.Employer;
import DB.Offer;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.Map;

public class Main {
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

    public static void main(final String[] args) throws Exception {

//        Functions.insertCandidate("Petya Petkova");
//        Functions.insertCategory("CEO");
//        Functions.insertEmployer("Denislav Tsvetkov");

//        Functions.insertOffer(6, "CEO position", "Full-time job", 6);
//        Functions.insertOffer(3, "something", "sth", 6);
//        Functions.deactivateOffer(29);

//        Functions.applyForJob(6, 29);
//        Functions.applyForJob(6,22);
    }


}