package Forms;

import Classes.OspiteSocial;
import Classes.*;
import DAO.MySQLStanzaDAOImpl;
import Classes.Abitazione;
import Classes.Stanza;
import DAO.StanzaDAO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GestioneAbitazioni_Form extends javax.swing.JFrame {

    
    private JScrollPane tmp;
    
    public GestioneAbitazioni_Form() {
        initComponents();
        this.setName("GestioneAbitazioneFrame");
        addAction();
        CaricaAbitazioni(); 
    }
    
    
    public void CaricaAbitazioni(){
        List<Abitazione> abitazioni=new ArrayList();
        abitazioni=OspiteSocial.getInstance().visualizzaAlloggiUtente(OspiteSocial.getInstance().getCf());
        
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridBagLayout());
            buttonPanel.setSize(new Dimension(723, 144)); 

            JScrollPane pane = new JScrollPane();
            pane.setSize(new Dimension(723,144 )); 
            pane.setName("ListPane");
            tmp=pane;

            GridBagConstraints constraint = new GridBagConstraints();
            constraint.gridx = 0;
            constraint.gridy = GridBagConstraints.RELATIVE;

                for(int i = 0; i < abitazioni.size(); i++) {
                   JButton button = new JButton();
                   button.setName(abitazioni.get(i).getId().toString());
                   button.setText(abitazioni.get(i).toString());
                   button.setPreferredSize(new Dimension(710, 40));
                   buttonPanel.add(button, constraint);

                   button.addActionListener( new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JButton button = (JButton) e.getSource();
                            RiempiCampiAbitazione(button.getName());
                            CaricaStanze(Integer.parseInt(button.getName()));
                        }
                   });

                }
            
            pane.setViewportView(buttonPanel);
            jPanel3.add(pane); 
           
            if(abitazioni.size()==0){
                JOptionPane.showMessageDialog(null, "Nessuna Abitazione da mostare", "", 2);
            }
    }
    
    public void CaricaStanze(Integer codiceAbitazione){
        jPanel5.removeAll();
        List<Stanza> stanze=new ArrayList();
       
        stanze=OspiteSocial.getInstance().visualizzaStanzeAbitazione(codiceAbitazione);
        
           JPanel buttonPanel = new JPanel();
           buttonPanel.setLayout(new GridBagLayout());
           buttonPanel.setSize(new Dimension(723, 144)); 
           
           JScrollPane pane = new JScrollPane();
           pane.setSize(new Dimension(723,144 ));
           
           GridBagConstraints constraint = new GridBagConstraints();
           constraint.gridx = 0;
           constraint.gridy = GridBagConstraints.RELATIVE;
           
                for(int i = 0; i < stanze.size(); i++) {
                   JButton button = new JButton();
                   button.setName("stanza"+stanze.get(i).getId().toString());
                   button.setText(stanze.get(i).toString());
                   button.setPreferredSize(new Dimension(710, 40));
                   buttonPanel.add(button, constraint);

                   button.addActionListener( new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JButton button = (JButton) e.getSource();

                            ModificaStanza_Frame rf = new ModificaStanza_Frame(Integer.parseInt(((JButton)e.getSource()).getName().replaceFirst("stanza", "")));
                            rf.setVisible(true);
                            rf.pack();
                            rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            JFrame frame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());
                            frame.dispose();
                        }
                   });
                }
            
            pane.setViewportView(buttonPanel);
            jPanel5.add(pane); 
    }
    
  
    
    public void RiempiCampiAbitazione(String codiceAbitazione){
           Abitazione a;
           a=OspiteSocial.getInstance().visualizzaAlloggio(Integer.parseInt(codiceAbitazione));
                   
           Id.setText(a.getId().toString());
           Indirizzo.setText(a.getIndirizzo());
           DistanzaCentro.setText(a.getDistanzaCentro().toString());
           DistanzaStazione.setText(a.getDistanzaStazione().toString());
           TempoTermineModifiche.setText(a.getTempoTermineModifiche().toString());
           DataInizioDisponibilita.setDate(a.getDataInizioDisponibilita());
           DataFineDisponibilita.setDate(a.getDataFineDisponibilita());
           Citta.setText(a.getCitta());
           TariffaGiornaliera.setText(a.getTariffaGiornaliera().toString());
    }
    
    

    public void addAction()
    {
        Component[] components = jPanel3.getComponents();
        for(Component component : components)
        {
            if(component instanceof JLabel)
            {
                JLabel label = (JLabel) component;
                label.addMouseListener(new MouseAdapter() {
                    public void MouseEntered(java.awt.event.MouseEvent evt) {
                        label.setBackground(Color.LIGHT_GRAY);
                        label.setOpaque(true); 
                    }
                });
            }
        }
        
    }                  
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Indirizzo = new javax.swing.JTextField();
        DistanzaCentro = new javax.swing.JTextField();
        DistanzaStazione = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Id = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        DataInizioDisponibilita = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jLabel_Create_Account = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Citta = new javax.swing.JTextField();
        TempoTermineModifiche = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        DataFineDisponibilita = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        TariffaGiornaliera = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(52, 152, 219));
        jPanel1.setPreferredSize(new java.awt.Dimension(728, 512));

        jPanel2.setBackground(new java.awt.Color(231, 76, 60));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Gestione Abitazioni");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 153));
        jPanel3.setForeground(new java.awt.Color(0, 204, 153));

        DistanzaStazione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DistanzaStazioneActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Indirizzo:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Distanza dal centro:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Distanza dalla stazione:");

        jButton1.setText("Modifica");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Elimina");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Id.setForeground(new java.awt.Color(240, 240, 240));
        Id.setEnabled(false);
        Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Tempo disponibile modifica:");

        jButton3.setText("Aggiungi");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel_Create_Account.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_Create_Account.setForeground(new java.awt.Color(255, 51, 51));
        jLabel_Create_Account.setText("                    Menu");
        jLabel_Create_Account.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_Create_Account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_Create_AccountMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_Create_AccountMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_Create_AccountMouseExited(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Città:");

        Citta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CittaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Data inizio disponibilità:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("Data inizio disponibilità:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Tariffa giornaliera:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(17, 17, 17))
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(58, 58, 58)
                                            .addComponent(DistanzaStazione, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(DistanzaCentro, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(Indirizzo)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TempoTermineModifiche, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(288, 288, 288)
                                .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(DataInizioDisponibilita, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Citta, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(TariffaGiornaliera, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(DataFineDisponibilita, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_Create_Account, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Indirizzo))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(DistanzaCentro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DistanzaStazione, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TempoTermineModifiche, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DataInizioDisponibilita, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DataFineDisponibilita, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TariffaGiornaliera, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                .addComponent(Citta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel_Create_Account, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 147, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdActionPerformed

    private void DistanzaStazioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DistanzaStazioneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DistanzaStazioneActionPerformed

    private void jLabel_Create_AccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Create_AccountMouseClicked

        Menu_Form rf = new Menu_Form();
        rf.setVisible(true);
        rf.pack();
        rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();

    }//GEN-LAST:event_jLabel_Create_AccountMouseClicked

    private void jLabel_Create_AccountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Create_AccountMouseEntered

        Border label_border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.red);
        jLabel_Create_Account.setBorder(label_border);

    }//GEN-LAST:event_jLabel_Create_AccountMouseEntered

    private void jLabel_Create_AccountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Create_AccountMouseExited

        Border label_create_accont_border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray);
        jLabel_Create_Account.setBorder(label_create_accont_border);

    }//GEN-LAST:event_jLabel_Create_AccountMouseExited

    private void CittaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CittaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CittaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String cf=OspiteSocial.getInstance().getCf();
        String indirizzo=Indirizzo.getText();
        String citta=Citta.getText();
        Float distanzaCentro=Float.parseFloat(DistanzaCentro.getText());
        Float distanzaStazione=Float.parseFloat(DistanzaStazione.getText());
        Integer tempoTermineModifiche=Integer.parseInt(TempoTermineModifiche.getText());
        Date dataInizioDisponibilita=new java.sql.Date(DataInizioDisponibilita.getDate().getTime());
        Date dataFineDisponibilita=new java.sql.Date(DataFineDisponibilita.getDate().getTime());
        Integer codiceAbitazione=Integer.parseInt(Id.getText());

        OspiteSocial.getInstance().eliminaAlloggio(codiceAbitazione,cf ,indirizzo,citta,distanzaCentro,distanzaStazione,tempoTermineModifiche,dataInizioDisponibilita,dataFineDisponibilita);

        jPanel3.remove(tmp);
        jPanel5.removeAll();
        jPanel5.repaint();

        Id.setText("");
        Indirizzo.setText("");
        TariffaGiornaliera.setText("");
        DistanzaCentro.setText("");
        DistanzaStazione.setText("");
        Citta.setText("");
        TempoTermineModifiche.setText("");
        DataInizioDisponibilita.setDate(null);
        DataFineDisponibilita.setDate(null);

        CaricaAbitazioni();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Integer codiceAbitazione;
        String cf=OspiteSocial.getInstance().getCf();
        String indirizzo=Indirizzo.getText();
        String citta=Citta.getText();
        Float distanzaCentro=Float.parseFloat(DistanzaCentro.getText());
        Float distanzaStazione=Float.parseFloat(DistanzaStazione.getText());
        Integer tempoTermineModifiche=Integer.parseInt(TempoTermineModifiche.getText());
        Date dataInizioDisponibilita=new java.sql.Date(DataInizioDisponibilita.getDate().getTime());
        Date dataFineDisponibilita=new java.sql.Date(DataFineDisponibilita.getDate().getTime());
        Integer tariffaGiornaliera=Integer.parseInt(TariffaGiornaliera.getText());

        codiceAbitazione=OspiteSocial.getInstance().inserimentoAlloggio(cf ,indirizzo,citta,distanzaCentro,distanzaStazione,tempoTermineModifiche,dataInizioDisponibilita,dataFineDisponibilita,tariffaGiornaliera);

        jPanel3.remove(tmp);

        Id.setText("");
        Indirizzo.setText("");
        TariffaGiornaliera.setText("");
        DistanzaCentro.setText("");
        DistanzaStazione.setText("");
        Citta.setText("");
        TempoTermineModifiche.setText("");
        DataInizioDisponibilita.setDate(null);
        DataFineDisponibilita.setDate(null);

        CaricaAbitazioni();

        AggiungiStanza_Frame rf = new AggiungiStanza_Frame(codiceAbitazione);
        rf.setVisible(true);
        rf.pack();
        rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String cf=OspiteSocial.getInstance().getCf();
        String indirizzo=Indirizzo.getText();
        String citta=Citta.getText();
        Float distanzaCentro=Float.parseFloat(DistanzaCentro.getText());
        Float distanzaStazione=Float.parseFloat(DistanzaStazione.getText());
        Integer tempoTermineModifiche=Integer.parseInt(TempoTermineModifiche.getText());
        Date dataInizioDisponibilita=new java.sql.Date(DataInizioDisponibilita.getDate().getTime());
        Date dataFineDisponibilita=new java.sql.Date(DataFineDisponibilita.getDate().getTime());
        Integer codiceAbitazione=Integer.parseInt(Id.getText());
        Integer tariffaGiornaliera=Integer.parseInt(TariffaGiornaliera.getText());

        OspiteSocial.getInstance().modificaDatiAlloggio(codiceAbitazione,cf ,indirizzo,citta,distanzaCentro,distanzaStazione,tempoTermineModifiche,dataInizioDisponibilita,dataFineDisponibilita,tariffaGiornaliera);

        jPanel3.remove(tmp);
        jPanel5.removeAll();
        jPanel5.repaint();

        CaricaAbitazioni();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(GestioneAbitazioni_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestioneAbitazioni_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestioneAbitazioni_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestioneAbitazioni_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestioneAbitazioni_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Citta;
    private com.toedter.calendar.JDateChooser DataFineDisponibilita;
    private com.toedter.calendar.JDateChooser DataInizioDisponibilita;
    private javax.swing.JTextField DistanzaCentro;
    private javax.swing.JTextField DistanzaStazione;
    private javax.swing.JTextField Id;
    private javax.swing.JTextField Indirizzo;
    private javax.swing.JTextField TariffaGiornaliera;
    private javax.swing.JTextField TempoTermineModifiche;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Create_Account;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
