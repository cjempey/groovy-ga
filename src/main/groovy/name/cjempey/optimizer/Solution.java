package name.cjempey.optimizer;

public interface Solution {
	
	int getFitness();
	
	Solution cross(Solution other);
	
	void mutate();
	
}
