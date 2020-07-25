package com.comtrade.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Offers implements Serializable, CommonDomain {

    private int id_restaurant_menu;
    private String restaurant_menu_name;
    private double restaurant_menu_price;
    private String restaurant_menu_type;
    private Ingredients ingredients = new Ingredients();
    private List<Ingredients> menuIngredientsList = new ArrayList<>();

    public Offers(int id_restaurant_menu, String restaurant_menu_name, double restaurant_menu_price, String restaurant_menu_type, List<Ingredients> menuIngredientsList) {
        this.id_restaurant_menu = id_restaurant_menu;
        this.restaurant_menu_name = restaurant_menu_name;
        this.restaurant_menu_price = restaurant_menu_price;
        this.restaurant_menu_type = restaurant_menu_type;
        this.menuIngredientsList = menuIngredientsList;
    }

    public Offers(int id_restaurant_menu, String restaurant_menu_name) {
        this.id_restaurant_menu = id_restaurant_menu;
        this.restaurant_menu_name = restaurant_menu_name;
    }

    public Offers(String restaurant_menu_name) {
        this.restaurant_menu_name = restaurant_menu_name;
    }

    public Offers(int id_restaurant_menu) {
        this.id_restaurant_menu = id_restaurant_menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offers offer = (Offers) o;
        return id_restaurant_menu == offer.id_restaurant_menu &&
                Double.compare(offer.restaurant_menu_price, restaurant_menu_price) == 0 &&
                Objects.equals(restaurant_menu_name, offer.restaurant_menu_name) &&
                Objects.equals(restaurant_menu_type, offer.restaurant_menu_type) &&
                Objects.equals(ingredients, offer.ingredients) &&
                Objects.equals(menuIngredientsList, offer.menuIngredientsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_restaurant_menu, restaurant_menu_name, restaurant_menu_price, restaurant_menu_type, ingredients, menuIngredientsList);
    }

    public Offers(String restaurant_menu_name, double restaurant_menu_price, String restaurant_menu_type, Ingredients ingredients) {
        this.restaurant_menu_name = restaurant_menu_name;
        this.restaurant_menu_price = restaurant_menu_price;
        this.restaurant_menu_type = restaurant_menu_type;
        this.ingredients = ingredients;
    }

    public Offers(int id_restaurant_menu, String restaurant_menu_name, double restaurant_menu_price, String restaurant_menu_type) {
        this.id_restaurant_menu = id_restaurant_menu;
        this.restaurant_menu_name = restaurant_menu_name;
        this.restaurant_menu_price = restaurant_menu_price;
        this.restaurant_menu_type = restaurant_menu_type;
    }

    public Offers(String restaurant_menu_name, double restaurant_menu_price, String restaurant_menu_type) {
        this.restaurant_menu_name = restaurant_menu_name;
        this.restaurant_menu_price = restaurant_menu_price;
        this.restaurant_menu_type = restaurant_menu_type;
    }

    public Offers(int id_restaurant_menu, String restaurant_menu_name, double restaurant_menu_price, String restaurant_menu_type, Ingredients ingredients) {
        this.id_restaurant_menu = id_restaurant_menu;
        this.restaurant_menu_name = restaurant_menu_name;
        this.restaurant_menu_price = restaurant_menu_price;
        this.restaurant_menu_type = restaurant_menu_type;
        this.ingredients = ingredients;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public String getRestaurant_menu_type() {
        return restaurant_menu_type;
    }

    public void setRestaurant_menu_type(String restaurant_menu_type) {
        this.restaurant_menu_type = restaurant_menu_type;
    }

    public Offers(String restaurant_menu_name, double restaurant_menu_price, String restaurant_menu_type, List<Ingredients> menuIngredientsList) {
        this.restaurant_menu_name = restaurant_menu_name;
        this.restaurant_menu_price = restaurant_menu_price;
        this.restaurant_menu_type = restaurant_menu_type;
        this.menuIngredientsList = menuIngredientsList;
    }

    public List<Ingredients> getMenuIngredientsList() {
        return menuIngredientsList;
    }

    public void setMenuIngredientsList(List<Ingredients> menuIngredientsList) {
        this.menuIngredientsList = menuIngredientsList;
    }

    public Offers(int id_restaurant_menu, String restaurant_menu_name, double restaurant_menu_price) {
        this.id_restaurant_menu = id_restaurant_menu;
        this.restaurant_menu_name = restaurant_menu_name;
        this.restaurant_menu_price = restaurant_menu_price;

    }

    public Offers() {

    }


    public Offers(String restaurant_menu_name, double restaurant_menu_price) {
        this.restaurant_menu_name = restaurant_menu_name;
        this.restaurant_menu_price = restaurant_menu_price;
    }


    public int getId_restaurant_menu() {
        return id_restaurant_menu;
    }

    public void setId_restaurant_menu(int id_restaurant_menu) {
        this.id_restaurant_menu = id_restaurant_menu;
    }

    public String getRestaurant_menu_name() {
        return restaurant_menu_name;
    }

    public void setRestaurant_menu_name(String restaurant_menu_name) {
        this.restaurant_menu_name = restaurant_menu_name;
    }

    public double getRestaurant_menu_price() {
        return restaurant_menu_price;
    }

    public void setRestaurant_menu_price(double restaurant_menu_price) {
        this.restaurant_menu_price = restaurant_menu_price;
    }

    @Override
    public String returnTableName() {
        return " restaurant_offer ";
    }

    @Override
    public String saveReturnColums() {
        return " (restaurant_offer_name, restaurant_offer_price, offer_type) ";
    }

    @Override
    public String saveReturnQuestionMarks() {
        return " (?,?,?) ";
    }

    @Override
    public PreparedStatement setInsertValues(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, restaurant_menu_name);
            preparedStatement.setDouble(2, restaurant_menu_price);
            preparedStatement.setString(3, restaurant_menu_type);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public CommonDomain readObjects(ResultSet resultSet) {
        Offers offers = null;
        try {
            offers = new Offers(resultSet.getInt("id_restaurant_offer"),
                    resultSet.getString("restaurant_offer_name"),resultSet.getDouble("restaurant_offer_price"),
                    resultSet.getString("offer_type"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offers;
    }

    @Override
    public String returnIDQuestionMarks() {
        return null;
    }

    @Override
    public String idColumnName() {
        return " id_restaurant_offer ";
    }

    @Override
    public String familiarColumns() {
        return " restaurant_offer_name ";
    }

    @Override
    public PreparedStatement returnIDPreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, restaurant_menu_name);

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
        return " ? ";
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