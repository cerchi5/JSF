import java.util.ArrayList;

public class PlaylistTemplate {

    private String playlistName;
    private String playlistCategory;
    private String ownerName;
    private ArrayList<WorkoutTemplate> workouts;

    public PlaylistTemplate(String playlistName, String playlistCategory, String ownerName){
        setPlaylistName(playlistName);
        setPlaylistCategory(playlistCategory);
        setOwnerName(ownerName);
        workouts = new ArrayList<WorkoutTemplate>();
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistCategory() {
        return playlistCategory;
    }

    public void setPlaylistCategory(String playlistCategory) {
        this.playlistCategory = playlistCategory;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public ArrayList<WorkoutTemplate> getWorkouts() {
        return workouts;
    }

    public void addWorkout(WorkoutTemplate workout) {
        this.workouts.add(workout);
    }

    public void setWorkouts(ArrayList<WorkoutTemplate> workouts) {
        this.workouts = workouts;
    }
}
