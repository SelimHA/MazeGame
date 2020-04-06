/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static maze.GameConstants.savegame_location_details;
import static maze.GameConstants.savegame_location_map;

/**
 *
 * @author
 */
public class GameCommands {
    
    
    /**
     *This method will generate a map (a 2d array of Cells).
     * This method guarantees that from any point in the map you can get to any other point and the route is unique (i.e.: you can get there in just one way)
     * @param mapdimensions: length of the map (starts with 0 i.e. map.length)
     * @param border: color of border of the cell
     * @param background: background color of the cell
     * @param player: color of the player (the color of font on the cell where player stands)
     * @return
     */
    public Cell[][] generateMap(int mapdimensions, Color border, Color background, Color player){
        Cell[][] map = new Cell[mapdimensions][mapdimensions];        
        for(int i = 0; i<mapdimensions; i++){
            for(int ii = 0; ii<mapdimensions; ii++){
                map[i][ii] = new Cell(i,ii, border, background, player);
            }
        }
        //Based on the description: https://en.wikipedia.org/wiki/Maze_generation_algorithm#Recursive_backtracker
        boolean isfinished = false;
        ArrayList<Cell> toevaluate = new ArrayList<Cell>();
        Cell current = map[0][0];
        while(isfinished == false){
            current.setCreatorVisited(true);
            ArrayList<Cell> neighbors = getNeighborsMapCreator(current, map);
            Random rnd = new Random();
            if(neighbors.size()>0){ //if it has a neighbor choose one neighbor at random and go to its direction
                Cell visit = neighbors.get(rnd.nextInt(neighbors.size()));
                if(current.getRow()-visit.getRow()==1){
                    //visit is above current
                   current.setTopBorder(false);
                    visit.setBottomBorder(false);
                }else if(current.getRow()-visit.getRow()==-1){
                    current.setBottomBorder(false);
                    visit.setTopBorder(false);
                }else if(current.getColumn()-visit.getColumn()==1){
                    //visit is left to current
                    current.setLeftBorder(false);
                    visit.setRightBorder(false);
                }else if(current.getColumn()-visit.getColumn()==-1){
                    current.setRightBorder(false);
                    visit.setLeftBorder(false);
                }//possible extension to support multiple levels
                //otherwise else could have been used, as one of them must happen as they visit is an unvisited neighbor
                if(current.getNumberOfBorders()!=0) //if equal to zero, all surrounding cells must have been visited, thus there is no need to add the cell for further evaluation, it will increase the speed of the algorithm
                    toevaluate.add(current); //later on we may return to this cell and expand in other directions too
                current = visit;
            }else if(toevaluate.isEmpty() == false){ //if it has no neighbor choose one cell from the ones added to toevaluate arraylist and evaluate it
                current = toevaluate.get(rnd.nextInt(toevaluate.size()));
                toevaluate.remove(current);
            }else{// if no cells remained for evaluation and there is no neighbor to current cell, then we finished with map generation.
                isfinished = true;
            }
        }
        return map;
    }
    /**
     * This method will find the cells neighboring a given cell and return those cells in an ArrayList.
     * This method only works when creating the maze, and for initialized maps
     * @param current the cell for which you need to find its neighbors
     * @param map the cells in which current is located, must be initialized
     * @return
     */
    public ArrayList<Cell> getNeighborsMapCreator(Cell current, Cell[][] map){
        ArrayList<Cell> toreturn = new ArrayList<Cell>();
        int row = current.getRow();
        int column = current.getColumn();
        if(row>0 && map[row-1][column].isCreatorVisited() == false){
            //if row exist and was not visited before
            toreturn.add(map[row-1][column]);
            //with if we prevented ArrayIndexOutOfBoundsException
        }
        if(row+1<map[0].length && map[row+1][column].isCreatorVisited() == false){
            toreturn.add(map[row+1][column]);
        }
        if(column>0 && map[row][column-1].isCreatorVisited() == false){
            toreturn.add(map[row][column-1]);
        }
        if(column+1<map[0].length && map[row][column+1].isCreatorVisited() == false){
            toreturn.add(map[row][column+1]);
        }
        return toreturn;
    }
    
