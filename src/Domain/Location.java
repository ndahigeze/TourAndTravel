/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Ndahigeze
 */
@Entity
@Table(
     uniqueConstraints = {@UniqueConstraint(columnNames= {"name","description"})}
)
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;
    private String name;
    @OneToOne(mappedBy="location")
    @Transient
    private Guide guide;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    private String description;
    @Transient
    @OneToMany(mappedBy="location",fetch=FetchType.EAGER)
    private List<Application> request;
     @Transient
    @OneToMany(mappedBy="location",fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
    private List<LocationServicePackage> service;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Application> getRequest() {
        return request;
    }

    public void setRequest(List<Application> request) {
        this.request = request;
    }

  
    public List<LocationServicePackage> getService() {
        return service;
    }

    public void setService(List<LocationServicePackage> service) {
        this.service = service;
    }

    /**
     * @return the guide
     */
    public Guide getGuide() {
        return guide;
    }

    /**
     * @param guide the guide to set
     */
    public void setGuide(Guide guide) {
        this.guide = guide;
    }
}
