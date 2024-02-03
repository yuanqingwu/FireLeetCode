package basic.graph;

/**
 * 表示一条有向边。
 * 无向边可以看成是两条有向边的组合。
 */
public class Edge {
    /**
     * 权值
     */
    public int weight;
    /**
     * 起始点
     */
    public Node from;
    /**
     * 到达点
     */
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

}