package graph;

/**
 * ζζζεΊ
 */

public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph g) {
        DirectedCycle cycleFinder = new DirectedCycle(g);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(g);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }
}
