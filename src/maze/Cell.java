/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author
 */
public class Cell extends JLabel {
    //Borders:
    private boolean[] borders = new boolean[4]; //order: right-bottom-left-top border
    //Creation
    private boolean create_visited = false;
    //Pathfinding
    private boolean path_visited=false;
    private Cell ancestor;
    private int total_value = 0, heuristic_value = 0, current_value = 0;
    //Cell appearance:
    private Color border_color, background_color, font_color;
    //Positions:
    private boolean hasplayer = false;
    private String player_text = "M";
    private boolean isfinish = false;
    private String finish_text = "F";
    //Cell position:
    private int row, column;
    //Neighbors of the cell:
    ArrayList<Cell> neighbors;
    /**
     *Cell objects are used to create the maze. They are a jLabel with some special properties
     * @param rownumber Row number, must be non-negative;
     * @param columnnumber Column number, must be non-negative
     * @param bordercolor the color of the border of the cell
     * @param backgroundcolor the color of background of the cell
     * @param playercolor the color of the player's figure on the cell, or in other words the font color of the cell
     */
    public Cell(int rownumber, int columnnumber, Color bordercolor, Color backgroundcolor, Color playercolor){
        this.setVisible(false);
        row = rownumber;
        column = columnnumber;
        background_color = backgroundcolor;
        border_color = bordercolor;
        font_color = playercolor;
        for(int i = 0; i<borders.length; i++)
            borders[i]=true;
        //Text direction:
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
        //Initialize neighbors
        neighbors = new ArrayList<Cell>();
    }

    /**
     *Sets the cell to be visible with all pre-set properties.
     */
    public void drawCell(){
        int topborderwidth = 0, rightborderwidth = 0, bottomborderwidth = 0, leftborderwidth = 0; //first assume that no side has border
        if(this.hasTopBorder()){ //all if cases here check whether they have a border, if yes than it sets the border's width to 1;
            topborderwidth = 1;
        }
        if(this.hasRightBorder()){
            rightborderwidth = 1;
        }
        if(this.hasBottomBorder()){
            bottomborderwidth = 1;
        }
        if(this.hasLeftBorder()){
            leftborderwidth = 1;
        }
        //now that all border sizes have been set we can draw the borders.
        this.setBorder(BorderFactory.createMatteBorder(topborderwidth, leftborderwidth, bottomborderwidth, rightborderwidth, border_color));
        this.setOpaque(true);
        this.setBackground(background_color);
        this.setForeground(font_color);
        this.setVisible(true);
    }

    /**
     *Returns the row's number where the cell is located (numbering starts from 0), 0th row is the topmost.
     * @return
     */
    public int getRow(){
        return row;
    }
    
    /**
     *Returns the column's number where the cell is located (numbering starts from 0), 0th column is the leftmost.
     * @return
     */
    public int getColumn(){
        return column;
    }
    
    
    /**
     *Returns a boolean whether the cell has a Right border(true) or not.
     * @return
     */
    public boolean hasRightBorder(){
        return borders[0];
    }

    /**
     *Sets the top border to the value of given parameter.
     * @param isborder true: will have right border false: will NOT have right border.
     */
    public void setRightBorder( boolean isborder){
        borders[0]=isborder;
    }
    /**
     *Returns a boolean whether the cell has a Bottom border(true) or not.
     * @return
     */
    public boolean hasBottomBorder(){
        return borders[1];
    }

    /**
     *Sets the top border to the value of given parameter.
     * @param isborder true: will have bottom border false: will NOT have bottom border.
     */
    public void setBottomBorder( boolean isborder){
        borders[1]=isborder;
    }
    
    /**
     *Returns a boolean whether the cell has a Top border(true) or not.
     * @return
     */
    public boolean hasLeftBorder(){
        return borders[2];
    }

    /**
     *Sets the left border to the value of given parameter.
     * @param isborder true: will have left border false: will NOT have left border.
     */
    public void setLeftBorder( boolean isborder){
        borders[2]=isborder;
    }
    
    /**
     *Returns a boolean whether the cell has a Top border(true) or not.
     * @return
     */
    public boolean hasTopBorder(){
        return borders[3];
    }
    /**
     *Sets the top border to the value of given parameter.
     * @param isborder true: will have top border false: will NOT have top border.
     */
    public void setTopBorder( boolean isborder){
        borders[3]=isborder;
    }
    
    /**
     *This method returns how many borders surround the cell.
     * @return
     */
    public int getNumberOfBorders(){
        int border = 0;
        for(int i = 0; i<borders.length; i++){
            if(borders[i]==true){
                border++;
            }
        }
        return border;
    }
    
