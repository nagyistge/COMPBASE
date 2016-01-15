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
}
