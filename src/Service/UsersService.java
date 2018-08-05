/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.LocationDao;
import DAO.PrivilegeDao;
import DAO.UserDao;
import Domain.Location;
import Domain.Message;
import Domain.Privilege;
import Domain.Users;
import com.google.gson.Gson;
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
@Path("service")
public class UsersService {
    Message msg;
    Converter c;
    //functionrelated to the 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Users/")
    public String user(){
      List<Users> list=new UserDao().findAll(Users.class);
          Gson g=new Gson();
          String json=g.toJson(list);
          return json;
    }
    
    @POST
    @Path("/user/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findUser(@PathParam("code") String id){
        System.out.print(id);
        Users user=new UserDao().findOne(Users.class, id);
        Gson g=new Gson();
        String json=g.toJson(user);
        return json;
    }
    
    @Path("create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String saveUser(Users o){
       msg=new Message();
       c=new Converter();
        if(check(o.getUserName())){
             msg.setMessage("Account is "+new UserDao().create(o));
        }else{
        msg.setMessage("Username Is Already Taken");
        }
       return c.toJsonClass(msg);
    }
    @Path("/updateUser/")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateUser(Users user){
     new UserDao().update(user);
     return usersList();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login/{username}/{password}/{privilege}")
    public String login(@PathParam("username") String username,@PathParam("password") String password,@PathParam("privilege")int id){
        Privilege p=new PrivilegeDao().findOne(Privilege.class, id);
        c=new Converter();
        List<Users> list=new UserDao().login(username, c.md5(password),p);
        Gson g=new Gson();
        String json=g.toJson(list);
        return json;
    }
    
    public String usersList(){
        List<Users> list=new UserDao().findAll(Users.class);
        Gson g=new Gson();
        String json=g.toJson(list);
        return json;
    }
    
  public boolean check(String username){
      List<Users> list=new UserDao().checkUser(username);
        return list.isEmpty();
  } 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/checkSave/{username}")
    public String checkUsername(@PathParam("username") String username){
      List<Users> list=new UserDao().checkUser(username);
      Gson g=new Gson();
      String json=g.toJson(list);
        return json;
  }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Byid/{id}")
    public String byId(@PathParam("id") int id){
      Users list=new UserDao().findOne(Users.class,id);
      Gson g=new Gson();
      String json=g.toJson(list);
        return json;
  }
    

}
