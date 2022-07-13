package graph;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

public class BellmanFordSP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQ;
    private Queue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    private class EdgeWeightedCycleFinder {

        private EdgeWeightedDirectedCycle cycle;
        public EdgeWeightedCycleFinder(EdgeWeightedDigraph g) {
            cycle = new EdgeWeightedDirectedCycle(g);
        }

        public Iterable<DirectedEdge> cycle() {
            Queue<DirectedEdge> path = new Queue<>();
            double allWeight = 0;
            for (DirectedEdge e : cycle.cycle()) {
                path.enqueue(e);
                allWeight += e.weight();
            }
            return allWeight < 0 ? path : null;
        }
    }

    public BellmanFordSP(EdgeWeightedDigraph g, int s) {
        distTo = new double[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        onQ = new boolean[g.V()];
        queue = new Queue<>();
        for (int v = 0; v < g.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(g, v);
        }
    }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            if (cost++ % g.V() == 0) {
                findNegativeCycle();
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt;
        spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++)
            if (edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);

        EdgeWeightedCycleFinder cf;
        cf = new EdgeWeightedCycleFinder(spt);

        cycle = cf.cycle();
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }
}
