//source: https://github.com/acmeism/RosettaCodeData/blob/master/Task/Haversine-formula/Java/haversine-formula.java

public class Haversine {

    public final double R = 6371; // In kilometers
    double lat1;
    double lon1;
    double lat2;
    double lon2;

    public Haversine(double lat1, double lon1, double lat2, double lon2) {
        this.lat1 = lat1;
        this.lon1 = lon1;
        this.lat2 = lat2;
        this.lon2 = lon2;
    }

    public double calcHaversine() {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2.0),2.0) + Math.pow(Math.sin(dLon / 2.0),2.0) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2.0 * Math.asin(Math.sqrt(a));
        double distance = Math.round(((R*c)*10.0)/10.0);

        return R*c;
    }

}
