/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourandtravel;

import Service.ApplicationService;
import Service.GuideService;
import Service.LocationService;
import Service.LocationServicePackageService;
import Service.UsersService;
import com.sun.net.httpserver.HttpServer;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Ndahigeze
 */
public class TourAndTravel {

    /**
     * @param args the command line arguments
     */
     private final static int PORT = 9998;
    private final static String HOST="http://localhost/";
    public static void main(String[] args) {
        URI baseUri = UriBuilder.fromUri(HOST).port(PORT).build();
        ResourceConfig config = new ResourceConfig(
                UsersService.class,
                LocationService.class,
                LocationServicePackageService.class,
                ApplicationService.class,
                GuideService.class
        );
        HttpServer server= JdkHttpServerFactory.createHttpServer(baseUri, config);
      // UsersService s=new UsersService();
       //System.out.println(s.viewUser());
      /* Users u=new Users();
       u.setCode("code3");
       u.setEmainl("email");
       u.setFirstName("fname");
       u.setGender("male");
       u.setLastName("lname");
       u.setPassword("password");
       u.setUserName("pass");
       u.setRegDate(new Date());
       new UserDao().create(u);*/
    }
    
}
