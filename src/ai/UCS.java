package ai;

import model.Node;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.*;

public class UCS {

    public void search(Node startNode) {

        PriorityQueue<Node> frontier = new PriorityQueue<Node>();
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        if (startNode.isGoal()) {
            System.out.println("you win!");
            printResult(startNode, 0);
            return;
        }
        frontier.add(startNode);
        inFrontier.put(startNode.hash(), true);
        while (!frontier.isEmpty()) {
            Node temp = frontier.poll();
            if (temp.isGoal()) {
                printResult(temp, 0);
                System.out.println("you win !!!");
                return;
            }
          //  System.out.println(temp.depth);
            // inFrontier.remove(temp.hash());
            ArrayList<Node> children = temp.successor(true);
            for (Node child : children) {
                if (!(inFrontier.containsKey(child.hash()))) {
                    
                    frontier.add(child);
                    inFrontier.put(child.hash(), true);
                }
            }
        }
        System.out.println("no solution");
    }

    public void printResult(Node node, int depthCounter) {
        if (node.getParent() == null) {
            System.out.println("depth is written in the first step");
            return;
        }
        System.out.println(node.toString());
        node.drawState(true);
        printResult(node.getParent(), node.getParent().depth);
    }

    

}
