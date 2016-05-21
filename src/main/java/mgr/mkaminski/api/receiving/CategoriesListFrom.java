package mgr.mkaminski.api.receiving;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CategoriesListFrom {
	private List<CategoryFrom> categories;
	
	@XmlElement
	public List<CategoryFrom> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryFrom> categories) {
		this.categories = categories;
	}
	
	
}
