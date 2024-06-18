/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Dell
 */
public class Product {
    private int id;
    private String name;
    private String model;
    private int BrandID;
    private int cateId;
    private int colorId;
    private int SizeId;
    private String description;
    private double price;
    private int quantity;
    private int sold;
    private int view;
    private  String publicationDate;
    private String createdAt;
    private String updatedAt;

    public Product() {
    }

    public Product(int id, String name, String model, int BrandID, int cateId, int colorId, int SizeId, String description, double price, int quantity, int sold, int view, String publicationDate, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.BrandID = BrandID;
        this.cateId = cateId;
        this.colorId = colorId;
        this.SizeId = SizeId;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.sold = sold;
        this.view = view;
        this.publicationDate = publicationDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int BrandID) {
        this.BrandID = BrandID;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSizeId() {
        return SizeId;
    }

    public void setSizeId(int SizeId) {
        this.SizeId = SizeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
