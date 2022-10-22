package ai;

import model.Node;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public void search(Node startNode) {
        Queue<Node> frontier = new LinkedList<Node>();
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
            // inFrontier.remove(temp.hash()); otherwise, we get stuck on infinite loop
            ArrayList<Node> children = temp.successor(false);
            for (Node child : children) {
                if (!(inFrontier.containsKey(child.hash()))) {
                    if (child.isGoal()) {
                        printResult(child, 0);
                        System.out.println("you win !!!");
                        return;
                    }
                    frontier.add(child);
                    inFrontier.put(child.hash(), true);
                }
            }
        }
        System.out.println("no solution");
    }

    public void printResult(Node node, int depthCounter) {
        if (node.getParent() == null) {
            System.out.println("problem solved at a depth of  : " + depthCounter);
            return;
        }
        System.out.println(node.toString());
        node.drawState(false);
        printResult(node.getParent(), depthCounter + 1);
    }

}
