package invenio.visual;

import prefuse.action.layout.Layout;

public interface LayoutManager {
	String[] getLayouts();
	Layout getLayout(int layoutType, Object extra, String groupName);
}
