/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author ASUS
 */
public class Product {

    private int productId;
    private String name;
    private int brandId;
    private int catrgoryId;
    private String description;
    private double price;
    private int quantity;
    private Timestamp publicationDate;
    private String status;

    public Product() {

    }

    public Product(int productId, String name, int brandId, int catrgoryId, String description, double price, int quantity, Timestamp publicationDate, String status) {
        this.productId = productId;
        this.name = name;
        this.brandId = brandId;
        this.catrgoryId = catrgoryId;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.publicationDate = publicationDate;
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getCatrgoryId() {
        return catrgoryId;
    }

    public void setCatrgoryId(int catrgoryId) {
        this.catrgoryId = catrgoryId;
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

    public Timestamp getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", name=" + name + ", brandId=" + brandId + ", catrgoryId=" + catrgoryId + ", description=" + description + ", price=" + price + ", quantity=" + quantity + ", publicationDate=" + publicationDate + ", status=" + status + '}';
    }

}
