import java.util.ArrayList;
import java.util.Objects;

public class Place {
    String city;
    String state;
    String zipcode;
    int estimatedpopulation;
    double latitude;
    double longitude;


    double distanceFromOrigin;

    //constructor
    public Place(String city, String state, String zipcode, int estimatedpopulation, double latitude, double longitude) {
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.estimatedpopulation = estimatedpopulation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distanceFromOrigin = -1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getEstimatedpopulation() {
        return estimatedpopulation;
    }

    public void setEstimatedpopulation(int estimatedpopulation) {
        this.estimatedpopulation = estimatedpopulation;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;
        Place place = (Place) o;
        return Objects.equals(city, place.city) &&
                Objects.equals(zipcode, place.zipcode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(city, zipcode);
    }

    @Override
    public String toString() {
        return "Place{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", estimatedpopulation=" + estimatedpopulation +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", distanceFromOrigin=" + distanceFromOrigin +
                '}';
    }

}

