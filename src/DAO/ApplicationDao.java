/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Domain.Application;
import Domain.Location;
import Domain.Users;
import UtilPack.UtilClass;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ndahigeze
 */
public class ApplicationDao extends GenericDao<Application> {
    public List<Application> getApplication(){
         Session ss=UtilClass.getSessionFactory().openSession();
         Query que=ss.createQuery("From Application u where u.status= 0");
         List<Application> list=que.list();
         ss.close();
         return list; 
    }
    
       public List<Application> getDoneApplication(){
         Session ss=UtilClass.getSessionFactory().openSession();
         List<Application> list=null;
         try{
              Query que=ss.createQuery("From Application u where u.status= 1");
              list=que.list();
              ss.close();
         }catch(Exception ex){
             System.out.println("SomeThing Is wrong"+ex.getMessage());
         }
         return list; 
    }    
    
     
    public List<Application> getDetails(Users u,Location l){
         Session ss=UtilClass.getSessionFactory().openSession();
         List<Application> list=null;
        try{
          Query que=ss.createQuery("From Application p where p.client= :v and p.status= 0 and p.location= :l");
         que.setParameter("v", u);
         que.setParameter("l",l );
         list=que.list();
         ss.close();
        }catch(Exception ex){
            System.out.println("There is Some Problem");
        }
        
         return list; 
    }
    
     public List<Application> getDoneBy(Users u,int l){
         Session ss=UtilClass.getSessionFactory().openSession();
         List<Application> list=null;
        try{
          Query que=ss.createQuery("From Application p where p.client= :v and p.status= :l");
         que.setParameter("v", u);
         que.setParameter("l",l );
         list=que.list();
         ss.close();
        }catch(Exception ex){
            System.out.println("There is Some Problem");
        }
        
         return list; 
    }
    
    
    
    public String mark(Location l,Date d){
         Session ss=UtilClass.getSessionFactory().openSession();
         try{ ss.beginTransaction();
            Query que=ss.createQuery("update Application p set p.status=1 where p.location= :v and p.bookDate= :da");
            que.setParameter("v", l);
            que.setParameter("da",d );
            que.executeUpdate();
            ss.getTransaction().commit();
           ss.close();
            return "updated";
         }catch(Exception ex){
              return "There are Some broblem Try Again";
         }
        
    }
    public List<Application> SearchByDateLocation(Location l,Date d){
         Session ss=UtilClass.getSessionFactory().openSession();
         List<Application> list=null;
        try{
          Query que=ss.createQuery("From Application p where status=0 and p.location= :lo and p.bookDate= :bd");
         que.setParameter("lo", l);
         que.setParameter("bd",d );
         list=que.list();
         ss.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }
//   and la.code= :lo  
}
