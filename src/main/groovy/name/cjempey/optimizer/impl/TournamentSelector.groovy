package name.cjempey.optimizer.impl

import java.security.PublicKey;

import name.cjempey.optimizer.Selector
import name.cjempey.optimizer.Solution
import name.cjempey.optimizer.Selector.Mode

class TournamentSelector<T extends Solution> implements Selector {
  private List<T> entrants
  private int tourneySize
  private Mode mode = Mode.MAXIMIZE

  public T select(List<T> from) {

    determineTourneySize(from);

    determineEntrants(from)

    return determineWinner();
  }

  private T determineWinner() {
    T retVal = entrants.get(0);
    entrants.each { soln ->
      if (mode == Mode.MINIMIZE) {
        if (soln.fitness < retVal.fitness) {
          retVal = soln;
        }
      } else if (mode == Mode.MAXIMIZE) {
        if (soln.fitness > retVal.fitness) {
          retVal = soln;
        }
      }
    }
    return retVal
  }

  private determineEntrants(List from) {
    entrants = new ArrayList<>();

    tourneySize.times {
      int idx = (Math.random() * from.size()).round()
      entrants.add(from.get(idx))
    }
  }

  private determineTourneySize(List from) {
    tourneySize = Math.log10(from.size()).round()
  }
  
  public Mode getMode() { return mode; }
  public void setMode(Mode newMode) { this.mode = newMode }
}
