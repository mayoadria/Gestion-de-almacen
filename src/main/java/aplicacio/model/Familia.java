/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

import java.sql.Date;

/**
 *
 * @author oriol
 */
public class Familia {
    private int id_fam;
    private String nom_familia;
    private String descripcio;
    private Date data_alta_fam;
    private int id_proveidor_fam;
    private String Observacions;

    public Familia(int id_fam, String nom_familia, String descripcio, Date data_alta_fam, int id_proveidor_fam, String Observacions) {
        this.id_fam = id_fam;
        this.nom_familia = nom_familia;
        this.descripcio = descripcio;
        this.data_alta_fam = data_alta_fam;
        this.id_proveidor_fam = id_proveidor_fam;
        this.Observacions = Observacions;
    }

    public void setId_fam(int id_fam) {
        this.id_fam = id_fam;
    }

    public void setNom_familia(String nom_familia) {
        this.nom_familia = nom_familia;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setData_alta_fam(Date data_alta_fam) {
        this.data_alta_fam = data_alta_fam;
    }

    public void setId_proveidor_fam(int id_proveidor_fam) {
        this.id_proveidor_fam = id_proveidor_fam;
    }

    public void setObservacions(String Observacions) {
        this.Observacions = Observacions;
    }

    public int getId_fam() {
        return id_fam;
    }

    public String getNom_familia() {
        return nom_familia;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public Date getData_alta_fam() {
        return data_alta_fam;
    }

    public int getId_proveidor_fam() {
        return id_proveidor_fam;
    }

    public String getObservacions() {
        return Observacions;
    }

    @Override
    public String toString() {
        return "Familia{" + "id_fam=" + id_fam + ", nom_familia=" + nom_familia 
                + ", descripcio=" + descripcio + ", data_alta_fam=" 
                + data_alta_fam + ", id_proveidor_fam=" + id_proveidor_fam 
                + ", Observacions=" + Observacions + '}';
    }
    
    
}
