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
import dhbwka.wwi.vertsys.javaee.portfolio01.classes.AngebotsTyp;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;




@Stateless
@RolesAllowed("allowed-user")
public class AngebotsTypBean extends EntityBean<AngebotsTyp, Long> {

    public AngebotsTypBean() {
        super(AngebotsTyp.class);
    }

    /**
     * Auslesen aller Kategorien, alphabetisch sortiert.
     *
     * @return Liste mit allen Kategorien
     */
    public List<AngebotsTyp> findAllSorted() {
        return this.em.createQuery("SELECT c FROM AngebotsTyp c ORDER BY c.name").getResultList();
    }
}
