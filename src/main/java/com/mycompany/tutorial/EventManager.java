package com.mycompany.tutorial;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import com.mycompany.tutorial.util.HibernateUtil;

public class EventManager {

    public static void main(String[] args) {
        EventManager mgr = new EventManager();
        if (args.length != 1) {
            Long personId = mgr.createAndStorePerson("Foo", "Bar");
            System.out.println(personId);
        }
        if (args[0].equals("store")) {
            mgr.createAndStoreEvent("My Event", new Date());
        } else if (args[0].equals("list")) {
            System.out.println("hello world!");
            List<Event> events = (List<Event>) (List<?>) mgr.listEvents();
            for (Event event : events) {
                System.out.println("event " + event.getTitle() + " @ " + event.getDate());
            }
        } else if (args[0].equals("addpersontoevent")) {
            Long eventId = mgr.createAndStoreEvent("My Event", new Date());
            Long personId = mgr.createAndStorePerson("Foo", "Bar");
            mgr.addPersonToEvent(personId, eventId);
            System.out.println("Added person " + personId + " to event " + eventId);
        }

        HibernateUtil.getSessionFactory().close();
    }

    private Long createAndStorePerson(String firstName, String lastName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Person person = new Person(firstName, lastName);
        session.beginTransaction();
        Long personId = (Long) session.save(person);
        session.getTransaction().commit();
        return personId;
    }

    private Long createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        theEvent.setType(EventType.DEAD);
        Long eventId = (Long) session.save(theEvent);

        session.getTransaction().commit();
        return eventId;
    }

    private void addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Person aPerson = (Person) session.load(Person.class, personId);
        Event anEvent = (Event) session.load(Event.class, eventId);
        aPerson.getEvents().add(anEvent);
        session.getTransaction().commit();
    }

    private List listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from events").list();
        session.getTransaction().commit();
        return result;
    }
}
