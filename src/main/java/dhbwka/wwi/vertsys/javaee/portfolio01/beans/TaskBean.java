/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.portfolio01.beans;
//Test Kommentar
import dhbwka.wwi.vertsys.javaee.portfolio01.classes.Category;
import dhbwka.wwi.vertsys.javaee.portfolio01.classes.Task;
import dhbwka.wwi.vertsys.javaee.portfolio01.classes.AngebotsTyp;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
//Test
/**
 * Einfache EJB mit den üblichen CRUD-Methoden für Aufgaben
 */
@Stateless
@RolesAllowed("allowed-user")
public class TaskBean extends EntityBean<Task, Long> { 
   
    public TaskBean() {
        super(Task.class);
    }
    
    /**
     * Alle Aufgaben eines Benutzers, nach Fälligkeit sortiert zurückliefern.
     * @param username Benutzername
     * @return Alle Aufgaben des Benutzers
     */
    public List<Task> findByUsername(String username) {
        return em.createQuery("SELECT t FROM Task t WHERE t.owner.username = :username ORDER BY t.dueDate")
                 .setParameter("username", username)
                 .getResultList();
    }
    
    /**
     * Suche nach Aufgaben anhand ihrer Bezeichnung, Kategorie und Status.
     * 
     * Anders als in der Vorlesung behandelt, wird die SELECT-Anfrage hier
     * mit der CriteriaBuilder-API vollkommen dynamisch erzeugt.
     * 
     * @param search In der Kurzbeschreibung enthaltener Text (optional)
     * @param category Kategorie (optional)
     * @param angebotstyp AngebotsTyp (optional)
     * @return Liste mit den gefundenen Aufgaben
     */
    public List<Task> search(String search, Category category, AngebotsTyp angebotstyp) {
        // Hilfsobjekt zum Bauen des Query
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        
        // SELECT t FROM Task t
        CriteriaQuery<Task> query = cb.createQuery(Task.class);
        Root<Task> from = query.from(Task.class);
        query.select(from);

        // ORDER BY dueDate
        query.orderBy(cb.asc(from.get("dueDate")));
        
        // WHERE t.shortText LIKE :search
        if (search != null && !search.trim().isEmpty()) {
            query.where(cb.like(from.get("shortText"), "%" + search + "%"));
        }
        
        // WHERE t.category = :category
        if (category != null) {
            query.where(cb.equal(from.get("category"), category));
        }
        /*
        // WHERE t.angebotstyp = :angebotstyp
        if (angebotstyp != null) {
            query.where(cb.equal(from.get("angebotstyp"), angebotstyp));
        } */
        
        return em.createQuery(query).getResultList();
    }
}
