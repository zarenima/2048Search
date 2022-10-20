package ai;

import model.Node;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class IDS {
    public void search(Node startNode, int cutoff) {
        for (int i = 0; i < cutoff; i++){
            Stack<Node> frontier = new Stack<Node>();
            Hashtable<String, Boolean> inFrontier = new Hashtable<>();
            Hashtable<String, Boolean> exp = new Hashtable<>();

            if (startNode.isGoal()) {
                System.out.println("you win!");
                printResult(startNode, 0);
                return;
            }
            frontier.push(startNode);
            // inFrontier.put(startNode.hash(), true);
            inFrontier.put(startNode.hash(), true);

            while (!frontier.isEmpty()) {
                Node temp = frontier.pop();
                if(temp.depth > i) {
                    continue;
                }
                //inFrontier.remove(temp.hash());

                exp.put(temp.hash(), true);

                ArrayList<Node> children = temp.successor();
                for (Node child : children) {
                    if (!((inFrontier.containsKey(child.hash()))|| (exp.containsKey(child.hash())) ) ){
                        if (child.isGoal()) {
                            printResult(child, 0);
                            System.out.println("you win !!!");
                            return;
                        }
                        frontier.push(child);
                        inFrontier.put(child.hash(), true);
                    }
                }
            }
        }
        System.out.printf("no solution");
    }

    public void printResult(Node node, int depthCounter) {
        if (node.getParent() == null) {
            System.out.println("problem solved at a depth of  : " + depthCounter);
            return;
        }
        System.out.println(node.toString());
        node.drawState();
        printResult(node.getParent(), depthCounter + 1);
    }
}
