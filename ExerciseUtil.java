import java.util.Scanner;
import java.util.Random;
import java.util.List;

/**
*   Exercise Utility
*       - class that contains all utility methods
*         for the exercise
*/

public final class ExerciseUtil {
    
    private static Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    /** gets a non-negative integer from the user */
    public static int getInteger(String message, boolean allowZero) {
        int num;
        System.out.print(message);
        
        while(!scanner.hasNextInt()) {
            System.out.println("You did not enter an integer");
            System.out.print(message);
            scanner.next();
        }

        num = scanner.nextInt();

        if((!allowZero && num <= 0) || (num < 0)) {
            System.out.println("0 and lower is not allowed.");
            num = getInteger(message,false);
        }

        return num;
    }

    /** gets a string input from user while displaying a message */
    public static String getStringInput(String message) {
        System.out.print(message);
        return scanner.next();
    }


    /* emulates unique key for cells */
    public static boolean isUnique(List<Racer> racers, String entry) {
        boolean found = true;
        entry = entry.toUpperCase();
        for(Racer racer : racers) {
            if(entry.equals(racer.getName())){
                found = false;
                break;
            }
        }
        return found;
    }


    /* gets a unique string input from user */
    public static String getStringInput(String message, List<Racer> racers, boolean isUnique){
        String str;
        boolean unique = false;
        if(!isUnique){
            return getStringInput(message);
        }

        do{
            str = getStringInput(message);
            unique = isUnique(racers,str);
            if(!unique){
                System.out.println("Racer " + str + " already exists.");
            }
        }while(!unique);
        
        return str;
    }

    /**returns a number from 1 to 10*/
    public static int getSpeed() {
        Random r = new Random();
        return ((r.nextInt(10))+1);
    }

    /** prints race headers */
    public static void printHeaders() {
        
        System.out.println("Name\t\t\t"
                        + "DT\t"
                        + "DL\t"
                        + "Speed");
    
    }


}

interface Racing {
    public void start(Racer racer);
}