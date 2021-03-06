import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PaintBooleanMatrix {

  public static boolean[][] paint(boolean[][] graph, int x, int y) {
    int row = graph.length;
    int col = graph[0].length;

    boolean target = graph[x][y];

    dfs(graph, target, x, y);

    return graph;
  }

  static void dfs(boolean[][] graph, boolean target, int r, int c) {
    if (r < 0 || c < 0 || r >= graph.length || c >= graph[0].length || graph[r][c] != target) {
      return;
    }
    if (graph[r][c] == target) {
      graph[r][c] = !graph[r][c]; // -1 to mark flip color
    }
    dfs(graph, target, r - 1, c);
    dfs(graph, target, r + 1, c);
    dfs(graph, target, r, c - 1);
    dfs(graph, target, r, c + 1);
  }

  public static void main(String[] args) {
    int[] arr = new int[]{1, 2, 4};
    List<int[]> a = Arrays.asList(arr);
    System.out.println(a.size());
    String str = "a girl is a girl";
    System.out.println(str.indexOf("girl", 2));
  }

}
