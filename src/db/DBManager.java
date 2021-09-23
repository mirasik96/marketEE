package db;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;

    static {
        try{
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres?currentSchema=shop",
                    "postgres", "qwe");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Items> getItems() throws SQLException {
        ArrayList<Items> items = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("" +
                "SELECT i.id, i.name, i.price, i.details, i.img, i.brand_id, b.name as brandName, i.likes " +
                "FROM items i " +
                "INNER JOIN brands b ON i.brand_id = b.id");
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            items.add(new Items(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getString("details"),
                    resultSet.getString("img"),
                    new Brand(resultSet.getLong("brand_id"),
                            resultSet.getString("brandName"),
                            null),
                    resultSet.getInt("likes")));
        }
        statement.close();
        return items;
    }

    public static ArrayList<Brand> getBrands() throws SQLException {
        ArrayList<Brand> brands = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("" +
                "SELECT * FROM brands");
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            brands.add(new Brand(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("country")));
        }
        statement.close();
        return brands;
    }

    public static Items getItem(Long id) throws SQLException {
        Items item = new Items();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            item.setId(id);
            item.setName(resultSet.getString("name"));
            item.setPrice(resultSet.getDouble("price"));
            item.setDetails(resultSet.getString("details"));
            item.setImg(resultSet.getString("img"));
            item.setBrand(getBrand(resultSet.getLong("brand_id")));
            item.setLike(resultSet.getInt("likes"));
        }
        statement.close();
        return item;
    }

    public static Brand getBrand(Long id) throws SQLException {
        Brand brand = new Brand();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM brands WHERE id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            brand.setId(id);
            brand.setName(resultSet.getString("name"));
            brand.setCountry(resultSet.getString("country"));
        }
        statement.close();
        return brand;
    }

    public static void addItem(Items item) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "INSERT INTO items(name, price, details, img, brand_id) VALUES(?,?,?,?,?)");
        statement.setString(1, item.getName());
        statement.setDouble(2, item.getPrice());
        statement.setString(3, item.getDetails());
        statement.setString(4, item.getImg());
        statement.setLong(5, item.getBrand().getId());
        statement.executeUpdate();
        statement.close();
    }

    public static void addBrand(Brand brand) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "INSERT INTO brands(name, country) VALUES(?,?)");
        statement.setString(1, brand.getName());
        statement.setString(2, brand.getCountry());
        statement.executeUpdate();
        statement.close();
    }

    public static void updateItem(Items item) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "UPDATE items SET name=?, price=?, details=?, img=?, brand_id=? WHERE id=?");
        statement.setString(1, item.getName());
        statement.setDouble(2, item.getPrice());
        statement.setString(3, item.getDetails());
        statement.setString(4, item.getImg());
        statement.setLong(5, item.getBrand().getId());
        statement.setLong(6, item.getId());
        statement.executeUpdate();
        statement.close();
    }

    public static void updateBrand(Brand brand) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "UPDATE brands SET name=?, country=? WHERE id=?");
        statement.setString(1, brand.getName());
        statement.setString(2, brand.getCountry());
        statement.setLong(3, brand.getId());
        statement.executeUpdate();
        statement.close();
    }

    public static void deleteItem(Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "DELETE FROM items WHERE id = ?");
        statement.setLong(1, id);
        statement.executeUpdate();
        statement.close();
    }

    public static void deleteBrand(Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "DELETE FROM brands WHERE id = ?");
        statement.setLong(1, id);
        statement.executeUpdate();
        statement.close();
    }

    public static User login(String email) throws SQLException {
        User user = null;
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            user = new User(resultSet.getLong("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("fullname"),
                    resultSet.getBoolean("is_admin"));
        }
        statement.close();
        return user;
    }

    public static void addUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "INSERT INTO users(email, password, fullname, is_admin) VALUES(?,?,?,false)");
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFullname());
        statement.executeUpdate();
        statement.close();
    }

    public static void updateUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "UPDATE users SET password=?, fullname=? WHERE id=?");
        statement.setString(1, user.getPassword());
        statement.setString(2, user.getFullname());
        statement.setLong(3, user.getId());
        statement.executeUpdate();
        statement.close();
    }

    public static void deleteUser(Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "DELETE FROM users WHERE id = ?");
        statement.setLong(1, id);
        statement.executeUpdate();
        statement.close();
    }

    public static ArrayList<Review> getReviews(Long itemId) throws SQLException {
        ArrayList<Review> reviews = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("" +
                "SELECT r.id, r.review, r.item_id, r.user_id, r.post_date, u.fullname " +
                "FROM reviews r " +
                "INNER JOIN users u ON r.user_id = u.id " +
                "WHERE r.item_id = ? " +
                "ORDER BY r.post_date DESC");
        statement.setLong(1, itemId);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            reviews.add(new Review(resultSet.getLong("id"),
                    resultSet.getString("review"),
                    new Items(resultSet.getLong("item_id"),null,0,null,null,null),
                    new User(resultSet.getLong("user_id"), null,null,resultSet.getString("fullname"),false),
                    resultSet.getTimestamp("post_date")));
        }
        statement.close();
        return reviews;
    }

    public static void addReview(Review review) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "INSERT INTO reviews(review, item_id, user_id, post_date) VALUES(?,?,?,NOW())");
        statement.setString(1, review.getReview());
        statement.setLong(2, review.getItem().getId());
        statement.setLong(3, review.getUser().getId());
        statement.executeUpdate();
        statement.close();
    }

    public static void deleteReview(Long id, Long userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "DELETE FROM reviews WHERE id = ? AND user_id = ?");
        statement.setLong(1, id);
        statement.setLong(2, userId);
        statement.executeUpdate();
        statement.close();
    }

    public static int toLike(Long itemId, Long userId){

        boolean liked = false;
        int likeAmount = 0;

        try{

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM item_likes WHERE user_id = ? AND item_id = ?"
            );

            statement.setLong(1, userId);
            statement.setLong(2, itemId);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                liked = true;
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        try{

            String query = "DELETE FROM item_likes WHERE user_id = ? AND item_id = ?";

            if(!liked){
                query = "INSERT INTO item_likes (user_id, item_id) VALUES (?,?)";
            }

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, userId);
            statement.setLong(2, itemId);
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT COUNT(*) like_amount FROM item_likes WHERE item_id = ?");

            statement.setLong(1, itemId);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                likeAmount = resultSet.getInt("like_amount");
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE items SET likes = ? WHERE id = ?");

            statement.setInt(1, likeAmount);
            statement.setLong(2, itemId);
            statement.executeUpdate();

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return likeAmount;

    }
}