    /**
     *Sets the borders' color to given color. This will not have any visual effect on the cells. drawCell() method must be run.
     * @param bordercolor Color of the cell
     */
    public void setBorderColor(Color bordercolor){
        border_color = bordercolor;
    }

    /**
     * Returns the color of the border as a Color object
     * @return
     */
    public Color getBorderColor(){
        return border_color;
    }
    
    /**
     *Returns whether the maze-generator have visited the cell already
     * @return
     */
    public boolean isCreatorVisited(){
        return create_visited;
    }

    /**
     *Sets the cell to visited/not visited state by the maze-generator algorithm
     * @param isvisited true: visited; false: not-visited; by default the value is false;
     */
    public void setCreatorVisited(boolean isvisited){
        create_visited = isvisited;
    }
    
    /**
     *Returns whether the pathfinding algorithm has visited the cell already.
     * @return
     */
    public boolean isPathFindingVisited(){
        return path_visited;
    }
    
    
    /**
     *Sets whether the pathfinding algorithm visited the cell or not
     * By default it is false.
     * @param isvisited true: yes, false: no
     */
    public void setPathFindingVisited(boolean isvisited){
        path_visited = isvisited;
    }
    
    
    /**
     *Returns the value of cell without heuristics for pathfinding
     * @return
     */
    public int getValue(){
        return current_value;
    }
    
    /**
     *Returns the heuristics for pathfinding
     * @return
     */
    public int getHeuristics(){
        return heuristic_value;
    }
    
    /**
     *Returns the total value of cell with heuristics for pathfinding
     * @return
     */
    public int getTotalValue(){
        return total_value;
    }
    
    /**
     *Sets the value of the cell without the heuristics, automatically updates the total value
     * @param value value without heuristics
     */
    public void setValue(int value){
        current_value = value;
        updateTotalValue();
    }
    
    /**
     * Sets the value of heuristics for the cell, automatically updates the total value
     * @param value value of heuristics
     */
    public void setHeuristic(int value){
        heuristic_value = value;
        updateTotalValue();
    }
    
    private void updateTotalValue(){ //used to update the value of total_value;
        total_value = current_value + heuristic_value;
    }
    
    /**
     *Sets the default values for a* search.
     */
    public void setDefaultPathFindingValues(){
        heuristic_value = 0;
        current_value = 0;
        total_value = 0;
        path_visited = false;
    }
    /**
     *Returns the ancestor of the Cell used in the pathfinding algorithm.
     * Null value is returned if it has no ancestor.
     * @return
     */
    public Cell getAncestor(){
        return ancestor;
    }
    
    /**
     *Returns whether the cell has or has not got an ancestor used in the pathfinding algorithm.
     * @return
     */
    public boolean hasAncestor(){
        return ancestor != null;
    }
    
    /**
     *Sets the ancestor of the cell used in the pathfinding algorithm to the given cell
     * @param ancest: ancestor of the cell
     */
    public void setAncestor(Cell ancest){
        ancestor = ancest;
    }
    
    /**
     * Returns whether the player is on the cell or not.
     * @return
     */
    public boolean isPlayerPosition(){
        return hasplayer;
    }
    
    /**
     *Sets or removes players position from current Cell depending on the value of the parameter.
     * Automatically updates the cell's appearance.
     * Note that this method works independently of other cells, so its the developers task to ensure that only one cell has a player.
     * @param isplayer true: sets player position; false: removes player position
     */
    public void setPlayerPosition( boolean isplayer){
        hasplayer = isplayer;
        if(isplayer){
            this.setText(player_text);
        }else{
            this.setText("");
        }
    }
    
    /**
     *Returns whether the cell is the finishposition
     * By default a cell is not a finishposition
     * @return
     */
    public boolean isFinishPosition(){
        return isfinish;
    }
    
    /**
     * Sets or removes finish position from current Cell depending on the value of the parameter.
     * Automatically updates the cell's appearance.
     * Note that this method works independently of other cells, so its the developers task to ensure that only one cell has a finishposition.
     * By default a cell is not a finishposition
     * @param finish true: sets finish position; false: removes finish position
     */
    public void setFinishPosition(boolean finish){
        isfinish = finish;
        if(finish == true)
            this.setText(finish_text);
        else
            this.setText("");
    }
    
    /**
     *Sets the neighbor of this cell (orientation is not important, only should contain top, right, bottom and left neighbors)
     * @param neighbor_list: arraylist containing the neighbors
     */
    public void setNeighbors (ArrayList<Cell> neighbor_list){
        neighbors = neighbor_list;
    }

    /**
     *Returns the neighbors of this cell (top,right, bottom and left (orientation may vary))
     * @return
     */
    public ArrayList<Cell> getNeighbors(){
        return neighbors;
    }
            
}
