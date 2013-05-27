/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panels.GrillesHoraires;

import Containers.Grille;
import GUI.Panels.Consultation.M_Consultation;
import GUI.Panels.Main;
import Helpers.SwingUtils;
import Network.NetworkClient;
import my.cr.PacketCom.PacketCom;
import States.States;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Greenlamp
 */
public class M_ConsulterGrillesHoraires extends javax.swing.JPanel {

    /**
     * Creates new form M_ConsulterGrillesHoraires
     */
    Main parent = null;
    NetworkClient socket = null;

    public M_ConsulterGrillesHoraires(Main parent, NetworkClient socket) {
        initComponents();
        this.socket = socket;
        this.parent = parent;
        recupererGrillesHoraires();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Baccueil = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Gtableau = new javax.swing.JTable();
        Bsupprimer = new javax.swing.JButton();
        Bnouveau = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Grilles horaires");

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
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Baccueil)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        Gtableau.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Semaine", "année", "Date début", "Date fin", "Date création", "Date modification", "ambulance", "lieu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Gtableau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GtableauMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Gtableau);

        Bsupprimer.setText("Supprimer");
        Bsupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BsupprimerActionPerformed(evt);
            }
        });

        Bnouveau.setText("Nouveau");
        Bnouveau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BnouveauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1240, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Bnouveau, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Bsupprimer)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bnouveau)
                    .addComponent(Bsupprimer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BnouveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BnouveauActionPerformed
        parent.setTypeGrille("new");
        parent.changeState(Main.EDITNEWGRILLLE);
    }//GEN-LAST:event_BnouveauActionPerformed

    private void BsupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BsupprimerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BsupprimerActionPerformed

    private void GtableauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GtableauMouseClicked
        if(evt.getClickCount() == 2){
            int row = Gtableau.getSelectedRow();
            int semaine = Integer.parseInt((String)Gtableau.getValueAt(row, 0));
            String ambulance = (String)Gtableau.getValueAt(row, 6);
            if(ambulance == null){
                return;
            }
            String lieu = (String)Gtableau.getValueAt(row, 7);
            int année = Integer.parseInt((String)Gtableau.getValueAt(row, 1));
            if(!locked(ambulance, lieu, semaine, année)){
                Grille grille = getGrille(semaine, année, ambulance, lieu);
                if(grille != null){
                    parent.setGrille(grille);
                    parent.setTypeGrille("edit");
                    parent.changeState(Main.EDITNEWGRILLLE);
                }
            }else{
                parent.afficherMessage("La grille est en cours de modification par un autre utilisateur");
            }
        }
    }//GEN-LAST:event_GtableauMouseClicked

    private void BaccueilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BaccueilActionPerformed
        parent.changeState(Main.LOGGED);
    }//GEN-LAST:event_BaccueilActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Baccueil;
    private javax.swing.JButton Bnouveau;
    private javax.swing.JButton Bsupprimer;
    private javax.swing.JTable Gtableau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void recupererGrillesHoraires() {
        PacketCom packet = new PacketCom(States.GET_GRILLES_HORAIRES, null);
        socket.send(packet);
        try {
            PacketCom packetReponse = socket.receive();
            reponseFromServeur(packetReponse);
        } catch (Exception ex) {
            Logger.getLogger(M_Consultation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void reponseFromServeur(PacketCom packetReponse) {
        String type = packetReponse.getType();
        Object contenu = packetReponse.getObjet();

        if(type.equals(States.GET_GRILLES_HORAIRES_OUI)){
            LinkedList<String[]> listeEquipes = (LinkedList<String[]>) contenu;
            for(String[] equipe : listeEquipes){
                Vector vector = new Vector();
                for(String elm : equipe){
                    vector.addElement(elm);
                }
                SwingUtils.addToTable(Gtableau, vector);
            }
        }else{
            System.out.println("Erreur.");
        }
    }

    private Grille getGrille(int semaine, int année, String ambulance, String lieu) {
        Grille grille = null;
        Object[] data = {semaine, année, ambulance, lieu};
        PacketCom packet = new PacketCom(States.GET_GRILLE, (Object)data);
        socket.send(packet);
        try {
            PacketCom packetReponse = socket.receive();
            if(packetReponse.getType().equals(States.GET_GRILLE_OUI)){
                grille = (Grille)packetReponse.getObjet();
            }
        } catch (Exception ex) {
            Logger.getLogger(M_ConsulterGrillesHoraires.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grille;
    }

    private boolean locked(String ambulance, String lieu, int semaine, int année) {
        boolean lock = true;
        Object[] data = {semaine, année, ambulance, lieu};
        PacketCom packet = new PacketCom(States.CHECK_LOCK_GRILLE, (Object)data);
        socket.send(packet);
        try {
            PacketCom packetReponse = socket.receive();
            if(packetReponse.getType().equals(States.GRILLE_LOCKED)){
                lock = true;
            }else if(packetReponse.getType().equals(States.GRILLE_UNLOCKED)){
                lock = false;
            }
        } catch (Exception ex) {
            Logger.getLogger(M_ConsulterGrillesHoraires.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lock;
    }
}