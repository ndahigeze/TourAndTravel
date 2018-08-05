/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.ApplicationDao;
import DAO.LocationDao;
import DAO.UserDao;
import Domain.Application;
import Domain.Location;
import Domain.Message;
import Domain.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Path("applicationService")
public class ApplicationService {
    Message m;
    Converter c;
   @Path("create")
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public String createApplication(Application a){
     m=new Message(); 
     System.out.println(a.getBookDate());
     m.setMessage("Request Received And"+new ApplicationDao().create(a));
     c=new Converter();
     return c.toJsonClass(m);
   }
   @Path("getRequest")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public String getRequest(){
       List<Application> list=new ApplicationDao().getApplication();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
       return gson.toJson(list);
   }
   
    @Path("getDoneRequest")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public String getDoneRequest(){
       List<Application> list=new ApplicationDao().getDoneApplication();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
       return gson.toJson(list);
   }
   
   @Path("getDetails/{id}/{locationID}")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public String getTripDatails(@PathParam("id")int id,@PathParam("locationID")int locId){
       Users u=new Users();
       Location l=new Location();
       l.setCode(locId);
       u.setCode(id);
       List<Application> list=new ApplicationDao().getDetails(u,l);
       Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
       return gson.toJson(list);
   }
   
   @Path("mark")
   @PUT
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public String markDoneApplication(Application a){
     m=new Message(); 
     m.setMessage(new ApplicationDao().mark(a.getLocation(),a.getBookDate()));
     c=new Converter();
     return c.toJsonClass(m);
   }
  
   @Path("searchTrip/{id}/{date}")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public String searchTrip(@PathParam("id")int id,@PathParam("date") String date) throws ParseException{
       Location l=new LocationDao().findOne(Location.class, id);
        List<Application> list=new ApplicationDao().SearchByDateLocation(l,todateConverter(date));
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(list);
   } 
   
   @Path("findDoneByCustomer/{id}/{status}")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
    public String saerchByClient(@PathParam("id")int id,@PathParam("status")int status){
      Users u=new UserDao().findOne(Users.class, id);
      List<Application> list=new ApplicationDao().getDoneBy(u, status);
      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
      return gson.toJson(list);
    }
   Date todateConverter(String date) throws ParseException{
    Date dt=null;
    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    dt=format.parse(date);
    return dt;
   }

}
