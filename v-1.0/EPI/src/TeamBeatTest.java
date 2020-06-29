import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class TeamBeatTest {

  MatchResult match1 = new MatchResult("a", "b");
  MatchResult match2 = new MatchResult("b", "c");
  MatchResult match3 = new MatchResult("c", "d");
  MatchResult match4 = new MatchResult("d", "e");
  MatchResult match5 = new MatchResult("n", "f");
  MatchResult match6 = new MatchResult("e", "z");
  List<MatchResult> matches = new ArrayList<>();


  @Test
  public void canABeatB() {
    matches.add(match1);
    matches.add(match2);
    matches.add(match3);
    matches.add(match4);
    matches.add(match5);
    matches.add(match6);

    assertFalse(TeamBeat.canABeatB(matches, "a", "f"));
  }
}