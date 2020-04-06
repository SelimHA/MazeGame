/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class Maze {
    //This is the main method, it decides whether the app has been run before (then heads to MainScreen). If not FirstRun is shown.
    static File firstrun = new File ("./MazeFiles/firstrun.maze");
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        if(firstrun.exists()){//means the software had been run prior
            new MainScreen().setVisible(true);
        }else{
            if(!firstrun.getParentFile().exists()) //if MazeFiles folder is missing, create it
                firstrun.getParentFile().mkdirs();
            try {
                firstrun.createNewFile(); //firstrun is an empty file indicating that the game had been run before. If it is deleted/non-existent than FirstRun window will appear.
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, LanguageTexts.INITIALIZER_FAILED_TO_WRITE_FIRST_RUN_FILE);
                Logger.getLogger(Maze.class.getName()).log(Level.SEVERE, null, ex);
            }
            new FirstRun().setVisible(true);
        }
    }
}
