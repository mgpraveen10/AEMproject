package AemProject.core.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.TidyJsonItemWriter;
import com.day.cq.wcm.api.WCMMode;

@Component(service = Servlet.class, property = { "sling.servlet.methods=" + "GET",
        "sling.servlet.paths=" + "/AemProject/bin/api/content/v6", "sling.servlet.extensions=" + "json" })
public class JsonServletV6TrimRecursive extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(JsonServletV6TrimRecursive.class);

    Resource resource;
    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        WCMMode wcmMode = WCMMode.fromRequest(request);

        String pageName = request.getParameter("page").trim();
        String nodePath = "/content/AemProject/en/" + pageName;

        response.setContentType("json");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "max-age=0");
        JSONObject jsonObject = new JSONObject();

        final PrintWriter out = response.getWriter();
        out.write("{");
        try {
            Node node = null;
            resource = request.getResourceResolver().getResource(nodePath + "/jcr:content/root");
            if (resource != null) {
                node = resource.adaptTo(Node.class);
                NodeIterator nodeItr = node.getNodes();
                recusive(out, nodeItr);
                out.write("}");
            } else {
                out.println("{Please provide correct parameters ex: Host:/bin/service.en_us.authenticated.home.mobile}");
            }
        } catch (Exception e) {
            out.print(e);
            out.write("e");
        }
        out.flush();
        out.close();
    }

    public void recusive(PrintWriter out, NodeIterator nodeItr) {
        boolean flag=false;
        try {
            while (nodeItr.hasNext()) {
                if(flag==true) {
                    flag=false;
                    out.print(',');
                }
                Node cNode = nodeItr.nextNode();
                if (cNode.getName().equals("cq:responsive")) {
                
                    continue;
                }
                
                if ((cNode.getProperty("sling:resourceType").getString())
                        .equals("wcm/foundation/components/responsivegrid")) {
                    NodeIterator nodeItr1 = cNode.getNodes();
                    recusive(out, nodeItr1);
                    flag=true;
                    continue;
                    
                }
                
                

                String resourceName = cNode.getProperty("sling:resourceType").getString();
                resourceName = resourceName.substring(resourceName.lastIndexOf('/') + 1);
                if (nodeItr.hasNext()/*||flag==true*/) {
                	
                    //flag=false;
                    out.write("\"" + resourceName + "\"" + ":" + resourceToJSON(cNode).toString() + ",");
                } else {
                    out.write("\"" + resourceName + "\"" + ":" + resourceToJSON(cNode).toString());
                }
            }
        } catch (Exception e) {
            out.print(e);
            out.write("e");
        }
    }

   


    public JSONObject resourceToJSON(final Node node) {
        final StringWriter stringWriter = new StringWriter();

        TidyJsonItemWriter jsonWriter = new TidyJsonItemWriter(null);

        final Set<String> propertiesToIgnore = new HashSet();
        propertiesToIgnore.add("jcr:lastModifiedBy");
        propertiesToIgnore.add("jcr:primaryType");
        propertiesToIgnore.add("cq:lastModifiedBy");
        propertiesToIgnore.add("jcr:created");
        propertiesToIgnore.add("jcr:createdBy");
       
        propertiesToIgnore.add("cq:template");
        propertiesToIgnore.add("jcr:lastModified");
        propertiesToIgnore.add("cq:lastModified");
        propertiesToIgnore.add("jcr:mixinTypes");
        propertiesToIgnore.add("jcr:versionHistory");
        propertiesToIgnore.add("jcr:baseVersion");
        propertiesToIgnore.add("sling:resourceType");
        jsonWriter = new TidyJsonItemWriter(propertiesToIgnore);
        JSONObject jsonObject = null;
        try {
            /* Get JSON with no limit to recursion depth. */
            jsonWriter.dump(node, stringWriter, -1, true);
            jsonObject = new JSONObject(stringWriter.toString());
        } catch (RepositoryException | JSONException e) {
            LOG.error("Could not create JSON", e);
        }

        return jsonObject;
    }


}
