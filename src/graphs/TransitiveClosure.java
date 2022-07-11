package graphs;

public class TransitiveClosure {

    private DirectedDFS[] all;

    public TransitiveClosure(Digraph g) {
        all = new DirectedDFS[g.V()];
        for (int v = 0; v < g.V(); v++)
            all[v] = new DirectedDFS(g, v);
    }

    boolean reachable(int v, int w) {
        return all[v].marked(w);
    }
}
