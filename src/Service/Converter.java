/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.util.List;

/**
 *
 * @author Ndahigeze
 * @param <X>
 */
public class Converter<X> {
    public String ListToGson(List<X> l){
        Gson g=new Gson();
        String json=g.toJson(l);
        return json;
    }
    public String toJsonClass(X l){
        Gson g=new Gson();
        String json=g.toJson(l);
        return json;  
    }
    public List<X> fromJson(String json){
      Gson g=new Gson();
        Type type = new TypeToken<List<X>>() {}.getType();
      List<X> list=g.fromJson(json, type);
      return list;
    }
    public X fromJsonClass(String json){
      Gson g=new Gson();
      Type type=new TypeToken<X>(){}.getType();
       X x=g.fromJson(json, type);
       return x;
    }
     public String md5(String c){
       try{
           MessageDigest dg= MessageDigest.getInstance("MD5");
           dg.update((c).getBytes("UTF8"));
           String str=new String(dg.digest());
           return str;
       }catch(Exception ex){
           return ex.getMessage();
       }
    }
}
