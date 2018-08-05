/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Domain.Privilege;
import Domain.Users;
import UtilPack.UtilClass;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ndahigeze
 */
public class UserDao extends GenericDao<Users>{
    public List<Users> login(String username,String password,Privilege p){
      Session ss=UtilClass.getSessionFactory().openSession();
      Query que=ss.createQuery("From Users u where u.userName= :v and u.password= :p and u.privilege= :s");
      que.setParameter("v", username);
      que.setParameter("p", password);
      que.setParameter("s", p);
      List<Users> list=que.list();
      return list;
    }
    public  List<Users> checkUser(String username){
       Session ss=UtilClass.getSessionFactory().openSession();
      Query que=ss.createQuery("From Users u where u.userName= :v");
      que.setParameter("v", username);
      List<Users> list=que.list();
      return list;
    }
}
