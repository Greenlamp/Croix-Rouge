/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;


public class KeyPair {
    private String key;
    private Object value;

    public KeyPair(){
        setKey(null);
        setValue(null);
    }

    public KeyPair(String key, Object value){
        setKey(key);
        setValue(value);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof KeyPair)) return false;
        KeyPair other = (KeyPair) o;
        return this.getKey().equals(other.getKey());
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
