package day4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CandidateStreamingOperations {

	public static void main(String[] args) {
		
		List<Candidate> CandidateList = new ArrayList<Candidate>();
		CandidateList = InterviewRepository.getCandidateList();
	
		//list candidate names from Pune city
		System.out.println("List of Pune Candidates:");
		CandidateList.stream().filter(c -> c.getCity().equalsIgnoreCase("pune")).forEach(System.out::println);

		//list city and count of candidates per city
		System.out.println("Candidate count per city");
		java.util.Map<String, Long> nameCount = CandidateList.stream().collect(Collectors.groupingBy(c -> c.getCity(), Collectors.counting()));
        nameCount.forEach((name, count) -> {
            System.out.println(name + ":" + count);
        });

		//list technical expertise and count of candidates
		System.out.println("Candidate count by Technical Expertise");
		java.util.Map<String, Long> nameCount2 = CandidateList.stream().collect(Collectors.groupingBy(c -> c.getTechnicalExpertise(), Collectors.counting()));
        nameCount2.forEach((name, count) -> {
            System.out.println(name + ":" + count);
        });
		//list fresher candidates
		System.out.println("Fresher Candidate list");
		CandidateList.stream().filter(c -> c.getYearsOfExperience()==0).forEach(System.out::println);
		//listing candidates with highest experience
		System.out.println("Sorted List of Candidates by Experience");
		CandidateList.stream().sorted((c1,c2) -> {
			return c1.getYearsOfExperience() - c2.getYearsOfExperience();
		}).forEach(System.out::println);


		//sort the candidates by city name
		printLine();
		System.out.println("Sorted List of Candidates by City Name");
		CandidateList.stream().sorted( (c1,c2) -> {
			return c1.getCity().compareToIgnoreCase(c2.getCity());
		}).forEach(System.out::println);
	}

	private static void printLine() {
		// TODO Auto-generated method stub
		System.out.println("======================================================");
	}
}