package backend.sitrack.model;


public class ReportInfo {
	
	private	String loginCode;
	private String reportDate;
	private String reportType;
	private String text;
	private String textLabel;
	private Double speedLabel;
	private Double latitude;
	private Double longitude;
	private Double gpsDop;
	private Double speed;
	private Integer heading;
	private Integer gpsSatellites;
	
	
	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	
	
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	
	
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getGpsDop() {
		return gpsDop;
	}
	public void setGpsDop(Double gpsDop) {
		this.gpsDop = gpsDop;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTextLabel() {
		return textLabel;
	}
	public void setTextLabel(String textLabel) {
		this.textLabel = textLabel;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Integer getHeading() {
		return heading;
	}
	public void setHeading(Integer heading) {
		this.heading = heading;
	}
	public Integer getGpsSatellites() {
		return gpsSatellites;
	}
	public void setGpsSatellites(Integer gpsSatellites) {
		this.gpsSatellites = gpsSatellites;
	}
	public Double getSpeedLabel() {
		return speedLabel;
	}
	public void setSpeedLabel(Double speedLabel) {
		this.speedLabel = speedLabel;
	}
	
}
