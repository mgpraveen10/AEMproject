package AemProject.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = Servlet.class,
property = {
"sling.servlet.methods=" + "GET",
"sling.servlet.paths=" + "/AemProject/bin/api/content/v4",
"sling.servlet.extensions=" + "json"
}
)
public class JsonServletV4Trim1Presponse extends SlingSafeMethodsServlet {
	
	private static final long serialVersionUID = 1L;
	Resource resource;
    @Reference
    ResourceResolverFactory resolverFactory;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
    	
    	 String nodePath="/content/AemProject/en/"+request.getParameter("page").trim();

    	 PrintWriter out=response.getWriter();
    	//   response.setStatus(404);
         response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");
         response.setHeader("Cache-Control","max-age=0");

    }
    }


