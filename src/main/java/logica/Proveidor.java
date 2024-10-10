/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.sql.Date;

/**
 * Classe que representa un proveïdor amb totes les seves propietats. Inclou
 * atributs per al nom, CIF, id, estat, data de creació, correu electrònic,
 * valoració i altres dades relacionades amb el proveïdor.
 * Clase que representa un proveedor en el sistema. Contiene información
 * detallada sobre el proveedor, como su nombre, CIF, estado, ID, motivo de
 * inactividad, fecha de creación, correo electrónico, calificación y tiempo de
 * colaboración.
 *
 * @autor Anna
 */
public class Proveidor {

    private String nom_proveidor;
    private String cif;
    private int id_proveidor;
    private String motiu_inactiu;
    private Date data_creacio;
    private String correu_electronic;
    private float rating_proveidor;
    private int mesos_de_colaboracio;
    private boolean actiu;

    /**
     * Constructor buit per defecte.
     * Constructor vacío de la clase Proveidor. Inicializa un proveedor sin
     * datos específicos.
     */
    public Proveidor() {
    }

    /** 
     * Constructor que inicialitza un proveïdor amb els valors proporcionats.
     *
     * @param i Identificador del proveïdor.
     * @param proveidor_Test Nom del proveïdor.
     * @param proveidortestcom Correu electrònic del proveïdor.
     * @param f Valoració del proveïdor.
     * @param ciF123456 CIF del proveïdor.
     * @param string Motiu d'inactivitat.
     * @param actiu Estat actiu/inactiu del proveïdor.
     * @param i0 Mesos de col·laboració del proveïdor.
     * @param b Estat actiu/inactiu del proveïdor.
     */
    public Proveidor(int i, String proveidor_Test, String proveidortestcom, float f, String ciF123456, String string, String actiu, int i0, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Obté l'identificador del proveïdor.
     *
     * @return Identificador del proveïdor.
     *
     */
    public int getId_proveidor() {
        return id_proveidor;
    }

    /**
     * Estableix l'identificador del proveïdor.
     *
     * @param id_proveidor Identificador del proveïdor.
     *
     */
    public void setId_proveidor(int id_proveidor) {
        this.id_proveidor = id_proveidor;
    }

    /**
     * Obté el motiu d'inactivitat del proveïdor.
     *
     * @return Motiu d'inactivitat del proveïdor.
     *
     */
    public String getMotiu_inactiu() {
        return motiu_inactiu;
    }

    /**
     * Estableix el motiu d'inactivitat del proveïdor.
     *
     * @param motiu_inactiu Motiu d'inactivitat.
     *
     */
    public void setMotiu_inactiu(String motiu_inactiu) {
        this.motiu_inactiu = motiu_inactiu;
    }

    /**
     * Obté la data de creació del proveïdor.
     *
     * @return Data de creació del proveïdor.
     *
     */
    public Date getData_creacio() {
        return data_creacio;
    }

    /**
     * Estableix la data de creació del proveïdor.
     *
     * @param data_creacio Data de creació del proveïdor.
     *
     */
    public void setData_creacio(Date data_creacio) {
        this.data_creacio = data_creacio;
    }

    /**
     * Obté el correu electrònic del proveïdor.
     *
     * @return Correu electrònic del proveïdor.
     *
     */
    public String getCorreu_electronic() {
        return correu_electronic;
    }

    /**
     * Estableix el correu electrònic del proveïdor.
     *
     * @param correu_electronic Correu electrònic del proveïdor.
     *
     */
    public void setCorreu_electronic(String correu_electronic) {
        this.correu_electronic = correu_electronic;
    }

    /**
     * Obté la valoració del proveïdor.
     *
     * @return Valoració del proveïdor.
     *
     */
    public float getRating_proveidor() {
        return rating_proveidor;
    }

    /**
     * Estableix la valoració del proveïdor.
     *
     * @param rating_proveidor Valoració del proveïdor.
     *
     */
    public void setRating_proveidor(float rating_proveidor) {
        this.rating_proveidor = rating_proveidor;
    }

    /**
     * Obté els mesos de col·laboració del proveïdor.
     *
     * @return Mesos de col·laboració del proveïdor.
     *
     */
    public int getMesos_de_colaboracio() {
        return mesos_de_colaboracio;
    }

    /**
     * Estableix els mesos de col·laboració del proveïdor.
     *
     * @param mesos_de_colaboracio Mesos de col·laboració del proveïdor.
     *
     */
    public void setMesos_de_colaboracio(int mesos_de_colaboracio) {
        this.mesos_de_colaboracio = mesos_de_colaboracio;
    }

    /**
     * Obtiene el CIF del proveedor.
     *
     * @return CIF del proveedor.
     */
    public String getCif() {
        return cif;
    }

    /**
     * Estableix el CIF del proveïdor.
     *
     * @param cif CIF del proveïdor.
     *
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * Representación en forma de cadena de la instancia de Proveidor.
     *
     * @return Cadena con el nombre, CIF y estado del proveedor.
     */
    @Override
    public String toString() {
        return "Proveidor{" + "nom_proveidor=" + nom_proveidor + ", cif=" + cif + ", id_proveidor=" + id_proveidor + ", motiu_inactiu=" + motiu_inactiu + ", data_creacio=" + data_creacio + ", correu_electronic=" + correu_electronic + ", rating_proveidor=" + rating_proveidor + ", mesos_de_colaboracio=" + mesos_de_colaboracio + ", actiu=" + actiu + '}';
    }

    /**
     * Enum que defineix els possibles estats d'un proveïdor (actiu o inactiu).
     * Enum que define los posibles estados de un proveedor: ACTIU o INACTIU.
     */
    public enum EstatProveidor {
        ACTIU, INACTIU
    }

    /**
     * Obté l'estat actiu/inactiu del proveïdor.
     *
     * @return true si el proveïdor és actiu, false si no ho és.
     */
    public boolean isActiu() {
        return actiu;
    }

    /**
     * Estableix l'estat actiu/inactiu del proveïdor.
     *
     * @param actiu Estat del proveïdor (true per actiu, false per inactiu).
     */
    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }

    /**
     * Obté el nom del proveïdor.
     *
     * @return Nom del proveïdor.
     */
    public String getNom_proveidor() {
        return nom_proveidor;
    }

    /**
     * Estableix el nom del proveïdor.
     *
     * @param nom_proveidor Nom del proveïdor.
     */
    public void setNom_proveidor(String nom_proveidor) {
        this.nom_proveidor = nom_proveidor;
    }

}
