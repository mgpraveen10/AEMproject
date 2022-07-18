package AemProject.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.Self;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables=Resource.class )

public class MainHeaderNav {
	
	
	 @Inject @Named("logo") 
	   private String logo;

	    public String getLogo() {
		return logo;
	}
	    @Inject  @Named("linkforlogo") 
		private String link;
	    
		
	    public String getLink() {
			return link ;
		
	    }
	
	 @Inject @Named("multifield")
	   private List<MainHeaderNavSub> multi;

	    public List<MainHeaderNavSub> getMulti() {
		return multi;
}  

}
