package hw7;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addVertex('a');
        graph.addVertex('b');
        graph.addVertex('c');
        graph.addVertex('d');
        graph.addVertex('e');
        graph.addVertex('f');
        graph.addVertex('g');
        graph.addVertex('h');
        graph.addVertex('i');
        graph.addVertex('k');

        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 9);
        graph.addEdge(8, 0);
        graph.addEdge(8, 9);

        graph.widthTraverseFromTo(9, 0);
    }
}
