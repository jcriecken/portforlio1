/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.portfolio01.functions;

import dhbwka.wwi.vertsys.javaee.portfolio01.beans.CategoryBean;
import dhbwka.wwi.vertsys.javaee.portfolio01.beans.TaskBean;
import dhbwka.wwi.vertsys.javaee.portfolio01.classes.Category;
import dhbwka.wwi.vertsys.javaee.portfolio01.classes.Task;
import dhbwka.wwi.vertsys.javaee.portfolio01.classes.AngebotsTyp;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet für die Startseite bzw. jede Seite, die eine Liste der Aufgaben
 * zeigt.
 */
@WebServlet(urlPatterns = {"/app/tasks/"})
public class TaskListServlet extends HttpServlet {

    @EJB
    private CategoryBean categoryBean;
    
    @EJB
    private TaskBean taskBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verfügbare Kategorien und Stati für die Suchfelder ermitteln
        request.setAttribute("categories", this.categoryBean.findAllSorted());
        request.setAttribute("angebotstypen", AngebotsTyp.values());

        // Suchparameter aus der URL auslesen
        String searchText = request.getParameter("search_text");
        String searchCategory = request.getParameter("search_category");
        String searchAngebotstyp = request.getParameter("search_angebotstyp");

        // Anzuzeigende Aufgaben suchen
        Category category = null;
        AngebotsTyp angebotstyp = null;

        if (searchCategory != null) {
            try {
                category = this.categoryBean.findById(Long.parseLong(searchCategory));
            } catch (NumberFormatException ex) {
                category = null;
            }
        }

        if (searchAngebotstyp != null) {
            try {
                angebotstyp = AngebotsTyp.valueOf(searchAngebotstyp);
            } catch (IllegalArgumentException ex) {
                angebotstyp = null;
            }

        }

        List<Task> tasks = this.taskBean.search(searchText, category, angebotstyp);
        request.setAttribute("tasks", tasks);

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/app/task_list.jsp").forward(request, response);
    }
}
