import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;

@ManagedBean(name = "WorkoutList")
@SessionScoped
public class WorkoutList implements Serializable{

    private String loggedUsername;
    private ArrayList<Template> allWorkouts;
    private ArrayList<String> playlistsNames;
    private ArrayList<PlaylistTemplate> workoutsPlaylists;

    private String option;
    private int reps;
    private String selectedWorkoutName;
    private String selectedCategory;

    public WorkoutList(){
        loggedUsername = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username").toString();
        setAllWorkouts();
        setWorkoutsPlaylists();
        setPlaylistNames();
        setReps(10);
    }

    public ArrayList<Template> getAllWorkouts() {
        return allWorkouts;
    }

    public void setAllWorkouts() {
        allWorkouts = new ArrayList<Template>();
        this.allWorkouts = MongoConnect.getAllWorkouts();

//        MongoConnect.getPlaylistsFrom(loggedUsername);
        // this is REAL !!!!
        // -> setWork..   DONE!!
        // get the arraylist and parse it   DONE!!
        // selectonemenu  DONE!!
        //inputNumber reps !!
        //verify category before add
        // how to add
        // add
        // have fun :)
    }

    public void addWorkoutToPlaylist(){
        System.out.println(option +"  "+reps+"  "+selectedWorkoutName+"  "+selectedCategory);

        MongoConnect.addWorkoutInPlaylist(loggedUsername,getSelectedCategory(),getOption(),getSelectedWorkoutName(),getReps());
//        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("workoutName").toString());

    }

    public ArrayList<String> getPlaylistsNames() {
        return playlistsNames;
    }

    public void setPlaylistNames() {
        playlistsNames = new ArrayList<String>();
        for(PlaylistTemplate x : workoutsPlaylists){
            playlistsNames.add(x.getPlaylistName());
        }
    }

    public ArrayList<PlaylistTemplate> getWorkoutsPlaylists() {
        return workoutsPlaylists;
    }

    public void setWorkoutsPlaylists() {
        workoutsPlaylists = new ArrayList<PlaylistTemplate>();
        this.workoutsPlaylists = MongoConnect.getPlaylistsFrom(loggedUsername);
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public String getSelectedWorkoutName() {
        return selectedWorkoutName;
    }

    public void setSelectedWorkoutName(String selectedWorkoutName) {
        this.selectedWorkoutName = selectedWorkoutName;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
}
