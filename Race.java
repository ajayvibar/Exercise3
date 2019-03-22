import java.util.Random;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.*;


/**
	Race class
	Simulates horse racing with user input number of horses, distance and horse names
	The race starts when all horses have reached the barn (at distance 10); horses
	that arrive early will wait for the other horses before the race acutally begins.

	@java_ver: 1.8.0
	@author: Aron Vibar
	@date: 03/01/2019

*/


public class Race {

	private List<Racer> racers = new ArrayList<>();
	private Integer distance;
	private static boolean TO_BARN = true;
	private static int BARN_DISTANCE = 10;
	private static Optional<String> crier;
	private static List<String> WARCRIES = new ArrayList<>(Arrays.asList(
											"Yikes",
											"Oof",
											null,
											"E",
											"Me Me Bigboy",
											"Yay",
											"Clean water",
											"Nice",
											"Wow"));

	private static String warcry(Racer horse) {
		Random r = new Random();
		int index = r.nextInt(WARCRIES.size());
		crier = Optional.ofNullable(WARCRIES.get(index));
		if(crier.isPresent()) {
			return (horse.getName() + ": " +WARCRIES.get(index));	
		}else{
			return (horse.getName() + ": No warcry for this racer");
		}	
	}

	private void startRace(Racer horse) {	
		Racing prix = (racer) -> {
			while(!racer.isAtDestination()) {
				runAtDistance(racer);
				if(racer.isAtDestination()) {
					if(TO_BARN) {
						System.out.println(racer.getName() + " has reached the barn.");
					}else{
						System.out.println(warcry(racer));
					}
				}
			}
		};

		prix.start(horse);
	}

	private void runAtDistance(Racer horse) {
		horse.setSpeed(ExerciseUtil.getSpeed());
		horse.setDistanceTravelled(horse.getSpeed() + horse.getDistanceTravelled());
		if (horse.getDistanceTravelled() >= this.distance) {
			warcry(horse);
			horse.setDistanceTravelled(distance);
		}
		System.out.println(horse.toString());
		horse.log();
	}

	public Race(int racers) {
		this.distance = (ExerciseUtil.getInteger("Enter race distance: ",false));
		
		Random r = new Random();
		for (int i=0;i<racers;i++) {
			Racer horse = new Racer(ExerciseUtil.getStringInput("Enter horse name: ",this.racers,true));
			if(!(horse.isHealthy())) {
				System.out.println(horse.getName()
							 + " is not healthy and"
							 + " will not join the race.");
			}else{
				horse.setDestination(BARN_DISTANCE);
				this.racers.add(horse);	
			}
			
		}

	}

	public void printRacers() {
		this.racers.forEach(System.out::println);
	}

	public boolean verifyRace() {
		if(this.racers.size() < 2) {
			System.out.println("There are less than 2 racers."
								+ " The race will not continue.");
			return false;
		}

		return true;
	}

	public void printRaceLog() {
		System.out.println("\nSummary\n");
		ExerciseUtil.printHeaders();
		this.racers.forEach((racer) -> {
			racer.printLog();
			System.out.println("\n");
		});
	}

	public void runRace() {
		ExerciseUtil.printHeaders();		
		this.racers.parallelStream()
					.forEach(this::startRace);

		TO_BARN = false;
		System.out.println("All racers have reached the start.");
		
		for(Racer racer : this.racers) {
			racer.setDistanceTravelled(0);
			racer.setDestination(this.distance);
			racer.clearLogs();
		}

		this.racers.parallelStream()
					.forEach(this::startRace);	

	} 

}