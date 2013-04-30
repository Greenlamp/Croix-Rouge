/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Recherche.Criteres;

import Recherche.TupleRecherche;
import java.util.LinkedList;

/**
 *
 * @author Greenlamp
 */
public interface DBA {
    public LinkedList<TupleRecherche> searchByCritere(String request);

}
