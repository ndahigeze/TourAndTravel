/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Ndahigeze
 */
@Entity
public class Trip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;
   @ManyToOne(fetch=FetchType.EAGER)
    private Users guide;
    @OneToOne(fetch=FetchType.EAGER)
    private Application request;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Users getGuide() {
        return guide;
    }

    public void setGuide(Users guide) {
        this.guide = guide;
    }

    public Application getRequest() {
        return request;
    }

    public void setRequest(Application request) {
        this.request = request;
    }

  
   
}
