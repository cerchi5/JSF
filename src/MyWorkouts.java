import com.mongodb.Mongo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

@ManagedBean(name = "MyWorkouts")
@SessionScoped
public class MyWorkouts implements Serializable{

    private String loggedUsername;
    private ArrayList<PlaylistTemplate> playlists;
    private String newPlaylistName;

    public MyWorkouts(){
        loggedUsername = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username").toString();
        setPlaylists(MongoConnect.getPlaylistsFrom(loggedUsername));
    }

    public void increment(String playlistName, String workoutName){
        int newReps;

        for(PlaylistTemplate x : playlists){
            if(x.getPlaylistName().compareTo(playlistName) == 0){
                for(WorkoutTemplate y : x.getWorkouts()){
                    if(y.getName().compareTo(workoutName) == 0){
                        newReps = y.getReps();
                        newReps++;
                        y.setReps(newReps);

                        MongoConnect.updateReps(loggedUsername,playlistName,workoutName,newReps);
                    }
                }
            }
        }
    }

    public void decrement(String playlistName, String workoutName){
        int newReps;

        for(PlaylistTemplate x : playlists){
            if(x.getPlaylistName().compareTo(playlistName) == 0){
                for(WorkoutTemplate y : x.getWorkouts()){
                    if(y.getName().compareTo(workoutName) == 0){
                        newReps = y.getReps();
                        newReps--;
                        y.setReps(newReps);

                        MongoConnect.updateReps(loggedUsername,playlistName,workoutName,newReps);
                    }
                }
            }
        }
    }

    public void delete(String playlistName, String workoutName){
        MongoConnect.removeWorkout(loggedUsername,playlistName,workoutName);
        setPlaylists(MongoConnect.getPlaylistsFrom(loggedUsername));
        reload();
    }

    public void createPlaylist(){
        System.out.println("CREATE"+ newPlaylistName);

        MongoConnect.createPlaylist(loggedUsername,newPlaylistName);

        reload();
    }

    public ArrayList<PlaylistTemplate> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<PlaylistTemplate> playlists) {
        this.playlists = playlists;
    }

    public String getNewPlaylistName() {
        return newPlaylistName;
    }

    public void setNewPlaylistName(String newPlaylistName) {
        this.newPlaylistName = newPlaylistName;
    }

    public void reload() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
