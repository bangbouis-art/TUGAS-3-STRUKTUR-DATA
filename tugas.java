import java.util.*;
public class GraphSearchAll{
    private Map<String,List<String>> adjList = new HashMap<>();
    private Map<String, Integer>nodeValues = new HashMap<>();

    // Menambahkan edge (hubungan antar node secara undirected)
    public void addEdge(String u,String v) {
        adjList.computeIfAbsent(u,k -> new ArrayList<>()).add(v);
        adjList.computeIfAbsent(v,k -> new ArrayList<>()).add(u);
    }
    //Menentukan nilai pada setiap node(memuat 2 digit NIM terakhir: 58 pada a5)
    public void setNodeValue(String node, int value) {
        nodeValues.put(node,value);
    }
    // 1.Algoritma Depth-First Search (DFS)
    public boolean dfsSearch(String startNode, int target, Set<String> visited) {
        visited.add(startNode);
        int currentValue = nodeValues.get(startNode);
        System.out.println("Mengunjungi node: " + startNode + " dengan nilai: " + currentValue);
        if (currentValue == target) {
            System.out.println("-> Target " + target + " DITEMUKAN pada node " + startNode + "!"):
            return true;
        }
        List<String> neighbors = adjList.getOrDefault(startNode,new ArrayList<>());
        Collections.sort(neighbors);
        // Urutkan tetanggan agar deterministik
        for (String neighbor : neighbors) {
            if (! visited.contains(neighbor)) {
                if (dfsSearch(neighbor, target, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    //2.Algoritma Breadth-First Search(BFS)
    public boolean bfsSearch(String startNode, int target) {
        Set<String>visited = new HashSet<>();
        Queue<String>visited = new LinkedList<>();

        visited.add(startNode);
        queue.add(startNode);
        while (!queue.isEmpty()) {
            String currentNode = queue.poll();
            int currentValue = nodeValues.get(currentNode);

            System.out.printIn("Mengunjungi node: " + currentNode + " dengan nilai: " + currentValue);
            if (currentValue == target) {
                System.out.printIn(" -> Target " + target + " DITEMUKAN pada node " + currentNode + "!");
                return true;
            }
            List<String> neighbors = adjList.getOrDefault(currentNode,new ArrayList<>());

            Collections.sort(neighbors);
            for (String neighbor : neighbors){
                if (! visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        GraphSearchAll graph = new GraphSearchAll();
        //Inisiasi nilai node(mengandung 2 digit NIM terakhir saya: 58 pada a5)
        graph.setNodeValue("a1",12);
        graph.setNodeValue("a2",25);
        graph.setNodeValue("a3",34);
        graph.setNodeValue("a4",41);
        graph.setNodeValue("a5",58);
        //Target nilai n = 58
        graph.setNodeValue("a6",67);
        graph.setNodeValue("a7",72);
        graph.setNodeValue("a8",89);
        //Membentuk struktur graf(8 node: a1-a8)
        graph.addEdge("a1", "a2");
        graph.addEdge("a1", "a3");
        graph.addEdge("a2", "a4");
        graph.addEdge("a2", "a5");
        graph.addEdge("a3", "a6");
        graph.addEdge("a4", "a7");
        graph.addEdge("a5", "a8");
        int target = 58;
        //Menjalankan DFS 
        System.out.println("=== 1.PENCARIAN MENGGUNAKAN DFS ===");
        System.out.println("Mencari nilai target n = " + target);
        Set<String> visitedDfs =new HashSet<>();
        boolean foundDfs = graph.dfsSearch("a1", target, visitedDfs);
        if (!foundDfs) {
            System.out.println("Target tidak ditemukan dalam graf.");

            System.out.println("\n--------------------------------------\n");
            //Menjalankan BFS
            System.out.println("=== 2.PENCARIAN MENGGUNAKAN BFS ===");
            System.out.println("Mencari nilai target n = " + target);
            boolean foundBFS = graph.bfsSearch("a1", target);
            if(!foundBFS) {
               System.out.println("Target tidak ditemukan dalam graf.");
            }
        }
    }
}