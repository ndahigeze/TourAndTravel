/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Domain.Location;
import Domain.LocationServicePackage;
import UtilPack.UtilClass;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ndahigeze
 */
public class ServiceDao extends GenericDao<LocationServicePackage> {
    public List<LocationServicePackage> findByLocation(int id){
       List<LocationServicePackage> list=null;
        try{
            Session ss=UtilClass.getSessionFactory().openSession();
                Query que=ss.createQuery("from LocationServicePackage p join fetch p.location s where s.code= :v");
                que.setParameter("v", id);
                list=que.list();
        }catch(Exception ex){
            
        }
      
       return list;
    }
      public String DeleteByLocation(Location l){
          try{
            Session ss=UtilClass.getSessionFactory().openSession();
            Query que=ss.createQuery("Delete from LocationServicePackage p where p.location= :v");
            que.setParameter("v", l);
            return "Service Deleted";
          }catch(HibernateException ex){
           return "Problem in deletig Service";
          }
    }
    
}
