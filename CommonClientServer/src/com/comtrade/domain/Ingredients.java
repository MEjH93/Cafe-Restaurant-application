package com.comtrade.domain;


import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ingredients implements Serializable, CommonDomain {

    private int id_ingredient;
    private String ingredient_name;
    private double price;
    private String quantity_measure;
    private double quantity;
    private int idRestaurant;
    private ExistingIngredients existingIngredients = new ExistingIngredients();


    public Ingredients(int id_ingredient, String ingredient_name, String quantity_measure, int idRestaurant) {
        this.id_ingredient = id_ingredient;
        this.ingredient_name = ingredient_name;
        this.quantity_measure = quantity_measure;
        this.idRestaurant = idRestaurant;
    }




    @Override
	public String toString() {
		return "[" + ingredient_name + " where price is "
				+ price + " and we have " + quantity + " of them ]";
	}




	public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Ingredients(int id_ingredient, String ingredient_name, String quantity_measure, double quantity) {
        this.id_ingredient = id_ingredient;
        this.ingredient_name = ingredient_name;
        this.quantity_measure = quantity_measure;
        this.quantity = quantity;
    }

    public Ingredients(String ingredient_name, double price, String quantity_measure, double quantity) {
        this.ingredient_name = ingredient_name;
        this.price = price;
        this.quantity_measure = quantity_measure;
        this.quantity = quantity;
    }

    public Ingredients(int id_ingredient, String ingredient_name, double price, String quantity_measure) {
        this.id_ingredient = id_ingredient;
        this.ingredient_name = ingredient_name;
        this.price = price;
        this.quantity_measure = quantity_measure;
    }

    public Ingredients(int id_ingredient, String ingredient_name, double price, String quantity_measure, double quantity) {
        this.id_ingredient = id_ingredient;
        this.ingredient_name = ingredient_name;
        this.price = price;
        this.quantity_measure = quantity_measure;
        this.quantity = quantity;
    }

    public Ingredients(int id_ingredient, String ingredient_name, double price) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredients that = (Ingredients) o;
        return Objects.equals(ingredient_name, that.ingredient_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient_name);
    }

    public Ingredients() {
    }

    public Ingredients(String ingredient_name, double quantity) {
        this.ingredient_name = ingredient_name;
        this.quantity = quantity;

    }

    public Ingredients(int ingredient_id, double quantity) {
        this.id_ingredient = ingredient_id;
        this.quantity = quantity;
    }

    public Ingredients(String name, String measurement, double quantity2, double price2) {
    	 this.ingredient_name = name;
         this.price = price2;
         this.quantity_measure = measurement;
         this.quantity = quantity2;
		
	}














	public Ingredients(String name, Double price2, String measurement, Double quantity2, int idIngredient) {
		this.ingredient_name = name;
		this.price = price2;
		this.quantity_measure = measurement;
		this.quantity = quantity2;
		this.id_ingredient = idIngredient;
	}




	public Ingredients(int id_ingredient2, double getprice, int i, double quantity2, String quantity_measure2,
			String ingredient_name2) {
		this.id_ingredient = id_ingredient2;
		this.price = getprice;
		this.idRestaurant = i;
		this.quantity = quantity2;
		this.quantity_measure = quantity_measure2;
		this.ingredient_name = ingredient_name2;
	}




	public Ingredients(double getprice, int i, double quantity2, String quantity_measure2, String ingredient_name2) {
		this.price = getprice;
		this.quantity = quantity2;
		this.quantity_measure = quantity_measure2;
		this.ingredient_name = ingredient_name2;
		this.idRestaurant = i;
	}




	public int getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(int id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }


    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public double getprice() {
        return price;
    }

    public void setprice(double price) {
        this.price = price;
    }


    public String getQuantity_measure() {
        return quantity_measure;
    }

    public void setQuantity_measure(String quantity_measure) {
        this.quantity_measure = quantity_measure;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String returnTableName() {
        return " ingredients ";
    }

    @Override
    public String saveReturnColums() {
        return " (ingredient_name, purchase_price, quantity_measure) ";
    }

    @Override
    public String saveReturnQuestionMarks() {
        return " (?,?,?) ";
    }

    @Override
    public PreparedStatement setInsertValues(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, ingredient_name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, quantity_measure);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }


    @Override
    public CommonDomain readObjects(ResultSet resultSet) {   
    	Ingredients ingredient = null;
        try {
            ingredient = new Ingredients  (resultSet.getInt("id_ingredient"), resultSet.getString("ingredient_name"),
                    resultSet.getDouble("purchase_price"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredient;
    }

    @Override
    public String returnIDQuestionMarks() {
        return " ? ";
    }

    @Override
    public String idColumnName() {
        return "id_ingredient";
    }

    @Override
    public String familiarColumns() {
        return " ingredient_name ";
    }

    @Override
    public PreparedStatement returnIDPreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, ingredient_name);
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