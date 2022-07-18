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

public class CardMulti {
	
	 @Inject @Named("logolink") 
	   private String logo;

	    public String getLogo() {
		return logo;
	}
	
 @Inject @Named("title") 
   private String title;

    public String getTitle() {
	return title;
}
    @Inject  @Named("subtitle") 
	private String subtitle;
    
	
    public String getSubtitle() {
		return subtitle;
	}
	
    @Inject  @Named("linkURL") 
	private String link;
    
	
    public String getLink() {
		return link ;
	
    }}
	



