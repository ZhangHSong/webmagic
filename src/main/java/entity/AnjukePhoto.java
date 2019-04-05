package entity;

public class AnjukePhoto {

	private long number;// 编号
	private String indoorPhoto;// 室内图
	private String floorPhoto;// 户型图
	private String environmentPhoto;// 环境图

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getIndoorPhoto() {
		return indoorPhoto;
	}

	public void setIndoorPhoto(String indoorPhoto) {
		this.indoorPhoto = indoorPhoto;
	}

	public String getFloorPhoto() {
		return floorPhoto;
	}

	public void setFloorPhoto(String floorPhoto) {
		this.floorPhoto = floorPhoto;
	}

	public String getEnvironmentPhoto() {
		return environmentPhoto;
	}

	public void setEnvironmentPhoto(String environmentPhoto) {
		this.environmentPhoto = environmentPhoto;
	}

	@Override
	public String toString() {
		return "AnjukePhoto [number=" + number + ", indoorPhoto=" + indoorPhoto + ", floorPhoto=" + floorPhoto
				+ ", environmentPhoto=" + environmentPhoto + "]";
	}

}
