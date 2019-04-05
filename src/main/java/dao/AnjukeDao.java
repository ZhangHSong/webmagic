package dao;

import entity.AnjukeMainInformation;
import entity.AnjukePhoto;

public interface AnjukeDao {

	public int addMainInformation(AnjukeMainInformation anjukeMainInformation);

	public int addPhotoInformation(AnjukePhoto anjukePhoto);

}
