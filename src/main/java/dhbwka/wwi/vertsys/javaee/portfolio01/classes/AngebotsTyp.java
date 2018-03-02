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


public enum AngebotsTyp {
    BIETE, KAUFE;

    /**
     * Bezeichnung ermitteln
     *
     * @return Bezeichnung
     */
    public String getLabel() {
        switch (this) {
            case BIETE:
                return "Biete";
            case KAUFE:
                return "Kaufe";
            default:
                return this.toString();
        }
    }
}
