/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.ServiceDao;
import Domain.Location;
import Domain.LocationServicePackage;
import Domain.Message;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ndahigeze
 */
@Path("servicePackage")
public class LocationServicePackageService {
    Converter c;
  @Path("create")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public void createLocation(LocationServicePackage l){
    new ServiceDao().create(l);
  }
  @Path("viewByLocation/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String findByLocation(@PathParam("id")int id){
      List<LocationServicePackage> list=new ServiceDao().findByLocation(id);
       c=new Converter();
      return c.ListToGson(list);
  }
  @Path("viewTripService")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String findAll(){
   List<LocationServicePackage> list=new ServiceDao().findAll(LocationServicePackage.class);
       c=new Converter();
      return c.ListToGson(list);
  }
  @Path("update")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public String update(LocationServicePackage l){
      c=new Converter();
      Message msg=new Message();
      new ServiceDao().create(l);
      msg.setMessage("Service is "+new ServiceDao().update(l));
      return c.toJsonClass(msg);
  } 
  @Path("delete")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public String delete(LocationServicePackage s){
      c=new Converter();
      Message msg=new Message();
      msg.setMessage("Service is "+new ServiceDao().delete(s));
      return c.toJsonClass(msg);
  }
  
}
