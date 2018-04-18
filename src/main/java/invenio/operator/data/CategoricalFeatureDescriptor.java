package invenio.operator.data;


public interface CategoricalFeatureDescriptor extends FeatureDescriptor<CategoricalFeature> {
	int getNumCategories();
	String getCategory(int index);
	int getCategory(String category) throws MissingElementException;
	String[] getCategories();
	void setCategories(String[] categories) throws IllegalStateException, DuplicateElementException;
	
	int getDefaultCategoryIndex();
	String getDefaultCategory();
	void setDefaultCategory(int defValue) throws IllegalStateException;
	boolean hasDefaultCategory();
	
	boolean isLocked();
}
