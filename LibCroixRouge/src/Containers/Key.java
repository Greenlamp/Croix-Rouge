/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.Serializable;


public class Key implements Serializable{
    private int x;
    private int y;
    private CelluleGrille value;

    public Key(){

    }

    public Key(int x, int y, CelluleGrille value){
        setX(x);
        setY(y);
        setValue(value);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof Key)) return false;
        Key other = (Key) o;
        return x == other.x && y == other.y;
    }

    public CelluleGrille getValue() {
        return value;
    }

    public void setValue(CelluleGrille value) {
        this.value = value;
    }

}
