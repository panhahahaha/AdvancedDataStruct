package Graph;

import java.lang.reflect.Array;
import java.util.*;

import java.util.LinkedList;

/**
 * Represents a non-directional graph where each vertex
 * is a Node object. Connections between nodes are based
 * on the cartesian coordinate system.
 *
 * @author James Baumeister on 30/4/17
 */
public class Graph {

    /**
     * Connects all nodes, building their E, W, S, N, NE, SE, NW, SW edges,
     * in that order. The nodes should form a 10x10 square grid, and the array
     * is such that every i'th node % 10 = 9 is a right edge.
     * See the assignment specification for more information.
     *
     * @param nodes An array of Node objects to be connected
     * @return An array of connected Node objects
     */
    public void connectNodes(Node[] nodes) {
        // TODO
        System.out.println("Node [] length is" + nodes.length);
        int[][] Directions = {{0, 1},//East
                {0, -1},//west
                {1, 0},//south
                {-1, 0},//north
                {-1, 1},//North-east
                {1, 1},//south-east
                {-1, -1},//north-west
                {1, -1},//south-west
        };
        int index = 10;
        //因为当index%10==0时候，在图像上位于左边缘，由此说明在进行索引的时候应该类似与（0，0）（0，1）（0，2）
        //转换为二维索引应是[0][0],[1][0],[2][0]
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int node_index = i * 10 + j;
                Node fromnode = nodes[node_index];
                double current_x = fromnode.getPosition().x;
                double current_y = fromnode.getPosition().y;
                for (int[] direction : Directions) {
                    double edge_x = i + direction[0];
                    double edge_y = j + direction[1];
                    if (valid_coordinate((int) edge_x, (int) edge_y)) {
                        int new_index = (int) (edge_x * 10 + edge_y);
                        Node tonode = nodes[new_index];
                        Edge edge = new Edge(fromnode, tonode);
                        fromnode.getEdges().add(edge);
//                        tonode.getEdges().add(edge);
                    }
                }
            }
        }

    }

    private boolean valid_coordinate(int x, int y) {
        if (x < 0 || y < 0 || x >= 10 || y >= 10) {
            return false;
        }
        return true;
    }

    /**
     * Searches for an edge from the source node to the destination.
     *
     * @param source      The source, or first, node
     * @param destination The destination, or second, node
     * @return The edge between the nodes, or null if not found
     */
    public Edge getEdge(Node source, Node destination) {
//        System.out.println("source:"+source+"destination:"+destination);
        for (Edge edge : source.getEdges()) {
//            System.out.println("edge:"+edge);
            Node tonode = edge.getToNode();
            Node fromenode = edge.getFromNode();
//            if (destination.equals(tonode)){
//                return edge;
//            }
            if (destination.equals(tonode) && fromenode.equals(source)) {
                return edge;
            }
        }


        return null;
    }

    /**
     * From an array of Node objects, this calculates the total cost of
     * travelling (i.e. sum of weights) from the first to the last nodes.
     *
     * @param vertices An array of Node objects representing a path
     * @return The total cost of travel, or if no edge Double.POSITIVE_INFINITY.
     */
    public double calculateTotalWeight(Node[] vertices) {

        // TODO
        int len = vertices.length;
        if (len <= 1) {
            return 0;
        }
        double total_weight = 0;
        for (int i = 0; i < len - 1; i++) {
            Node current_node = vertices[i];
            Node next_node = vertices[i + 1];
            Edge unsure_edge = new Edge(current_node, next_node);
            boolean found = false;
            for (Edge edge : current_node.getEdges()) {
                if (edge.equals(unsure_edge)) {
                    total_weight += edge.getWeight();
                    found = true;
                    continue;
                }
            }

            if (!found) {
                return Double.POSITIVE_INFINITY;
            }
        }

        return total_weight;
    }


    /**
     * Performs a breadth-first search of the graph and returns the shortest
     * path from one node to another.
     *
     * @param start  The node from which to start searching
     * @param target The target node to which a path is built
     * @return An array of Node objects representing the path from start to target, in that order
     */
    public Node[] breadthFirstSearch(Node start, Node target) {
        System.out.println("start:" + start + "target:" + target);
        // TODO
        Queue<Node> Qeue_node = new LinkedList<Node>();
        ArrayList<Node> Has_table_node = new ArrayList<>();
        Map<Node, Node> recall = new HashMap<Node, Node>();
        int[][] Direction = {{0, 1},//👉
                {1, 0}//👇
                , {0, -1}//👈
                , {-1, 0}//👆
                ,};
        Qeue_node.add(start);
        Has_table_node.add(start);
        while (!Qeue_node.isEmpty()) {
            Node node = Qeue_node.poll();

            for (Edge edge : node.getEdges()) {
                if (edge == null) {
                    continue;
                }
                Node push_element = edge.getToNode();
                if (Has_table_node.contains(push_element)) {
                    continue;

                }
                recall.put(push_element, node);
                if (push_element.equals(target)) {
                    LinkedList<Node> path = new LinkedList<>();
//                    path.addLast(target);
                    Node head = recall.get(target);
                    int index = 0;
                    while (!head.equals(start)) {
                        path.addFirst(head);
                        head = recall.get(head);
                        index++;
                    }
                    path.addFirst(head);
                    path.addLast(target);
                    Node[] path_0 = path.toArray(new Node[0]);
                    return path_0;
                }
                Qeue_node.add(push_element);
                Has_table_node.add(push_element);
            }


        }
        return null;
    }


    /**
     * Performs a depth-first search of the graph and returns the first-found
     * path from one node to another.
     *
     * @param start  The node from which to start searching
     * @param target The target node to which a path is built
     * @return An array of Node objects representing the path from start to target, in that order
     */
    public Node[] depthFirstSearch(Node start, Node target) {
        // TODO
        Stack<Node> node_stake = new Stack<Node>();
        ArrayList<Node> visited = new ArrayList<>();
        node_stake.add(start);
        visited.add(start);
        dfs_helper(visited, node_stake, start, target);
        Node[] path = node_stake.toArray(new Node[0]);
        System.out.println("path is:"+path.length);
        return path;
    }

    private boolean dfs_helper(ArrayList<Node> visited, Stack<Node> node_stake, Node start, Node target) {
        if (node_stake.peek() == null) {
            return false;

        }
        for (Edge edge : start.getEdges()) {
            Node tonode = edge.getToNode();
            if (visited.contains(tonode)) {
                continue;
            }
            node_stake.push(tonode);
            visited.add(tonode);
            if (target.equals(tonode)) {
                System.out.println("Finding!" + "\n" + node_stake);
                return true;
            }
            if(dfs_helper(visited, node_stake, tonode, target)){
                return true;
            };
        }
        node_stake.pop();
        return false;
    }


    /**
     * Performs a search of the graph using Dijkstra's algorithm, which takes into
     * account the edge weight. It should return the least-costly path from one node
     * to another.
     *
     * @param start  The node from which to start searching
     * @param target The target node to which a path is built
     * @return An array of Node objects representing the path from start to target, in that order
     */
    public Node[] dijkstrasSearch(Node start, Node target) {
        // TODO
        ArrayList<Node> visited = new ArrayList<>();
        ArrayList<Node> all_node = new ArrayList<>();
        this.get_all_nodes(start, all_node);
        System.out.println("all_node size is :" + all_node.size());
        double[] distance = new double[all_node.size()];
        ArrayList<ArrayList<Node>> path = new ArrayList<>();
        test_for_running(all_node,start,target);
        while (visited.size() != all_node.size()) {
            Node min_node = null;
            double min_weights = Double.MAX_VALUE;
            //finding a min_node and a min_weight
            for (Node node : all_node) {
                for (Edge edge : node.getEdges()) {
                    Node to_node = edge.getToNode();
                    if (!visited.contains(node) && min_weights > edge.getWeight()) {
                        min_weights = edge.getWeight();
                        min_node = to_node;
                    }
                }

            }

            if (min_node == null) {
                break;
            }
            //ending.......
            for (Edge edge : min_node.getEdges()) {
                Node to_node = edge.getToNode();
                int index_to_node = all_node.indexOf(to_node);
                int index_current = all_node.indexOf(min_node);
                if (!visited.contains(to_node) && distance[index_to_node] > distance[index_current] + edge.getWeight()) {
                    distance[index_to_node] = distance[index_current] + edge.getWeight();

                }
            }
            visited.add(min_node);
        }
        for (int i = 0 ;i < 10 ; i ++){
            for(int j = 0;j<10;j++){
                int index = i*10+j;
                System.out.print(distance[index]);
            }
            System.out.println();
        }
        double min_weight;


        return null;
    }
    private void test_for_running(ArrayList<Node> nodes,Node start, Node target){
        System.out.println("start is contained in nodes:"+nodes.contains(start));
        System.out.println("end is contained in nodes:"+nodes.contains(target));
    }

    private void get_all_nodes(Node start, ArrayList<Node> all_node) {
        ArrayList<Edge> edges = start.getEdges();
        for (Edge edge : edges) {
            Node to_node = edge.getToNode();
            if (all_node.contains(to_node)) {
                continue;
            }
            all_node.add(to_node);
            get_all_nodes(to_node, all_node);
        }


    }

}
