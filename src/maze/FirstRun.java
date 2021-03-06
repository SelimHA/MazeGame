/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;

/**
 *
 * @author 
 */
public class FirstRun extends javax.swing.JFrame {
    Settings settings;
    int language = 0; //By default English
    /**
     * Creates new form FirstRun
     */
    public FirstRun() {
        settings = new Settings();
        initComponents();
        //Filling the language list
        for (String currentlanguage : GameConstants.languages) {
            language_list.addItem(currentlanguage);
        }
        applyTexts();
    }
    
    private void applyTexts(){
        //translate texts
        label_select_language.setText(LanguageTexts.FIRST_RUN_PLEASE_SELECT_LANGUAGE[language]);
        button_proceed.setText(LanguageTexts.FIRST_RUN_PROCEED_BUTTON[language]);
        fillTextArea();
        Text_Description.setCaretPosition(0); //always show text from 1st line (if language changed)
    }

    private void fillTextArea(){
        //First choosing the appropriate language, then loading the description file in the given language, and after setting the textbox
        File description;
        if(language == GameConstants.ENGLISH){
            description = new File("./MazeFiles/description_EN.txt");
        }else if(language == GameConstants.HUNGARIAN){
            description = new File("./MazeFiles/description_HU.txt");
        }else{
            description = new File("./MazeFiles/description_FR.txt");
        }
        Text_Description.setText(readText(description));
    }
    
    private String readText(File description){
        //will read the file given in parameters and set the text area to show it
        //If any further modification occurs with the text, it must be saved in UTF8
        String text = "";
        BufferedReader read = null;
        try{
            read = new BufferedReader( new InputStreamReader(new FileInputStream(description), "UTF8")); //Here  UTF8 texts are read, in other places it is ANSI, so we don't have to specify its type and we can use FileReader (no characters with accent)
            String current;
            while((current = read.readLine())!=null){
                text = text +"\r\n"+ current;
            }
        } catch (FileNotFoundException ex) {
            //show a text that file is missing
            return LanguageTexts.FIRST_RUN_NO_TEXT_FOUND[language];
        } catch (IOException ex) {
            //show a text that there has been a read-write exception
            return LanguageTexts.FIRST_RUN_ERROR[language];
        }finally{
            if(read!=null)
                try {
                    read.close();
            } catch (IOException ex) {
                return LanguageTexts.FIRST_RUN_ERROR[language];
            }
        }
        return text;
        //this text will be set to be shown on the textarea
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        language_list = new javax.swing.JComboBox<>();
        label_select_language = new javax.swing.JLabel();
        button_proceed = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Text_Description = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        language_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                language_listActionPerformed(evt);
            }
        });

        label_select_language.setText("Please select a language:");

        button_proceed.setText("Proceed");
        button_proceed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_proceedActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Text_Description.setEditable(false);
        Text_Description.setColumns(20);
        Text_Description.setLineWrap(true);
        Text_Description.setRows(5);
        Text_Description.setWrapStyleWord(true);
        jScrollPane1.setViewportView(Text_Description);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(button_proceed, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_select_language)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(language_list, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(language_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_select_language))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(button_proceed, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void language_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_language_listActionPerformed
        //if change is made in languages, automatically translate everything into selected language
        language = language_list.getSelectedIndex();
        applyTexts();
    }//GEN-LAST:event_language_listActionPerformed

    private void button_proceedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_proceedActionPerformed
        //if button is pressed, save the changes made in settings and go and show the MainScreen
        settings.setLanguageInt(language_list.getSelectedIndex());
        settings.saveSettings();
        this.dispose();
        new MainScreen().setVisible(true);
       
    }//GEN-LAST:event_button_proceedActionPerformed

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
            java.util.logging.Logger.getLogger(FirstRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FirstRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FirstRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FirstRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FirstRun().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Text_Description;
    private javax.swing.JButton button_proceed;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_select_language;
    private javax.swing.JComboBox<String> language_list;
    // End of variables declaration//GEN-END:variables
}
