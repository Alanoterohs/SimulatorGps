package backend.sitrack.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class DataRandom {
	
	Random random1 = new Random();
	Random random2 = new Random();
	
	double latitude = random1.nextDouble() * (90);		
	double latitudeRadian = Math.toRadians(latitude);
	
	double lastLatitude = latitude;
	double lastLatitudeRadian = Math.toRadians(latitude);
	
	
	double differenceLatitude = (latitudeRadian - lastLatitudeRadian);
	double sinLatitude = Math.sin(differenceLatitude / 2);
	
	public double getLatitude(double latitude) {
		return latitude;
	}
	
	public double getLongitude() {
		Random random2 = new Random();
		double longitude = random2.nextDouble() * (180);
		double longitudeRadian = Math.toRadians(longitude);
		
		double lastLongitude = longitude;
		double lastLongitudeRadian = Math.toRadians(lastLongitude);
		double differenceLongitude = (longitudeRadian - lastLongitudeRadian);
		double sinlon = Math.sin(differenceLongitude / 2);
		return sinlon;
	}
	
	public double getDistance(double latitude1, double latitude2, double longitude1, double longitude2) {
		  double lastLatitudeRadians = Math.toRadians(latitude1);
		  double lastLongitudeRadians = Math.toRadians(longitude1);
		  double latitudeRadian = Math.toRadians(latitude2);
		  double longitudeRadian = Math.toRadians(longitude2);

		  double differenceLongitude = (longitudeRadian - lastLongitudeRadians);
		  double differenceLatitude = (latitudeRadian - lastLatitudeRadians);

		  double sinlat = Math.sin(differenceLatitude / 2);
		  double sinlon = Math.sin(differenceLongitude / 2);

		  double a = (sinlat * sinlat) + Math.cos(lastLatitudeRadians)*Math.cos(latitudeRadian)*(sinlon*sinlon);
		  double c = 2 * Math.asin (Math.min(1.0, Math.sqrt(a)));

		  final double distanceInKms = 6371 * c ;
		  return distanceInKms;
	}
	
	public double getSpeed(double distanceKm) {
		return distanceKm / 0.01666;
	}
	
	public String dateISO8601() {
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); //formato solicitado
		dateFormat.setTimeZone(timeZone);
		String dateIso8601 = dateFormat.format(new Date());
		return dateIso8601;
	}
	




	
}
