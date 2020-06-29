import static org.junit.Assert.*;

import org.junit.Assert;

public class PaintBooleanMatrixTest {

  boolean[][] graph;
  boolean[][] res;

  @org.junit.Before
  public void setUp() throws Exception {
    graph = new boolean[4][3];
    graph[0][0] = true;
    graph[1][0] = true;
    graph[2][1] = true;
    graph[3][2] = true;
    res = new boolean[4][3];
    res[0][0] = true;
    res[1][0] = true;
    res[2][0] = true;
    res[2][1] = true;
    res[3][0] = true;
    res[3][1] = true;
    res[3][2] = true;
  }

  @org.junit.Test
  public void paint() {
    boolean[][] act = PaintBooleanMatrix.paint(graph, 2, 0);
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(res[i][j], act[i][j]);
      }
    }
  }

  @org.junit.Test
  public void dfs() {
  }
}