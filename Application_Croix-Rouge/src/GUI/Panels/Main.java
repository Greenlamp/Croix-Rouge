/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panels;

import Containers.Adresse;
import Containers.Complementaire;
import Containers.Decouverte;
import Containers.Formations;
import Containers.Grille;
import Containers.Groupe;
import Containers.Identite;
import Containers.Residence;
import Containers.Telephone;
import Containers.Urgence;
import Containers.Utilisateur;
import Containers.Volontaire;
import EquipeVolontaire.M_ConsulterEquipesVolontaire;
import EquipeVolontaire.M_NewEditEquipeVolontaire;
import EquipeVolontaire.M_SearchCrit;
import GUI.Panels.GestionDroits.M_GestionDroits;
import GUI.Panels.GestionGroupes.M_GestionGroupes;
import GUI.Panels.GestionGroupes.M_NewEditGroupes;
import GUI.Panels.GestionUtilisateurs.M_GestionUtilisateurs;
import GUI.Panels.GestionUtilisateurs.M_NewEditUtilisateurs;
import GrillesHoraires.M_ConsulterGrillesHoraires;
import GrillesHoraires.M_NewEditGrilleHoraire;
import Network.NetworkClient;
import NouveauVolontaire.M_NouveauVolontaireP1;
import NouveauVolontaire.M_NouveauVolontaireP2;
import NouveauVolontaire.M_NouveauVolontaireP3;
import NouveauVolontaire.M_NouveauVolontaireP4;
import NouveauVolontaire.M_NouveauVolontaireP5;
import NouveauVolontaire.M_NouveauVolontaireP6;
import NouveauVolontaire2.M_Adresse;
import NouveauVolontaire2.M_Decouverte;
import NouveauVolontaire2.M_Formations;
import NouveauVolontaire2.M_Identite;
import NouveauVolontaire2.M_RenseignementsComplementaires;
import NouveauVolontaire2.M_Residence;
import NouveauVolontaire2.M_Téléphone;
import NouveauVolontaire2.M_Urgence;
import PacketCom.PacketCom;
import Recherche.TupleRecherche;
import States.States;
import java.awt.Image;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

