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
    static java.sql.Statement stmt1;
    static ResultSet rs;
    static ResultSet rs1;

    public static void main(String[] args) {
        double longitude=-1000;
        double latitude=-1000;
        Scanner keyboard = new Scanner(System.in);

        String enterZip;
        double enterMiles;
        double milesToKm;

        System.out.println("Please input a zipcode that you are located: ");
        enterZip = keyboard.nextLine();

        System.out.println("Please enter miles: ");
        enterMiles = keyboard.nextInt();
        milesToKm = 1.609*enterMiles;

        String host = "jdbc:mysql://turing.cs.missouriwestern.edu:3306/misc";
        String user = "csc254";
        String password = "age126";
        String queryString1 = "SELECT city, state, `long`, lat FROM zips2 WHERE zipcode like '"+enterZip+"' LIMIT 5";
        String queryString = "SELECT city, state, zipcode, estimatedpopulation, country, `long`, lat FROM zips2 WHERE locationtype LIKE 'PRIMARY' AND zipcodetype LIKE 'STANDARD'";
        ArrayList<Place> listPlaces = new ArrayList<Place>();//to hold all the places read in from database

        try {
            conn = DriverManager.getConnection(host,user, password);

            if(conn == null)
                System.out.println("Connection failed");
            else
                System.out.println("Connection successfull!");

            //for the first query
            stmt1 = conn.createStatement();
            rs1 = stmt1.executeQuery(queryString1);
            ResultSetMetaData rsMetaData1 = rs1.getMetaData();
            int numberOfColumns1 = rsMetaData1.getColumnCount();

            //for the second query
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();

            //finding number of columns in query 1
            System.out.println("Number of columns: " +numberOfColumns1);
            for(int i=1; i<=numberOfColumns1;i++){
                System.out.printf("Column %2d: %s (%s)\n",i, rsMetaData1.getColumnName(i),rsMetaData1.getColumnTypeName(i));
            }
            //finding the longitude and latitude given the uder zipcode

            while(rs1.next()){
                String city = rs1.getString("city");
                latitude = rs1.getDouble("lat");
                longitude = rs1.getDouble("long");

                System.out.println("Test: "+city+" "+latitude+" "+longitude);
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

                Haversine test = new Haversine(latitude,longitude,lat,lon);//creates Haversine object
                double testDistance = test.calcHaversine();// does the Haversine distance formaula

                //compares the distance calculated by the haversine class and the given milesToKm
                if(testDistance <=milesToKm) {
                  //  System.out.println(testDistance);
                    Place place = new Place(city, state, zipcode, estpop, lat, lon, testDistance); // creates place object
                    listPlaces.add(place);//adding object to arraylist
                    //System.out.println(count+" "+place);

                }


                count++;

            }//end while loop

            //compares all the places added using the equals method in Place class.
            //If the state and state are the same then it
            for(int i =0; i<=listPlaces.size()-1;i++){
                for(int j =i+1; j< listPlaces.size()-2; j++){
                    if((listPlaces.get(i)).equals(listPlaces.get(j))){
                        int newPop = ((listPlaces.get(j)).getEstimatedpopulation()) + ((listPlaces.get(i)).getEstimatedpopulation());
                        (listPlaces.get(i)).setEstimatedpopulation(newPop);
                        listPlaces.remove(j);
                    }//end of if
                }//end of inner for
            }//end of outer for
            //Compare again to make sure duplicates are removed.
            for(int i =0; i<=listPlaces.size()-1;i++){
                for(int j =i+1; j< listPlaces.size()-2; j++){
                    if((((listPlaces.get(i).getCity())).equals((listPlaces.get(j)).getCity())) && ((listPlaces.get(i).getState())).equals((listPlaces.get(j)).getState())){
                        int newPop = ((listPlaces.get(j)).getEstimatedpopulation()) + ((listPlaces.get(i)).getEstimatedpopulation());
                        (listPlaces.get(i)).setEstimatedpopulation(newPop);
                        listPlaces.remove(j);
                    }//end of if
                }//end of inner for
            }//end of outer for

            System.out.println("Finished! There are "+(listPlaces.size()-1)+" places within "+enterMiles+" miles from this zipocde: "+enterZip +". Here are the list of places within this distance.");
            for(int i=0;i<=listPlaces.size()-1;i++){
                System.out.println(i+" "+listPlaces.get(i).toString());
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
