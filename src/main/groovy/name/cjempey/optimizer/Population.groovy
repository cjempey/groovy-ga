package name.cjempey.optimizer

class Population<T extends Solution> {
	def size;

	Selector selector;
	
	List<T> pop;
	
	Population(size, mode) {
		this.size = size;
		
		this.selector = new name.cjempey.optimizer.impl.TournamentSelector();
        selector.mode = mode;
	}		

	void init() {
		pop = new ArrayList<T>();
		size.times {
			pop << T.random();
		}
	}
	
	public T select() {
		return selector.select(pop);
	}
	
	List<T> getTopN(int n) {
		List<T> retVal = new ArrayList<>();
		n.times {
			retVal << pop.get(it)
		}
	}
}
