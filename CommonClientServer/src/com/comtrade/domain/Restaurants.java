package com.comtrade.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Restaurants implements CommonDomain, Serializable {

    private int id_restaurant;
    private String name_restaurant;
    private String restaurantImage;

    public Restaurants(){

    }


    public Restaurants(int id_restaurant, String name_restaurant,String restaurantImage) {
        this.id_restaurant = id_restaurant;
        this.name_restaurant = name_restaurant;
        this.restaurantImage = restaurantImage;
    }

    public Restaurants(String name_restaurant,String restaurantImage) {
        this.name_restaurant = name_restaurant;
        this.restaurantImage = restaurantImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurants that = (Restaurants) o;
        return id_restaurant == that.id_restaurant &&
                Objects.equals(name_restaurant, that.name_restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_restaurant, name_restaurant);
    }

    public int getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public String getName_restaurant() {
        return name_restaurant;
    }

    public void setName_restaurant(String name_restaurant) {
        this.name_restaurant = name_restaurant;
    }


    @Override
    public String returnTableName() {
        return " restaurants ";
    }

    @Override
    public String saveReturnColums() {
        return " (name_restaurant, street, number, city, imageRestaurant) ";
    }

    @Override
    public String saveReturnQuestionMarks() {
        return " (?,?,?,?,?) ";
    }

    @Override
    public PreparedStatement setInsertValues(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, name_restaurant);
            preparedStatement.setString(2, restaurantImage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public Object readObjects(ResultSet resultSet) {
        return null;
    }

    @Override
    public String returnIDQuestionMarks() {
        return " ? ";
    }

    @Override
    public String idColumnName() {
        return "id_restaurant";
    }

    @Override
    public String familiarColumns() {
        return " name_restaurant ";
    }

    @Override
    public PreparedStatement returnIDPreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, name_restaurant);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String returnColumnforDelete() {
        return null;
    }

    @Override
    public String return1QuestionMark() {
        return null;
    }

    @Override
    public PreparedStatement returnDeletePreparedStatement(PreparedStatement preparedStatement) {
        return null;
    }

	@Override
	public String saveReturnQuestionMark() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String returnIDQuestionMark() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String commonColumns() {
		// TODO Auto-generated method stub
		return null;
	}
}