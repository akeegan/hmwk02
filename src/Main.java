import com.mysql.jdbc.Statement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


/*
Andrea Koch, CSC 346, Spring 2018, Homework02
 */


public class Main {
    static Connection conn;
    static java.sql.Statement stmt;
    static ResultSet rs;
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        String enterZip;
        int enterMiles;

        System.out.println("Please input a zipcode that you are located: ");
        enterZip = keyboard.nextLine();

        System.out.println("Please enter miles: ");
        enterMiles = keyboard.nextInt();

        String host = "jdbc:mysql://turing.cs.missouriwestern.edu:3306/misc";
        String user = "csc254";
        String password = "age126";
        String queryString = "SELECT city, state, zipcode, estimatedpopulation, country, `long`, lat FROM zips2 WHERE locationtype LIKE 'PRIMARY' AND zipcodetype LIKE 'STANDARD' LIMIT 100";
        ArrayList<Place> places = new ArrayList<Place>();//to hold all the places read in from database

        try {
            conn = DriverManager.getConnection(host,user, password);

            if(conn == null)
                System.out.println("Connection failed");
            else
                System.out.println("Connection successfull!");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);

            ResultSetMetaData rsMetaData = rs.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();

            System.out.println("Number of columns: " +numberOfColumns);
            for(int i=1; i<=numberOfColumns;i++){
                System.out.printf("Column %2d: %s (%s)\n",i, rsMetaData.getColumnName(i),rsMetaData.getColumnTypeName(i));
            }
            int count =0;
            while(rs.next()){
                //returns null when hits the end
                //put in array list, then do a contains  -> in Place
                //to add up populations do equals method if both state and city ==, then add pops
                String state = rs.getString("state");
                String city = rs.getString("city");
                String zipcode = rs.getString("zipcode");
                int estpop = rs.getInt("estimatedpopulation");
                double lat = rs.getDouble("lat");
                double lon = rs.getDouble("long");
                Place place = new Place(city, state , zipcode, estpop, lat, lon);
                places.add(place);//adding object to arraylist
               // System.out.println(count+" "+place);
                count++;

            }

            for(int i=0;i<=places.size()-1;i++){

                System.out.println(i+" "+places.get(i).toString());
            }

            conn.close();
        } catch (SQLException e) {
           // e.printStackTrace();
            System.err.println("Oops. Failed to connect to "+host);
            System.err.println(e.getMessage());
            System.exit(1);

        }

    }
}
