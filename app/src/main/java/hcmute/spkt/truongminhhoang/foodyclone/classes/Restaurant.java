package hcmute.spkt.truongminhhoang.foodyclone.classes;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Restaurant implements Serializable {
    public String id;
    public String image;
    public String name;
    public String category;
    public String avgPrice;
    public String address;

    public Restaurant(String id, String image, String name, String category, String avgPrice, String address) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.category = category;
        this.avgPrice = avgPrice;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NonNull
    @Override
    public String toString()  {
        return this.name+" ("+ this.address+")";
    }

}