/**
 *
 * @author Greenlamp
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */

    public static String UNLOGGED = "UNLOGGED";
    public static String LOGGED = "LOGGED";
    public static String CONSULTATION = "CONSULTATION";
    public static String NOUVEAU_VOLONTAIREP1 = "NOUVEAU_VOLONTAIREP1";
    public static String NOUVEAU_VOLONTAIREP2 = "NOUVEAU_VOLONTAIREP2";
    public static String NOUVEAU_VOLONTAIREP3 = "NOUVEAU_VOLONTAIREP3";
    public static String NOUVEAU_VOLONTAIREP4 = "NOUVEAU_VOLONTAIREP4";
    public static String NOUVEAU_VOLONTAIREP5 = "NOUVEAU_VOLONTAIREP5";
    public static String NOUVEAU_VOLONTAIREP6 = "NOUVEAU_VOLONTAIREP6";
    public static String NOUVEAU_VOLONTAIREP7 = "NOUVEAU_VOLONTAIREP7";
    public static String NOUVEAU_VOLONTAIREP8 = "NOUVEAU_VOLONTAIREP8";
    public static String GESTION_DROITS = "GESTION_DROITS";
    public static String GESTION_GROUPES = "GESTION_GROUPES";
    public static String GESTION_UTILISATEURS = "GESTION_UTILISATEURS";
    public static String EDITNEWUSER = "EDITNEWUSER";
    public static String GESTION_EQUIPES = "GESTION_EQUIPES";
    public static String EDITNEWEQUIPE = "EDITNEWEQUIPE";
    public static String SEARCH_CRIT = "SEARCH_CRIT";
    public static String GESTION_GRILLES_HORAIRES = "GESTION_GRILLES_HORAIRES";
    public static String EDITNEWGRILLLE = "EDITNEWGRILLLE";
    public static String NOUVEAU_VOLONTAIRE = "NOUVEAU_VOLONTAIRE";
    public static String NOUVEAU_VOLONTAIRE_PAGE_2 = "NOUVEAU_VOLONTAIRE_PAGE_2";
    public static String NOUVEAU_VOLONTAIRE_PAGE_3 = "NOUVEAU_VOLONTAIRE_PAGE_3";
    public static String NOUVEAU_VOLONTAIRE_PAGE_4 = "NOUVEAU_VOLONTAIRE_PAGE_4";
    public static String NOUVEAU_VOLONTAIRE_PAGE_5 = "NOUVEAU_VOLONTAIRE_PAGE_5";
    public static String NOUVEAU_VOLONTAIRE_PAGE_6 = "NOUVEAU_VOLONTAIRE_PAGE_6";
    public static String NOUVEAU_VOLONTAIRE_PAGE_7 = "NOUVEAU_VOLONTAIRE_PAGE_7";
    public static String NOUVEAU_VOLONTAIRE_PAGE_8 = "NOUVEAU_VOLONTAIRE_PAGE_8";
    public static String NOUVEAU_VOLONTAIRE_PAGE_9 = "NOUVEAU_VOLONTAIRE_PAGE_9";
    public static String NOUVEAU_GROUPE = "NOUVEAU_GROUPE";
    public static String EDIT_GROUPE = "EDIT_GROUPE";
    public static String NOUVEAU_UTILISATEUR = "NOUVEAU_UTILISATEUR";
    public static String EDIT_UTILISATEUR = "EDIT_UTILISATEUR";

    private boolean connected = false;
    private LinkedList<String> droits = null;

    private String loginUser = null;
    String equipeSelected = null;
    LinkedList<TupleRecherche> listeVolontaire = null;

    private String actualState = Main.UNLOGGED;

    NetworkClient socket = null;

    Volontaire volontaire = null;
    private String matricule = null;

    private Grille grille = null;
    private Grille grilleOld = null;
    private int celluleRow;
    private int celluleColumn;
    private String typeGrille;

    private Groupe groupe = null;

    private Utilisateur utilisateur = null;


    public Main() {
        initComponents();
        ImageIcon icone = new ImageIcon(this.getClass().getResource("/Images/Croix-Rouge.jpg"));
        Image image = icone.getImage();
        setIconImage(image);
        socket = new NetworkClient("127.0.0.1", 12345, false);
        refreshPanel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Gscene = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Application de Gestion de la Croix-Rouge");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Gscene.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Gscene, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Gscene, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        disconnect();
        unlockGrille();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Gscene;
    // End of variables declaration//GEN-END:variables


    void connectSql() {
        this.socket.connect();
    }

    void disconnectSql() {
        this.socket.disconnect();
    }

    public void changeState(String state) {
        this.setActualState(state);
        refreshPanel();
    }

    void setDroits(LinkedList<String> droits) {
        this.droits = droits;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
        this.loadBackup();
    }

    private void refreshPanel() {
        cleanState();
        if(this.getActualState().equals(Main.UNLOGGED)){
            M_Connexion mConnexion = new M_Connexion(this, this.socket);
            Gscene.add(mConnexion);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.LOGGED)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            Menu menu = new Menu(this);
            Gscene.add(menu);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.CONSULTATION)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_Consultation mConsultation = new M_Consultation(this, this.socket);
            Gscene.add(mConsultation);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_VOLONTAIRE)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            //M_NouveauVolontaire m_NouveauVolontaire = new M_NouveauVolontaire(this, this.socket);
            //Gscene.add(m_NouveauVolontaire);

            M_Identite mIdentite = new M_Identite(this, socket);
            Gscene.add(mIdentite);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_VOLONTAIRE_PAGE_2)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            //M_NouveauVolontaire m_NouveauVolontaire = new M_NouveauVolontaire(this, this.socket);
            //Gscene.add(m_NouveauVolontaire);

            M_Decouverte mDecouverte = new M_Decouverte(this, socket);
            Gscene.add(mDecouverte);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_VOLONTAIRE_PAGE_3)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_RenseignementsComplementaires mRenseignementsComplementaires = new M_RenseignementsComplementaires(this, socket);
            Gscene.add(mRenseignementsComplementaires);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_VOLONTAIRE_PAGE_4)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_Adresse mAdresse = new M_Adresse(this, socket);
            Gscene.add(mAdresse);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_VOLONTAIRE_PAGE_5)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_Residence mResidence = new M_Residence(this, socket);
            Gscene.add(mResidence);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_VOLONTAIRE_PAGE_6)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_Téléphone mTéléphone = new M_Téléphone(this, socket);
            Gscene.add(mTéléphone);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_VOLONTAIRE_PAGE_7)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_Urgence mUrgence = new M_Urgence(this, socket);
            Gscene.add(mUrgence);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_VOLONTAIRE_PAGE_8)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_Formations mFormations = new M_Formations(this, socket);
            Gscene.add(mFormations);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_VOLONTAIREP1)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NouveauVolontaireP1 m_NouveauVolontaireP1 = new M_NouveauVolontaireP1(this, this.socket);
            Gscene.add(m_NouveauVolontaireP1);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_VOLONTAIREP2)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NouveauVolontaireP2 m_NouveauVolontaireP2 = new M_NouveauVolontaireP2(this, this.socket);
            Gscene.add(m_NouveauVolontaireP2);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_VOLONTAIREP3)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NouveauVolontaireP3 m_NouveauVolontaireP3 = new M_NouveauVolontaireP3(this, this.socket);
            Gscene.add(m_NouveauVolontaireP3);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_VOLONTAIREP4)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NouveauVolontaireP4 m_NouveauVolontaireP4 = new M_NouveauVolontaireP4(this, this.socket);
            Gscene.add(m_NouveauVolontaireP4);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_VOLONTAIREP5)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NouveauVolontaireP5 m_NouveauVolontaireP5 = new M_NouveauVolontaireP5(this, this.socket);
            Gscene.add(m_NouveauVolontaireP5);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_VOLONTAIREP6)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NouveauVolontaireP6 m_NouveauVolontaireP6 = new M_NouveauVolontaireP6(this, this.socket);
            Gscene.add(m_NouveauVolontaireP6);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.GESTION_DROITS)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_GestionDroits mGestionDroits = new M_GestionDroits(this, this.socket);
            Gscene.add(mGestionDroits);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.GESTION_GROUPES)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_GestionGroupes mGestionGroupes = new M_GestionGroupes(this, this.socket);
            Gscene.add(mGestionGroupes);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.GESTION_UTILISATEURS)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_GestionUtilisateurs mGestionUtilisateurs = new M_GestionUtilisateurs(this, this.socket);
            Gscene.add(mGestionUtilisateurs);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.EDITNEWUSER)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NewEditUtilisateurs fenetre = new M_NewEditUtilisateurs(this, this.socket, "Modifier un utilisateur");
            Gscene.add(fenetre);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.EDITNEWGRILLLE)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NewEditGrilleHoraire m_NewEditGrilleHoraire = new M_NewEditGrilleHoraire(this, this.socket, "Nouvelle grille d'horaire");
            Gscene.add(m_NewEditGrilleHoraire);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.GESTION_EQUIPES)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_ConsulterEquipesVolontaire mConsulterEquipesVolontaire = new M_ConsulterEquipesVolontaire(this, socket);
            Gscene.add(mConsulterEquipesVolontaire);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.EDITNEWEQUIPE)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NewEditEquipeVolontaire mNewEditEquipeVolontaire = new M_NewEditEquipeVolontaire(this, socket);
            Gscene.add(mNewEditEquipeVolontaire);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.SEARCH_CRIT)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_SearchCrit mSearchCrit = new M_SearchCrit(this, socket);
            Gscene.add(mSearchCrit);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_GROUPE)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NewEditGroupes mNewEditGroupes = new M_NewEditGroupes(this, socket, "Nouveau groupe");
            Gscene.add(mNewEditGroupes);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.EDIT_GROUPE)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NewEditGroupes mNewEditGroupes = new M_NewEditGroupes(this, socket, "Modifier un groupe");
            Gscene.add(mNewEditGroupes);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.NOUVEAU_UTILISATEUR)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NewEditUtilisateurs mNewEditUtilisateurs = new M_NewEditUtilisateurs(this, socket, "Nouveau utilisateur");
            Gscene.add(mNewEditUtilisateurs);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.EDIT_UTILISATEUR)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NewEditUtilisateurs mNewEditUtilisateurs = new M_NewEditUtilisateurs(this, socket, "Modifier un utilisateur");
            Gscene.add(mNewEditUtilisateurs);
            Gscene.revalidate();
        }else if(this.getActualState().equals(Main.GESTION_GRILLES_HORAIRES)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_ConsulterGrillesHoraires m_ConsulterGrillesHoraires = new M_ConsulterGrillesHoraires(this, socket);
            Gscene.add(m_ConsulterGrillesHoraires);
            Gscene.revalidate();
        }
    }

    public Volontaire getVolontaire(){
        return volontaire;
    }

    public void setVolontaireP1(Identite identite, Adresse adresse, Residence residence) {
        volontaire.setIdentite(identite);
        volontaire.setAdresse(adresse);
        volontaire.setResidence(residence);
    }

    public void setVolontaireP2(Telephone telephone, Urgence urgence) {
        volontaire.setTelephone(telephone);
        volontaire.setUrgence(urgence);
    }

    public void setVolontaireP3(Decouverte decouverte) {
        volontaire.setDecouverte(decouverte);
    }


    public void setVolontaireP4(Complementaire complementaire) {
        volontaire.setComplementaire(complementaire);
    }

    public void setVolontaireP5(Formations formations) {
        volontaire.setFormations(formations);
    }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
        //System.out.println("Message : " + message);
    }

    public void setNomEquipeSelected(String equipeSelected) {
        this.equipeSelected = equipeSelected;
    }

    public String getEquipeSelected() {
        return this.equipeSelected;
    }

    public LinkedList<TupleRecherche> getListeVolontaire() {
        return this.listeVolontaire;
    }

    public void setListeVolontaire(LinkedList<TupleRecherche> listeVolontaire) {
        this.listeVolontaire = listeVolontaire;
    }

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public void cleanState(){
        if(!(actualState.equals(Main.EDITNEWGRILLLE) || actualState.equals(Main.SEARCH_CRIT))){
            unlockGrille();
            setGrille(null);
            setGrilleOld(null);
        }
        if(!actualState.equals(Main.NOUVEAU_VOLONTAIRE) && !actualState.equals(Main.NOUVEAU_VOLONTAIRE_PAGE_2) && !actualState.equals(Main.NOUVEAU_VOLONTAIRE_PAGE_3) && !actualState.equals(Main.NOUVEAU_VOLONTAIRE_PAGE_4) && !actualState.equals(Main.NOUVEAU_VOLONTAIRE_PAGE_5) && !actualState.equals(Main.NOUVEAU_VOLONTAIRE_PAGE_6) && !actualState.equals(Main.NOUVEAU_VOLONTAIRE_PAGE_7) && !actualState.equals(Main.NOUVEAU_VOLONTAIRE_PAGE_8) && !actualState.equals(Main.NOUVEAU_VOLONTAIRE_PAGE_9)){
            matricule = null;
            //volontaire = null;
        }
        if(!actualState.equals(Main.NOUVEAU_GROUPE) && ! actualState.equals(Main.EDIT_GROUPE)){
            groupe = null;
        }
        if(!actualState.equals(Main.NOUVEAU_UTILISATEUR) && ! actualState.equals(Main.EDIT_UTILISATEUR)){
            utilisateur = null;
        }
    }

    public int getCelluleRow() {
        return celluleRow;
    }

    public void setCelluleRow(int celluleRow) {
        this.celluleRow = celluleRow;
    }

    public int getCelluleColumn() {
        return celluleColumn;
    }

    public void setCelluleColumn(int celluleColumn) {
        this.celluleColumn = celluleColumn;
    }

    public void setTypeGrille(String type) {
        this.typeGrille = type;
    }

    public String getTypeGrille(){
        return this.typeGrille;
    }

    public Grille getGrilleOld() {
        return grilleOld;
    }

    public void setGrilleOld(Grille grilleOld) {
        this.grilleOld = grilleOld;
    }

    private void unlockGrille() {
        if(grille != null){
            PacketCom packet = new PacketCom(States.UNLOCK_GRILLE, (Object)grille);
            socket.send(packet);
            try {
                PacketCom packetReponse = socket.receive();
                if(packetReponse.getType().equals(States.UNLOCK_GRILLE_NON)){
                    afficherMessage("erreur de déverouillage de grille");
                }
            } catch (Exception ex) {
                Logger.getLogger(M_ConsulterGrillesHoraires.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void Log(Exception e){
        FileHandler fh = null;
        try {
            fh = new FileHandler("log.txt", true);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Logger logger = Logger.getLogger("logger");
        logger.addHandler(fh);
        logger.log(Level.SEVERE, null, e);
    }

    public String getActualState() {
        return actualState;
    }

    public void setActualState(String actualState) {
        this.actualState = actualState;
    }

    public void disconnect() {
        if(connected){
            PacketCom packet = new PacketCom(States.DISCONNECT, null);
            socket.send(packet);
            try {
                socket.receive();
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            connected = false;
        }
        this.socket.disconnect();
        Main main = new Main();
        main.setVisible(true);
        this.dispose();
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setVolontaire(Volontaire volontaire) {
        this.volontaire = volontaire;
        this.volontaire.backupTexte(loginUser);
    }

    public void cleanBackup() {
        if(this.volontaire != null){
            this.volontaire.clearBackupTexte(loginUser);
            this.volontaire = null;
            this.matricule = null;
        }
    }

    public void loadBackup(){
        Volontaire volontaire = new Volontaire();
        volontaire.loadBackupTexte(loginUser);
        if(volontaire.getIdentite() != null){
            this.volontaire = volontaire;
        }
    }

    public Groupe getGroupe() {
        return this.groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
