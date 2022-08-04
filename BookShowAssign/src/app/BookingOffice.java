package app;

import java.util.ArrayList;
import java.util.List;

public class BookingOffice {

	public static void main(String[] args) {
		List<Show> list = new ArrayList<Show>();

		ShowManagerImpl dmi = new ShowManagerImpl();
		list = dmi.populate("show.txt");
		System.out.println("SHOWS ARE AS FOLLOWS::");
		System.out.println("-----------------------------------------");
		list.stream().forEach(s-> System.out.println(s));
		System.out.println();


		try {
			list=dmi.bookShow(list, "Sahi re Sahi", "06:30", 1);
			System.out.println();
			DisplayShow display = s-> {
				System.out.println(s);
				System.out.println();
			};
			
			System.out.println("Current seat availability are as follows::");
			display.displayAllShows(list);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
