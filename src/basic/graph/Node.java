package basic.graph;

import java.util.ArrayList;

public class Node {

    /**
     * 点代表的值，数据类型灵活定义
     */
    public int value;
    /**
     * 入度
     */
    public int in;
    /**
     * 出度
     */
    public int out;
    /**
     * 从此 Node 发散出去的点集
     */
    public ArrayList<Node> nextNodes;
    /**
     * 从此 Node 发散出去的边集
     */
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nextNodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

}
