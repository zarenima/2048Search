package model;

import core.Constants;

import java.util.ArrayList;

public class Node implements Comparable<Node>{
    Board board;
    Node parent;
    Movement previousMovement;
    public int depth;

    public Node(Board board, Node parent, Movement previousMovement, int depth) {
        this.parent = parent;
        this.board = board;
        this.previousMovement = previousMovement;
        this.depth = depth;
    }

    public Node(Board board, Node parent, Movement previousMovement){
        this.parent = parent;
        this.board = board;
        this.previousMovement = previousMovement;
        this.depth = 0;
    }

    public ArrayList<Node> successor(boolean ucs) {
        int cl = 1 ;
        int cr = 1 ;
        int cd = 1 ;
        int cu = 1 ;

        if(ucs==true){
            cl = 1;
            cr = 3 ;
            cd = 5 ;
            cu = 7; 
        }
        ArrayList<Node> result = new ArrayList<Node>();
        result.add(new Node(board.moveLeft(), this, Movement.LEFT, this.depth + cl));
        result.add(new Node(board.moveRight(), this, Movement.RIGHT, this.depth + cr));
        result.add(new Node(board.moveDown(), this, Movement.DOWN, this.depth + cd));
        result.add(new Node(board.moveUp(), this, Movement.UP, this.depth + cu));
        return result;
    }

    public void drawState(boolean ucs) {
        System.out.println("moved to : " + this.previousMovement);
        if(ucs==true)System.out.println("depth: " + this.depth);
        for (int i = 0; i < board.row; i++) {
            for (int j = 0; j < board.col; j++) {
                System.out.print(Constants.ANSI_BRIGHT_GREEN + board.cells[i][j] + spaceRequired(board.cells[i][j]));
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------");
    }

    public boolean isGoal() {
        return board.isGoal();
    }

    public int pathCost() {
        //todo
        return 1;
    }

    public int heuristic() {
        // TODO: 2/16/2022 implement heuristic function
        int result = 0;
        if(Board.mode==Constants.MODE_ADVANCE){
                result = 0 ; 
                for (int i = 0; i < this.board.row; i++) {
                    for (int j = 0; j < this.board.col; j++) {
                        if (this.board.cells[i][j]==0) {
                            result++ ;
                        }
                    }
                }
                
            
           
        }else{
            for (int i = 0; i < this.board.row; i++) {
                for (int j = 0; j < this.board.col; j++) {
                    if (this.board.cells[i][j] > result && this.board.cells[i][j] <= this.board.goalValue) {
                        result = this.board.cells[i][j];
                    }
                }
            }
        }
        return result;
        // return 0;
    }

    public String hash() {
        StringBuilder hash = new StringBuilder();
        hash.append(board.hash()).append("/PM=").append(previousMovement.toString());
        return hash.toString();
    }

    private String spaceRequired(int cell) {
        int length = String.valueOf(cell).length();
        String result = " ".repeat(5 - length);
        result += " ";
        return result;
    }

    public Node getParent() {
        return parent;
    }

    // public int highest_lower(){
        
    // }

    @Override
    public int compareTo(Node node) {
        //return node.board.highest_lower();
        // astar
        return  -(this.heuristic() + this.depth) + (node.heuristic() + node.depth) ;
    

    }
}