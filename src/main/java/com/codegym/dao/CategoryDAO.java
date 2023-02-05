package com.codegym.dao;

import com.codegym.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {
    // người dùng thao tác với dữ liệu (Tạo mới, đọc, cập nhật, xoá).
    private String jdbcURL = "jdbc:mysql://localhost:3306/qlspJDBC?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    // các câu lệnh truy vấn SQL:
    private static final String INSERT_CATEGORY_SQL = "INSERT INTO category (name) VALUES (?);";
    private static final String SELECT_CATEGORY_ID = "select id,name from category where id =?";
    private static final String SELECT_ALL_CATEGORY = "select * from category";
    private static final String DELETE_CATEGORY_SQL = "delete from category where id = ?;";
    private static final String UPDATE_CATEGORY_SQL = "update category set name = ? where id = ?;";


    public CategoryDAO() {
    }

    // Interface Connection Cung cấp các phương thức để kết nối tới CSDL
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Category> selectAllCategory() {
        List<Category> listCategory = new ArrayList<>();

        // tạo kết nối với db
        Connection connection = getConnection();

        // Tao Cau Lenh
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_ALL_CATEGORY);

            // Thuc Hien Truy Van
            ResultSet resultSet = preparedStatement.executeQuery();

            // Xu Ly Doi tuong resultSet
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                listCategory.add(new Category(id, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCategory;
    }
    // ket thuc method selectAllCategory() tra ve 1 list<Category>


    @Override
    public void insertCategory(Category category) {

        Connection connection = getConnection(); // tạo kết nối
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(INSERT_CATEGORY_SQL);// Tạo Câu Lệnh
            preparedStatement.setString(1, category.getName());// setString thiet lap gtri cho tham so
            preparedStatement.executeUpdate();// thực hiện câu lệnh cập nhậ

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category selectCategory(int id) {
        Category category = null;
        // tao ket noi voi DB
        Connection connection=getConnection();

        // Tao cau lenh SQL
        try {
            PreparedStatement preparedStatement=
                    connection.prepareStatement(SELECT_CATEGORY_ID);
            preparedStatement.setInt(1,id);

            //Thuc hien truy van hay update
            ResultSet resultSet=preparedStatement.executeQuery();

            // Xử lý đối tượng ResultSet.
            while (resultSet.next()){
                String name=resultSet.getString("name");
                category=new Category(id,name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean deleteCategory(int id) {
        boolean rowDeleted = false;
      Connection connection=getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(DELETE_CATEGORY_SQL);
            preparedStatement.setInt(1,id);

            rowDeleted= preparedStatement.executeUpdate()>0;// thuc hien truy van

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    @Override
    public boolean updateCategory(Category category) {
        boolean rowUpdated = false;
        // Tao ket noi DB
        Connection connection=getConnection();

        // Tao cau lenh SQL
        try {
            PreparedStatement preparedStatement=
                    connection.prepareStatement(UPDATE_CATEGORY_SQL);

            //  thiet lap gia tri cho tham so
            preparedStatement.setString(1,category.getName());
            preparedStatement.setInt(2,category.getId());

           rowUpdated= preparedStatement.executeUpdate()>0;// thuc hien truy van

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}
