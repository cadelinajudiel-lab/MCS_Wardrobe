package practical.exam;

public class Lao_ThemeParkTest {
    private boolean isEngineRunning = false;
    private boolean isBrakeEngaged = true;
    private int currentPositionMeters = 0;
    private final int STATION_DESTINATION_METERS = 500; // Track length

    // 1. Function to start the engine/motor
    public void startEngine() {
        if (!isBrakeEngaged) {
            System.out.println("Safety Warning: Disengage brakes before starting engine!");
            return;
        }

        // Release brakes and engage motor
        isBrakeEngaged = false;
        isEngineRunning = true;
        System.out.println("Engine started. Dispatching ride from station...");
    }

    // 2. Function simulating ride movement towards destination
    public void advanceRide(int distanceMeters) {
        if (!isEngineRunning) {
            System.out.println("Cannot move: Engine is off.");
            return;
        }

        currentPositionMeters += distanceMeters;
        System.out.println("Ride position: " + currentPositionMeters + "m / " + STATION_DESTINATION_METERS + "m");

        // Check if destination is reached
        if (currentPositionMeters >= STATION_DESTINATION_METERS) {
            applyBrakes();
        }
    }

    // 3. Function to apply brakes and bring ride to a full stop
    public void applyBrakes() {
        System.out.println("Station approach detected! Engaging magnetic brakes...");
        isEngineRunning = false;
        isBrakeEngaged = true;
        currentPositionMeters = STATION_DESTINATION_METERS; // Reset/lock at station
        System.out.println("Ride safely stopped at station. Safe for unboarding.");
    }

    // Main execution point matching the file name
    public static void main(String[] args) {
        Lao_ThemeParkTest coaster = new Lao_ThemeParkTest();

        coaster.startEngine();            // Start motor
        coaster.advanceRide(250);         // Mid-course
        coaster.advanceRide(250);         // Reaches end -> automatically triggers applyBrakes()
    }
}
