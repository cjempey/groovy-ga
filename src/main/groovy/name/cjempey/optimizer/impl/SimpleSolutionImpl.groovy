package name.cjempey.optimizer.impl

import name.cjempey.optimizer.Solution;

public class SimpleSolutionImpl implements Solution {

	int number;
	
	SimpleSolutionImpl() {
		number = (Math.random() * 10).round();
	}
	
	SimpleSolutionImpl(value) {
		number = value;
	}
	
	@Override
	public int getFitness() {
		return number;
	}

	@Override
	public Solution cross(Solution other) {
		def retval = null;
		if (other.class == SimpleSolutionImpl) {
			retval = new SimpleSolutionImpl((this.number + other.number) / 2)
		}
		return retval;
	}

	@Override
	public void mutate() {
		if (Math.random()>0.5) {
			number++;
		} else {
			number--;
		}
	}

}
