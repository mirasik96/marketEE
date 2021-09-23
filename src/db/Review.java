package db;

import java.sql.Timestamp;

public class Review {
    private Long id;
    private String review;
    private Items item;
    private User user;
    private Timestamp postDate;

    public Review() {
    }

    public Review(Long id, String review, Items item, User user, Timestamp postDate) {
        this.id = id;
        this.review = review;
        this.item = item;
        this.user = user;
        this.postDate = postDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }
}
