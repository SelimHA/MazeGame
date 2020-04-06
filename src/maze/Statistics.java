/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class Statistics {
    int win_normal, win_timer, win_challange, lost_normal, lost_timer, lost_challange, win_total, lost_total; //values for all possibilities
    //Above int could have been put into an array, however it would be more difficult to oversee which index stands for which value
    //an array could have been used, however it would be harder to overlook which index stands for which type of winning/losing
    File statslocation = new File("./MazeFiles/stats.maze"); //Location of statistics file
    Settings settings = new Settings();
    public Statistics(){
        loadStatistics();
    }
    
    /**
     *Refreshes the statistics, statistics are automatically refreshed when other methods in statistics are executed.
     */
    public void refreshStatistics(){
        writeStatistics();
    }
    
    /**
     *Resets the statistics (all values will be 0), and saves it.
     */
    public void resetStatistics(){
        win_normal=0;
        win_timer=0;
        win_challange=0;
        lost_normal=0;
        lost_timer=0;
        lost_challange=0;
        win_total=0;
        lost_total=0;
        refreshStatistics();
    }
    /**
     *Adds a new win to normal mode
     */
    public void newNormalWin(){
        win_normal++;
        win_total++;
        refreshStatistics();
    }

    /**
     *Adds a new win to Timer mode
     */
    public void newTimerWin(){
        win_timer++;
        win_total++;
        refreshStatistics();
    }

    /**
     *Adds a new win to Challenge mode
     */
    public void newChallangeWin(){
        win_challange++;
        win_total++;
        refreshStatistics();
    }
    
    /**
     *Adds a new lost game to normal mode
     */
    public void newNormalLost(){
        lost_normal++;
        lost_total++;
        refreshStatistics();
    }

    /**
     *Adds a new lost game to Timer mode
     */
    public void newTimerLost(){
        lost_timer++;
        lost_total++;
        refreshStatistics();
    }

    /**
     *Adds a new lost game to Challange mode
     */
    public void newChallangeLost(){
        lost_challange++;
        lost_total++;
        refreshStatistics();
    }
    
    /**
     * Returns the total number of wins.
     * @return
     */
    public int getTotalWin(){
        return win_total;
    }
    /**
     * Returns the total number of lost games.
     * @return
     */
    public int getTotalLost(){
        return lost_total;
    }
    
    /**
     * Returns the number of normal mode wins.
     * @return
     */
    public int getNormalWin(){
        return win_normal;
    }
    /**
     * Returns the number of normal mode game losts.
     * @return
     */
    public int getNormalLost(){
        return lost_normal;
    }
    /**
     * Returns the number of timer mode wins.
     * @return
     */
    public int getTimerWin(){
        return win_timer;
    }
    /**
     * Returns the number of timer mode game losts.
     * @return
     */
    public int getTimerLost(){
        return lost_timer;
    }
    /**
     * Returns the number of challenge mode wins.
     * @return
     */
    public int getChallangeWin(){
        return win_challange;
    }
    /**
     * Returns the number of challenge mode game losts.
     * @return
     */
    public int getChallangeLost(){
        return lost_challange;
    }
    
    /**
     *Writes the current values stored in the object to a file
     * Format will follow the sample file
     */
    public void writeStatistics(){
        BufferedWriter write = null;
        try{
           if(!statslocation.exists()){ //if the statistic files does not exist create new
               if(!statslocation.getParentFile().exists()) //if the folder containing the statistic file does not exist than create that folder
                   statslocation.getParentFile().mkdirs();
               statslocation.createNewFile();
           }
           //now we can start writing the file
           write = new BufferedWriter(new FileWriter(statslocation));
           write.write(win_normal+"\r\n");
           write.write(win_timer+"\r\n");
           write.write(win_challange+"\r\n");
           write.write(lost_normal+"\r\n");
           write.write(lost_timer+"\r\n");
           write.write(lost_challange+"\r\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, LanguageTexts.STATISTICS_IO_EXCEPTION_OCCURED[settings.getLanguageInt()]);
        }finally{
            if(write !=null){
                try {
                    write.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, LanguageTexts.STATISTICS_IO_EXCEPTION_OCCURED[settings.getLanguageInt()]);
                }
            }
        }
    }
    
    private void loadStatistics(){ //it is a fail than nothing method (it means that if any part of reading fails every value will be reset to default -> prevent using false/corrupted data)
        BufferedReader read = null;
        try{
            if(!statslocation.exists()){//it means non-existent stats file, inform user and create a new default one
                JOptionPane.showMessageDialog(null, LanguageTexts.STATISTICS_FILE_DOES_NOT_EXIST[settings.getLanguageInt()]);
                writeStatistics();
            }
            read = new BufferedReader(new FileReader(statslocation));
            win_normal = Integer.parseInt(read.readLine());
            win_timer = Integer.parseInt(read.readLine());
            win_challange = Integer.parseInt(read.readLine());
            lost_normal = Integer.parseInt(read.readLine());
            lost_timer = Integer.parseInt(read.readLine());
            lost_challange = Integer.parseInt(read.readLine());
            win_total = win_normal+win_timer+win_challange;
            lost_total = lost_normal+lost_timer+lost_challange;
        } catch (FileNotFoundException ex) {//If file not found, we should create a new one with default values, resetStatistics supports FileNotFound, user is informed what the error is
            resetStatistics();
            JOptionPane.showMessageDialog(null, LanguageTexts.STATISTICS_ERROR_OCCURED_RESETTED[settings.getLanguageInt()]);
        } catch (IOException ex) { //same as above catch (new stats file will be created), bot now user will get another warning
            resetStatistics();
            JOptionPane.showMessageDialog(null, LanguageTexts.STATISTICS_IO_EXCEPTION_OCCURED[settings.getLanguageInt()]);
        }catch(Exception e){
            //if any exception occurs all the statistics will be resetted and user informed
            resetStatistics();
            JOptionPane.showMessageDialog(null, LanguageTexts.STATISTICS_ERROR_OCCURED_RESETTED[settings.getLanguageInt()]);
        }finally{
            if(read!=null)
                try {
                    read.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, LanguageTexts.STATISTICS_IO_EXCEPTION_OCCURED[settings.getLanguageInt()]);
            }
        }
    }
    
}
