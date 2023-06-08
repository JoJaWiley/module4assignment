import java.sql.*;
import java.util.*;

public class ClassicCars {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try { //Use a try/catch block to catch any database exceptions
            //Create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/classicmodels", "root", "admin");
        
        //Create a new SQL statement using the connection
        Statement stmt = con.createStatement();

        //Execute SQL statement to retrieve productnames of products from classic cars product line
        ResultSet rs = stmt.executeQuery("select productname from products where productline = 'Classic Cars';");

        //display the resulting product names
        while(rs.next()) {
            System.out.println(rs.getString(1));
        }

        //initialize the scanner
        Scanner sc = new Scanner(System.in);

        //prompt the user to choose a product line to view inventory
        System.out.println("Please choose a product line to view our inventory!");
        System.out.println("Choose: Classic Cars [enter 1] Ships [enter 2] or Motorcycles [enter 3]");

        int productLine = sc.nextInt();

        switch (productLine) {
        case 1 -> rs = stmt.executeQuery("select productname from products where productline = 'Classic Cars';");
        case 2 -> rs = stmt.executeQuery("select productname from products where productline = 'Ships';"); 
        case 3 -> rs = stmt.executeQuery("select productname from products where productline = 'Motorcycles';");
        default -> System.out.println("You did not enter a valid choice.");
        }

        //display the resulting product names
        while(rs.next()) {
            System.out.println(rs.getString(1));
        }

        System.out.println("---------------------------------------------------");

       // rs = stmt.executeQuery("select productname from products where productname = 'USS Monitor'");
       // if (!rs.next()) {
            // insert USS monitor product into products
         //   stmt.executeUpdate("INSERT INTO PRODUCTS \n" +
           //         "(productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP)\n"
             //       +
               //     "VALUES \n" +
                 //   "(\"S72_3213\",\"USS Monitor\", \"Ships\", \"1:72\", \"Second Gear Diecast\", \"Ironclad warship with her steam engine and revolving turret\", 780, 35.00, 55.00);\n");

         //   rs = stmt.executeQuery("select productname, productscale from products where productname = 'USS Monitor'");

           // while (rs.next()) {
             //   System.out.println(rs.getString(1) + " " + rs.getString(2));
           // }
       // }

        System.out.println("---------------------------------------------------");

        //insert USS monitor product into products
        stmt.executeUpdate("INSERT INTO PRODUCTS \n" +
        "(productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP)\n" +
        "VALUES \n" +
        "(\"S72_3213\",\"USS Monitor\", \"Ships\", \"1:72\", \"Second Gear Diecast\", \"Ironclad warship with her steam engine and revolving turret\", 780, 35.00, 55.00);\n");
       
        rs = stmt.executeQuery("select productname, productscale from products where productname = 'USS Monitor'");

       while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2));
        }

        System.out.println("---------------------------------------------------");

        //update the scale to 1:10
        stmt.executeUpdate("update products set productscale = '1:10' where productname = 'USS Monitor';");

        //query to retreive the updated product
        rs = stmt.executeQuery("select productname, productscale from products where productname = 'USS Monitor'");

        //display the result
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2));
        }

        System.out.println("---------------------------------------------------");

        //Delete the USS Monitor record from the database
        stmt.executeUpdate("delete from products where productname = 'USS Monitor';");

        //query to retrieve productname from products
        rs = stmt.executeQuery("select productname from products;");

        //display the resulting product names
        while(rs.next()) {
            System.out.println(rs.getString(1));
        }

        con.close();
        sc.close();
    
    } catch(Exception e) {
        System.out.println(e);
    }
}
}
