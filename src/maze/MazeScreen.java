/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author
 */
public class MazeScreen extends JFrame implements ActionListener, KeyListener, WindowListener {
    String title_part1, title_part2, title_part3="";
    //Settings:
    Settings settings = new Settings();
    //Statistics
    Statistics stats = new Statistics();
    //Basic functions:
    GameCommands gamecommands = new GameCommands();
    //mapsize:
    int mapdimensions;
    //map
    Cell[][] map;
    //Appearance
    Color background_color;
    //Give up button:
    JButton button_giveup;
    //Player's position:
    int player_row = 0, player_column = 0;
    int finish_row, finish_column;
    //int gametype
    int gametype;
    //Language
    int language;
    //timer for challange and timer modes:
    Timer timer;
    int game_time; //time entered by user
    int remaining_time;
    int start_time = 0;
    //For challange mode:
    int lives_remaining;
    //Loading game ->if game is a saved one player cannot exit without saving.(even if not moved at all)
    boolean gameloaded = false;
    /**
     *This is the MazeScreen object
     * @param gamemode: integer for the game's mode found in the GameConstants class
     * @param time
     * @param mazesize
     * @param lives
     */
    public MazeScreen(int gamemode, int time, int mazesize, int lives){
        gametype = gamemode;
        mapdimensions = mazesize;
        game_time = time;
        remaining_time = time;
        lives_remaining = lives -1; //Remaining lives are 1 less than total lives
        importSettings();
        createMap();
        setTexts();
        timer = new Timer(1000,this);
        drawGUI();
    }

    private void importSettings() {
        language = settings.getLanguageInt();
        background_color = settings.getBackgroundColor();
    }
    
    private void createMap(){
        if(!GameConstants.savegame_location_map.exists() || !GameConstants.savegame_location_details.exists()){ //If files are missing or do not exist than generate map
            map = gamecommands.generateMap(mapdimensions,settings.getCellBorderColor(),settings.getCellBackgroundColor(),settings.getPlayerColor());
            createFinishPosition();
        }
        else{
            loadMap(); //loads the map from files
        }
    }
    
    private void loadMap(){
        map = gamecommands.loadMap(settings.getCellBorderColor(), settings.getCellBackgroundColor(), settings.getPlayerColor()); //contians map, all cells initialized
        ArrayList<Integer> data = gamecommands.loadGameFiles(); //contains info
        //After loading, delete files as they will not be used anymore
        GameConstants.savegame_location_map.delete();
        GameConstants.savegame_location_details.delete();
        if(data==null || map == null){ //If null is returned when loading the files there was an error -> Terminate game
            showFatalErrorWarning();
        }
        mapdimensions = map.length;
        player_row = data.get(0);
        if(player_row>=map.length || player_row<0) //If data in file was modified and now player_row would be out of array -> Terminate game
            showFatalErrorWarning();
        player_column = data.get(1);
        if(player_column>=map[0].length || player_column<0)//If data in file was modified and now player_column would be out of array -> Terminate game
            showFatalErrorWarning();
        finish_row = data.get(2);
        if(finish_row>=map.length || finish_row<0)//If data in file was modified and now finish_row would be out of array -> Terminate game
            showFatalErrorWarning();
        finish_column = data.get(3);
        if(finish_column>=map[0].length || finish_column<0)//If data in file was modified and now finish_column would be out of array -> Terminate game
            showFatalErrorWarning();
        gametype = data.get(4);
        if(gametype>2)//Gametype cannot be more than 2 currently, if new game mode is added, modify this statement
            showFatalErrorWarning();
        game_time = data.get(5);
        remaining_time = data.get(6);
        lives_remaining = data.get(7);
        if(lives_remaining<0 && gametype==GameConstants.CHALLANGE_MODE){ //lives cannot be less than 0 (possibly someone edited the savegame file) -> Terminate game
            showFatalErrorWarning();
        }
        gameloaded = true;
    }
    
    private void showFatalErrorWarning(){
        JOptionPane.showMessageDialog(this,LanguageTexts.FATAL_ERROR_EXITING_TEXT[language], LanguageTexts.WARNING_TEXT[language] , JOptionPane.OK_OPTION);
        this.dispose();
        new MainScreen().setVisible(true);
    }
    
    private void setTexts(){
        //Sets title of Window in current language and shows relevant info according to which game mode was choosen.
        //If new game mode is added please add if statement, otherwise user will not see all information on the game
        title_part1 = LanguageTexts.MAZE_TITLE_GAMENAME[language];
        if(gametype == GameConstants.NORMAL_MODE){
            title_part1 = title_part1 + " " + LanguageTexts.NORMAL_MODE_TEXT_jOPTION[language];
            title_part2 = " - "+LanguageTexts.MAZE_TITLE_TIME_ELAPSED[language];
        }else if(gametype == GameConstants.TIMER_MODE){
            title_part1 = title_part1 + " " + LanguageTexts.TIMER_MODE_TEXT_jOPTION[language];
            title_part2 = " - "+LanguageTexts.MAZE_TITLE_TIME_REMAINING[language];
        }else if(gametype == GameConstants.CHALLANGE_MODE){
            title_part1 = title_part1 + " " + LanguageTexts.CHALLENGE_MODE_TEXT_jOPTION[language];
            title_part2 = " - "+LanguageTexts.MAZE_TITLE_TIME_REMAINING[language];
            title_part3 = " - "+LanguageTexts.MAZE_TITLE_LIVES_REMAINING[language];
        }
    }
   
