import java.sql.*;
import java.util.*;

public class CC1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try { //Use a try/catch block to catch any database exceptions
            //Create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/classicmodels", "root", "admin");
        
        //Create a new SQL statement using the connection
        Statement stmt = con.createStatement();

        //Execute SQL statement to retrieve productnames of products from classic cars product line
        ResultSet rs = stmt.executeQuery("select * from products");

        //initialize arraylist to store records
        ArrayList<String[]> records = new ArrayList<>();
        
        while (rs.next()) {
            String[] cols = {rs.getString(1), rs.getString(2)};
            records.add(cols);
        }

        for (int i = 0; i < records.size(); i++)
                System.out.println("Enter: " + i + " " + records.get(i)[0] + " " + records.get(i)[1]);

        
    
        
} catch(Exception e) {
    System.out.println(e);
        }
    }
}
