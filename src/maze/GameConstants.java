/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.io.File;

/**
 *
 * @author
 */
public class GameConstants {
       static int NORMAL_MODE = 0; //Normal mode's value
       static int TIMER_MODE = 1; //Timer mode's value
       static int CHALLANGE_MODE = 2; //Challange mode's value

       static int ENGLISH = 0; //English language's value
       static int HUNGARIAN = 1;//Hungarian language's value
       static int FRENCH = 2;//French language's value
       
       //Color table: //first 3 names/color (English-Hungarian-Frnech)
       static String[][] color_names = {{"Black","Fekete","Noir"},{"Grey","Szürke","Gris"},{"Blue","Kék","Bleu"},{"Green","Zöld","Vert"},{"Orange","Narancs","Orange"},{"Red","Piros","Rouge"},{"Yellow","Citrom","Jaune"},{"Pink","Rózsaszín","Rose"},{"White","Fehér","Blanc"}};
       static String[] languages = {"English","Magyar","Français"}; //It will be the refernce value for the number of languages, it is the task of the developer to ensure that all language files for the given language is available
       
       static File savegame_location_map = new File("./MazeFiles/savegame_map.maze"); //location of file containing info about the saved map
       static File savegame_location_details = new File("./MazeFiles/savegame_details.maze"); //location of file containing info about last game's status/info.
}
