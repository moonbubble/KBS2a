package tspScherm;

public class Klant
{
	private String voornaam;
	private String achternaam;
	private String adres;
	private String postcode;
	private String plaats;
	
	public Klant(String voornaam, String achternaam, String adres, String postcode, String plaats)
	{
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.adres = adres;
		this.postcode = postcode;
		this.plaats = plaats;
	}
	
	public String getVoornaam()
	{
		return voornaam;
	}
	
	public String getAchternaam()
	{
		return achternaam;
	}
	
	public String getAdres()
	{
		return adres;
	}
	
	public String getPostcode()
	{
		return postcode;
	}
	
	public String getPlaats()
	{
		return plaats;
	}
}
