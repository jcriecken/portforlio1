/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */

/**
 *
 * @author D067849
 */

package dhbwka.wwi.vertsys.javaee.portfolio01.beans;
import dhbwka.wwi.vertsys.javaee.portfolio01.classes.PreisTyp;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;




@Stateless
@RolesAllowed("todo-app-user")
public class PreisTypBean extends EntityBean<PreisTyp, Long> {

    public PreisTypBean() {
        super(PreisTyp.class);
    }

    /**
     * Auslesen aller Kategorien, alphabetisch sortiert.
     *
     * @return Liste mit allen Kategorien
     */
    public List<PreisTyp> findAllSorted() {
        return this.em.createQuery("SELECT c FROM AngebotsTyp c ORDER BY c.name").getResultList();
    }
}
