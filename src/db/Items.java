package db;

public class Items {
    private Long id;
    private String name;
    private double price;
    private String details;
    private String img;
    private Brand brand;
    private int like;

    public Items() {
    }

    public Items(Long id, String name, double price, String details, String img, Brand brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.img = img;
        this.brand = brand;
    }

    public Items(Long id, String name, double price, String details, String img, Brand brand, int like) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.img = img;
        this.brand = brand;
        this.like = like;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
