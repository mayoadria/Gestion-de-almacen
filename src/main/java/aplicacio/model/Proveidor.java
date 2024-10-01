/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

/**
 *
 * @author Anna
 */
public class Proveidor {

    String nom;
    String cif;
    EstatProveidor Estat;

    public Proveidor(String nom, String cif, EstatProveidor Estat) {
        this.nom = nom;
        this.cif = cif;
        this.Estat = Estat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
        return "Proveidor{" + "nom=" + nom + ", cif=" + cif + ", Estat=" + Estat + '}';
    }

    public enum EstatProveidor {
        ACTIU, INACTIU
    }

}
