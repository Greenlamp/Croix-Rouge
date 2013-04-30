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
import Containers.Identite;
import Containers.Key;
import Containers.Residence;
import Containers.Telephone;
import Containers.Urgence;
import Containers.Volontaire;
import EquipeVolontaire.M_ConsulterEquipesVolontaire;
import EquipeVolontaire.M_NewEditEquipeVolontaire;
import EquipeVolontaire.M_SearchCrit;
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
import PacketCom.PacketCom;
import Recherche.TupleRecherche;
import States.States;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sun.util.calendar.Gregorian;

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

    private boolean connected = false;
    private String groupe = null;
    private LinkedList<String> droits = null;

    private String loginUser = null;
    String equipeSelected = null;
    LinkedList<TupleRecherche> listeVolontaire = null;

    private String actualState = Main.UNLOGGED;

    NetworkClient socket = null;

    Volontaire volontaire = new Volontaire();

    private Grille grille = null;
    private Grille grilleOld = null;
    private int celluleRow;
    private int celluleColumn;
    private String typeGrille;


    public Main() {
        initComponents();
        ImageIcon icone = new ImageIcon(this.getClass().getResource("/Images/Croix-Rouge.jpg"));
        Image image = icone.getImage();
        setIconImage(image);
        socket = new NetworkClient("127.0.0.1", 12345, false);
        hide("all");
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Mfichier = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        Mmenu = new javax.swing.JMenu();
        Mconsulter = new javax.swing.JMenuItem();
        MnouveauVolontaire = new javax.swing.JMenuItem();
        MgererEquipe = new javax.swing.JMenuItem();
        MgererGrillesHoraires = new javax.swing.JMenuItem();
        Madministration = new javax.swing.JMenu();
        MgestionDroits = new javax.swing.JMenuItem();
        MgestionGroupes = new javax.swing.JMenuItem();
        MgestionUtilisateurs = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Application de Gestion de la Croix-Rouge");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Gscene.setLayout(new java.awt.BorderLayout());

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Croix-Rouge.jpg"))); // NOI18N
        jMenu1.setFocusable(false);
        jMenuBar1.add(jMenu1);

        Mfichier.setText("Fichier");

        jMenuItem6.setText("Déconnexion");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        Mfichier.add(jMenuItem6);

        jMenuBar1.add(Mfichier);

        Mmenu.setText("Menu");

        Mconsulter.setText("Consulter");
        Mconsulter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MconsulterActionPerformed(evt);
            }
        });
        Mmenu.add(Mconsulter);

        MnouveauVolontaire.setText("Nouveau volontaire");
        MnouveauVolontaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnouveauVolontaireActionPerformed(evt);
            }
        });
        Mmenu.add(MnouveauVolontaire);

        MgererEquipe.setText("Gérer equipes de volontaires");
        MgererEquipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MgererEquipeActionPerformed(evt);
            }
        });
        Mmenu.add(MgererEquipe);

        MgererGrillesHoraires.setText("Gérer grilles horaires");
        MgererGrillesHoraires.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MgererGrillesHorairesActionPerformed(evt);
            }
        });
        Mmenu.add(MgererGrillesHoraires);

        jMenuBar1.add(Mmenu);

        Madministration.setText("Administration");

        MgestionDroits.setText("Gestion Droits");
        MgestionDroits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MgestionDroitsActionPerformed(evt);
            }
        });
        Madministration.add(MgestionDroits);

        MgestionGroupes.setText("Gestion Groupes");
        MgestionGroupes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MgestionGroupesActionPerformed(evt);
            }
        });
        Madministration.add(MgestionGroupes);

        MgestionUtilisateurs.setText("Gestion Utilisateurs");
        MgestionUtilisateurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MgestionUtilisateursActionPerformed(evt);
            }
        });
        Madministration.add(MgestionUtilisateurs);

        jMenuBar1.add(Madministration);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Gscene, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Gscene, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void MconsulterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MconsulterActionPerformed
        if(this.actualState.equals(Main.UNLOGGED)){
            return;
        }
        this.actualState = Main.CONSULTATION;
        refreshPanel();
    }//GEN-LAST:event_MconsulterActionPerformed

    private void MnouveauVolontaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnouveauVolontaireActionPerformed
        if(this.actualState.equals(Main.UNLOGGED)){
            return;
        }
        this.actualState = Main.NOUVEAU_VOLONTAIREP1;
        refreshPanel();
    }//GEN-LAST:event_MnouveauVolontaireActionPerformed

    private void MgestionDroitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MgestionDroitsActionPerformed
        if(this.actualState.equals(Main.UNLOGGED)){
            return;
        }
        this.actualState = Main.GESTION_DROITS;
        refreshPanel();
    }//GEN-LAST:event_MgestionDroitsActionPerformed

    private void MgestionGroupesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MgestionGroupesActionPerformed
        if(this.actualState.equals(Main.UNLOGGED)){
            return;
        }
        this.actualState = Main.GESTION_GROUPES;
        refreshPanel();
    }//GEN-LAST:event_MgestionGroupesActionPerformed

    private void MgestionUtilisateursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MgestionUtilisateursActionPerformed
        if(this.actualState.equals(Main.UNLOGGED)){
            return;
        }
        this.actualState = Main.GESTION_UTILISATEURS;
        refreshPanel();
    }//GEN-LAST:event_MgestionUtilisateursActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        this.socket.disconnect();
        Main main = new Main();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void MgererEquipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MgererEquipeActionPerformed
        if(this.actualState.equals(Main.UNLOGGED)){
            return;
        }
        this.actualState = Main.GESTION_EQUIPES;
        refreshPanel();
    }//GEN-LAST:event_MgererEquipeActionPerformed

    private void MgererGrillesHorairesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MgererGrillesHorairesActionPerformed
        if(this.actualState.equals(Main.UNLOGGED)){
            return;
        }
        this.actualState = Main.GESTION_GRILLES_HORAIRES;
        refreshPanel();
    }//GEN-LAST:event_MgererGrillesHorairesActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
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
    private javax.swing.JMenu Madministration;
    private javax.swing.JMenuItem Mconsulter;
    private javax.swing.JMenu Mfichier;
    private javax.swing.JMenuItem MgererEquipe;
    private javax.swing.JMenuItem MgererGrillesHoraires;
    private javax.swing.JMenuItem MgestionDroits;
    private javax.swing.JMenuItem MgestionGroupes;
    private javax.swing.JMenuItem MgestionUtilisateurs;
    private javax.swing.JMenu Mmenu;
    private javax.swing.JMenuItem MnouveauVolontaire;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem6;
    // End of variables declaration//GEN-END:variables


    void connectSql() {
        this.socket.connect();
    }

    void disconnectSql() {
        this.socket.disconnect();
    }

    public void changeState(String state) {
        this.actualState = state;
        refreshPanel();
    }

    void setDroits(LinkedList<String> droits) {
        this.droits = droits;
    }

    void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    private void refreshPanel() {
        cleanState();
        if(this.actualState.equals(Main.UNLOGGED)){
            M_Connexion mConnexion = new M_Connexion(this, this.socket);
            Gscene.add(mConnexion);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.LOGGED)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();
            hide("interdit");
        }else if(this.actualState.equals(Main.CONSULTATION)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_Consultation mConsultation = new M_Consultation(this, this.socket);
            Gscene.add(mConsultation);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.NOUVEAU_VOLONTAIREP1)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NouveauVolontaireP1 m_NouveauVolontaireP1 = new M_NouveauVolontaireP1(this, this.socket);
            Gscene.add(m_NouveauVolontaireP1);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.NOUVEAU_VOLONTAIREP2)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NouveauVolontaireP2 m_NouveauVolontaireP2 = new M_NouveauVolontaireP2(this, this.socket);
            Gscene.add(m_NouveauVolontaireP2);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.NOUVEAU_VOLONTAIREP3)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NouveauVolontaireP3 m_NouveauVolontaireP3 = new M_NouveauVolontaireP3(this, this.socket);
            Gscene.add(m_NouveauVolontaireP3);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.NOUVEAU_VOLONTAIREP4)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NouveauVolontaireP4 m_NouveauVolontaireP4 = new M_NouveauVolontaireP4(this, this.socket);
            Gscene.add(m_NouveauVolontaireP4);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.NOUVEAU_VOLONTAIREP5)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NouveauVolontaireP5 m_NouveauVolontaireP5 = new M_NouveauVolontaireP5(this, this.socket);
            Gscene.add(m_NouveauVolontaireP5);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.NOUVEAU_VOLONTAIREP6)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NouveauVolontaireP6 m_NouveauVolontaireP6 = new M_NouveauVolontaireP6(this, this.socket);
            Gscene.add(m_NouveauVolontaireP6);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.GESTION_DROITS)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_GestionDroits mGestionDroits = new M_GestionDroits(this, this.socket);
            Gscene.add(mGestionDroits);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.GESTION_GROUPES)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_GestionGroupes mGestionGroupes = new M_GestionGroupes(this, this.socket);
            Gscene.add(mGestionGroupes);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.GESTION_UTILISATEURS)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_GestionUtilisateurs mGestionUtilisateurs = new M_GestionUtilisateurs(this, this.socket);
            Gscene.add(mGestionUtilisateurs);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.EDITNEWUSER)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NewEditUtilisateurs fenetre = new M_NewEditUtilisateurs(this, this.socket, "Modifier un utilisateur", loginUser);
            Gscene.add(fenetre);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.EDITNEWGRILLLE)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NewEditGrilleHoraire m_NewEditGrilleHoraire = new M_NewEditGrilleHoraire(this, this.socket, "Nouvelle grille d'horaire");
            Gscene.add(m_NewEditGrilleHoraire);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.GESTION_EQUIPES)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_ConsulterEquipesVolontaire mConsulterEquipesVolontaire = new M_ConsulterEquipesVolontaire(this, socket);
            Gscene.add(mConsulterEquipesVolontaire);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.EDITNEWEQUIPE)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_NewEditEquipeVolontaire mNewEditEquipeVolontaire = new M_NewEditEquipeVolontaire(this, socket);
            Gscene.add(mNewEditEquipeVolontaire);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.SEARCH_CRIT)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_SearchCrit mSearchCrit = new M_SearchCrit(this, socket);
            Gscene.add(mSearchCrit);
            Gscene.revalidate();
        }else if(this.actualState.equals(Main.GESTION_GRILLES_HORAIRES)){
            Gscene.removeAll();
            Gscene.repaint();
            Gscene.revalidate();

            M_ConsulterGrillesHoraires m_ConsulterGrillesHoraires = new M_ConsulterGrillesHoraires(this, socket);
            Gscene.add(m_ConsulterGrillesHoraires);
            Gscene.revalidate();
        }
    }

    private void cacherInterdit() {
        //Cacher bouton administration
        if(!droits.contains("SEE_ADMIN")){
            Madministration.setVisible(false);
        }
    }

    private void hide(String type){
        if(type.equals("all")){
            Madministration.setVisible(false);
            MgestionDroits.setVisible(false);
            MgestionGroupes.setVisible(false);
            MgestionUtilisateurs.setVisible(false);
            MnouveauVolontaire.setVisible(false);
            MgererEquipe.setVisible(false);
            MgererGrillesHoraires.setVisible(false);
        }else if(type.equals("interdit")){
            if(droits.contains("SEE_ADMIN")){
                Madministration.setVisible(true);
            }
            if(droits.contains("SEE_MANAGE_RIGHTS")){
                MgestionDroits.setVisible(true);
            }
            if(droits.contains("SEE_MANAGE_GROUP")){
                MgestionGroupes.setVisible(true);
            }
            if(droits.contains("SEE_MANAGE_USER")){
                MgestionUtilisateurs.setVisible(true);
            }
            if(droits.contains("CREATE_VOLUNTEER")){
                MnouveauVolontaire.setVisible(true);
            }
            if(droits.contains("SEE_MANAGER_TEAM")){
                MgererEquipe.setVisible(true);
            }
            MgererGrillesHoraires.setVisible(true);
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
        if(!actualState.equals(Main.NOUVEAU_VOLONTAIREP1) && !actualState.equals(Main.NOUVEAU_VOLONTAIREP2) && !actualState.equals(NOUVEAU_VOLONTAIREP3) && !actualState.equals(NOUVEAU_VOLONTAIREP4) && !actualState.equals(NOUVEAU_VOLONTAIREP5) && !actualState.equals(NOUVEAU_VOLONTAIREP6) && !actualState.equals(NOUVEAU_VOLONTAIREP7) && !actualState.equals(NOUVEAU_VOLONTAIREP8)){
            setVolontaireP1(null, null, null);
            setVolontaireP2(null, null);
            setVolontaireP3(null);
            setVolontaireP4(null);
            setVolontaireP5(null);
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
}
