/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class Settings {
    private Color background_color = Color.GREEN, player_color = Color.WHITE, cell_background_color = Color.BLACK, cell_border_color = Color.YELLOW, last_cells_color = Color.PINK;
    private int max_map_size = 50;
    private boolean override_max_map_size = false;
    private int default_time = -1;
    private int language = GameConstants.ENGLISH;
    private int latest_cells_number = 5;
    private File settings = new File("./MazeFiles/Settings.maze");
    /**
     *This object is used to store the settings. Values are automatically imported from the settings file. If import is unsuccessful default values will be used and user would be notified.
     */
    public Settings(){
        loadSettings();
    }
    
    /**
     *Returns the background color of Maze stored in the object. If object has been improperly loaded/initiated, it returns a default value.
     * @return the color of the background
     */
    public Color getBackgroundColor(){
        return background_color;
    }

    /**
     * Sets the background color for the Maze for this object. Note that this method does not save the object. You need to initiate the saveSettings method.
     * @param background the background color you want for the game (MazeScreen)
     */
    public void setBackgroundColor(Color background){
        background_color = background;
    }
    
    /**
     *Returns the background color of the cell in the current object. If object has been improperly loaded/initiated, it returns a default value.
     */
    public Color getPlayerColor(){
        return player_color;
    }

    /**
     * Sets the background color for the cell for this object. Note that this method does not save the object. You need to initiate the saveSettings method.
     * @param player the font/player color for the cell
     */
    public void setPlayerColor(Color player){
        player_color = player;
    }
    
    /**
     *Returns the background color of the cells  in the current object. If object has been improperly loaded/initiated, it returns a default value.
     * @return
     */
    public Color getCellBackgroundColor(){
        return cell_background_color;
    }

    /**
     * Sets the background color for the cell in the current object. Note that this method does not save the object. You need to initiate the saveSettings method.
     * @param background the background color of the cell
     */
    public void setCellBackgroundColor(Color background){
        cell_background_color = background;
    }

    /**
     *Returns the border color of the cell stored in this object.
     * @return
     */
    public Color getCellBorderColor(){
        return cell_border_color;
    }
    /**
     * Sets the border color of the cell for this object. Note that this method does not save the object. You need to initiate the saveSettings method.
     * @param border_color the border color of the cell
     */
    public void setCellBorderColor(Color border_color){
        cell_border_color = border_color;
    }

    /**
     *Returns the color of the last x cells that the player has visited ("hover effect") if enabled. Note that this method does not save the object. You need to initiate the saveSettings method.
     * @return
     */
    public Color getLastCellsColor(){
        return last_cells_color;
    }
    /**
     *Sets the color of the last x cells that the player has visited ("hover effect") if enabled. Note that this method does not save the object. You need to initiate the saveSettings method.
     * @param color the color of the last x cells
     */
    public void setLastCellsColor(Color color){
        last_cells_color = color;
    }
    
    /**
     *Returns the max map size allowed by the user.
     * @return
     */
    public int getMaxMapSize(){
        return max_map_size;
    }

    /**
     * Sets the max map size allowed by the user. Note that this method does not save the object. You need to initiate the saveSettings method.
     * @param max_size the maximum number of mapsize allowed
     */
    public void setMaxMapSize(int max_size){
        max_map_size = max_size;
    }

    /**
     * Returns whether user can enter bigger map size than allowed and get still accepted it. 
     * @return
     */
    public boolean isOverridableMapsize(){
        return override_max_map_size;
    }

    /**
     * Sets whether user can enter bigger map size than allowed and get still accepted it. Note that this method does not save the object. You need to initiate the saveSettings method.
     * @param value whether user can override max mapsize
     */
    public void setOverridableMapsize(boolean value){
        override_max_map_size = value;
    }
    
    /**
     *Gets the default time the user selected for timer and challenge mode, this will be autofilled. Negative number and numbers less than 5 also accepted (it means no autofill required).
     * @return
     */
    public int getDefaultTime(){
        return default_time;
    }

    /**
     *Gets the default time the user selected for timer and challenge mode, this will be autofilled. Negative number and numbers less than 5 also accepted (it means no autofill required). Note that this method does not save the object. You need to initiate the saveSettings method.
     * @param time the default time for timer and challenge modes
     */
    public void setDefaultTime(int time){
        default_time = time;
    }
    
    /**
     * Returns which language have been selected. Language numbers are located in GameConstants class.
     * @return
     */
    public int getLanguageInt(){
        return language;
    }

    /**
     * Sets which language the player wants to use. Language numbers are located in GameConstants class. Note that this method does not save the object. You need to initiate the saveSettings method.
     * @param language_int the language you want to use
     */
    public void setLanguageInt(int language_int){
        language = language_int;
    }
    
    /**
     *Returns how many of the prior latest steps of the player should be shown. 0 means no cells.
     * @return
     */
    public int getLatestCellsNumber(){
        return latest_cells_number;
    }

    /**
     *Sets how many of the prior latest steps of the player should be shown. 0 means no cells. Note that this method does not save the object. You need to initiate the saveSettings method.
     * @param number the number of last cells you want to show
     */
    public void setLatestCellsNumber(int number){
        latest_cells_number = number;
    }
    
    /**
     *Refreshes the values from the predefined file containing the settings.
     */
    public void refreshSettings(){
        loadSettings();
    }
    
    private void loadSettings(){
        //reading settings is all or nothing, i.e.: everything is default all, everything is as defined in the file
        Color[] colors = new Color[5]; //Background-Player/Text-Cellbackground-Border-Last_cells
        int temp_max_map_size;
        boolean temp_override_max_map_size;
        int temp_default_time;
        int temp_language;
        int temp_latest_cells_number;
        BufferedReader read = null;
        try{
            if(!settings.exists()){//if file does not exist inform user and create new one with default values
                JOptionPane.showMessageDialog(null, LanguageTexts.SETTINGS_FILE_DOES_NOT_EXIST);
                saveSettings(); //save settings method can handle creating the path to the settings and file containing the settings
            }
            read = new BufferedReader(new FileReader(settings));
            //First read the colors:
            for(int current = 0; current<5; current++){
                String[] color_values = read.readLine().split("\t");
                int[] colorRGB = new int[4];
                for(int i = 0; i<4; i++){
                    colorRGB[i]=Integer.parseInt(color_values[i]);
                    if(colorRGB[i]>255 || colorRGB[i]<0) //Then not RGB color code
                        throw new Exception();
                }
                colors[current]= new Color(colorRGB[0],colorRGB[1],colorRGB[2],colorRGB[3]);
            }
            //now read the language;
            temp_language = Integer.parseInt(read.readLine());
            if(temp_language<0 || temp_language>=GameConstants.languages.length)//Language does not exist, it is the task of the developer to ensure that all translation for a given lanugage was done for all texts.
                throw new Exception();
            //read the max map size:
            temp_max_map_size = Integer.parseInt(read.readLine());
            //decide whether user can ovverride max mapsize
            String overridable_mapsize = read.readLine();
            if(overridable_mapsize.toLowerCase().equals("t")){
                temp_override_max_map_size = true;
            }else if(overridable_mapsize.toLowerCase().equals("f")){
                temp_override_max_map_size = false;
            }else{ //any invalid data
                throw new Exception();
            }
            //now read default time:
            temp_default_time = Integer.parseInt(read.readLine());
            //now read latest cells number:
            temp_latest_cells_number = Integer.parseInt(read.readLine());
            //everything is correct so we can procede on replacing the old/default data with the new one
            background_color = colors[0];
            player_color = colors[1];
            cell_background_color = colors[2];
            cell_border_color = colors[3];
            last_cells_color = colors[4];
            max_map_size = temp_max_map_size;
            override_max_map_size = temp_override_max_map_size;
            default_time = temp_default_time;
            language = temp_language;
            latest_cells_number = temp_latest_cells_number;
        } catch (FileNotFoundException ex) { //In exception current/default settings are saved and then loaded, an error warning is also shown
            JOptionPane.showMessageDialog(null, LanguageTexts.SETTINGS_FILE_DOES_NOT_EXIST);
            saveSettings();
            loadSettings();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, LanguageTexts.IO_EXCEPTION_OCCURED);
            saveSettings();
            loadSettings();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, LanguageTexts.ERROR_OCCURED_RELOAD);
            saveSettings();
            loadSettings();
        }finally{
            if(read!=null)
                try {
                    read.close();
            } catch (IOException ex) {
                Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     *Save the current values into the predefined file according to the format shown in the template file
     */
    public void saveSettings(){
        BufferedWriter write = null;
        try{
            if(!settings.exists()){
                File folder = new File(settings.getParent());
                if(!folder.exists())
                    folder.mkdir();
                settings.createNewFile();
            }
            write = new BufferedWriter(new FileWriter(settings));
            //now we will start to write in the same order as shown in template file:
            write.write(background_color.getRed()+"\t"+background_color.getGreen()+"\t"+background_color.getBlue()+"\t"+background_color.getAlpha()+"\r\n");
            write.write(player_color.getRed()+"\t"+player_color.getGreen()+"\t"+player_color.getBlue()+"\t"+player_color.getAlpha()+"\r\n");
            write.write(cell_background_color.getRed()+"\t"+cell_background_color.getGreen()+"\t"+cell_background_color.getBlue()+"\t"+cell_background_color.getAlpha()+"\r\n");
            write.write(cell_border_color.getRed()+"\t"+cell_border_color.getGreen()+"\t"+cell_border_color.getBlue()+"\t"+cell_border_color.getAlpha()+"\r\n");
            write.write(last_cells_color.getRed()+"\t"+last_cells_color.getGreen()+"\t"+last_cells_color.getBlue()+"\t"+last_cells_color.getAlpha()+"\r\n");
            write.write(language+"\r\n");
            write.write(max_map_size+"\r\n");
            if(override_max_map_size){
                write.write("t\r\n");
            }else{
                write.write("f\r\n");
            }
            write.write(default_time+"\r\n");
            write.write(latest_cells_number+"\r\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, LanguageTexts.IO_EXCEPTION_OCCURED_SAVING);
        }finally{
            if(write !=null){
                try {
                    write.close();
                } catch (IOException ex) {
                    Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
