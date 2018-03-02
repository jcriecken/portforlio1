package dhbwka.wwi.vertsys.javaee.portfolio01.classes;

public enum PreisTyp {
    FESTPREIS, VERHANDLUNGSBASIS;

    /**
     * Bezeichnung ermitteln
     *
     * @return Bezeichnung
     */
    public String getLabel() {
        switch (this) {
            case FESTPREIS:
                return "Festpreis";
            case VERHANDLUNGSBASIS:
                return "Verhandlungsbasis";
            default:
                return this.toString();
        }
    }
}
