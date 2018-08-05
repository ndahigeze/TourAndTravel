/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Domain.Location;
import UtilPack.UtilClass;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ndahigeze
 */
public class LocationDao extends GenericDao<Location> {
    public List<Location> findByName(String name){
        Session ss=UtilClass.getSessionFactory().openSession();
        Query que=ss.createQuery("from Location d where d.name= :v");
        que.setParameter("v", name);
        List<Location> list=que.list();
        return list;
    }
}
