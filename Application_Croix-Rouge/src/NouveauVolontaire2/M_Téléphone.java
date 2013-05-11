/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NouveauVolontaire2;

import Containers.Telephone;
import Containers.Volontaire;
import GUI.Panels.Main;
import Network.NetworkClient;
import PacketCom.PacketCom;
import States.States;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Greenlamp
 */
public class M_Téléphone extends javax.swing.JPanel {

    /**
     * Creates new form M_Téléphone
     */
    Main parent = null;
    NetworkClient socket = null;
    Volontaire volontaire = null;
    String matricule = null;
    boolean edited = false;
    public M_Téléphone(Main parent, NetworkClient socket) {
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
            if(volontaire.getTelephone() != null){
                this.completerChampTelephone();
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

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Gtitre = new javax.swing.JLabel();
        Baccueil1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        Ggsm = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        GautreGsm = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        GtelephoneFix = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        GtelephoneProfessionnelle = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        Gfax = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Téléphone");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(1178, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        Gtitre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Gtitre.setText("Nouveau volontaire");

        Baccueil1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Baccueil1.setForeground(new java.awt.Color(0, 0, 255));
        Baccueil1.setText("ACCUEIL");
        Baccueil1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Baccueil1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Baccueil1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Gtitre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Gtitre)
                    .addComponent(Baccueil1))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel16.setText("Gsm");

        jLabel17.setText("Autre Gsm");

        jLabel18.setText("Telephone Fix");

        jLabel19.setText("Telephone Professionnelle");

        jLabel20.setText("Fax");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Ggsm)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addGap(0, 255, Short.MAX_VALUE))
                    .addComponent(GautreGsm)
                    .addComponent(GtelephoneFix)
                    .addComponent(GtelephoneProfessionnelle)
                    .addComponent(Gfax))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ggsm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GautreGsm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GtelephoneFix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GtelephoneProfessionnelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Gfax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(153, 153, 153));

        jLabel118.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel118.setText("Page: ");

        jLabel119.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel119.setText("6");

        jLabel120.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel120.setText("/");

        jLabel121.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel121.setText("9");

        jButton5.setText("Terminer");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Annuler");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Précédent");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Suivant");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel118)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel119)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel120)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel121)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(817, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel118)
                    .addComponent(jLabel119)
                    .addComponent(jLabel120)
                    .addComponent(jLabel121)
                    .addComponent(jButton6)
                    .addComponent(jButton5)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
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
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(checkChamps()){
            creerClasse();
            envoyerClasse();
            parent.cleanBackup();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(checkChamps()){
            creerClasse();
            parent.changeState(Main.NOUVEAU_VOLONTAIRE_PAGE_5);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if(checkChamps()){
            creerClasse();
            parent.changeState(Main.NOUVEAU_VOLONTAIRE_PAGE_7);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        parent.changeState(Main.LOGGED);
        parent.cleanBackup();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void Baccueil1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Baccueil1ActionPerformed
        parent.changeState(Main.LOGGED);
    }//GEN-LAST:event_Baccueil1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Baccueil1;
    private javax.swing.JTextField GautreGsm;
    private javax.swing.JTextField Gfax;
    private javax.swing.JTextField Ggsm;
    private javax.swing.JTextField GtelephoneFix;
    private javax.swing.JTextField GtelephoneProfessionnelle;
    private javax.swing.JLabel Gtitre;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    private boolean checkChamps() {
        return true;
    }

    private void creerClasse() {
        String gsm = Ggsm.getText();
        String autreGsm = GautreGsm.getText();
        String telephoneFix = GtelephoneFix.getText();
        String telephonePro = GtelephoneProfessionnelle.getText();
        String fax = Gfax.getText();
        if(gsm.isEmpty() && autreGsm.isEmpty() && telephoneFix.isEmpty() && telephonePro.isEmpty() && fax.isEmpty()){

        }else{
            Telephone telephone = new Telephone();
            telephone.setGsm(gsm);
            telephone.setAutreGsm(autreGsm);
            telephone.setTelephoneFix(telephoneFix);
            telephone.setTelephoneProfesionnelle(telephonePro);
            telephone.setFax(fax);
            if(checkRegister(telephone)){
                if(volontaire == null){
                    volontaire = new Volontaire();
                }
                volontaire.setTelephone(telephone);
                parent.setVolontaire(volontaire);
            }
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
            Logger.getLogger(M_Téléphone.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(cont){
            parent.cleanBackup();
            parent.changeState(Main.LOGGED);
        }
    }

    private void completerChampTelephone() {
        Telephone telephone = volontaire.getTelephone();
        String gsm = telephone.getGsm();
        String autreGsm = telephone.getAutreGsm();
        String telephoneFix = telephone.getTelephoneFix();
        String telephonePro = telephone.getTelephoneProfesionnelle();
        String fax = telephone.getFax();

        Ggsm.setText(gsm);
        GautreGsm.setText(autreGsm);
        GtelephoneFix.setText(telephoneFix);
        GtelephoneProfessionnelle.setText(telephonePro);
        Gfax.setText(fax);
    }

    private boolean checkRegister(Telephone telephone) {
        //gsm.isEmpty() && autreGsm.isEmpty() && telephoneFix.isEmpty() && telephonePro.isEmpty() && fax.isEmpty()
        if((telephone.getGsm() != null && telephone.getGsm().isEmpty()) &&
            (telephone.getAutreGsm() != null && telephone.getAutreGsm().isEmpty()) &&
            (telephone.getTelephoneFix() != null && telephone.getTelephoneFix().isEmpty()) &&
            (telephone.getTelephoneProfesionnelle() != null && telephone.getTelephoneProfesionnelle().isEmpty()) &&
            (telephone.getFax() != null && telephone.getFax().isEmpty()))
        {
            return false;
        }else{
            return true;
        }
    }
}
