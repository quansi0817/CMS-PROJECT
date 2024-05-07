package ca.sheridancollege.SichaoQuan.database;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ca.sheridancollege.SichaoQuan.beans.Customer;
import ca.sheridancollege.SichaoQuan.beans.Leads;

import ca.sheridancollege.SichaoQuan.beans.Users;

@Repository
public class DatabaseAccess {

	@Autowired
	@Lazy
	protected NamedParameterJdbcTemplate jdbc;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 

	// -------------------------------------leads----------------------------------------------
	// * GetLeadsList
	public List<Leads> getLeadsList() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "SELECT * FROM LEADS";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Leads>(Leads.class)); 
	}

	// * GetLeadById
	public Leads getleadsById(Long leadsId) {  //The purpose of the method is to query the database for the Leads record that matches the given leadsId, and return it as an object of type Leads.
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM Leads WHERE leadsId = :leadsId";
		namedParameters.addValue("leadsId", leadsId);
		return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<Leads>(Leads.class));
	}
	
	public List<Customer> getAllCustomers() {
	    String query = "SELECT * FROM CUSTOMER";
	    return jdbc.query(query, new BeanPropertyRowMapper<Customer>(Customer.class));
	}

	// * GetLeadByCustomerId
	public List<Leads> getLeadsListbyCustomerId(Long CustomerId) { 
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM Leads WHERE customerId = :CustomerId";
		namedParameters.addValue("CustomerId", CustomerId);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Leads>(Leads.class)); // BeanPropertyRowMapper
	}
	
	// * Insert
	public void insertLeads(Leads leads) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource(); 
		String query = "INSERT INTO Leads (customerId, projectName, products, unitPrice, quantity,currentStage,currentStageId) VALUES (:customerId, :projectName, :products, :unitPrice, :quantity, :currentStage,:currentStageId)";
		namedParameters.addValue("customerId", leads.getCustomerId()); // 
		namedParameters.addValue("projectName", leads.getProjectName());
		namedParameters.addValue("products", leads.getProducts());
		namedParameters.addValue("unitPrice", leads.getUnitPrice());
		namedParameters.addValue("quantity", leads.getQuantity());
		namedParameters.addValue("currentStage", leads.getCurrentStage());
		namedParameters.addValue("currentStageId", leads.getCurrentStageId());

		int rowsAffected = jdbc.update(query, namedParameters);// 

		if (rowsAffected > 0) {
			System.out.println("Leads Created");
		}
	}

	// * Delete
	public void deleteLeads(Long leadsId) { 
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM LEADS WHERE leadsId=:leadsId";
		namedParameters.addValue("leadsId", leadsId);
		jdbc.update(query, namedParameters);
	}

	// * Edit
	public void editLeadsById(Leads leads, Long leadsId) { 
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "UPDATE LEADS SET customerId = :customerId, projectName = :projectName, products = :products, unitPrice = :unitPrice, quantity = :quantity, currentStage = :currentStage, currentStageId = :currentStageId WHERE leadsId = :leadsId";

		namedParameters.addValue("customerId", leads.getCustomerId()); 
		namedParameters.addValue("projectName", leads.getProjectName());
		namedParameters.addValue("products", leads.getProducts());
		namedParameters.addValue("unitPrice", leads.getUnitPrice());
		namedParameters.addValue("quantity", leads.getQuantity());
		namedParameters.addValue("currentStage", leads.getCurrentStage());
		namedParameters.addValue("currentStageId", leads.getCurrentStageId());
		namedParameters.addValue("leadsId", leadsId); 
		int rowsAffected = jdbc.update(query, namedParameters);

		if (rowsAffected > 0) {
			System.out.println("Updated Leads with ID " + leads.getLeadsId() + " in the database.");
		}
	}
	
	public List<Leads> getLeadsListByUserId(Long userId) {
	    MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	    
	    String query = "SELECT * FROM Leads l JOIN Customer c ON l.customerId = c.customerId WHERE c.userId = :userId";
	    namedParameters.addValue("userId", userId); 
	    return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Leads>(Leads.class));
	}
	// -------------------------------------customers----------------------------------------------

	// // * GetCustomerList
	public List<Customer> getCustomerList() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM CUSTOMER";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Customer>(Customer.class));
	}
	public List<Customer> getCustomerListByUserId(Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM Customer WHERE userId = :userId";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Customer>(Customer.class)); 
	}
	// * GetCustomerById
	public Customer getCustomerById(Long customerId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM Customer WHERE customerId = :customerId";
		namedParameters.addValue("customerId", customerId);
		return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<Customer>(Customer.class));
	}

	// * Insert
	public void insertCustomer(Customer customer) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource(); 
		String query = "INSERT INTO Customer (name, phone, email) VALUES (:name, :phone, :email)";
		namedParameters.addValue("name", customer.getName()); 
		namedParameters.addValue("phone", customer.getPhone());
		namedParameters.addValue("email", customer.getEmail());
		

		int rowsAffected = jdbc.update(query, namedParameters);

		if (rowsAffected > 0) {
			System.out.println("Customer Created");
		}
	}

	// * Delete
	public void deleteCustomer(Long customerId) { 
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM CUSTOMER WHERE customerId=:customerId";
		namedParameters.addValue("customerId", customerId);
		jdbc.update(query, namedParameters);
	}

	// * Edit
	public void editCustomerById(Customer customer, Long customerId) { 
															// edit
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "UPDATE CUSTOMER SET name = :name, phone = :phone, email = :email";

		namedParameters.addValue("name", customer.getName()); 
		namedParameters.addValue("phone", customer.getPhone());
		namedParameters.addValue("email", customer.getEmail());
		
		int rowsAffected = jdbc.update(query, namedParameters);

		if (rowsAffected > 0) {
			System.out.println("Updated Customer with ID " + customer.getCustomerId() + " in the database.");
		}
	}

	// user

	public Users findUserAccount(String email) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM sec_user where email = :email";
		namedParameters.addValue("email", email);
		try {
			return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(Users.class));
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	// Method to get User Roles for a specific User id
	public List<String> getRolesById(Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT sec_role.roleName " + "FROM user_role, sec_role "
				+ "WHERE user_role.roleId = sec_role.roleId " + "AND userId = :userId";
		namedParameters.addValue("userId", userId);
		return jdbc.queryForList(query, namedParameters, String.class);
	}

	public void addUser(String email, String password) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO sec_user " + "(email, encryptedPassword, enabled) "
				+ "VALUES (:email, :encryptedPassword, 1)";
		namedParameters.addValue("email", email);
		namedParameters.addValue("encryptedPassword", passwordEncoder.encode(password));
		jdbc.update(query, namedParameters);
	}

	public void addRole(Long userId, Long roleId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO user_role (userId, roleId) " + "VALUES (:userId, :roleId)";
		namedParameters.addValue("userId", userId);
		namedParameters.addValue("roleId", roleId);
		jdbc.update(query, namedParameters);
	}



	    public int getTotalLeadsByUserId(Long userId) {
	        String sql = "SELECT COUNT(*) FROM LEADS JOIN CUSTOMER ON LEADS.customerId = CUSTOMER.customerId WHERE CUSTOMER.userId = :userId";
	        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	        namedParameters.addValue("userId", userId);
	        return jdbc.queryForObject(sql, namedParameters, Integer.class);
	    }

	    public BigDecimal getTotalAmountWonByUserId(Long userId) {
	        String sql = "SELECT SUM(LEADS.unitPrice * LEADS.quantity) AS totalAmount FROM LEADS JOIN CUSTOMER ON LEADS.customerId = CUSTOMER.customerId WHERE CUSTOMER.userId = :userId AND LEADS.currentStage = 'Won'";
	        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	        namedParameters.addValue("userId", userId);
	        return jdbc.queryForObject(sql, namedParameters, BigDecimal.class);
	    }

	    public BigDecimal getTotalAmountOngoingByUserId(Long userId) {
	        String sql = "SELECT SUM(LEADS.unitPrice * LEADS.quantity) AS totalAmount FROM LEADS JOIN CUSTOMER ON LEADS.customerId = CUSTOMER.customerId WHERE CUSTOMER.userId = :userId AND LEADS.currentStageId IN (1, 2)";
	        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	        namedParameters.addValue("userId", userId);
	        return jdbc.queryForObject(sql, namedParameters, BigDecimal.class);
	    }
	}
