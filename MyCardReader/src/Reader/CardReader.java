/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Reader;

import Containers.Carte;
import be.belgium.eid.eidlib.BeID;
import be.belgium.eid.event.CardListener;
import be.belgium.eid.exceptions.EIDException;
import be.belgium.eid.objects.IDAddress;
import be.belgium.eid.objects.IDData;
import be.belgium.eid.objects.IDPhoto;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

public class CardReader implements CardListener, Runnable{
    private BeID eid;
    private IDData idData;
    private IDAddress idAddress;
    private IDPhoto idPhoto;
    private Carte carte;
    private boolean filled;
    private boolean inited;
    JTextField gNom;
    private boolean ready;
    LinkedList<String> test;

    public CardReader(JTextField gNom){
        setCarte(new Carte());
        this.gNom = gNom;
        test = new LinkedList<>();
        setReady(false);
    }

    public void start(){
        setEid(new BeID(true));
        setCarte(carte);
        setFilled(false);
        getEid().enableCardListener(this);
    }

    public void init() throws EIDException{
        setIdData(getEid().getIDData());
        setIdAddress(getEid().getIDAddress());
        setIdPhoto(getEid().getIDPhoto());
    }

    public void addListener(CardListener listener){
        getEid().enableCardListener(listener);
    }

    public String getSexe(){
        String sexe = null;
        char lettre = getIdData().getSex();
        sexe = Character.toString(lettre);

        return sexe;
    }

    public String getNom(){
        String nom = null;
        nom = getIdData().getName();

        return nom;
    }

    public String getPrenom(){
        String prenom = null;
        prenom = getIdData().get1stFirstname();
        if(!prenom.isEmpty()){
            prenom = prenom.split(" ")[0];
        }

        return prenom;
    }

    public String getRue(){
        String rue = null;
        rue = getIdAddress().getStreet();
        String[] split = rue.split(" ");
        int count = split.length;
        int i=0;
        StringBuilder sb = new StringBuilder();
        for(String elm : split){
            try{
                int num = Integer.parseInt(elm);
            }catch(Exception ex){
                sb.append(elm);
                if(i != count - 2){
                    sb.append(" ");
                }
            }
            i++;
        }
        rue = sb.toString();
        return rue;
    }

    public int getNumero(){
        String rue = null;
        int numero = -1;
        rue = getIdAddress().getStreet();
        String[] split = rue.split(" ");
        for(String elm : split){
            try{
                int num = Integer.parseInt(elm);
                numero = num;
            }catch(Exception ex){
            }
        }
        return numero;
    }

    public String getDateNaissance(){
        String dateNaissance = null;
        Date birthDate = getIdData().getBirthDate();
        dateNaissance = new SimpleDateFormat("dd/MM/yyyy").format(birthDate);
        return dateNaissance;
    }

    public int getCodePostal(){
        int codePostal = -1;
        String zipCode = getIdAddress().getZipCode();
        if(!zipCode.isEmpty()){
            codePostal = Integer.parseInt(zipCode);
        }
        return codePostal;
    }

    public String getVille(){
        String localite = null;
        localite = getIdAddress().getMunicipality();
        return localite;
    }

    public String getNationalite() {
        String nationalite = null;
        nationalite = getIdData().getNationality();
        return nationalite;
    }

    public byte[] getPhoto(){
        byte[] photo = null;
        photo = getIdPhoto().getPhoto();
        return photo;
    }

    public BeID getEid() {
        return eid;
    }

    public void setEid(BeID eid) {
        this.eid = eid;
    }

    public IDData getIdData() {
        return idData;
    }

    public void setIdData(IDData idData) {
        this.idData = idData;
    }

    public IDAddress getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(IDAddress idAddress) {
        this.idAddress = idAddress;
    }

    public IDPhoto getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(IDPhoto idPhoto) {
        this.idPhoto = idPhoto;
    }

    public void fill(Carte carte) {
        carte.setNom(this.getNom());
        carte.setPrenom(this.getPrenom());
        carte.setSexe(this.getSexe());
        carte.setRue(this.getRue());
        carte.setNumero(this.getNumero());
        carte.setDateNaissance(this.getDateNaissance());
        carte.setCodePostal(this.getCodePostal());
        carte.setVille(this.getVille());
        carte.setNationalite(this.getNationalite());
        carte.setPhoto(this.getPhoto());
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean isInited() {
        return inited;
    }

    public void setInited(boolean inited) {
        this.inited = inited;
    }

    @Override
    public void cardInserted() {
        if(!inited){
            try {
                this.init();
            } catch (EIDException ex) {
                setInited(false);
            }
            if(!filled){
                this.fill(getCarte());
                if(carte == null){
                    setFilled(false);
                }else{
                    setFilled(true);
                }
            }
            fillChamp();
        }
    }

    public void fillChamp(){
        while(true){
            synchronized(test){
                while(test.isEmpty()){
                    try {
                        test.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CardReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    gNom.setText(carte.getNom());
                    test.clear();
                }
            }
        }
    }

    @Override
    public void cardRemoved() {
        setCarte(new Carte());
        setInited(false);
        setFilled(false);
    }

    @Override
    public void run() {
        start();
    }

    public Carte getData(){
        System.out.println("getData");
        try {
            int cpt = 0;
            while(cpt < 5){
                if(isFilled()){
                    System.out.println("filled");
                    return carte;
                }
                Thread.sleep(1000);
                cpt++;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(CardReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        synchronized(test){
            this.ready = ready;
            if(ready == true){
                test.add("a");
                test.notify();
            }else{
                test.clear();
            }
        }
    }
}
