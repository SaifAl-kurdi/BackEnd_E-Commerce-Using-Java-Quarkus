package importxmlfile.controller;

import importxmlfile.template.TemplateDesignPattern;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.file.Paths;
import java.nio.file.Files;

@ApplicationScoped
@Path("importxml")
public class ImportXMLController {

    @Inject
    TemplateDesignPattern templateDesignPattern;

    @POST
    @Path("parsing")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object importXML(@QueryParam("filePath") String filePath,
                            @QueryParam("field") String field) throws Exception {
        if (!filePath.endsWith(".xml")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("This file is not xml file.").build();
        }

        java.nio.file.Path path = Paths.get(filePath);
        byte[] fileContent = Files.readAllBytes(path);
        templateDesignPattern.importxml(fileContent, field);
        return Response.status(Response.Status.OK).build();
    }
}
