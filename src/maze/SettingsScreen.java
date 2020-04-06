/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class SettingsScreen extends javax.swing.JFrame implements WindowListener {
    Settings settings = new Settings();
    int language;
    /**
     * Creates new form Settings
     */
    public SettingsScreen() {
        initComponents();
        this.addWindowListener(this); //Default close operation has been modified to do nothing as WindowListener is used
        language = settings.getLanguageInt();
        this.setTitle(LanguageTexts.SETTINGS_TEXT[language]);
        translateLabels(); //sets label to selected language
        addToolTips();//adds tooltips in appropriate language to the labels
        fillDropDownColors(); //fills the dropdown lists with colors
        fillLanguageList();//fills the language lists with available languages in GameConstants
        fillSettings();//fills setttings with contents of the settings file, so user can see the current state
        hideIrrelevantSettings();//hides the irrelevant settings
        //i.e.: if autofill time is not selected other settings related ot it are hidden
    }
    
    private void translateLabels(){
        label_backgroundcolor.setText(LanguageTexts.BACKGROUND_COLOR_LIST_TEXT[language]);
        label_bordercolor.setText(LanguageTexts.BORDER_COLOR_LIST_TEXT[language]);
        label_cellcolor.setText(LanguageTexts.CELL_COLOR_LIST_TEXT[language]);
        label_playercolor.setText(LanguageTexts.PLAYER_COLOR_LIST_TEXT[language]);
        label_language.setText(LanguageTexts.LANGUAGE_LIST_TEXT[language]);
        label_default_max_maze_size.setText(LanguageTexts.MAX_MAZE_SIZE_TEXT[language]);
        label_max_maze_size_info.setText(LanguageTexts.MAX_MAZE_SIZE_INFO_TEXT[language]);
        check_autofill_time.setText(LanguageTexts.AUTO_FILL_CHECKBOX_TEXT[language]);
        label_defaulttime.setText(LanguageTexts.DEFAULT_TIME_TEXT[language]);
        label_seconds.setText(LanguageTexts.SECOND_S_TEXT[language]);
        label_timewarning.setText(LanguageTexts.TIME_VALUE_LONG_TEXT[language]);
        label_lastvisited.setText(LanguageTexts.LAST_VISITED_CELLS_TEXT[language]);
        check_show_last_cells.setText(LanguageTexts.CHECK_SHOW_LAST_CELLS_TEXT[language]);
        label_showlast.setText(LanguageTexts.SHOW_LAST_TEXT[settings.getLanguageInt()]);
        label_cells.setText(LanguageTexts.SHOW_LAST_CELLS_END_TEXT[language]);
        label_backgroundlastcells.setText(LanguageTexts.LAST_CELLS_COLOR_LIST_TEXT[language]);
        label_advancedsettings.setText(LanguageTexts.ADVANCED_SETTINGS_TEXT[language]);
        check_allow_bigger_maps.setText(LanguageTexts.CHECK_ALLOW_BIGGER_MAP_THAN_DEFAULT[language]);
        button_save.setText(LanguageTexts.BUTTON_SAVE_CHANGES_TEXT[language]);
        button_cancel.setText(LanguageTexts.BUTTON_CANCEL_CHANGES[language]);
    }
    
    private void addToolTips(){
        label_backgroundcolor.setToolTipText(LanguageTexts.SETTINGS_TOOL_TIP_BACKGROUND_COLOR[language]);
        label_bordercolor.setToolTipText(LanguageTexts.SETTINGS_TOOL_TIP_BORDER_COLOR[language]);
        label_cellcolor.setToolTipText(LanguageTexts.SETTINGS_TOOL_TIP_CELL_COLOR[language]);
        label_playercolor.setToolTipText(LanguageTexts.SETTINGS_TOOL_TIP_PLAYER_COLOR[language]);
        label_language.setToolTipText(LanguageTexts.SETTINGS_TOOL_TIP_LANGUAGE[language]);
        label_default_max_maze_size.setToolTipText(LanguageTexts.SETTINGS_TOOL_TIP_MAX_MAZE_SIZE[language]);
        check_autofill_time.setToolTipText(LanguageTexts.SETTINGS_TOOL_TIP_AUTOFILL_TIME[language]);
        label_lastvisited.setToolTipText(LanguageTexts.SETTINGS_TOOL_TIP_SHOW_LAST_CELLS[language]);
        label_backgroundlastcells.setToolTipText(LanguageTexts.SETTINGS_TOOL_TIP_BACKGROUND_LAST_CELLS[language]);
        check_allow_bigger_maps.setToolTipText(LanguageTexts.SETTINGS_TOOL_TIP_BIGGER_MAZE_THAN_DEFAULT[language]);
    }
    
    private void fillDropDownColors(){
        //fills the colors in dropdown lists
        for(int i = 0; i<GameConstants.color_names.length; i++){
            combo_background.addItem(GameConstants.color_names[i][language]);
            combo_border.addItem(GameConstants.color_names[i][language]);
            combo_cellcolor.addItem(GameConstants.color_names[i][language]);
            combo_font.addItem(GameConstants.color_names[i][language]);
            combo_lastcells.addItem(GameConstants.color_names[i][language]);
        }
    }
    
    private void fillLanguageList(){
        //Filling the language list
        for(int i  = 0; i<GameConstants.languages.length; i++){
            combo_language.addItem(GameConstants.languages[i]);
        }
    }
    
    private void fillSettings(){
        //pre-fills the settings from current values, so user can see what are currently set
        combo_background.setSelectedItem(getColorName(settings.getBackgroundColor()));
        combo_border.setSelectedItem(getColorName(settings.getCellBorderColor()));
        combo_cellcolor.setSelectedItem(getColorName(settings.getCellBackgroundColor()));
        combo_font.setSelectedItem(getColorName(settings.getPlayerColor()));
        combo_language.setSelectedIndex(settings.getLanguageInt());
        text_max_maze_size.setText(settings.getMaxMapSize()+"");
        if(settings.getLatestCellsNumber()>=0){
            check_show_last_cells.setSelected(true);
        }
        if(settings.getDefaultTime()>=5 && settings.getDefaultTime()<=8000){
            check_autofill_time.setSelected(true);
            field_default_time.setText(settings.getDefaultTime()+"");
        }
        if(settings.getLatestCellsNumber()>0 && settings.getLatestCellsNumber()<=20){
            check_show_last_cells.setSelected(true);
        }
        field_lastcells.setText(settings.getLatestCellsNumber()+"");
        combo_lastcells.setSelectedItem(getColorName(settings.getLastCellsColor()));
        if(settings.isOverridableMapsize()){
            check_allow_bigger_maps.setSelected(true);
        }
    }

    private String getColorName(Color color){
        //From color given in parameters it returns the name of the color
        //If name of color is not in list Yellow is returend
        //The above is not supposed to happen unless settings file is modified
        if(color.getRed() == Color.BLACK.getRed() && color.getGreen() == Color.BLACK.getGreen() && color.getBlue() == Color.BLACK.getBlue()){
            return GameConstants.color_names[0][language];
        }else if(color.getRed() == Color.GRAY.getRed() && color.getGreen() == Color.GRAY.getGreen() && color.getBlue() == Color.GRAY.getBlue()){
            return GameConstants.color_names[1][language];
        }else if(color.getRed() == Color.BLUE.getRed() && color.getGreen() == Color.BLUE.getGreen() && color.getBlue() == Color.BLUE.getBlue()){
            return GameConstants.color_names[2][language];
        }else if(color.getRed() == Color.GREEN.getRed() && color.getGreen() == Color.GREEN.getGreen() && color.getBlue() == Color.GREEN.getBlue()){
            return GameConstants.color_names[3][language];
        }else if(color.getRed() == Color.ORANGE.getRed() && color.getGreen() == Color.ORANGE.getGreen() && color.getBlue() == Color.ORANGE.getBlue()){
            return GameConstants.color_names[4][language];
        }else if(color.getRed() == Color.PINK.getRed() && color.getGreen() == Color.PINK.getGreen() && color.getBlue() == Color.PINK.getBlue()){
            return GameConstants.color_names[7][language];
        }else if(color.getRed() == Color.RED.getRed() && color.getGreen() == Color.RED.getGreen() && color.getBlue() == Color.RED.getBlue()){
            return GameConstants.color_names[5][language];
        }else if(color.getRed() == Color.WHITE.getRed() && color.getGreen() == Color.WHITE.getGreen() && color.getBlue() == Color.WHITE.getBlue()){
            return GameConstants.color_names[8][language];
        }else{ //Yellow
            return GameConstants.color_names[6][language];
        }
    }
    
    private void hideIrrelevantSettings(){
        hideAutoFill();
        hideLastCells();
    }
    
    private void hideAutoFill(){
        boolean operation = true; //assume it is selected
        //If auto-fill time was not selected we don't have to show the details and other settings connected to it
        if(!check_autofill_time.isSelected())
            operation = false;
        panel_defaulttime.setVisible(operation);
    }
    private void hideLastCells(){
        boolean operation = true; //assume it is selected
        //If showing last cells was not selected we don't have to show the details about it.
        if(!check_show_last_cells.isSelected())
            operation = false;
        panel_lastCells.setVisible(operation);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_backgroundcolor = new javax.swing.JLabel();
        label_bordercolor = new javax.swing.JLabel();
        label_playercolor = new javax.swing.JLabel();
        check_show_last_cells = new javax.swing.JCheckBox();
        combo_background = new javax.swing.JComboBox<>();
        label_advancedsettings = new javax.swing.JLabel();
        check_allow_bigger_maps = new javax.swing.JCheckBox();
        button_save = new javax.swing.JButton();
        label_language = new javax.swing.JLabel();
        combo_language = new javax.swing.JComboBox<>();
        combo_border = new javax.swing.JComboBox<>();
        combo_font = new javax.swing.JComboBox<>();
        label_cellcolor = new javax.swing.JLabel();
        combo_cellcolor = new javax.swing.JComboBox<>();
        button_cancel = new javax.swing.JButton();
        panel_lastCells = new javax.swing.JPanel();
        label_showlast = new javax.swing.JLabel();
        field_lastcells = new javax.swing.JTextField();
        label_cells = new javax.swing.JLabel();
        label_max_20 = new javax.swing.JLabel();
        label_backgroundlastcells = new javax.swing.JLabel();
        combo_lastcells = new javax.swing.JComboBox<>();
        panel_defaulttime = new javax.swing.JPanel();
        label_defaulttime = new javax.swing.JLabel();
        field_default_time = new javax.swing.JTextField();
        label_seconds = new javax.swing.JLabel();
        label_timewarning = new javax.swing.JLabel();
        label_lastvisited = new javax.swing.JLabel();
        label_default_max_maze_size = new javax.swing.JLabel();
        text_max_maze_size = new javax.swing.JTextField();
        label_max_maze_size_info = new javax.swing.JLabel();
        check_autofill_time = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Settings");

        label_backgroundcolor.setText("Background color:");

        label_bordercolor.setText("Border color:");

        label_playercolor.setText("Player and font color:");

        check_show_last_cells.setText("Show last cells");
        check_show_last_cells.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_show_last_cellsActionPerformed(evt);
            }
        });

        label_advancedsettings.setText("Advanced settings:");

        check_allow_bigger_maps.setText("Allow bigger maze-size than allowed by default");

        button_save.setText("Save");
        button_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_saveActionPerformed(evt);
            }
        });

        label_language.setText("Language:");

        label_cellcolor.setText("Cell color:");

        combo_cellcolor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_cellcolorActionPerformed(evt);
            }
        });

        button_cancel.setText("Cancel changes");
        button_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_cancelActionPerformed(evt);
            }
        });

        label_showlast.setText("Show last");

        label_cells.setText("cells");

        label_max_20.setText("Max. 20");

        label_backgroundlastcells.setText("Background color of last cells:");

        javax.swing.GroupLayout panel_lastCellsLayout = new javax.swing.GroupLayout(panel_lastCells);
        panel_lastCells.setLayout(panel_lastCellsLayout);
        panel_lastCellsLayout.setHorizontalGroup(
            panel_lastCellsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_lastCellsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_showlast)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_lastCellsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_max_20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(field_lastcells, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_cells)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panel_lastCellsLayout.createSequentialGroup()
                .addComponent(label_backgroundlastcells)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_lastcells, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel_lastCellsLayout.setVerticalGroup(
            panel_lastCellsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_lastCellsLayout.createSequentialGroup()
                .addGroup(panel_lastCellsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_showlast)
                    .addComponent(field_lastcells, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_cells))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_max_20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_lastCellsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_backgroundlastcells)
                    .addComponent(combo_lastcells, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        label_defaulttime.setText("Default time (timer/challange modes):");

        label_seconds.setText("seconds");

        label_timewarning.setText("<html>If value given above is less than 5 sec <br>\nor more than 8000 sec auto-fill time will be disabled.");

        javax.swing.GroupLayout panel_defaulttimeLayout = new javax.swing.GroupLayout(panel_defaulttime);
        panel_defaulttime.setLayout(panel_defaulttimeLayout);
        panel_defaulttimeLayout.setHorizontalGroup(
            panel_defaulttimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_defaulttimeLayout.createSequentialGroup()
                .addGroup(panel_defaulttimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_defaulttimeLayout.createSequentialGroup()
                        .addComponent(label_defaulttime)
                        .addGap(10, 10, 10)
                        .addComponent(field_default_time, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_seconds))
                    .addComponent(label_timewarning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 72, Short.MAX_VALUE))
        );
        panel_defaulttimeLayout.setVerticalGroup(
            panel_defaulttimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_defaulttimeLayout.createSequentialGroup()
                .addGroup(panel_defaulttimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_defaulttime)
                    .addComponent(field_default_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_seconds))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_timewarning, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        label_lastvisited.setText("Last visited cells:");

        label_default_max_maze_size.setText("Max maze size:");

        label_max_maze_size_info.setText("Must be more than 9 and less than 70");

        check_autofill_time.setText("Auto-fill time");
        check_autofill_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_autofill_timeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_advancedsettings)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(117, 117, 117)
                                        .addComponent(label_default_max_maze_size)
                                        .addGap(10, 10, 10))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label_backgroundcolor, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(label_playercolor, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(label_bordercolor, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(label_cellcolor, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(label_language, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(combo_language, 0, 136, Short.MAX_VALUE)
                                    .addComponent(text_max_maze_size)
                                    .addComponent(combo_background, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(combo_border, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(combo_cellcolor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(combo_font, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(check_autofill_time)
                                    .addComponent(panel_defaulttime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addComponent(label_max_maze_size_info))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panel_lastCells, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(label_lastvisited)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(check_show_last_cells)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(check_allow_bigger_maps)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_backgroundcolor)
                    .addComponent(combo_background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_border, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_bordercolor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_cellcolor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_cellcolor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_font, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_playercolor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_language)
                    .addComponent(combo_language, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_default_max_maze_size)
                    .addComponent(text_max_maze_size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_max_maze_size_info)
                .addGap(29, 29, 29)
                .addComponent(check_autofill_time)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_defaulttime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_lastvisited)
                    .addComponent(check_show_last_cells))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_lastCells, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_advancedsettings)
                .addGap(18, 18, 18)
                .addComponent(check_allow_bigger_maps)
                .addGap(19, 19, 19)
                .addComponent(button_save)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_cancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void check_show_last_cellsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_show_last_cellsActionPerformed
        hideLastCells();
    }//GEN-LAST:event_check_show_last_cellsActionPerformed

    private void button_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_cancelActionPerformed
        new MainScreen().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_button_cancelActionPerformed

    private void combo_cellcolorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_cellcolorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_cellcolorActionPerformed

    private void button_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_saveActionPerformed
        saveChanges();// TODO add your handling code here:
    }//GEN-LAST:event_button_saveActionPerformed

    private void check_autofill_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_autofill_timeActionPerformed
        hideAutoFill();        // TODO add your handling code here:
    }//GEN-LAST:event_check_autofill_timeActionPerformed

    private void saveChanges(){
        String errors ="";//If any error arises, then it will be added to this string, so user can see where he/she made mistakes
        int error_numbers = 0; //Counts how many errors have occured in entered data
        int language = settings.getLanguageInt(); //same language for all error messages guaranteed
        //Now we have to set all values of settings object
        settings.setBackgroundColor(getColor(combo_background.getSelectedIndex()));
        settings.setCellBorderColor(getColor(combo_border.getSelectedIndex()));
        settings.setCellBackgroundColor(getColor(combo_cellcolor.getSelectedIndex()));
        settings.setPlayerColor(getColor(combo_font.getSelectedIndex()));
        settings.setLanguageInt(combo_language.getSelectedIndex());
        try{
            int maxmaze = Integer.parseInt(text_max_maze_size.getText());
            if(maxmaze<10 || maxmaze>=70)
                throw new Exception();
            settings.setMaxMapSize(maxmaze);
        }catch(Exception e){ //Any error that occurs, either parsing or the given value is out of range, we should add it to errors
            errors = errors+LanguageTexts.SETTINGS_ERROR_MAX_MAZE_SIZE[language]+"\r\n";
            error_numbers++;
        }
        if(!check_autofill_time.isSelected()){//if not selected we can put any values less than 5 or more than 8000 and it will not autofill the time
            settings.setDefaultTime(-1);
        }else{
            try{
                settings.setDefaultTime(Integer.parseInt(field_default_time.getText()));
            }catch(Exception e){ //if number format is wrong than we append it to errors String
                errors = errors+LanguageTexts.ERROR_DEFAULT_TIME[language]+"\r\n";
                error_numbers++;
            }
        }
        if(!check_show_last_cells.isSelected()){
            settings.setLatestCellsNumber(0);
        }else{
            try{
                int lastcells = Integer.parseInt(field_lastcells.getText());
                if(lastcells>=0 && lastcells<=20)
                    settings.setLatestCellsNumber(lastcells);
                else
                    throw new Exception();
            }catch(Exception e){//error arising from parsing or given number is out of range
                errors = errors+LanguageTexts.ERROR_LAST_CELLS_NUMBER[language];
                error_numbers++;
            }
        }
        settings.setLastCellsColor(getColor(combo_lastcells.getSelectedIndex()));
        if(check_allow_bigger_maps.isSelected())
            settings.setOverridableMapsize(true);
        else
            settings.setOverridableMapsize(false);
        if(error_numbers == 0){ //If no errors have occured we can save everything
            settings.saveSettings();
            this.dispose();
            new MainScreen().setVisible(true);
        }
        else{//If error occured text should be shown to user what to change
            String error_message = LanguageTexts.ERRORS_OCCURED[language]+"\r\n"+errors+LanguageTexts.ERRORS_MODIFY_PROBLEMS[language];
            JOptionPane.showMessageDialog(this, error_message);
        }
    }
    
    private Color getColor(int colorindex){ //colorindex must be the same in GameConstants color_names and all combo_boxes containing colors
        switch(colorindex){
            case 0:
                return Color.BLACK;
            case 1:
                return Color.GRAY;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.ORANGE;
            case 5:
                return Color.RED;
            case 6:
                return Color.YELLOW;
            case 7:
                return Color.PINK;
            default: //nothing else remains let us call this the default case, when extending the project, it will be easier to see where possible error is (if any).
                return Color.WHITE;
        }
    }
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
            java.util.logging.Logger.getLogger(SettingsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SettingsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SettingsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SettingsScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SettingsScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_cancel;
    private javax.swing.JButton button_save;
    private javax.swing.JCheckBox check_allow_bigger_maps;
    private javax.swing.JCheckBox check_autofill_time;
    private javax.swing.JCheckBox check_show_last_cells;
    private javax.swing.JComboBox<String> combo_background;
    private javax.swing.JComboBox<String> combo_border;
    private javax.swing.JComboBox<String> combo_cellcolor;
    private javax.swing.JComboBox<String> combo_font;
    private javax.swing.JComboBox<String> combo_language;
    private javax.swing.JComboBox<String> combo_lastcells;
    private javax.swing.JTextField field_default_time;
    private javax.swing.JTextField field_lastcells;
    private javax.swing.JLabel label_advancedsettings;
    private javax.swing.JLabel label_backgroundcolor;
    private javax.swing.JLabel label_backgroundlastcells;
    private javax.swing.JLabel label_bordercolor;
    private javax.swing.JLabel label_cellcolor;
    private javax.swing.JLabel label_cells;
    private javax.swing.JLabel label_default_max_maze_size;
    private javax.swing.JLabel label_defaulttime;
    private javax.swing.JLabel label_language;
    private javax.swing.JLabel label_lastvisited;
    private javax.swing.JLabel label_max_20;
    private javax.swing.JLabel label_max_maze_size_info;
    private javax.swing.JLabel label_playercolor;
    private javax.swing.JLabel label_seconds;
    private javax.swing.JLabel label_showlast;
    private javax.swing.JLabel label_timewarning;
    private javax.swing.JPanel panel_defaulttime;
    private javax.swing.JPanel panel_lastCells;
    private javax.swing.JTextField text_max_maze_size;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        int operation = JOptionPane.showConfirmDialog(this, LanguageTexts.CLOSE_WITHOUT_SAVING_WARNING_SETTINGS[language],LanguageTexts.WARNING_TEXT[language],JOptionPane.YES_NO_CANCEL_OPTION);
        if(operation == JOptionPane.YES_OPTION){
            saveChanges();
            new MainScreen().setVisible(true);
            this.dispose();
        }else if(operation == JOptionPane.NO_OPTION){
            new MainScreen().setVisible(true);
            this.dispose();
        }
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }
}
