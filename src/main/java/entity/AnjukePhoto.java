package entity;

public class AnjukePhoto {

	private long number;// ���
	private String indoorPhoto;// ����ͼ
	private String floorPhoto;// ����ͼ
	private String environmentPhoto;// ����ͼ

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
