/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import UtilPack.UtilClass;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ndahigeze
 * @param <X>
 */
public class GenericDao<X> {
     public String create(X o){
       Session ses=UtilClass.getSessionFactory().openSession();
       try{
           ses.beginTransaction();
       ses.save(o);
       ses.getTransaction().commit();
       ses.close();
          return "Created succefully";
       }catch(HibernateException ex){
          return "Not Saved Check the Duplicates try Again";
       }
      
       
    }  
    public String update(X o){
      Session ses=UtilClass.getSessionFactory().openSession();
      try{
        ses.beginTransaction();
        ses.update(o);
        ses.getTransaction().commit();
        ses.close();
        return "update Success";
      }catch(HibernateException ex){
         return "Not Updated Try Again";
      }
      
    }
    public String delete(X o){
      Session ses=UtilClass.getSessionFactory().openSession();
      try{
      ses.beginTransaction();
      ses.delete(o);
      ses.getTransaction().commit();
      ses.close();
        return "deleted Success";
      }catch(HibernateException ex){
        return "Not Deleted try Again";
      }
    }
    public X findOne(Class x,Serializable v){
        Session ses=UtilClass.getSessionFactory().openSession();
        X s=(X)ses.get(x, v);
        ses.close();
        return s;
    }
    public List<X> findAll(Class x){
     Session ses=UtilClass.getSessionFactory().openSession();
     Query q=ses.createQuery("from "+x.getName()+" s");
     List<X> list=q.list();
     ses.close();
     return list;
    }
}
