package hcmute.spkt.truongminhhoang.foodyclone.classes;

public class Food {
    public String image;
    public String name;
    public String category;
    public int price;

    public Food(String image, String name, String category, int price) {
        this.image = image;
        this.name = name;
        this.category = category;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
