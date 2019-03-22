import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
	Racer class:
	objects of this class are participants of
	race
*/

public class Racer {

	private String name;
	private boolean health;
	private Integer distanceTravelled;
	private Integer destination;
	private Integer speed;
	private List<String> raceLog = new ArrayList<>();

	public Racer(String name) {
		setName(name);
		setHealth();
		setSpeed(0);
		setDistanceTravelled(0);
	}

	public String getName() {
		return this.name;
	}

	public boolean isHealthy() {
		return this.health;
	}

	public Integer getDistanceTravelled() {
		return this.distanceTravelled;
	}

	public Integer getSpeed() {
		return this.speed;
	}

	public Integer getDistanceLeft(){
		if(this.destination - this.distanceTravelled <= 0){
			return 0;
		}else{
			return (this.destination - this.distanceTravelled); 
		}
	}

	protected void setDestination(int dest){
		this.destination = dest;
	}

	protected void setName(String name) {
		this.name = name.toUpperCase();
	}

	protected void setHealth() {
		Random r = new Random();
		this.health = r.nextBoolean();

	}

	protected void setDistanceTravelled(int dist) {
		this.distanceTravelled = dist;
	}

	protected void setSpeed(int spd) {
		this.speed = spd;
	}

	public Integer getDestination() {
		return this.destination;
	}

	public boolean isAtDestination() {
		if(this.getDistanceTravelled() >= this.destination) {
			this.setDistanceTravelled(this.destination);
			return  true;
		}else{
			return  false;
		}
	}

	public void log() {
		this.raceLog.add(this.toString());
	}

	public void printLog() {
		this.raceLog.forEach(System.out::println);
	}

	public void clearLogs() {
		this.raceLog.clear();
	}

	@Override
	public String toString(){
		return (this.name + "\t\t\t"
						+ this.distanceTravelled
						+ "\t" + this.getDistanceLeft()
						+ "\t" + this.speed);
	}

}