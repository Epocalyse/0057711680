package F28DA_CW2;

public class Airport{
	
	private String code;
	private String country;
	private String aport;
	Itinerary itin = new Itinerary();
	
	public Airport(String code, String country, String aport){
		this.code = code;
		this.country = country;
		this.aport = aport;
	}
	
	public String getCode(){
		return code;
	}
	
	public String getCountry(){
		return country;
	}
	
	public String getAirport(){
		return aport;
	}
	
	
}
