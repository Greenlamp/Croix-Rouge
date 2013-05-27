/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.LibCritereAndroid.Criteres;

import java.util.LinkedList;
import my.LibCritereAndroid.Recherche.TupleRecherche;

/**
 *
 * @author Greenlamp
 */
public interface DBA {
    public LinkedList<TupleRecherche> searchByCritere(String request);

}
