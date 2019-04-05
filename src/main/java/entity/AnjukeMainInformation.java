package entity;

public class AnjukeMainInformation {

	private Long number;// 编号
	private String name;// 名称
	private String address;// 地址+小区
	private double area;// 面积
	private String houseType;// 房型
	private String rentWay;// 出租方式
	private double price;// 价格
	private String paymentMethod;// 付款方式
	private String contacts;// 联系人
	private String releaseTime;// 发布时间
	private String description;// 描述

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
