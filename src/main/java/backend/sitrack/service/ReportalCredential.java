package backend.sitrack.service;

import backend.sitrack.model.ReportInfo;
import backend.sitrack.util.DataRandom;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import java.lang.Thread;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Random;
import java.util.Arrays;
import java.util.Base64;



@Service
public class ReportalCredential {

	private static String application = "ReportGeneratorTest";
	private static String secretKey = "ccd517a1-d39d-4cf6-af65-28d65e192149";
	private static String URI = "https://test-externalrgw.ar.sitrack.com/frame";
	
	private final String login_code = "98173";
	private final String text = "ALAN OTERO";
	private final String report_type = "2";
	private final Double gps_dop = 1.0;
	private final String text_label = "TAG";
	
	
	double latitude1 = -31.419972;
	double longitude1 = -64.188472;
	Integer j = 0;
	public Object startGps() {	
		try {
		ReportInfo reportInfo = new ReportInfo();
		DataRandom operation = new DataRandom();
			
		String dateIso8601 = operation.dateISO8601();

		DecimalFormatSymbols separator = new DecimalFormatSymbols();
		separator.setDecimalSeparator('.');
		DecimalFormat df2 = new DecimalFormat("#.######", separator);
		  
		Random r1 = new Random();
		Random r2 = new Random();
		  
		double latitude2 = r1.nextDouble() * (90);
		double longitude2 = r2.nextDouble() * (180);
		  
		String longParse1 = df2.format(longitude1);
	    String longParse2 = df2.format(longitude2);
	    longitude1 = Double.parseDouble(longParse1);
	    longitude2 = Double.parseDouble(longParse2);
	      
	    String latParse1 = df2.format(latitude1);
	    String latParse2 = df2.format(latitude2);
	    latitude1 = Double.parseDouble(latParse1);
	    latitude2 = Double.parseDouble(latParse2);	      
	      
	    double distanceKm =  operation.getDistance(latitude1, latitude2, longitude1, longitude2);
		double speed =  operation.getSpeed(distanceKm);
		speed = Math.round(distanceKm * 100) / 100d;
	    
		reportInfo.setLoginCode(login_code);
		reportInfo.setReportDate(dateIso8601);
		reportInfo.setReportType(report_type);
		reportInfo.setGpsDop(gps_dop);
		reportInfo.setGpsSatellites(3);
		reportInfo.setHeading(335);
		reportInfo.setLatitude(latitude1);
		reportInfo.setLongitude(longitude1);
		reportInfo.setSpeed(speed);
		reportInfo.setText(text);
		reportInfo.setTextLabel(text_label);
		
		longitude1 = longitude2;		  
		latitude1 = latitude2;

		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = getHeaders();
		HttpEntity<ReportInfo> httpEntityRequest = new HttpEntity<>(reportInfo, headers);
		System.out.println("Antes de response");
		ResponseEntity<ReportInfo> response = restTemplate
				.exchange(URI, HttpMethod.PUT, httpEntityRequest, ReportInfo.class);
		System.out.println("luego de response");
//		Integer codeStatus = response.getStatusCodeValue();
//		while(codeStatus == 429 || codeStatus >= 500) {
//			Thread.sleep(11000);
//			System.out.println("en while");
//			ResponseEntity<ReportInfo> repeatResponse = restTemplate.exchange(URI, HttpMethod.PUT, httpEntityRequest, ReportInfo.class);
//			codeStatus = repeatResponse.getStatusCodeValue();
//		}
		
		j = j + 1;
		Thread.sleep(60000);
		if (j != 11) {
			System.out.println("Recursion nro: " + j);
			startGps();
		} else {
			return "fin del simulador";
		}
		
		return "fin del simulador";
		
		} catch (RestClientResponseException e) {
	        return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	
	
	private static HttpHeaders getHeaders () {
		Date date= new Date();
		Long timestamp = (date.getTime() / 1000L);
		byte[] md5 = generateMD5(timestamp);
    	String signature = signatureBase64(md5);
    	
    	String authHeaderValue = "SWSAuth application=" + "\"" + application + "\"" + ',' + "signature=" + "\"" + signature + "\"" + ',' + "timestamp=" + "\"" + timestamp + "\"";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", authHeaderValue); 
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        
        return httpHeaders;
    }
	

//	MD5( application + secretKey + timestamp ) 
	public static byte[] generateMD5(Long timestamp) {
		try {
			String toString = application + secretKey + timestamp;
			byte[] toMD5 = toString.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] theDigest = md.digest(toMD5);
			return theDigest;			
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
//	Base64Enconder( MD5 )
	public static String signatureBase64(byte[] md5) {
		String signature = Base64.getEncoder().encodeToString(md5);
		return signature;
	}
	
	
}