    /**
     * This method will find the cells neighboring a given cell and return those cells in an ArrayList.
     * This is a general purpose method
     * @param current the cell for which you need to find its neighbors
     * @param map the cells in which current is located
     * @return
     */
    public ArrayList<Cell> getNeighbors(Cell current, Cell[][] map){ 
        ArrayList<Cell> toreturn = new ArrayList<Cell>();
        int row = current.getRow();
        int column = current.getColumn();
        if(row>0 && !map[row][column].hasTopBorder()){
            //if row exist and player could move to that point (it is truly a neighbor)
            toreturn.add(map[row-1][column]);
            //with if we prevented ArrayIndexOutOfBoundsException
        }
        if(row+1<map[0].length && !map[row][column].hasBottomBorder()){
            toreturn.add(map[row+1][column]);
        }
        if(column>0 && !map[row][column].hasLeftBorder()){
            //if column exist and player could move to that point (it is truly a neighbor)
            toreturn.add(map[row][column-1]);
        }
        if(column+1<map[0].length && !map[row][column].hasRightBorder()){
            toreturn.add(map[row][column+1]);
        }
        return toreturn;
    }
    
    /**
     *This method will return the number of unvisited neighboring cells of current
     * @param current: cell for which you want to find the unvisited neighboring cell
     * @param map: a 2d array containing initialized cells in which current is located
     * @return
     */
    public int getNegihborNumberForMapCreator(Cell current, Cell[][] map){
        return getNeighborsMapCreator(current, map).size();
    }
    
    /**
     *This method will find a path from player's position to finish position using A* pathfinding
     * @param map the 2d array containing the map
     * @param player_row the row number (starting from 0) where the player stands
     * @param player_column the column number (starting from 0) where the player stands
     * @param finish_row the row number (starting from 0) where the finish position is
     * @param finish_column the column number (starting from 0) where the finish position is
     */
    public void findPath(Cell[][] map, int player_row, int player_column, int finish_row, int finish_column){ // It will use A* search as described in the pseudocode at https://en.wikipedia.org/wiki/A*_search_algorithm
        //before running search we need to make sure that all elements have default Asearch values
        //Also we need to set the neighboring cells to each cell
        for(int i = 0; i<map.length; i++){
            for(int ii = 0; ii<map[0].length; ii++){
                map[i][ii].setDefaultPathFindingValues();
                map[i][ii].setNeighbors(getNeighbors(map[i][ii], map));
            }
        }
        ArrayList<Cell> closedList = new ArrayList<Cell>(); //contains completely evaluated cells
        ArrayList<Cell> openList = new ArrayList<Cell>(); //contains cells which are not completely evaluated
        //We need to start the search from player's position, add it to openList
        openList.add(map[player_row][player_column]);
        boolean ispath = false;
        while(openList.size()>0){//that is there are cells to evaluate
            int lowestindex = 0; //We need to find the cell with lowest total value;
            for(int i = 0; i<openList.size(); i++){
                if(openList.get(lowestindex).getTotalValue()>openList.get(i).getTotalValue()){
                    lowestindex = i;
                }
            }
            if(openList.get(lowestindex).isFinishPosition()){//if that cell (with lowest total value) is finish position we found the path, and we can quit
                ispath = true;
                break;
            }
            Cell current = openList.get(lowestindex); //else (as otherwise we have exited from loop), we will analyze the cell with lowest index
            closedList.add(current);//we will completely evaluate this cell, so we can add it to closedList
            openList.remove(openList.get(lowestindex));//and remove it from openList
            //Now we can start evaluating the values of the neighbors of the current cell
            ArrayList<Cell> currentneighbors = new ArrayList<Cell>();
            currentneighbors =  current.getNeighbors(); //now we have to start evaluating the neighbors around current
            for(Cell currentneighbor: currentneighbors){
                if(!closedList.contains(currentneighbor)){ //if the neighbor in question has not been evaluated
                    int tempvalue = (current.getValue()+1); //increase neighbors distance value by 1 as neighbor must be one step away/further from current
                    if(openList.contains(currentneighbor)){
                        if(tempvalue<currentneighbor.getValue()){//if this value (tempvalue) is smaller than the value at a previous visit
                            currentneighbor.setValue(tempvalue); //we have found a shorter path
                            //this will be even more important if more than 1 path is allowed in the game (e.g.: map creator extension)
                        }
                    }else{//if was not in openList, we may add it now with a total value of tempvalue
                        currentneighbor.setValue(tempvalue);
                        openList.add(currentneighbor);
                    }
                    currentneighbor.setHeuristic(getHeuristic(currentneighbor, map[finish_row][finish_column])); //set heuristic distance from current point to finish, using Pythagorean theorem
                    currentneighbor.setAncestor(current); //the cell before currentneighbor is current, ie:we got to currentneighbor via current
                }
            }
        }
        if(!ispath){//Currently this branch cannot happen as path is guaranteed, but later modifications to the software such as adding a map creator may use this branch too.
            JOptionPane.showMessageDialog(null,"Fatal error occured"); //modify it if new features added, this cannot happen currently as path is guaranteed
        }
        else{//We can mark the path, as it exists
            Cell current = map[finish_row][finish_column];
            //As we started the search from startposition, we have to mark the cells from finishposition as ancestor was marked.
            do{
                current.setText("X");
                current = current.getAncestor();
            }while(current.hasAncestor());
        }
    }
    
