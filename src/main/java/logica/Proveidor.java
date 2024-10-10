/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.sql.Date;

/**
 * Clase que representa un proveedor en el sistema. Contiene información
 * detallada sobre el proveedor, como su nombre, CIF, estado, ID, motivo de
 * inactividad, fecha de creación, correo electrónico, calificación y tiempo de
 * colaboración.
 *
 * @autor Anna
 */
public class Proveidor {

    private String nom;
    private String cif;
    private EstatProveidor Estat;
    private int id_proveidor;
    private String motiu_inactiu;
    private Date data_creacio;
    private String correu_electronic;
    private float rating_proveidor;
    private int mesos_de_colaboracio;

    /**
     * Constructor vacío de la clase Proveidor. Inicializa un proveedor sin
     * datos específicos.
     */
    public Proveidor() {

    }

    /**
     * Obtiene el ID único del proveedor.
     *
     * @return ID del proveedor.
     */
    public int getId_proveidor() {
        return id_proveidor;
    }

    /**
     * Establece el ID único del proveedor.
     *
     * @param id_proveidor ID del proveedor.
     */
    public void setId_proveidor(int id_proveidor) {
        this.id_proveidor = id_proveidor;
    }

    /**
     * Obtiene el motivo de inactividad del proveedor, si aplica.
     *
     * @return Motivo de inactividad.
     */
    public String getMotiu_inactiu() {
        return motiu_inactiu;
    }

    /**
     * Establece el motivo de inactividad del proveedor.
     *
     * @param motiu_inactiu Motivo de inactividad.
     */
    public void setMotiu_inactiu(String motiu_inactiu) {
        this.motiu_inactiu = motiu_inactiu;
    }

    /**
     * Obtiene la fecha de creación del proveedor.
     *
     * @return Fecha de creación.
     */
    public Date getData_creacio() {
        return data_creacio;
    }

    /**
     * Establece la fecha de creación del proveedor.
     *
     * @param data_creacio Fecha de creación.
     */
    public void setData_creacio(Date data_creacio) {
        this.data_creacio = data_creacio;
    }

    /**
     * Obtiene el correo electrónico del proveedor.
     *
     * @return Correo electrónico.
     */
    public String getCorreu_electronic() {
        return correu_electronic;
    }

    /**
     * Establece el correo electrónico del proveedor.
     *
     * @param correu_electronic Correo electrónico.
     */
    public void setCorreu_electronic(String correu_electronic) {
        this.correu_electronic = correu_electronic;
    }

    /**
     * Obtiene la calificación o puntuación del proveedor.
     *
     * @return Calificación del proveedor.
     */
    public float getRating_proveidor() {
        return rating_proveidor;
    }

    /**
     * Establece la calificación o puntuación del proveedor.
     *
     * @param rating_proveidor Calificación del proveedor.
     */
    public void setRating_proveidor(float rating_proveidor) {
        this.rating_proveidor = rating_proveidor;
    }

    /**
     * Obtiene el número de meses de colaboración con el proveedor.
     *
     * @return Meses de colaboración.
     */
    public int getMesos_de_colaboracio() {
        return mesos_de_colaboracio;
    }

    /**
     * Establece el número de meses de colaboración con el proveedor.
     *
     * @param mesos_de_colaboracio Meses de colaboración.
     */
    public void setMesos_de_colaboracio(int mesos_de_colaboracio) {
        this.mesos_de_colaboracio = mesos_de_colaboracio;
    }

    /**
     * Obtiene el nombre del proveedor.
     *
     * @return Nombre del proveedor.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Establece el nombre del proveedor.
     *
     * @param nom Nombre del proveedor.
     */
    public void setNom(String nom) {
        this.nom = nom;
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
     * Establece el CIF del proveedor.
     *
     * @param cif CIF del proveedor.
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * Obtiene el estado del proveedor, que puede ser ACTIU o INACTIU.
     *
     * @return Estado del proveedor.
     */
    public EstatProveidor getEstat() {
        return Estat;
    }

    /**
     * Establece el estado del proveedor.
     *
     * @param Estat Estado del proveedor.
     */
    public void setEstat(EstatProveidor Estat) {
        this.Estat = Estat;
    }

    /**
     * Representación en forma de cadena de la instancia de Proveidor.
     *
     * @return Cadena con el nombre, CIF y estado del proveedor.
     */
    @Override
    public String toString() {
        return "Proveidor{" + "nom=" + nom + ", cif=" + cif + ", Estat=" + Estat + '}';
    }

    /**
     * Enum que define los posibles estados de un proveedor: ACTIU o INACTIU.
     */
    public enum EstatProveidor {
        ACTIU, INACTIU
    }

}
