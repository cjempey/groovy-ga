package name.cjempey.optimizer.impl;

import name.cjempey.optimizer.impl.SimpleSolutionImpl;
import spock.lang.*

class SimplSolutionImplTests extends Specification {
	SimpleSolutionImpl cut = new SimpleSolutionImpl(5);
	
	def "fitness gives stored number"() {
		expect:
			cut.fitness == 5;
	}
	
	def "crossover gives average"() {
		setup:
			SimpleSolutionImpl other = new SimpleSolutionImpl(7);
		expect:
			cut.cross(other).fitness == 6;
	}
	
	def "mutate gives +1 or -1"() {
		when:
			cut.mutate();
		then:
			cut.fitness == 6 || cut.fitness == 4
	}	
}