package com.mycompany.mikicrud.registrations;

//import com.airhacks.control.business.registrations.entity.Attendee;
import com.mycompany.mikicrud.entities.Person;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author adam-bien.com
 */
public class RegistrationService {

    private EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction et;

    public void init() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_mikiCrud_jar_1.0-SNAPSHOTPU");
        this.em = this.emf.createEntityManager();
        this.et = this.em.getTransaction();
    }

    public List<Person> all() {
        return this.em.createNamedQuery("Person.findall").getResultList();
    }

    public Person save(Person attendee) {
        et.begin();
        Person merged = this.em.merge(attendee);
        et.commit();
        return merged;
    }

    public void save() {
        et.begin();
        em.flush();
        et.commit();
    }

    public void remove(Person attendee) {
        et.begin();
        this.em.remove(attendee);
        et.commit();
    }

    public void close() {
        et.begin();
        et.commit();
        em.close();
    }
}
