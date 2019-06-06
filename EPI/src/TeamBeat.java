import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TeamBeat {



  static boolean canABeatB(List<MatchResult> matches, String A, String B) {
    Map<String, Set<String>> graph = buildGraph(matches);
    return dfs(graph, new HashSet<>(), A, B);
  }

  static Map<String, Set<String>> buildGraph(List<MatchResult> matches) {
    Map<String, Set<String>> graph = new HashMap<>();
    for (MatchResult match : matches) {
      String win = match.winTeam;
      String lose = match.loseTeam;
      if (!graph.containsKey(win)) {
        graph.put(win, new HashSet<>());
      }
      graph.get(win).add(lose);
    }
    return graph;
  }

  static boolean dfs(Map<String, Set<String>> graph, Set<String> visited, String cur, String end) {
    if (cur.equals(end)) {
      return true;
    }
    if (visited.contains(cur) || !graph.containsKey(cur)) {
      return false;
    }
    visited.add(cur);
    for (String nei : graph.get(cur)) {
      if (dfs(graph, visited, nei, end)) {
        return true;
      }
    }
    return false;
  }

}
