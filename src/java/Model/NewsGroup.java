/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import dal.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Dell
 */
public class NewsGroup {
    private int id;
    private String name;
    private List<News> lnew;
    private String side;

    public NewsGroup() {
    }

    public NewsGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public NewsGroup(int id, String name, List<News> lnew, String side) {
        this.id = id;
        this.name = name;
        this.lnew = lnew;
        this.side = side;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<News> getLnew() {
        return lnew;
    }

    public void setLnew(List<News> lnew) {
        this.lnew = lnew;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
    
}
