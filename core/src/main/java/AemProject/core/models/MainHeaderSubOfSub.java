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

@Model(adaptables=Resource.class )

public class MainHeaderSubOfSub {
	
		 @Inject @Named("subtitle") 
		   private String subtitle;

		    public String getSubtitle() {
			return subtitle;
		}
		    @Inject  @Named("subtitlelink") 
			private String subtitlelink;
		    
			
		    public String getSubtitlelink() {
				return subtitlelink ;
			
		    }
	 }
