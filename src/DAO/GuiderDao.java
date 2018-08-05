/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Domain.Guide;
import UtilPack.UtilClass;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ndahigeze
 */
public class GuiderDao extends GenericDao<Guide> {
      public List<Guide> getAssigned(Date dt){
         Session ss=UtilClass.getSessionFactory().openSession();
         List<Guide> list=null;
         try{
              Query que=ss.createQuery("From Guide u where u.workDate= :p");
              que.setParameter("p", dt);
              list=que.list();
              ss.close();
         }catch(HibernateException ex){
             System.out.println("SomeThing Is wrong"+ex.getMessage());
         }
         return list;
      }   
      
       public List<Guide> getNotAssigned(Date dt){
         Session ss=UtilClass.getSessionFactory().openSession();
         List<Guide> list=null;
         try{
              Query que=ss.createQuery("From Guide u where u.workDate!= :p");
              que.setParameter("p", dt);
              list=que.list();
              ss.close();
         }catch(HibernateException ex){
             System.out.println("SomeThing Is wrong"+ex.getMessage());
         }
         return list;
      }   
}
