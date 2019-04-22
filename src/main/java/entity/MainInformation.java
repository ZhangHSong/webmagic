package entity;

public class MainInformation {
	private Long id;

	private String name;

	private String site;
	
	private String city;

	private Double area;

	private String houseType;

	private String rentWay;

	private Double price;

	private String paymentMethod;
	
	private String address;
	
	private String row_col;
	
	private String url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType == null ? null : houseType.trim();
	}

	public String getRentWay() {
		return rentWay;
	}

	public void setRentWay(String rentWay) {
		this.rentWay = rentWay == null ? null : rentWay.trim();
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod == null ? null : paymentMethod.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRow_col() {
		return row_col;
	}

	public void setRow_col(String row_col) {
		this.row_col = row_col;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

}