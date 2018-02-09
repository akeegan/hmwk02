import java.util.Objects;

public class Place {
    String city;
    String state;
    String zipcode;
    int estimatedpopulation;
    double latitude;
    double longitude;
    double distanceFromZipKm;
    double distanceFromZipMiles;

    //constructor
    public Place(String city, String state, String zipcode, int estimatedpopulation, double latitude, double longitude, double distanceFromZipKm) {
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.estimatedpopulation = estimatedpopulation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distanceFromZipKm = distanceFromZipKm;
        this.distanceFromZipMiles = distanceFromZipKm*0.62137;//convert KM to miles

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
    public double getDistanceFromZipKm() {
        return distanceFromZipKm;
    }

    public void setDistanceFromZipKm(int distanceFromZipKm) {
        this.distanceFromZipKm = distanceFromZipKm;
    }
    public double getDistanceFromZipMiles() {
        return distanceFromZipMiles;
    }

    public void setDistanceFromZipMiles(int distanceFromZipMiles) {
        this.distanceFromZipKm = distanceFromZipMiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;
        Place place = (Place) o;
        return Objects.equals(getCity(), place.getCity()) &&
                Objects.equals(getState(), place.getState());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCity(), getState());
    }

    @Override
    public String toString() {
        return "City:" + city + '\'' +
                ", State " + state + '\'' +
                ", Zipcode " + zipcode + '\'' +
                ", Estimated Population " + estimatedpopulation +
                ", Latitude" + latitude +
                ", Longitude " + longitude +
                ", distance from zipcode in KM " + distanceFromZipKm +
                ", distance from zipcode in Miles " + distanceFromZipMiles +
                ".";
    }

}

