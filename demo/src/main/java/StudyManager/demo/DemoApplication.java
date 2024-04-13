package StudyManager.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		String url = "jdbc:mysql://localhost:3306:jdbc";
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection();
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

}
