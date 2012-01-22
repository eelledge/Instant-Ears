
public class Business 
{
	private int ID;
	private int reviewCount;
	private double userRating;
	private double latitude;
	private double longitude;
	private String name;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipCode;
	private String phoneNumber;
	private String displayUrl;
	private String url;
	
	public Business()
	{		
		
	}
	
	public int getID() 
	{
		return ID;
	}

	public void setID(int iD) 
	{
		ID = iD;
	}

	public int getReviewCount() 
	{
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) 
	{
		this.reviewCount = reviewCount;
	}

	public double getUserRating() 
	{
		return userRating;
	}

	public void setUserRating(double userRating) 
	{
		this.userRating = userRating;
	}

	public double getLatitude() 
	{
		return latitude;
	}

	public void setLatitude(double latitude) 
	{
		this.latitude = latitude;
	}

	public double getLongitude() 
	{
		return longitude;
	}

	public void setLongitude(double longitude) 
	{
		this.longitude = longitude;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getAddress1() 
	{
		return address1;
	}

	public void setAddress1(String address1) 
	{
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) 
	{
		this.address2 = address2;
	}

	public String getCity() 
	{
		return city;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getState() 
	{
		return state;
	}

	public void setState(String state) 
	{
		this.state = state;
	}

	public String getZipCode() 
	{
		return zipCode;
	}

	public void setZipCode(String zipCode) 
	{
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}

	public String getDisplayUrl() 
	{
		return displayUrl;
	}

	public void setDisplayUrl(String displayUrl) 
	{
		this.displayUrl = displayUrl;
	}

	public String getUrl() 
	{
		return url;
	}

	public void setUrl(String url) 
	{
		this.url = url;
	}
}
