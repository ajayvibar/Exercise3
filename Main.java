public class Main {
	public static void main (String args[]) {
		Race r;
		do {
			r = new Race(ExerciseUtil.getInteger("Enter number of horses: ",
						false));
		}while(!(r.verifyRace()));
		
		r.runRace();
		r.printRaceLog();
	}

}