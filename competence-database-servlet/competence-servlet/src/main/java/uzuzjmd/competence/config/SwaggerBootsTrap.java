package uzuzjmd.competence.config;


import io.swagger.models.Contact;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.Swagger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by dehne on 05.07.2016.
 */
public class SwaggerBootsTrap extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        Info info = new Info()
                .title("Swagger Sample App")
                .description("Desc")
                .termsOfService("http://helloreverb.com/terms/")
                .contact(new Contact()
                        .email("apiteam@swagger.io"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));

        ServletContext context = config.getServletContext();
        Swagger swagger = new Swagger().info(info);
        context.setAttribute("swagger", swagger);
    }
}
