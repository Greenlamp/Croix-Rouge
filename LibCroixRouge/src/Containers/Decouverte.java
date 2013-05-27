/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Decouverte implements Serializable{
    private String description;

    public Decouverte(){
        setDescription(null);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description != null && description.isEmpty()) description = null;
        this.description = description;
    }
}
