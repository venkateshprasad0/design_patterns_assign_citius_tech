package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import app.CustomException.InvalidSeatNumberException;
import app.CustomException.SeatsNotAvailableException;
import app.CustomException.UnknownShowException;

public class ShowManagerImpl implements ShowManager {

	@Override
	public List<Show> populate(String filePath) {

		List<Show> shows= new ArrayList<>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File(filePath)));
			shows=br.lines().map(l-> {
				String arr[]=l.split(",");
				return new Show(arr[0],arr[1],Integer.parseInt(arr[2]));
			}).collect(Collectors.toList()) ;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return shows;
	}

	@Override
	public List<Show> bookShow(List<Show> showList, String showName, String show_time, int noOfSeats)
	throws UnknownShowException,InvalidSeatNumberException,SeatsNotAvailableException {
		List<Show> shows= new ArrayList<>();
		
		shows=showList.stream().
		filter(s->
		{
			LocalTime t1 = LocalTime.parse(s.getShowTime());
			LocalTime t2 = LocalTime.parse(show_time);
			
			if(s.getShowName().equalsIgnoreCase(showName) && t1.compareTo(t2)==0)
				return true;
			
			return false;
		})
		.collect(Collectors.toList());
		
		if(shows.isEmpty())
		{
			throw new UnknownShowException("Cannot find show with name="+showName+" and time="+show_time);
		}
		else {
			Show show=shows.get(0);
			
			if(noOfSeats<1 )
			{
				throw new InvalidSeatNumberException(noOfSeats+" is less than 1 ");
			}
			else if(noOfSeats>show.getSeatsAvailable())
			{
				throw new SeatsNotAvailableException(noOfSeats+" seats are not available. Only "+show.getSeatsAvailable()+" seats left.");
			}
			else {
				shows=showList.stream().map(s-> {
					if (s.getShowName().equalsIgnoreCase(showName) && 
							s.getShowTime().equalsIgnoreCase(show_time))
					{
						s.setSeatsAvailable((s.getSeatsAvailable()-noOfSeats));
					}
					return s;
				}).collect(Collectors.toList());
				
				System.out.println("Booking successfull");
			}	
		}
		
		return shows;
		
	}

}