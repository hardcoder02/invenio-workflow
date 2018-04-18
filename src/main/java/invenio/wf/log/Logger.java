package invenio.wf.log;

import java.util.ArrayList;
import java.util.List;

public class Logger {
	private static Logger logger;
	
	private List<LogTarget> targets = new ArrayList<LogTarget>();
	
	private Logger() {}
	
	public static Logger getInstance() {
		if (logger == null) {
			logger = new Logger();
		}
		return logger;
	}
	
	public void addLogTarget(LogTarget target) {
		targets.add(target);
	}
	
	public void log(Object source, String message) {
		for (LogTarget t : targets) {
			t.log(source, message + "\n");
		}
	}
}
