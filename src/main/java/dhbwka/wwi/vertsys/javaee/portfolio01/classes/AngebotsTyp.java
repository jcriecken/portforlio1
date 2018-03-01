/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 *
 * @author D067849
 */
package dhbwka.wwi.vertsys.javaee.portfolio01.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class AngebotsTyp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "typ_ids")
    @TableGenerator(name = "typ_ids", initialValue = 0, allocationSize = 50)
    private long id;

    @Column(length = 30)
    private String name;

    @OneToMany(mappedBy = "angebotstyp", fetch = FetchType.LAZY)
    List<Task> tasks = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public AngebotsTyp() {
    }

    public AngebotsTyp(String name) {
        this.name = name;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//</editor-fold>

}
