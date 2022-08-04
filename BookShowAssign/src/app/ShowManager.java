package app;

import java.util.List;

public interface ShowManager {

	public List<Show> populate(String filePath);
	public List<Show> bookShow(List<Show> showList, String showName,
				String show_time, int noOfSeats);
}
