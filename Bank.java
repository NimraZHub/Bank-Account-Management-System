package entities;

import java.io.Serializable;

public class Bank implements Serializable {
    private String name;
    private String branchcode;
    private String location;
    private String manager;

    public Bank(String name, String branchcode, String location, String manager) {
        this.name = name;
        this.branchcode = branchcode;
        this.location = location;
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranchcode() {
        return branchcode;
    }

    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
    
    @Override
    public String toString(){
        
        return " Bank name: " + name + " , Branch code: " + branchcode + " , Location: " + location + " , Manager: " + manager;
        
    }
    
    
    
    
}
