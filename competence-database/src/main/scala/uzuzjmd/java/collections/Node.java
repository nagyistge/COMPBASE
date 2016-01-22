package uzuzjmd.java.collections;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dehne on 14.01.2016.
 */
public class Node {
    public String id;
    public List<Node> children;
    public Node parent;

    public Node(String id) {
        this.id = id;
        children = new LinkedList<>();
    }

    @Override
    public String toString() {
        return stringify(this,"", "");
    }

    private String stringify(Node node, String result, String spacer) {
        spacer += "   ";
        for (Node child : children) {
            result += spacer + child.id + "\n";
            result += stringify(child, result, spacer);
        }
        return result;
    }
}
