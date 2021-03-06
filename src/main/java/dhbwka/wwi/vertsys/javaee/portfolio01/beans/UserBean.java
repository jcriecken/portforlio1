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

import dhbwka.wwi.vertsys.javaee.portfolio01.classes.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Spezielle EJB zum Anlegen eines Benutzers und Aktualisierung des Passworts.
 */
@Stateless
public class UserBean {

    @PersistenceContext
    EntityManager em;
    
    @Resource
    EJBContext ctx;

    /**
     * Gibt das Datenbankobjekt des aktuell eingeloggten Benutzers zurück,
     *
     * @return Eingeloggter Benutzer oder null
     */
    public User getCurrentUser() {
        return this.em.find(User.class, this.ctx.getCallerPrincipal().getName());
    }

        /**
     * @param username Benutzername
     * @return Alle Aufgaben des Benutzers
     */
    public List<String> getUserInfo(String username) {
        User user = this.em.find(User.class, this.ctx.getCallerPrincipal().getName());
        List<String> userinfo = new ArrayList<>();
        userinfo.add(user.getRealName());
        userinfo.add(user.getAdress());
        userinfo.add(user.getPostalCode());
        userinfo.add(user.getCity());        
        return userinfo;
    }
    
    
    /**
     *
     * @param username
     * @param password
     * @param realname
     * @param address
     * @param postalCode
     * @param city
     * @throws UserBean.UserAlreadyExistsException
     */
    public void signup(String username, String password, String realname, String address, String postalCode, String city) throws UserAlreadyExistsException {
        if (em.find(User.class, username) != null) {
            throw new UserAlreadyExistsException("Der Benutzername $B ist bereits vergeben.".replace("$B", username));
        }

        User user = new User(username, password, realname, address, postalCode, city);
        user.addToGroup("LoginGroup");
        em.persist(user);
    }

    /**
     *
     * @param username
     * @param oldPassword
     * @param newPassword
     * @throws UserBean.InvalidCredentialsException
     */
    @RolesAllowed("allowed-user")
    public void changePassword(String username, String oldPassword, String newPassword) throws InvalidCredentialsException {
        User user = em.find(User.class, username);

        if (user == null || !user.checkPassword(oldPassword)) {
            throw new InvalidCredentialsException("Benutzername oder Passwort sind falsch.");
        }

        user.setPassword(newPassword);
        em.merge(user);
    }

    /**
     * Fehler: Der Benutzername ist bereits vergeben
     */
    public class UserAlreadyExistsException extends Exception {

        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    /**
     * Fehler: Das übergebene Passwort stimmt nicht mit dem des Benutzers
     * überein
     */
    public class InvalidCredentialsException extends Exception {

        public InvalidCredentialsException(String message) {
            super(message);
        }
    }

}
