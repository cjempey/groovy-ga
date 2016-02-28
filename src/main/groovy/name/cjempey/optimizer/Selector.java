package name.cjempey.optimizer;

import java.util.List;

public interface Selector {
	public enum Mode { MAXIMIZE, MINIMIZE };
	
	<T extends Solution> T select(List<T> from);
	
	Mode getMode();
	void setMode(Mode newMode);
}
