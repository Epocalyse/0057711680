package F28DA_CW2;

public class FlightRoute {
	
	private String flight;
	private String codarr;
	private String timarr;
	private String coddep;
	private String timdep;
	private int price;

	public FlightRoute(String flname, String codarr, String timarr, String coddep, String timdep, int price){
		flight = flname;
		this.codarr = codarr;
		this.timarr = timarr;
		this.coddep = coddep;
		this.timdep = timdep;
		this.price = price;
	}
	
	public String getFlight(){
		return flight;
	}
	
	public String getArrCode(){
		return codarr;
	}
	
	public String getDepCode(){
		return coddep;
	}
	
	public int getArrTime(){
		return Integer.parseInt(timarr);
	}
	
	public int getDepTime(){
		return Integer.parseInt(timdep);
	}
	
	public int getPrice(){
		return price;
	}
}
