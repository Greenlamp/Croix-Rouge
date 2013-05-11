/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NouveauVolontaire2;

import Containers.Residence;
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
public class M_Residence extends javax.swing.JPanel {

    /**
     * Creates new form M_Residence
     */
    Main parent = null;
    NetworkClient socket = null;
    Volontaire volontaire = null;
    String matricule = null;
    boolean edited = false;
    public M_Residence(Main parent, NetworkClient socket) {
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
            if(volontaire.getResidence() != null){
                this.completerChampResidence();
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

        jPanel1 = new javax.swing.JPanel();
        Gtitre = new javax.swing.JLabel();
        Baccueil1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Gpays = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        Gville = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        Gnumero = new javax.swing.JTextField();
        Gboite = new javax.swing.JTextField();
        GcodePostal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Grue = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        Gtitre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Gtitre.setText("Nouveau volontaire");

        Baccueil1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Baccueil1.setForeground(new java.awt.Color(0, 0, 255));
        Baccueil1.setText("ACCUEIL");

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Gtitre)
                    .addComponent(Baccueil1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Adresse résidence");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(1126, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        Gpays.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belgique", "Afghanistan", "Afrique du Sud", "Albanie", "Algérie", "Allemagne", "Andorre", "Angola", "Anguilla", "Antarctique", "Antigua-et-Barbuda", "Antilles néerlandaises", "Arabie saoudite", "Argentine", "Arménie", "Aruba", "Australie", "Autriche", "Azerbaïdjan", "Bénin", "Bahamas", "Bahreïn", "Bangladesh", "Barbade", "Belau", "Belize", "Bermudes", "Bhoutan", "Biélorussie", "Birmanie", "Bolivie", "Bosnie-Herzégovine", "Botswana", "Brésil", "Brunei", "Bulgarie", "Burkina Faso", "Burundi", "Côte d'Ivoire", "Cambodge", "Cameroun", "Canada", "Cap-Vert", "Chili", "Chine", "Chypre", "Colombie", "Comores", "Congo", "Corée du Nord", "Corée du Sud", "Costa Rica", "Croatie", "Cuba", "Danemark", "Djibouti", "Dominique", "Égypte", "Émirats arabes unis", "Équateur", "Érythrée", "Estonie", "États-Unis", "Éthiopie", "Finlande", "Géorgie", "Gabon", "Gambie", "Ghana", "Gibraltar", "Grèce", "Grenade", "Groenland", "Guadeloupe", "Guam", "Guatemala", "Guinée", "Guinée équatoriale", "Guinée-Bissao", "Guyana", "Guyane française", "Haïti", "Honduras", "Hong Kong", "Hongrie", "Ile Bouvet", "Ile Christmas", "Ile Norfolk", "Iles Cayman", "Iles Cook", "Iles Féroé", "Iles Falkland", "Iles Fidji", "Iles Géorgie du Sud et Sandwich du Sud", "Iles Heard et McDonald", "Iles Marshall", "Iles Pitcairn", "Iles Salomon", "Iles Svalbard et Jan Mayen", "Iles Turks-et-Caicos", "Iles Vierges américaines", "Iles Vierges britanniques", "Iles des Cocos (Keeling)", "Iles mineures éloignées des États-Unis", "Inde", "Indonésie", "Iran", "Iraq", "Irlande", "Islande", "Israël", "Italie", "Jamaïque", "Japon", "Jordanie", "Kazakhstan", "Kenya", "Kirghizistan", "Kiribati", "Koweït", "Laos", "Lesotho", "Lettonie", "Liban", "Liberia", "Libye", "Liechtenstein", "Lituanie", "Luxembourg", "Macao", "Madagascar", "Malaisie", "Malawi", "Maldives", "Mali", "Malte", "Mariannes du Nord", "Maroc", "Martinique", "Maurice", "Mauritanie", "Mayotte", "Mexique", "Micronésie", "Moldavie", "Monaco", "Mongolie", "Montserrat", "Mozambique", "Népal", "Namibie", "Nauru", "Nicaragua", "Niger", "Nigeria", "Nioué", "Norvège", "Nouvelle-Calédonie", "Nouvelle-Zélande", "Oman", "Ouganda", "Ouzbékistan", "Pérou", "Pakistan", "Panama", "Papouasie-Nouvelle-Guinée", "Paraguay", "Pays-Bas", "Philippines", "Pologne", "Polynésie française", "Porto Rico", "Portugal", "Qatar", "République centrafricaine", "République démocratique du Congo", "République dominicaine", "République tchèque", "Réunion", "Roumanie", "Royaume-Uni", "Russie", "Rwanda", "Sénégal", "Sahara occidental", "Saint-Christophe-et-Niévès", "Saint-Marin", "Saint-Pierre-et-Miquelon", "Saint-Siège", "Saint-Vincent-et-les-Grenadines", "Sainte-Hélène", "Sainte-Lucie", "Salvador", "Samoa", "Samoa américaines", "Sao Tomé-et-Principe", "Seychelles", "Sierra Leone", "Singapour", "Slovénie", "Slovaquie", "Somalie", "Soudan", "Sri Lanka", "Suède", "Suisse", "Suriname", "Swaziland", "Syrie", "Taïwan", "Tadjikistan", "Tanzanie", "Tchad", "Terres australes françaises", "Territoire britannique de l'Océan Indien", "Thaïlande", "Timor Oriental", "Togo", "Tokélaou", "Tonga", "Trinité-et-Tobago", "Tunisie", "Turkménistan", "Turquie", "Tuvalu", "Ukraine", "Uruguay", "Vanuatu", "Venezuela", "Viêt Nam", "Wallis-et-Futuna", "Yémen", "Yougoslavie", "Zambie", "Zimbabwe" }));

        jLabel15.setText("Pays");

        jLabel14.setText("Ville");

        jLabel13.setText("Code Postal");

        jLabel12.setText("Boite");

        jLabel11.setText("Numéro");

        jLabel10.setText("Rue");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Grue)
                    .addComponent(Gville)
                    .addComponent(Gpays, 0, 357, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(Gnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel13))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(Gboite, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(GcodePostal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Grue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Gnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Gboite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GcodePostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Gville, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Gpays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(153, 153, 153));

        jLabel118.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel118.setText("Page: ");

        jLabel119.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel119.setText("5");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 357, Short.MAX_VALUE)
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
            parent.changeState(Main.NOUVEAU_VOLONTAIRE_PAGE_4);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if(checkChamps()){
            creerClasse();
            parent.changeState(Main.NOUVEAU_VOLONTAIRE_PAGE_6);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        parent.changeState(Main.LOGGED);
        parent.cleanBackup();
    }//GEN-LAST:event_jButton6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Baccueil1;
    private javax.swing.JTextField Gboite;
    private javax.swing.JTextField GcodePostal;
    private javax.swing.JTextField Gnumero;
    private javax.swing.JComboBox Gpays;
    private javax.swing.JTextField Grue;
    private javax.swing.JLabel Gtitre;
    private javax.swing.JTextField Gville;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    private void completerChampResidence() {
        Residence adresse = volontaire.getResidence();
        String rue = adresse.getRue();
        String numero = adresse.getNuméro();
        String boite = adresse.getBoite();
        String codePostal = adresse.getCodePostal();
        String ville = adresse.getVille();
        String pays = adresse.getPays();
        Grue.setText(rue);
        Gnumero.setText(numero);
        Gboite.setText(boite);
        GcodePostal.setText(codePostal);
        Gville.setText(ville);

        boolean found = false;
        for(int i=0; i<Gpays.getItemCount() && !found; i++){
            if(Gpays.getItemAt(i).toString().equals(pays)){
                Gpays.setSelectedIndex(i);
                found = true;
            }
        }
    }

    private boolean checkChamps() {
        return true;
    }

    private void creerClasse() {
        String rue = Grue.getText();
        String numero = Gnumero.getText();
        String boite = Gboite.getText();
        String codePostal = GcodePostal.getText();
        String ville = Gville.getText();
        String pays = Gpays.getSelectedItem().toString();
        if(rue.isEmpty() && numero.isEmpty() && boite.isEmpty() && codePostal.isEmpty() && ville.isEmpty() && pays.isEmpty()){

        }else{
            Residence residence = new Residence();
            residence.setRue(rue);
            residence.setNuméro(numero);
            residence.setBoite(boite);
            residence.setCodePostal(codePostal);
            residence.setVille(ville);
            residence.setPays(pays);
            if(checkRegister(residence)){
                if(volontaire == null){
                    volontaire = new Volontaire();
                }
                volontaire.setResidence(residence);
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
            Logger.getLogger(M_Adresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(cont){
            parent.cleanBackup();
            parent.changeState(Main.LOGGED);
        }
    }

    private boolean checkRegister(Residence residence) {
        if((residence.getRue() != null && residence.getRue().isEmpty()) &&
            (residence.getNuméro() != null && residence.getNuméro().isEmpty()) &&
            (residence.getBoite() != null && residence.getBoite().isEmpty()) &&
            (residence.getCodePostal() != null && residence.getCodePostal().isEmpty()) &&
            (residence.getVille() != null && residence.getVille().isEmpty()) &&
            (residence.getPays() != null && residence.getPays().isEmpty()))
        {
            return false;
        }else{
            return true;
        }
    }
}
