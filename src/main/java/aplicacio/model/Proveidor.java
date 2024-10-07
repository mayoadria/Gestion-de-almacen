/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

import java.sql.Date;

/**
 *
 * @author Anna
 */
public class Proveidor {

    private String nom_proveidor;
    private String cif;
    private EstatProveidor Estat;
    private int id_proveidor;
    private String motiu_inactiu;
    private Date data_creacio;
    private String correu_electronic;
    private float rating_proveidor;
    private int mesos_de_colaboracio;
    private boolean actiu; 
    
    public Proveidor() {

    }

    public boolean isActiu() {
        return actiu;
    }

    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }

    public int getId_proveidor() {
        return id_proveidor;
    }

    public void setId_proveidor(int id_proveidor) {
        this.id_proveidor = id_proveidor;
    }

    public String getMotiu_inactiu() {
        return motiu_inactiu;
    }

    public void setMotiu_inactiu(String motiu_inactiu) {
        this.motiu_inactiu = motiu_inactiu;
    }

    public Date getData_creacio() {
        return data_creacio;
    }

    public void setData_creacio(Date data_creacio) {
        this.data_creacio = data_creacio;
    }

    public String getCorreu_electronic() {
        return correu_electronic;
    }

    public void setCorreu_electronic(String correu_electronic) {
        this.correu_electronic = correu_electronic;
    }

    public float getRating_proveidor() {
        return rating_proveidor;
    }

    public void setRating_proveidor(float rating_proveidor) {
        this.rating_proveidor = rating_proveidor;
    }

    public int getMesos_de_colaboracio() {
        return mesos_de_colaboracio;
    }

    public void setMesos_de_colaboracio(int mesos_de_colaboracio) {
        this.mesos_de_colaboracio = mesos_de_colaboracio;
    }

    public String getNom_proveidor() {
        return nom_proveidor;
    }

    public void setNom_proveidor(String nom_proveidor) {
        this.nom_proveidor = nom_proveidor;
    }

   

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public EstatProveidor getEstat() {
        return Estat;
    }

    public void setEstat(EstatProveidor Estat) {
        this.Estat = Estat;
    }

    @Override
    public String toString() {
        return "Proveidor{" + "nom_proveidor=" + nom_proveidor + ", cif=" + cif + ", Estat=" + Estat + ", id_proveidor=" + id_proveidor + ", motiu_inactiu=" + motiu_inactiu + ", data_creacio=" + data_creacio + ", correu_electronic=" + correu_electronic + ", rating_proveidor=" + rating_proveidor + ", mesos_de_colaboracio=" + mesos_de_colaboracio + ", actiu=" + actiu + '}';
    }

    /*public enum EstatProveidor {
        ACTIU, INACTIU
    }*/
    public enum EstatProveidor {
        ACTIU("ACTIU"), 
        INACTIU("INACTIU");
    

    private String value;

    private EstatProveidor(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
     }
}
