package com.comtrade.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable,CommonDomain {
	
	private int id_user;
	private String first_name;
	private String username;
	private String password;
	private int status;
	private Set<Role> setRoleUser = new HashSet<>();
	private int idRoleUser;
	private String companyName;
	private int idCompany;
	private String roleName;
	
	
	public User(String username, String first_name, int idRoleUser, String companyName, int idCompany, String roleName) {
		super();
		this.username = username;
		this.first_name = first_name;
		this.idRoleUser = idRoleUser;
		this.companyName = companyName;
		this.idCompany = idCompany;
		this.roleName = roleName;
	}
	public User(String first_name, int idRoleUser, String roleName, int IdCompany, String companyName) {
		super();
		this.first_name = first_name;
		this.idRoleUser = idRoleUser;
		this.roleName = roleName;
		this.idCompany = idCompany;
		this.companyName = companyName;
	

		
		
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	public User(int id_user, String first_name, String username, String password, int status, Set<Role> setRoleUser,
			int idRoleUser, String companyName, int idCompany, String roleName) {
		super();
		this.id_user = id_user;
		this.first_name = first_name;
		this.username = username;
		this.password = password;
		this.status = status;
		this.setRoleUser = setRoleUser;
		this.idRoleUser = idRoleUser;
		this.companyName = companyName;
		this.idCompany = idCompany;
		this.roleName = roleName;
	}
	public User(int id_user, String first_name, String username, String password, int status, Set<Role> setRoleUser,
			int idRoleUser) {
		super();
		this.id_user = id_user;
		this.first_name = first_name;
		this.username = username;
		this.password = password;
		this.status = status;
		this.setRoleUser = setRoleUser;
		this.idRoleUser = idRoleUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getIdRoleUser() {
		return idRoleUser;
	}
	public void setIdRoleUser(int idRoleUser) {
		this.idRoleUser = idRoleUser;
	}
	public Set<Role> getSetRoleUser() {
		return setRoleUser;
	}
	public void setSetRoleUser(Set<Role> setRoleUser) {
		this.setRoleUser = setRoleUser;
	}
	public User() {
		
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	 @Override
	public String toString() {
		return "User [id_user=" + id_user + ", first_name=" + first_name + ", username=" + username + ", password="
				+ password + ", status=" + status + ", setRoleUser=" + setRoleUser + "]";
	}
	public User(int id_user, String first_name, String username, String password, int status, Set<Role> setRoleUser) {
		super();
		this.id_user = id_user;
		this.first_name = first_name;
		this.username = username;
		this.password = password;
		this.status = status;
		this.setRoleUser = setRoleUser;
	}
	
	public User(String firstName, String userName2, String password2, String role, int restaurantNumber) {
		this.first_name = firstName;
		this.username = userName2;
		this.password = password2;
		this.roleName = role;
		this.idCompany = restaurantNumber;
	}
	@Override
	    public String returnTableName() {
	        return " user ";
	    }

	    @Override
	    public String saveReturnColums() {
	        return " (username, password, first_name, status) ";
	    }

	    @Override
	    public String saveReturnQuestionMark() {
	        return " (?,?,?,?) ";
	    }

	    @Override
	    public PreparedStatement setInsertValues(PreparedStatement preparedStatement) {
	        try {
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, password);
	            preparedStatement.setString(3, first_name);
	            preparedStatement.setInt(4, status);
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
	    public String returnIDQuestionMark() {
	        return null;
	    }

	    @Override
	    public String idColumnName() {
	        return null;
	    }

	    @Override
	    public String commonColumns() {
	        return null;
	    }

	    @Override
	    public PreparedStatement returnIDPreparedStatement(PreparedStatement preparedStatement) {
	        return null;
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
		public String familiarColumns() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String returnIDQuestionMarks() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String saveReturnQuestionMarks() {
			// TODO Auto-generated method stub
			return null;
		}
	    
	    
	    
	    
	}