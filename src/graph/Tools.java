package graph;

public abstract class Tools {

    public static int degree(Graph g, int v) {
        int degree = 0;
        for (int w : g.adj(v)) degree++;
        return degree;
    }

    public static int maxDegree(Graph g) {
        int max = 0;
        for (int v = 0; v < g.V(); v++)
            if (degree(g, v) > max) max = degree(g, v);
        return max;
    }

    public static double avgDegree(Graph g) {
        return 2.0 * g.E() / g.V();
    }

    public static int numberOfSelfLoops(Graph g) {
        int count = 0;
        for (int v = 0; v < g.V(); v++)
            for (int w : g.adj(v))
                if (v == w) count++;
        return count / 2;
    }
}