    Container mapholder = new Container();
    //the below method will draw the GUI and set the parameters of the JFrame
    //The GUI will be composed of a 2D array of Cells held in a Container.
    private void drawGUI() {
        this.setLayout(new BorderLayout());
        this.setSize(500, 500);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //WindowListener is used instead
        this.addWindowListener(this);
        this.getContentPane().setBackground(background_color);
        setUpGiveUpButton();//set the properties of give up and add ActionListener to it
        //also it adds giveup to the jFrame
        map[player_row][player_column].setPlayerPosition(true);
        map[finish_row][finish_column].setFinishPosition(true);
        drawMap();
        this.addKeyListener(this);
        //Set the map to centre of the window
        this.add(mapholder, BorderLayout.CENTER);
    }
    
    private void drawMap(){
        //Basically adds every cell to a Container, which will be shown in the centre of the window
        mapholder.setLayout(new GridLayout(mapdimensions,mapdimensions));
        for(int i = 0; i<mapdimensions; i++){
            for(int ii = 0; ii<mapdimensions; ii++){
                map[i][ii].drawCell();
                mapholder.add(map[i][ii]);
            }
        }
    }
    
    private void setUpGiveUpButton() {
        button_giveup = new JButton(LanguageTexts.GIVE_UP_BUTTON_TEXT[language]);
        button_giveup.setToolTipText(LanguageTexts.GIVE_UP_BUTTON_TOOL_TIP_TEXT[language]);
        button_giveup.addActionListener(this);
        button_giveup.setFocusable(false);
        this.add(button_giveup, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(timer)){ //Timer continuosly invokes this method
            if(gametype == GameConstants.NORMAL_MODE){
                start_time++;//here elapsed time is important
                this.setTitle(title_part1+title_part2+start_time+title_part3);
            }else{
                //In remaining two game modes remaining time is important
                remaining_time--;
                if(gametype == GameConstants.TIMER_MODE)
                    this.setTitle(title_part1+title_part2+remaining_time+title_part3);
                else
                    this.setTitle(title_part1+title_part2+remaining_time+title_part3+lives_remaining); //lives should also be shown in challange mode
                if(remaining_time==0){
                    //player lost the game or a life
                    if(gametype == GameConstants.TIMER_MODE){
                        endGame(false);
                        //lost the game
                    }else if(gametype == GameConstants.CHALLANGE_MODE){
                        lives_remaining--;
                        if(lives_remaining == -1){
                            endGame(false);
                            //If player has no remaining lives he lost the game
                            //note that prior to if we took away one, and here not "posessed" lives, but remaining lives are counted
                        }else{
                            //If player had a remaining life, then map is shuffled, countdown of time restarted
                            remaining_time = game_time;
                            shuffleMap();
                        }
                    }
                }
            }
        }else if(ae.getSource().equals(button_giveup)){
            //If button is pressed game ends. Whenever game end, a path is shown to finish position and timer restarted, player will be unable to move
            endGame(false);
        }
    }
    
    private void shuffleMap(){
        map = gamecommands.generateMap(mapdimensions,settings.getCellBorderColor(),settings.getCellBackgroundColor(),settings.getPlayerColor());
        //creates new map
        mapholder.removeAll(); //mapholder is a Container used to store all the cells
        drawMap();//draws the map and adds all cells to mapholder
        map[player_row][player_column].setPlayerPosition(true);
        createFinishPosition();
        //All cells have been set to default, so we don't have to find the old finish position:
        map[finish_row][finish_column].setFinishPosition(true);
    }

    private void endGame(boolean won){
        this.removeKeyListener(this);//So user cannot control the player anymore
        timer.stop();
        button_giveup.setEnabled(false);//cannot press give up button as game has ended and already lost this round
        if(won){
            JOptionPane.showMessageDialog(this,LanguageTexts.WIN_GAME_POP_UP_MESSAGE[language]);
        }//lost case cannot be handled here as messages should pop up after pathfinding and stats handling, however timer must be stopped first and KeyListener removed
        gamestart = false;
        handleStats(won);//register statistics either win or lost game
        if(!won){
            gamecommands.findPath(map, player_row, player_column, finish_row, finish_column);
            JOptionPane.showMessageDialog(this,LanguageTexts.LOST_GAME_POP_UP_MESSAGE[language]);
        }
    }
    

