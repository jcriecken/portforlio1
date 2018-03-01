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
public class PreisTyp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "preis_ids")
    @TableGenerator(name = "preis_ids", initialValue = 0, allocationSize = 50)
    private long id;

    @Column(length = 30)
    private String name;

    @OneToMany(mappedBy = "preistyp", fetch = FetchType.LAZY)
    List<Task> tasks = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public PreisTyp() {
    }

    public PreisTyp(String name) {
        this.name = name;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter/Setter">
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    //</editor-fold>
}
