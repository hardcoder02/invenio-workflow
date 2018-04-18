package invenio.wf.items.vis.clustering.compare;


public class CCMatrixCell {
	private int countXOnly;
	private int countYOnly;
	private int countShared;
	
	
	public int getCountXOnly() {
		return countXOnly;
	}
	public void setCountXOnly(int countXOnly) {
		this.countXOnly = countXOnly;
	}
	public int getCountYOnly() {
		return countYOnly;
	}
	public void setCountYOnly(int countYOnly) {
		this.countYOnly = countYOnly;
	}
	public int getCountShared() {
		return countShared;
	}
	public void setCountShared(int countShared) {
		this.countShared = countShared;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(countXOnly).append(" / ");
		sb.append(countShared).append(" / ");
		sb.append(countYOnly);
		return sb.toString();
	}
}
