package Forms;

import Classes.OspiteSocial;
import Classes.Prenotazione;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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


public class RegistroPrenotazioniEffettuate_Form extends javax.swing.JFrame {

    private JScrollPane tmp;
    
    public RegistroPrenotazioniEffettuate_Form() {
        initComponents();
        CheckInB.setVisible(false);
        CheckOutB.setVisible(false);
        CheckInP.setVisible(false);
        CheckOutP.setVisible(false);
        CheckInF.setEnabled(false);
        addAction();
        CaricaPrenotazioni();
    }
    
    
    public void CaricaPrenotazioni(){
        List<Prenotazione> prenotazioni=new ArrayList();
        prenotazioni=OspiteSocial.getInstance().visualizzaPrenotazioni(OspiteSocial.getInstance().getCf());
        
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
            
           pane.setViewportView(buttonPanel);
           jPanel3.add(pane); 
           
            for(int i = 0; i < prenotazioni.size(); i++) {
                JButton button = new JButton();
                button.setName(prenotazioni.get(i).getCodicePrenotazione().toString()+"x"+prenotazioni.get(i).getOspiti().toString()+"x"+prenotazioni.get(i).getStanze().toString()+"x"+prenotazioni.get(i).getStato()+"x"+prenotazioni.get(i).getCheckIn()+"x"+prenotazioni.get(i).getCheckOut()+"x"+prenotazioni.get(i).getDataInizio());
                button.setText(prenotazioni.get(i).toString());
                button.setPreferredSize(new Dimension(710, 40));
                buttonPanel.add(button, constraint);

                button.addActionListener( new ActionListener() {
                     public void actionPerformed(ActionEvent e) {
                         JButton button = (JButton) e.getSource();

                         CodicePrenotazione.setText(button.getName().split("x")[0]);
                         Utenti.setText(button.getName().split("x")[1]);
                         Stanze.setText(button.getName().split("x")[2]);
                         Stato.setText(button.getName().split("x")[3]);
                         try {
                             CheckInF.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(button.getName().split("x")[6]));
                         } catch (ParseException ex) {
                             Logger.getLogger(RegistroPrenotazioniEffettuate_Form.class.getName()).log(Level.SEVERE, null, ex);
                         }
                         

                         if(button.getName().split("x")[3].equals("Accettata")){
                             CheckInB.setVisible(false);
                             CheckOutB.setVisible(false);
                             CheckInP.setVisible(true);
                             CheckOutP.setVisible(true);
                                 if(button.getName().split("x")[4].equals("null")){
                                     CheckInB.setVisible(true);
                                     CheckOutP.setVisible(false);
                                 }else if(button.getName().split("x")[5].equals("null")){
                                     try {
                                         CheckInF.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(button.getName().split("x")[4]));
                                     } catch (ParseException ex) {

                                     }
                                     CheckOutB.setVisible(true);
                                 }else{
                                     try {
                                         CheckInF.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(button.getName().split("x")[4]));
                                     } catch (ParseException ex) {

                                     }
                                     try {
                                         CheckOutF.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(button.getName().split("x")[5]));
                                     } catch (ParseException ex) {

                                     }
                                 }

                         }else{
                             CheckInP.setVisible(false);
                             CheckOutP.setVisible(false);
                         }
                     }
                });
             }
           
            if(prenotazioni.size()==0){
                JOptionPane.showMessageDialog(null, "Nessuna Prenotazione da mostare", "", 2);
            }
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
        jLabel2 = new javax.swing.JLabel();
        CodicePrenotazione = new javax.swing.JTextField();
        jLabel_Create_Account = new javax.swing.JLabel();
        Utenti = new javax.swing.JTextField();
        Stanze = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Stato = new javax.swing.JTextField();
        CheckInP = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        CheckInF = new com.toedter.calendar.JDateChooser();
        CheckInB = new javax.swing.JButton();
        CheckOutP = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        CheckOutF = new com.toedter.calendar.JDateChooser();
        CheckOutB = new javax.swing.JButton();

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Codice Prenotazione:");

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Utenti:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Stanze:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Stato:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Check in:");

        CheckInB.setText("CheckIn");
        CheckInB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckInBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CheckInPLayout = new javax.swing.GroupLayout(CheckInP);
        CheckInP.setLayout(CheckInPLayout);
        CheckInPLayout.setHorizontalGroup(
            CheckInPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CheckInPLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CheckInF, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CheckInB, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        CheckInPLayout.setVerticalGroup(
            CheckInPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CheckInPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CheckInPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CheckInF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CheckInB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Check Out:");

        CheckOutB.setText("CheckOut");
        CheckOutB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckOutBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CheckOutPLayout = new javax.swing.GroupLayout(CheckOutP);
        CheckOutP.setLayout(CheckOutPLayout);
        CheckOutPLayout.setHorizontalGroup(
            CheckOutPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CheckOutPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CheckOutF, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CheckOutB, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        CheckOutPLayout.setVerticalGroup(
            CheckOutPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CheckOutPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CheckOutPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CheckOutF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CheckOutB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel_Create_Account, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CodicePrenotazione, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Stato))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Utenti, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Stanze, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CheckInP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CheckOutP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(CodicePrenotazione))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Utenti, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(CheckInP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Stanze, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(CheckOutP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Stato, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(jLabel_Create_Account, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_Create_AccountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Create_AccountMouseExited

        Border label_create_accont_border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray);
        jLabel_Create_Account.setBorder(label_create_accont_border);
    }//GEN-LAST:event_jLabel_Create_AccountMouseExited

    private void jLabel_Create_AccountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Create_AccountMouseEntered

        Border label_border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.red);
        jLabel_Create_Account.setBorder(label_border);
    }//GEN-LAST:event_jLabel_Create_AccountMouseEntered

    private void jLabel_Create_AccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Create_AccountMouseClicked

        Menu_Form rf = new Menu_Form();
        rf.setVisible(true);
        rf.pack();
        rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel_Create_AccountMouseClicked

    private void CheckInBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckInBActionPerformed
            if(CheckInF.getDate()==null){
                JOptionPane.showMessageDialog(null, "Immetti una data valida di CheckIn!", "", 2);
                return;
            }
        boolean res=OspiteSocial.getInstance().checkIn(Integer.parseInt(CodicePrenotazione.getText()),new java.sql.Date(CheckInF.getDate().getTime()));
            if(res==false){
                JOptionPane.showMessageDialog(null, "Data non valida!", "", 2);
            }else CheckInB.setVisible(false);
    }//GEN-LAST:event_CheckInBActionPerformed

    private void CheckOutBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckOutBActionPerformed
            if(CheckOutF.getDate()==null){
                JOptionPane.showMessageDialog(null, "Immetti una data valida di CheckOut!", "", 2);
                return;
            }
        boolean res=OspiteSocial.getInstance().checkOut(Integer.parseInt(CodicePrenotazione.getText()),new java.sql.Date(CheckOutF.getDate().getTime()));
            if(res==false){
                JOptionPane.showMessageDialog(null, "Data non valida!", "", 2);
            }else CheckOutB.setVisible(false);
    }//GEN-LAST:event_CheckOutBActionPerformed

    
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroPrenotazioniEffettuate_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CheckInB;
    private com.toedter.calendar.JDateChooser CheckInF;
    private javax.swing.JPanel CheckInP;
    private javax.swing.JButton CheckOutB;
    private com.toedter.calendar.JDateChooser CheckOutF;
    private javax.swing.JPanel CheckOutP;
    private javax.swing.JTextField CodicePrenotazione;
    private javax.swing.JTextField Stanze;
    private javax.swing.JTextField Stato;
    private javax.swing.JTextField Utenti;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Create_Account;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
