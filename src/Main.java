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
/*  Искаме да имаме сайт за обяви за работа, който да е в полза както на работодатели
    така и на кандидати.
    Работодател не може да публикува колкото си иска обяви,
    а само 10 едновременно активни, като обявата е прост текст(описание),
    но има категории за вида професия:
    QA, Developer, Manager, DevOps, PM.
    Работодателят трябва да има акаут в системата.
    Кандидатът няма акаунт, но ако реши да кандидатства по определена обява
    то трябва да се създаде запис със следните характеристики:
    Имена на кандидата и по коя обява кандидатства.
    Кандидат може да се пробва по всички обяви, но за една само веднъж.
    Направете и справка(бекенд), която показва колко активни обяви имаме
    по категории и колко хора са кандидствали по всяка професия
            (QA, Developer, Manager, DevOps, PM).*/
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