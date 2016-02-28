package name.cjempey.optimizer.impl

import name.cjempey.optimizer.Selector;
import name.cjempey.optimizer.Solution
import name.cjempey.optimizer.Selector.Mode;
import spock.lang.*;

class TournamentSelectorTests extends Specification {
  TournamentSelector cut = new TournamentSelector();
  List<Solution> lst = Mock();
  Solution soln = Mock();
  Solution soln1 = Mock()
  Solution soln2 = Mock()
  Solution soln3 = Mock()


  def setup() {
    lst.get(_) >> soln;
    soln.fitness >> 0;
    soln1.fitness >> 3
    soln2.fitness >> 5
    soln3.fitness >> 7
  }

  def "determine correct tournament size"() {
    when:
      cut.determineTourneySize(lst);
    then:
      1 * lst.size() >> 1000;
      cut.tourneySize == 3;
  }

  def "determine entrants"() {
    setup:
      def tSize = 3
    when:
      cut.tourneySize = tSize;
      cut.determineEntrants(lst);
    then:
      tSize * lst.get(_) >>> [soln1, soln2, soln3]
      tSize * lst.size()
      cut.entrants.containsAll([soln1, soln2, soln3])
      // retainAll returns true if list changed
      // this asserts that only the mocks we returned
      // are in the entrants list
      !cut.entrants.retainAll([soln1, soln2, soln3])
  }

  def "determine highest"() {
    given:
      cut.entrants = [soln1, soln2, soln3]
      cut.mode = Mode.MAXIMIZE
    when:
      Solution winner = cut.determineWinner()
    then:
      winner.is(soln3)
  }

  def "determine lowest"() {
    given:
      cut.entrants = [soln1, soln2, soln3]
      cut.mode = Mode.MINIMIZE
    when:
      Solution winner = cut.determineWinner()
    then:
      winner.is(soln1)
  }

  def "full happy path"() {
    when:
      Solution winner = cut.select(lst)
    then:
      lst.size() >> 1000
      lst.get(_) >>> [soln1, soln2, soln3]
      winner.is(soln3)
  }
}
