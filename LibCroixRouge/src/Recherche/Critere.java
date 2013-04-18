/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Recherche;

import java.io.Serializable;


public class Critere implements Serializable{
    private int numero;
    private String type;
    private String donnee;

    public Critere(){
        setNumero(-1);
        setType(null);
        setDonnee(null);
    }

    public Critere(int numero, String type, String donnee){
        setNumero(numero);
        setType(type);
        setDonnee(donnee);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDonnee() {
        return donnee;
    }

    public void setDonnee(String donnee) {
        this.donnee = donnee;
    }
}