    @Override
    public void keyTyped(KeyEvent ke) {
    }
    
    ArrayList<Cell> lastcells = new ArrayList<Cell>(); //to handle the last cells, stack could not be used as user might turn back
                                                       //Stack only can give back the last element
    boolean gamestart = false; //for timer and challange mode, when first input, the time starts (independent whether correct or not);
    @Override
    public void keyPressed(KeyEvent ke) {
        boolean playermoved = false;
        if(!gamestart){
            timer.start();
            gamestart = true;
        }
        if(ke.getKeyCode() == KeyEvent.VK_UP && map[player_row][player_column].hasTopBorder() == false){
            //if up pressed and there is no upper border (i.e.:player can move upwards)
            map[player_row][player_column].setPlayerPosition(false);
            player_row--;
            map[player_row][player_column].setPlayerPosition(true);
            playermoved = true;
        }else if(ke.getKeyCode() == KeyEvent.VK_DOWN && map[player_row][player_column].hasBottomBorder() == false){
            map[player_row][player_column].setPlayerPosition(false);
            player_row++;
            map[player_row][player_column].setPlayerPosition(true);
            playermoved = true;
        }else if(ke.getKeyCode() == KeyEvent.VK_LEFT && map[player_row][player_column].hasLeftBorder() == false){
            map[player_row][player_column].setPlayerPosition(false);
            player_column--;
            map[player_row][player_column].setPlayerPosition(true);
            playermoved = true;
        }else if(ke.getKeyCode() == KeyEvent.VK_RIGHT && map[player_row][player_column].hasRightBorder() == false){
            map[player_row][player_column].setPlayerPosition(false);
            player_column++;
            map[player_row][player_column].setPlayerPosition(true);
            playermoved = true;
        }
        if(playermoved){
            gameloaded = false; //valid move does not matter anymore that game has been loaded or autogenerated (importance at WindowListener's closing method)
        }
        if(settings.getLatestCellsNumber()>0){
            //last cells should be shown if more than 0
            if(playermoved){
                //if player's movement was valid (i.e.:Player truly moved, not only pressed a random key)
                map[player_row][player_column].setBackground(settings.getLastCellsColor());
                if(lastcells.contains(map[player_row][player_column])) 
                // in all cases user might turn back, then the last cell might be the same as one visited prior,
                //the prior one has to be rewritten as last cell to show 'x' cells anytime.
                    lastcells.remove(map[player_row][player_column]);
                lastcells.add(map[player_row][player_column]);
            }
            if(lastcells.size()==settings.getLatestCellsNumber()){
                //if reached the max size allowed than remove the earliest cell
                lastcells.get(0).setBackground(settings.getCellBackgroundColor());
                lastcells.remove(0);
            }
        }
        if(map[player_row][player_column].isFinishPosition()){ //win case
            endGame(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    private void createFinishPosition() { // this method creates the initial finish position
        //location is in one of the corner furthest from player's initial position
        if(player_row<(int)(map.length/2)){
            //if true player is in upper part of the map, so put finish to lowest row
            finish_row = map.length-1;
        }else{
            finish_row = 0;
        }
        if(player_column<(int)(map[0].length/2)){
            //if true player is on right side, put finish to leftmost column
            finish_column = map[0].length-1;
        }else{
            finish_column = 0;
        }
    }

    private void handleStats(boolean won) { //GameMode is the integer of the game type (eg: normal mode), won: true -> won the game; else lost.
           if(won){
               if(gametype == GameConstants.NORMAL_MODE)
                   stats.newNormalWin();
               else if(gametype == GameConstants.TIMER_MODE)
                   stats.newTimerWin();
               else
                   stats.newChallangeWin();
           }else{
               if(gametype == GameConstants.NORMAL_MODE)
                   stats.newNormalLost();
               else if(gametype == GameConstants.TIMER_MODE)
                   stats.newTimerLost();
               else
                   stats.newChallangeLost();
           }
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        if(gamestart || gameloaded){ //game has not ended and player started to move (even if did wrong input game has started), or it was a saved map and no movement yet
            timer.stop();
            int decision = JOptionPane.showConfirmDialog(this, LanguageTexts.QUIT_WITHOUT_SAVING[language],LanguageTexts.WARNING_TEXT[language],JOptionPane.YES_NO_CANCEL_OPTION);
            if(decision == JOptionPane.YES_OPTION){
                saveGame();
            }else if(decision == JOptionPane.NO_OPTION){
                handleStats(false);
                this.dispose();
                new MainScreen().setVisible(true);
            }else{
                timer.start();
            }
        }else{
            this.dispose();
            new MainScreen().setVisible(true);
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
    
    private void saveGame(){ 
        //SAves the game
        gamecommands.saveGame(map, player_row, player_column, finish_row, finish_column, gametype,game_time, remaining_time, lives_remaining);
        this.dispose();
        new MainScreen().setVisible(true);
    }
}
