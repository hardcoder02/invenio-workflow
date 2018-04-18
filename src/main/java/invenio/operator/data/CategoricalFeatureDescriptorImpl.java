package invenio.operator.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CategoricalFeatureDescriptorImpl extends
		AbstractFeatureDescriptor<CategoricalFeature> implements CategoricalFeatureDescriptor {
	
	private int defaultCategory;
	private boolean defaultSet = false;
	
	private String[] categories = {};
	private Map<String, Integer> categoryIndex = new HashMap<String, Integer>();
	private boolean isLocked = false;
	
	protected CategoricalFeatureDescriptorImpl(String name) {
		super(name);
	}
	
	public String getCategory(int index) {
		return categories[index];
	}

	public int getNumCategories() {
		return categories.length;
	}

	public int getCategory(String category) throws MissingElementException {
		if ( !categoryIndex.containsKey(category))
			throw new MissingElementException("Category [" + category + "] does not exist");
		
		return categoryIndex.get(category);
	}
	
	public String[] getCategories() {
		return Arrays.copyOf(categories, categories.length);
	}

	public void setCategories(String[] categories)
			throws IllegalStateException, DuplicateElementException {
		if (isLocked())
			throw new IllegalStateException("Categories can only be set once");
		
		if (categories == null)
			throw new NullPointerException("Categories cannot be null");

		this.categories = Arrays.copyOf(categories, categories.length);
		rebuildIndex();
		
		this.isLocked = true;
	}

	

	public int getDefaultCategoryIndex() {
		return defaultCategory;
	}
	
	public String getDefaultCategory() {
		return categories[defaultCategory];
	}

	public void setDefaultCategory(int defValue) throws IllegalStateException {
		if ( !isLocked() )
			throw new IllegalStateException("Cannot set default category before category list");
		if ( defaultSet )
			throw new IllegalStateException("Default category can only be set once");
		if ( defValue < 0 || defValue >= categories.length )
			throw new IndexOutOfBoundsException("Default category must be in the range 0 to " + (categories.length - 1) );
		
		this.defaultCategory = defValue;
		defaultSet = true;
	}

	public boolean isLocked() {
		return isLocked;
	}
	
	public boolean hasDefaultCategory() {
		return defaultSet;
	}

	public boolean isEquivalentTo(FeatureDescriptor<CategoricalFeature> otherFD) {
		if ( !(otherFD instanceof CategoricalFeatureDescriptor) )
			return false;
		
		CategoricalFeatureDescriptor other = (CategoricalFeatureDescriptor) otherFD;
		if ( !getName().equals(other.getName()) || getNumCategories() != other.getNumCategories() )
			return false;
		
		for (int i = 0; i < getNumCategories(); i++)
			if ( !getCategory(i).equals( other.getCategory(i)))
				return false;
		
		return true;
	}
	
	private void rebuildIndex() throws DuplicateElementException {
		categoryIndex.clear();
		for (int i = 0; i < categories.length; i++) {
			Integer prev = categoryIndex.put(categories[i], i);
			if (prev != null) {
				categoryIndex.clear();
				categories = new String[0];
				throw new DuplicateElementException("Duplicate category: " + categories[i]);
			}
		}
	}
	
	public String getTypeDesc() {
		StringBuffer sb = new StringBuffer();
		sb.append("Categorical [");
		
		for (int i = 0; i < categories.length; i++) {
			if (i > 0)
				sb.append(",");
			sb.append( categories[i]);
		}
			
		sb.append("]");
		
		return sb.toString();
	}
	
	public String toString() {
		return getName() + ":Categorical";
	}
}
