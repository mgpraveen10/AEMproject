package AemProject.core.models;


import AemProject.core.models.Author;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.Required;

import javax.inject.Inject;


@Model(adaptables = Resource.class,
adapters = Author.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class Authorimplement implements Author{
	
	
	@Inject
	@Required
    @Default(values = "AEM")
    String fname;

    @Inject
    @Required
    @Default(values = "GEEKS")
    String lname;
    
    @Override
    public String getFirstName() {
        return fname;
    }

    @Override
    public String getLastName() {
        return lname;
    }

}
