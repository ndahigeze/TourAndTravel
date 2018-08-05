/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.GuiderDao;
import Domain.Guide;
import Domain.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@Path("guide")
public class GuideService {
        Converter c;
        @Path("create")
        @POST
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public String createGuider(Guide l){
            c=new Converter();
             Message msg=new Message();
             msg.setMessage( new GuiderDao().create(l));
           return c.toJsonClass(msg);
        }
        @Path("update")
        @PUT
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public String updateGuider(Guide l){
           Guide d=new GuiderDao().findOne(Guide.class, l.getCode());
           d.setLocation(l.getLocation());
           d.setWorkDate(l.getWorkDate());
           c=new Converter();
            Message msg=new Message();
            msg.setMessage( new GuiderDao().update(d));
           return c.toJsonClass(msg);
        }
  
        @Path("viewGuiders")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public String viewLocation(){
          List<Guide> list=new GuiderDao().findAll(Guide.class);
           Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
           c=new Converter();
           return gson.toJson(list);
        }
        
        @Path("asigned/{date}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public String viewAsigned(@PathParam("date")String date) throws ParseException{
          List<Guide> list=new GuiderDao().getAssigned(new SimpleDateFormat("yyyy-MM-dd").parse(date));
           Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
           c=new Converter();
           return gson.toJson(list);
        }
        @Path("notAsigned/{date}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public String viewNotAsigned(@PathParam("date")String date) throws ParseException{
          List<Guide> list=new GuiderDao().getNotAssigned(new SimpleDateFormat("yyyy-MM-dd").parse(date));
           Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
           c=new Converter();
           return gson.toJson(list);
        }
}
