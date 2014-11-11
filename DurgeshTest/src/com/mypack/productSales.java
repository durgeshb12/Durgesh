package com.mypack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class productSales {

	private JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private String productName;
	private String totalProdSold;
	private String totalAmount;
	
	
	
	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getTotalProdSold() {
		return totalProdSold;
	}


	public void setTotalProdSold(String totalProdSold) {
		this.totalProdSold = totalProdSold;
	}


	public String getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	String sqlQuery="SELECT p.productName,COUNT(s.productId) AS totalSold,SUM(s.saleAmount) AS totalAmount "
			+ "FROM product p JOIN saleEntry s ON p.productId=s.productId GROUP BY p.productId";
	
	
	
	public List<productSales> getProductDetails(){
		
		return jdbcTemplate.query(sqlQuery,new RowMapper<productSales>(){  
		    @Override  
		    public productSales mapRow(ResultSet rs, int rownumber) throws SQLException {  
		    	productSales pSale=new productSales();
				
				pSale.setProductName(rs.getString("productName"));
				pSale.setTotalProdSold(rs.getString("totalSold"));
				pSale.setTotalAmount(rs.getString("totalAmount"));
				
				return pSale;
		    }  
		    });  
		}  

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		productSales pSale=new productSales();
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		pSale=(productSales)context.getBean("test");
		
		 List<productSales> list=pSale.getProductDetails();
		
		 System.out.println("Product Name----------Total Product Sold--------Total Sale Amount");
		 for(productSales ps:list) {
			 
			 System.out.print(ps.getProductName() +"      ");
			 System.out.print(ps.getTotalProdSold() +"      ");
			 System.out.print(ps.getTotalAmount() +"      ");
			 
			 System.out.println();
		 }
		        
		 
		
		
	}
	
	
	
	
	}

