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
public interface CritereCustom {
    public void doSearch();
    public LinkedList<TupleRecherche> getResultats();

}