    private int getHeuristic(Cell currentneighbor, Cell cell_finish_position){ //Uses the Pythagorean theorem
        double heuristicsquared = Math.pow(currentneighbor.getRow()*cell_finish_position.getRow(),2)+Math.pow(currentneighbor.getColumn()*cell_finish_position.getColumn(),2);
        double heuristic = Math.pow(heuristicsquared, 0.5);
        return (int)heuristic;
    }
    
    /**
     *This method returns whether there exist a saved game file
     *True if yes, false if not
     * @return
     */
    public boolean savedGameExists(){
       if(savegame_location_map.exists() && savegame_location_details.exists())
           return true;
       return false;
    
    }
    
    /**
     * This method will save the current state of the map. This method initiates the saveMao and savePositions methods one after another.
     * 2 files will be created. The first will contain the details about the map, the second about locations
     * For further information, read the javadoc of saveMap and savePositions methods
     * @param map 2d array of initialized cells, the map you want to save
     * @param player_row the row number in which the player is, (numbering starts from 0)
     * @param player_column the column number in which the player is, (numbering starts from 0) 
     * @param finish_row the row number in which the finish position is, (numbering starts from 0)
     * @param finish_column the column number in which the finish position is, (numbering starts from 0)
     * @param gamemode the number of game mode/ game type (see integer values in GameConstants)
     * @param time_of_round the total time the player is allowed to play the game (for normal mode value is -1)
     * @param time_remaining the remaining time for the player to finish the game or until player loses a life (for normal mode value is -1)
     * @param lives_remaining the lives remaining for the player (for normal mode value is -1)
     */
    public void saveGame(Cell map[][], int player_row, int player_column, int finish_row, int finish_column, int gamemode,int time_of_round, int time_remaining, int lives_remaining){
        //Saved game format:
        //2 files, one containing player (row and column) and finishposition, gamemode, time remaining and lastly lives remaining in this order for each value in separate lines (applies for row and column numbers too)
        //A second file will containt the cells properties: first line contains the width of the map, the second the height and then each line contains a cell's properties will be written in one line. Lines contents: hasTopBorder(0 if no, 1 if yes), hasRightBorder, hasBottomBorder, hasTopBorder (tab separated in this order)
        //Cells properties are written linearly, and the writer goes row by row and in each row writes the cells from left to right
        saveMap(map);
        savePositions( player_row, player_column,  finish_row,  finish_column,  gamemode, time_of_round,  time_remaining, lives_remaining);
    }
    
