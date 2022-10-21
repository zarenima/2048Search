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

    public ArrayList<Node> successor() {
        ArrayList<Node> result = new ArrayList<Node>();
        result.add(new Node(board.moveLeft(), this, Movement.LEFT, this.depth + 1));
        result.add(new Node(board.moveRight(), this, Movement.RIGHT, this.depth + 1));
        result.add(new Node(board.moveDown(), this, Movement.DOWN, this.depth + 1));
        result.add(new Node(board.moveUp(), this, Movement.UP, this.depth + 1));
        return result;
    }

    public void drawState() {
        System.out.println("moved to : " + this.previousMovement);
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
        return 0;
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

    @Override
    public int compareTo(Node node) {
        // return node.board.highest_lower();

        if (this.depth > node.depth) {

            return 1;
        } else if (this.depth < node.depth) {

            return -1;
        } else {

            return 0;
        }
    }
}
