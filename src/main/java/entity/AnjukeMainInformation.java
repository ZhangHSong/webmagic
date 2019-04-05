package entity;

public class AnjukeMainInformation {

	private Long number;// ���
	private String name;// ����
	private String address;// ��ַ+С��
	private double area;// ���
	private String houseType;// ����
	private String rentWay;// ���ⷽʽ
	private double price;// �۸�
	private String paymentMethod;// ���ʽ
	private String contacts;// ��ϵ��
	private String releaseTime;// ����ʱ��
	private String description;// ����

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRentWay() {
		return rentWay;
	}

	public void setRentWay(String rentWay) {
		this.rentWay = rentWay;
	}

	@Override
	public String toString() {
		return "AnjukeMainInformation [number=" + number + ", name=" + name + ", address=" + address + ", area=" + area
				+ ", houseType=" + houseType + ", rentWay=" + rentWay + ", price=" + price + ", paymentMethod="
				+ paymentMethod + ", contacts=" + contacts + ", releaseTime=" + releaseTime + ", description="
				+ description + "]";
	}

}