    /**
     * It will save the properties of the map.
     * Structure of file:
     * First two rows:
     * number of rows in the map
     * number of columns in the map
     * Then for each cell going through linearly in map (row by row and inside that column by column):
     * hasRightBorder  hasBottomBorder  hasLeftBorder hasTopBorder (if yes 1 if no 0), tab separated
     * Output file: GameConstants.savegame_location_map
     * @param map the map you want to save, cells must be initialized
     */
    public void saveMap(Cell map[][]){
        BufferedWriter write = null;
        try {
            write = new BufferedWriter(new FileWriter(savegame_location_map));
            write.write(map.length+"\r\n");
            write.write(map[0].length+"\r\n");
            for(int i = 0; i<map.length; i++){
                for(int ii = 0; ii<map[0].length; ii++){
                    String towrite;
                    towrite = booleanToNumber(map[i][ii].hasRightBorder())+"\t"+booleanToNumber(map[i][ii].hasBottomBorder())+"\t"+booleanToNumber(map[i][ii].hasLeftBorder())+"\t"+booleanToNumber(map[i][ii].hasTopBorder())+"\r\n";
                    write.write(towrite);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GameCommands.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(write!=null)
                try {
                    write.close();
                } catch (IOException ex) {
                    Logger.getLogger(GameCommands.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    /**
     * Saves the positions and other data given in the parameters.
     * Structure of file:
     * each parameter given is saved in the order as the method requires you to give. Each parameter is written in a separate row.
     * Output: file at location GameConstants.savegame_location_details
     * @param player_row the row number in which the player is, (numbering starts from 0)
     * @param player_column the column number in which the player is, (numbering starts from 0) 
     * @param finish_row the row number in which the finish position is, (numbering starts from 0)
     * @param finish_column the column number in which the finish position is, (numbering starts from 0)
     * @param gamemode the number of game mode/ game type (see integer values in GameConstants)
     * @param time_of_round the total time the player is allowed to play the game (for normal mode value is -1)
     * @param time_remaining the remaining time for the player to finish the game or until player loses a life (for normal mode value is -1)
     * @param lives_remaining the lives remaining for the player (for normal mode value is -1)
     */
    public void savePositions(int player_row, int player_column, int finish_row, int finish_column, int gamemode,int time_of_round, int time_remaining, int lives_remaining){
        BufferedWriter write = null;
        try{
            write = new BufferedWriter(new FileWriter(savegame_location_details));
            write.write(player_row+"\r\n"+player_column+"\r\n"+finish_row+"\r\n"+finish_column+"\r\n"+gamemode+"\r\n"+time_of_round+"\r\n"+time_remaining+"\r\n"+lives_remaining);
        } catch (IOException ex) {
            Logger.getLogger(GameCommands.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(write!=null)
                try {
                    write.close();
            } catch (IOException ex) {
                Logger.getLogger(GameCommands.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private int booleanToNumber(boolean bool){
        if(bool){
            return 1;
        }
        return 0;
    }
    
    /**
     *This method will load only the map from the savegame_map.maze file.
     * This method DOES NOT destroy the savegame_map.maze file.
     * Loading happens by reading one by one the cells, initializing them with given parameters and setting its border to the value given in the file
     * Null is returned if the actual number of cell entries does not match the number given in the beginning of the file or there are any mistakes, bad data in the file (e.g.: number is missing, tab separtation is missing)
     * Else a map is returned with initialized cells and set borders.
     * @param border border color of cell
     * @param background background color of cell
     * @param player color of player/font
     * @return
     */
    public Cell[][] loadMap(Color border, Color background, Color player){
        BufferedReader read = null;
        Cell[][] map;
        try{
            read = new BufferedReader(new FileReader(savegame_location_map));
            int rows = Integer.parseInt(read.readLine());
            int columns = Integer.parseInt(read.readLine());
            map = new Cell[rows][columns];
            for(int i = 0; i<rows; i++){
                for(int ii = 0; ii<columns; ii++){
                    map[i][ii] = new Cell(i,ii, border, background, player); //intitialize map
                    String currentline;
                    String[] borders;
                    if((currentline = read.readLine())!=null)
                        borders = currentline.split("\t");// ideally contains 0s and 1s as described in saveGame(), orientation: top-right-bottom left;
                    else //Row and column number mismatch from actual data contained in the file -> error
                        throw new Exception();
                    boolean[] border_values = stringToBool(borders);
                    if(border_values == null || border_values.length!=4)//Error occured when converting or missing some borders
                        throw new Exception();
                    map[i][ii].setRightBorder(border_values[0]);
                    map[i][ii].setBottomBorder(border_values[1]);
                    map[i][ii].setLeftBorder(border_values[2]);
                    map[i][ii].setTopBorder(border_values[3]);
                }
            }
            if(read.readLine()!= null){//there are more cells than defined in the beginning of file -> null returned
                throw new Exception();
            }
            return map;
        }catch (Exception ex){//any exception occurs -> null returned
            return null;
        }finally{
            if(read!=null){
                try {
                        read.close();
                } catch (IOException ex) {
                    Logger.getLogger(GameCommands.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    private boolean[] stringToBool(String[] borders){
        //this method converts the String array containing the borders to integers
        boolean[] border_values = new boolean[4];
        for(int i = 0; i<border_values.length; i++){
            try{
                int currentint = Integer.parseInt(borders[i]);
                if(!(currentint == 0 || currentint == 1))//that is it is neither 0 or 1 than error happened
                    throw new Exception();
                boolean current;
                if(currentint == 0)
                    current = false;
                else
                    current = true;
                border_values[i] = current;
            }catch(Exception e){
                return null;
            }
        }
        return border_values;
    }
   
    
    /**
     * This method will load the data for a saved game from GameConstants.savegame_location_details and return it as an arraylist
     * The arraylist contains the data in the order and in the datatype as you have written it in savePositions method
     * Null is returned if file contains more data or data in wrong format (corrupted file) or any other Exception such as read-write, missing file occurs.
     * @return
     */
    public ArrayList<Integer> loadGameFiles(){
        BufferedReader read = null;
        ArrayList<Integer> gamedata = new ArrayList<Integer>();
        try{
            read = new BufferedReader(new FileReader(GameConstants.savegame_location_details));
            String current;
            while((current=read.readLine())!=null)
                gamedata.add(Integer.parseInt(current));
                //all data in the file are supposed to be integers
                //if error happens null returned
        } catch (Exception ex) {
            return null;
        }
        if(gamedata.size()!=8){//there are either less entries or more entries than needed in the file
                               // so file is corrupted
            return null;
        }
        return gamedata;
    }
}
