package AemProject.core.models;

import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.Self;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables = Resource.class)

public class MainHeaderNavSub {

	@Inject
	@Named("title")
	private String title;

	public String getTitle() {
		return title;
	}

	@Inject
	@Named("titlelink")
	private String titlelink;

	public String getTitlelink() {
		return titlelink;

	}

	@Inject
	@Named("jcr:multifield")
	private List<MainHeaderSubOfSub> m;

	public List<MainHeaderSubOfSub> getM() {
		return m;
	}

}
