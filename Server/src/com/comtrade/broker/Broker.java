package com.comtrade.broker;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;

import com.comtrade.SQLConnection.SQLConnection;
import com.comtrade.domain.Bill;
import com.comtrade.domain.CommonDomain;
import com.comtrade.domain.Ingredients;
import com.comtrade.domain.RestaurantTable;
import com.comtrade.domain.Role;
import com.comtrade.domain.User;

public class Broker {
    public void save(CommonDomain commonDomain) throws SQLException {

        String sql = "insert into " + commonDomain.returnTableName()
                + commonDomain.saveReturnColums() + " values " + commonDomain.saveReturnQuestionMark();
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement = commonDomain.setInsertValues(preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SQLException("Current thing wasn't saved, table: " + commonDomain.returnTableName() + " " + e.getMessage());
        }
    }

    public User returnRoleUser(User user) {
        String sql = "SELECT u.id_user, u.username, u.password, u.first_name as firstName, r.role_name AS role FROM user AS u " +
                "INNER JOIN user_role_map AS ur ON ur.id_user = u.id_user " +
                "INNER JOIN role AS r ON r.id_role = ur.id_role WHERE u.username = ? AND u.password = ?";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<Role> role = new HashSet<>();
            while (resultSet.next()) {
                Role userRole = new Role();
                userRole.setRole_name(resultSet.getString("role"));
                role.add(userRole);

            }
            if (resultSet.first()) {
				/*
				 * preparedStatement("select typeid from users where username=? and password=?",
				 * ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				 */
                int id_user = resultSet.getInt("id_user");
                String userFirstName = resultSet.getString("firstName");
                user.setId_user(id_user);
                user.setFirst_name(userFirstName);
                user.setSetRoleUser(role);
                return user;
            }

        } catch (SQLException e) {
           
            e.printStackTrace();
        }
        return null;

    }
    
    public void saveUser(User user) {
        int idUser = 0;
        ResultSet resultSet = null;
        String sql = "INSERT INTO user (first_name, username, password, status) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirst_name());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassword());
            if(user.getRoleName().equalsIgnoreCase("owner")) {
            	preparedStatement.setInt(4, 1);
            }else {
                preparedStatement.setInt(4, 0);
            }
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected != 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.first()) {
                    idUser = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
        }

        String sql1 = "INSERT INTO user_role_map(id_user, id_role) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql1);
            preparedStatement.setInt(1, idUser);
            //preparedStatement.setInt(2, user.getIdRoleUser());
            if(user.getRoleName().equalsIgnoreCase("owner")) {
            	 preparedStatement.setInt(2, 1);
            }else {
                preparedStatement.setInt(2, 3);
            }
        
            

            preparedStatement.execute();
        } catch (SQLException e) {
        }
        String sql2 = "INSERT INTO user_restaurant_map(id_restaurant, id_user) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql2);

            //preparedStatement.setInt(1, user.getIdCompany());
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, idUser);

            preparedStatement.execute();
        } catch (SQLException e) {
        }
    }
    public List<Role> getRolesBasedOnUser(int idOwner) {
    	
        List<Role>list = new ArrayList<>();
        Role userRole = null;
        String sql = "SELECT * FROM role WHERE role.id_role not in (?)";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idOwner);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                userRole = new Role(resultSet.getInt("id_role"), resultSet.getString("role_label"));
                list.add(userRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }
    public List<User> returnUsers(int idOwner) {

        List<User>list = new ArrayList<>();

        User user = null;
       // String sql = "SELECT first_name, role.id_role, role.role_name, restaurants.id_restaurant, restaurants.name_restaurant  " +
              //  "FROM user INNER JOIN user_restaurant_map on user.id_user = user_restaurant_map.id_user INNER JOIN restaurants on restaurants.id_restaurant = " +
              //  "user_restaurant_map.id_restaurant INNER JOIN user_role_map on user_role_map.id_user = user.id_user INNER JOIN role on role.id_role = " +
             //   "user_role_map.id_role where user.id_user not in (?)";
        
       String sql = "SELECT DISTINCT first_name as first_name,username as user_name, role.id_role as id_role, role.role_name as role_name, restaurants.id_restaurant as id_restaurant, restaurants.name_restaurant  as name_restaurant" +
        " FROM USER INNER JOIN user_restaurant_map ON USER.id_user = user_restaurant_map.id_user INNER JOIN restaurants ON restaurants.id_restaurant = user_restaurant_map.id_restaurant" +
        		" INNER JOIN user_role_map ON user_role_map.id_user = USER.id_user "+
        		" INNER JOIN role ON role.id_role = user_role_map.id_role";

        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	
                user = new User(resultSet.getString("first_name"),resultSet.getString("user_name"),
                		resultSet.getInt("id_role"), resultSet.getString("role_name"), resultSet.getInt("id_restaurant"),
                        resultSet.getString("name_restaurant"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;




    }

    public RestaurantTable returnTable(RestaurantTable restaurantTable) {
    	RestaurantTable restaurantTable1 = null;
        String sql = "SELECT * from restaurant_table WHERE table_number=? and id_restaurant=?";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, restaurantTable.getTable_number());
            preparedStatement.setInt(2, restaurantTable.getId_restaurant());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
            	restaurantTable1 = new RestaurantTable(resultSet.getInt("id_restaurant_table"), resultSet.getInt("table_number"),
                        resultSet.getInt("id_restaurant"),resultSet.getInt("table_status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantTable1;
    }
    
    public List<Ingredients> getIngredients(int id_ingredient) {
        List<Ingredients> listIngredients = new ArrayList<>();
        Ingredients ingredients = null;
        String sql = "select existing_ingredients.quantity, ingredients.id_ingredient, ingredients.price,"
        		+ " ingredients.ingredient_name, ingredients.measurement_quantity from existing_ingredients\r\n" + 
        		"inner join\r\n" + 
        		"ingredients on existing_ingredients.id_ingredient = ingredients.id_ingredient";

        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            //preparedStatement.setInt(1, id_ingredient);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ingredients = new Ingredients(resultSet.getInt("id_ingredient"), resultSet.getString("ingredient_name"),
                        resultSet.getDouble("price"), resultSet.getString("measurement_quantity"), resultSet.getDouble("quantity"));
                listIngredients.add(ingredients);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listIngredients;
    }

	public void addIngredient(Ingredients ingredient) {
		 int idIngredient = 0;
	        ResultSet resultSet = null;
	        String sql = "INSERT INTO ingredients (price, ingredient_name, measurement_quantity) VALUES (?,?,?)";
	        try {
	            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	            preparedStatement.setDouble(1, ingredient.getprice());
	            preparedStatement.setString(2, ingredient.getIngredient_name());
	            preparedStatement.setString(3, ingredient.getQuantity_measure());
	            int rowAffected = preparedStatement.executeUpdate();
	            if (rowAffected != 0) {
	                resultSet = preparedStatement.getGeneratedKeys();
	                if (resultSet.first()) {
	                    idIngredient = resultSet.getInt(1);
	                }
	            }
	        } catch (SQLException e) {
	        }
	        String sql1 = "INSERT INTO existing_ingredients (id_ingredient,id_restaurant, quantity) VALUES (?,?,?)";
	        try {
	        	 int idExistingIngredient = 0;
	 	        ResultSet resultSet2 = null;
	            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);
	            preparedStatement.setInt(1, idIngredient);
	            preparedStatement.setInt(2, 1);
	            preparedStatement.setDouble(3, ingredient.getQuantity());
	            
	            int rowAffected = preparedStatement.executeUpdate();
	            if (rowAffected != 0) {
	                resultSet2 = preparedStatement.getGeneratedKeys();
	                if (resultSet2.first()) {
	                	idExistingIngredient = resultSet2.getInt(1);
	                }
	            }
	            

	           // preparedStatement.execute();
	        } catch (SQLException e) {
	        }
	}

	public void saveRestaurantTable(RestaurantTable restaurantTable) {
		int id_restaurant_table = 0;
        ResultSet resultSet = null;
        List<Integer> restaurantTable1 = new CopyOnWriteArrayList<Integer>();
        String sql = "INSERT INTO restaurant_table (table_number, id_restaurant, table_status) VALUES (?,?,?)";
        String sql1 = "select restaurant_table.table_number from restaurant_table";
        try {
        	PreparedStatement preparedStatement1 = SQLConnection.getInstance().getConnection().prepareStatement(sql1);
        	ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
            	
            	restaurantTable1.add(resultSet1.getInt("table_number"));
            }
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            if(!restaurantTable1.contains(restaurantTable.getTable_number())) {
            	  preparedStatement.setInt(1, restaurantTable.getTable_number());
                  preparedStatement.setInt(2, 1);
                  preparedStatement.setInt(3, 0);
                  int rowAffected = preparedStatement.executeUpdate();
                  if (rowAffected != 0) {
                      resultSet = preparedStatement.getGeneratedKeys();
                      if (resultSet.first()) {
                      	id_restaurant_table = resultSet.getInt(1);
                      }
                  }
            }else {
            	JOptionPane.showMessageDialog(null, "Table with this number already exists, please try a different one");
            }
            
        } catch (SQLException e) {
        }
		
	}

	public List<Integer> returnAllTableNumbers(RestaurantTable restaurantTable) {
		List<Integer> tablez  = new ArrayList<>();
        String sql = "select restaurant_table.table_number from restaurant_table";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            //preparedStatement.setInt(1, restaurantTable.getTable_number());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	
            	tablez.add(resultSet.getInt("table_number"));
            }
           
            if (resultSet.first()) {
            	restaurantTable = new RestaurantTable(resultSet.getInt("table_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tablez;
	}
	  public List<Ingredients> getIngredientsWithQuantityBiggerThanZero(int id_ingredient) {
	        List<Ingredients> listIngredients = new ArrayList<>();
	        Ingredients ingredients = null;
	        String sql = "select existing_ingredients.quantity, ingredients.id_ingredient, ingredients.price,"
	        		+ " ingredients.ingredient_name, ingredients.measurement_quantity from existing_ingredients\r\n" + 
	        		"inner join\r\n" + 
	        		"ingredients on existing_ingredients.id_ingredient = ingredients.id_ingredient WHERE existing_ingredients.quantity > 0";

	        try {
	            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            while (resultSet.next()) {
	                ingredients = new Ingredients(resultSet.getInt("id_ingredient"), resultSet.getString("ingredient_name"),
	                        resultSet.getDouble("price"), resultSet.getString("measurement_quantity"), resultSet.getDouble("quantity"));

	                listIngredients.add(ingredients);

	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return listIngredients;
	    }

	public Object updateIngredients(Ingredients ingredient) {
		String sql = "update ingredients inner join existing_ingredients on existing_ingredients.id_ingredient = ingredients.id_ingredient "
				+ " SET existing_ingredients.quantity = ?, ingredients.price = ?, ingredients.ingredient_name = ?, ingredients.measurement_quantity = ?, existing_ingredients.id_restaurant = ?  where ingredients.id_ingredient= ?";
		try {
			PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
			preparedStatement.setDouble(1,ingredient.getQuantity());
			preparedStatement.setDouble(2,ingredient.getprice());
			preparedStatement.setString(3,ingredient.getIngredient_name());
			preparedStatement.setString(4, ingredient.getQuantity_measure());
			preparedStatement.setInt(5,1);
			preparedStatement.setInt(6, ingredient.getId_ingredient());
			preparedStatement.execute();
		//	Ingredients ingredient1 = new Ingredients((preparedStatement.setDouble(1,ingredient.getQuantity())),preparedStatement.setDouble(2,ingredient.getprice()),preparedStatement.setString(3,ingredient.getIngredient_name()),preparedStatement.setString(4, ingredient.getQuantity_measure()));
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredient;
	}

	public void saveBill(Bill bill) throws SQLException {
	     int idBill = 0;
	        ResultSet resultSet = null;
	        bill.setOrderList("a");
	        String sql = "INSERT INTO bill (table_number, bill_time, id_restaurant, waiterName, finalAmount) VALUES (?,?,?,?,?)";
	        try {
	            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	            preparedStatement.setInt(1, bill.getid_restaurantTable());
	            preparedStatement.setString(2, (bill.getDateTime()).toString());
	            preparedStatement.setInt(3, bill.getIdRestaurant());
	            preparedStatement.setString(4, bill.getWaiterName());
	            preparedStatement.setDouble(5, bill.getFinalAmount());
	            int rowAffected = preparedStatement.executeUpdate();
	           if (rowAffected != 0) {
	               resultSet = preparedStatement.getGeneratedKeys();
	                if (resultSet.first()) {
	                 idBill = resultSet.getInt(1);
	                }
	            }
	        } catch (SQLException e) {
	        	System.out.println(e.getMessage());
	        	e.printStackTrace();
	        }
	        String sql1 = "INSERT INTO bill_orders_map (id_bill, id_order) VALUES (?,?)";
	        try {
	            PreparedStatement preparedStatement1 = SQLConnection.getInstance().getConnection().prepareStatement(sql1);
	            preparedStatement1.setInt(1, idBill);
	            preparedStatement1.setInt(2, 2);
	            preparedStatement1.execute();
		
	} finally {
		try {
			if(resultSet!=null) resultSet.close();
		}catch (SQLException em){
			System.out.println(em.getMessage());
			em.printStackTrace();
		}
		
	}
	}

	    public List<Bill> returnAllBills(int idBill) {

        List<Bill>list = new ArrayList<>();

        Bill bill = null;
       String sql = "Select * from bill";

        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	
            	bill = new Bill(resultSet.getInt("table_number"),resultSet.getString("bill_time"),
                		resultSet.getInt("id_restaurant"), resultSet.getString("waiterName"), resultSet.getDouble("finalAmount"));
                list.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;




    }
}
	
	
	
