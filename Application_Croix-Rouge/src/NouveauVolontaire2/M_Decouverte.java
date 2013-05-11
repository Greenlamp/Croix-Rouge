/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NouveauVolontaire2;

import Containers.Decouverte;
import Containers.Volontaire;
import GUI.Panels.Main;
import Network.NetworkClient;
import PacketCom.PacketCom;
import States.States;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;

/**
 *
 * @author Greenlamp
 */
public class M_Decouverte extends javax.swing.JPanel {

    /**
     * Creates new form M_Decouverte
     */
    Main parent = null;
    NetworkClient socket = null;
    Volontaire volontaire = null;
    String matricule = null;
    boolean edited = false;
    public M_Decouverte(Main parent, NetworkClient socket) {
        initComponents();
        this.socket = socket;
        this.parent = parent;
        matricule = parent.getMatricule();
        if(matricule != null){
            edited = true;
            Gtitre.setText("Modifier un volontaire");
        }else{
            edited = false;
        }
        volontaire = parent.getVolontaire();
        if(volontaire != null){
            if(volontaire.getDecouverte() != null){
                this.completerChampDecouverte();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GbuttonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        Gtitre = new javax.swing.JLabel();
        Baccueil = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        G1 = new javax.swing.JRadioButton();
        G2 = new javax.swing.JRadioButton();
        G3 = new javax.swing.JRadioButton();
        G4 = new javax.swing.JRadioButton();
        G5 = new javax.swing.JRadioButton();
        G6 = new javax.swing.JRadioButton();
        G7 = new javax.swing.JRadioButton();
        G8 = new javax.swing.JRadioButton();
        G9 = new javax.swing.JRadioButton();
        Gprecision = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        Gtitre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Gtitre.setText("Nouveau volontaire");

        Baccueil.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Baccueil.setForeground(new java.awt.Color(0, 0, 255));
        Baccueil.setText("ACCUEIL");
        Baccueil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BaccueilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Baccueil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Gtitre)
                .addContainerGap(1026, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Gtitre)
                    .addComponent(Baccueil))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Comment avez-vous découvert le volontariat à la Croix-Rouge ?");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(810, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));

        G1.setBackground(new java.awt.Color(153, 153, 153));
        GbuttonGroup.add(G1);
        G1.setText("Un volontaire m'en a parlé");

        G2.setBackground(new java.awt.Color(153, 153, 153));
        GbuttonGroup.add(G2);
        G2.setText("Par un article/annonce dans la presse");

        G3.setBackground(new java.awt.Color(153, 153, 153));
        GbuttonGroup.add(G3);
        G3.setText("Via un stand Croix-Rouge présent sur un évènement");

        G4.setBackground(new java.awt.Color(153, 153, 153));
        GbuttonGroup.add(G4);
        G4.setText("Par un reportage à la TV ou à la radio");

        G5.setBackground(new java.awt.Color(153, 153, 153));
        GbuttonGroup.add(G5);
        G5.setText("Grâce au site web");

        G6.setBackground(new java.awt.Color(153, 153, 153));
        GbuttonGroup.add(G6);
        G6.setText("Au cours d'une formation aux premiers secours");

        G7.setBackground(new java.awt.Color(153, 153, 153));
        GbuttonGroup.add(G7);
        G7.setText("Par facebook");

        G8.setBackground(new java.awt.Color(153, 153, 153));
        GbuttonGroup.add(G8);
        G8.setText("Par une affiche");

        G9.setBackground(new java.awt.Color(153, 153, 153));
        GbuttonGroup.add(G9);
        G9.setText("Autre, précisez");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Gprecision)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(G9)
                            .addComponent(G8)
                            .addComponent(G7)
                            .addComponent(G5)
                            .addComponent(G4)
                            .addComponent(G3)
                            .addComponent(G2)
                            .addComponent(G1)
                            .addComponent(G6))
                        .addGap(0, 103, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(G1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(G2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(G3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(G4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(G5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(G6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(G7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(G8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(G9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Gprecision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Page: ");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("2");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("/");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("9");

        jButton4.setText("Terminer");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Annuler");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Précédent");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Suivant");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(817, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jButton5)
                    .addComponent(jButton4)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(checkChamps()){
            creerClasse();
            envoyerClasse();
            parent.cleanBackup();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(checkChamps()){
            creerClasse();
            parent.changeState(Main.NOUVEAU_VOLONTAIRE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(checkChamps()){
            creerClasse();
            parent.changeState(Main.NOUVEAU_VOLONTAIRE_PAGE_3);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void BaccueilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BaccueilActionPerformed
        parent.changeState(Main.LOGGED);
    }//GEN-LAST:event_BaccueilActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        parent.changeState(Main.LOGGED);
        parent.cleanBackup();
    }//GEN-LAST:event_jButton5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Baccueil;
    private javax.swing.JRadioButton G1;
    private javax.swing.JRadioButton G2;
    private javax.swing.JRadioButton G3;
    private javax.swing.JRadioButton G4;
    private javax.swing.JRadioButton G5;
    private javax.swing.JRadioButton G6;
    private javax.swing.JRadioButton G7;
    private javax.swing.JRadioButton G8;
    private javax.swing.JRadioButton G9;
    private javax.swing.ButtonGroup GbuttonGroup;
    private javax.swing.JTextField Gprecision;
    private javax.swing.JLabel Gtitre;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables

    private boolean checkChamps() {
        return true;
    }

    private void creerClasse() {
        String texte = null;
        for(Enumeration<AbstractButton> buttons = GbuttonGroup.getElements(); buttons.hasMoreElements();){
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()){
                if(button.equals(G9)){
                    texte = Gprecision.getText();
                }else{
                    texte = button.getText();
                }
            }
        }
        if(texte != null){
            Decouverte decouverte = new Decouverte();
            decouverte.setDescription(texte);
            if(volontaire == null){
                volontaire = new Volontaire();
            }
            volontaire.setDecouverte(decouverte);
            parent.setVolontaire(volontaire);
        }
    }

    private void envoyerClasse() {
        PacketCom packet = null;
        if(!edited){
            packet = new PacketCom(States.NOUVEAU_VOLONTAIRE, (Object)volontaire);
        }else{
            packet = new PacketCom(States.EDIT_VOLONTAIRE, (Object)volontaire);
        }
        socket.send(packet);
        boolean cont = false;
        try {
            PacketCom packetReponse = socket.receive();
            String type = packetReponse.getType();
            if(type.equals(States.EDIT_VOLONTAIRE_OUI)){
                parent.afficherMessage("Modification du volontaire réussi.");
                cont = true;
            }else if(type.equals(States.NOUVEAU_VOLONTAIRE_OUI)){
                parent.afficherMessage("Ajout du  nouveau volontaire réussi.");
                cont = true;
            }else{
                String message = (String) packetReponse.getObjet();
                volontaire = parent.getVolontaire();
                parent.afficherMessage(message);
            }
        } catch (Exception ex) {
            Logger.getLogger(M_Decouverte.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(cont){
            parent.cleanBackup();
            parent.changeState(Main.LOGGED);
        }
    }

    private void completerChampDecouverte() {
        Decouverte decouverte = volontaire.getDecouverte();
        boolean found = false;
        for(Enumeration<AbstractButton> buttons = GbuttonGroup.getElements(); buttons.hasMoreElements();){
            AbstractButton button = buttons.nextElement();
            if(button.getText().equals(decouverte.getDescription())){
                found = true;
                button.setSelected(true);
            }
        }
        if(!found && decouverte.getDescription() != null){
            G9.setSelected(true);
            Gprecision.setText(decouverte.getDescription());
        }
    }
}
