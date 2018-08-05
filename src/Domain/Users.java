/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;
 import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Ndahigeze
 */
@Entity
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;
    private String firstName;
    private String lastName;
    @Column(unique=true)
    private String userName;
    private String password;
    private String gender;
    private String emainl;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date regDate;
    @ManyToOne(fetch=FetchType.EAGER)
    private Privilege privilege;
     @Transient
    @OneToMany(mappedBy="guide",fetch=FetchType.EAGER)
    private List<Trip> trip;
     @Transient
     @OneToMany(mappedBy="client",fetch=FetchType.EAGER)
    private List<Application> aplication;
    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
  

    public List<Application> getAplication() {
        return aplication;
    }

    public void setAplication(List<Application> aplication) {
        this.aplication = aplication;
    }

    public List<Trip> getTrip() {
        return trip;
    }

    public void setTrip(List<Trip> trip) {
        this.trip = trip;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmainl() {
        return emainl;
    }

    public void setEmainl(String emainl) {
        this.emainl = emainl;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
 
}
