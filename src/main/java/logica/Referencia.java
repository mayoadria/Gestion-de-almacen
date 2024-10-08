/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.sql.Date;

/**
 *
 * @author mayoa
 */
public class Referencia {
    private int id;
    private String nom;
    private int quantitat;
    private String unitat_mida;
    private Date data_alta;
    private Date data_fabricacio;
    private String descripcio;
    private float preu;
    private int unitats_venudes;
    private int id_fam;
    private int id_proveidor;

    public Referencia() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public String getUnitat_mida() {
        return unitat_mida;
    }

    public void setUnitat_mida(String unitat_mida) {
        this.unitat_mida = unitat_mida;
    }

    public Date getData_alta() {
        return data_alta;
    }

    public void setData_alta(Date data_alta) {
        this.data_alta = data_alta;
    }

    public Date getData_fabricacio() {
        return data_fabricacio;
    }

    public void setData_fabricacio(Date data_fabricacio) {
        this.data_fabricacio = data_fabricacio;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public int getUnitats_venudes() {
        return unitats_venudes;
    }

    public void setUnitats_venudes(int unitats_venudes) {
        this.unitats_venudes = unitats_venudes;
    }

    public int getId_fam() {
        return id_fam;
    }

    public void setId_fam(int id_fam) {
        this.id_fam = id_fam;
    }

    public int getId_proveidor() {
        return id_proveidor;
    }

    public void setId_proveidor(int id_proveidor) {
        this.id_proveidor = id_proveidor;
    }
    
    @Override
    public String toString() {
        return "Id: "+id + " ; nom: " + nom + " ; Id familia: " + id_fam + " ; ID proveïdor: " + id_proveidor + 
                "; data de fabricacio: " + data_fabricacio +" ; data d'alta: " + data_alta +
                " ;preu : " + preu + " ; quantitats: " + quantitat+ " ;unitats venudes: " + unitats_venudes
                + " ; descripcio:" + descripcio+ " ;unitaat de mida: " + unitat_mida; 
    }

    
}
