/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.LocationDao;
import Domain.Location;
import Domain.Message;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ndahigeze
 */
@Path("location")
public class LocationService {
     //Location 
    Converter c;
  @Path("create")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public String createLocation(Location l){
    Message msg=new Message();
    msg.setMessage(new LocationDao().create(l));
    c=new Converter();
    return c.toJsonClass(msg);
  }
  @Path("viewLocation")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String viewLocation(){
    List<Location> list=new LocationDao().findAll(Location.class);
    c=new Converter();
    return c.ListToGson(list);
  }
  
  @Path("findByName/{name}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String findbyName(@PathParam("name")String name){
      List<Location> list=new LocationDao().findByName(name);
       c=new Converter();
       return c.ListToGson(list);
  }
  @Path("update")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public String update(Location l){
    Message msg=new Message();
    msg.setMessage("Location is "+new LocationDao().update(l));
     c=new Converter();
    return c.toJsonClass(msg);
  }
}
