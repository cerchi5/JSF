import org.primefaces.context.RequestContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

@ManagedBean(name = "WorkoutList")
@SessionScoped
public class WorkoutList implements Serializable {

    private String loggedUsername;
    private ArrayList<Template> allWorkouts;
    private ArrayList<String> playlistsNames;
    private ArrayList<PlaylistTemplate> workoutsPlaylists;

    private String option;
    private int reps;
    private String selectedWorkoutName;
    private String selectedCategory;
    private String selectedPlaylist;


    public WorkoutList() {
        loggedUsername = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username").toString();
        setAllWorkouts();
        MongoConnect.getPlaylistFrom(loggedUsername,"abc");
        setWorkoutsPlaylists();
        setPlaylistNames();
        setReps(10);
    }

    public void reload() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Template> getAllWorkouts() {
        return allWorkouts;
    }

    public void setAllWorkouts() {
        allWorkouts = new ArrayList<Template>();
        this.allWorkouts = MongoConnect.getAllWorkouts();
        reload();
    }

    public void categorySelected(String category) {
        this.allWorkouts = new ArrayList<Template>();
        this.allWorkouts = MongoConnect.getWorkoutsByCategory(category);
        reload();
    }

    public void executeShowModal(String playlistName) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("show_modal_edit('" + playlistName + "')");
    }

    public void addWorkoutToPlaylist() {
//        System.out.println(option +"  "+reps+"  "+selectedWorkoutName+"  "+selectedCategory);
        MongoConnect.addWorkoutInPlaylist(loggedUsername, getSelectedCategory(), getOption(), getSelectedWorkoutName(), (int) getReps());
    }

    public ArrayList<WorkoutTemplate> getPlaylistFrom(String playlistName) {
        return MongoConnect.getPlaylistFrom(loggedUsername, playlistName);
    }

    public ArrayList<String> getPlaylistsNames() {
        return playlistsNames;
    }

    public void setPlaylistNames() {
        playlistsNames = new ArrayList<String>();
        for(PlaylistTemplate x : workoutsPlaylists) {
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

    public double getReps() {
        return (double) reps;
    }

    public void setReps(double reps) {
        this.reps = (int) reps;
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

    public String getSelectedPlaylist() {
        return selectedPlaylist;
    }

    public void setSelectedPlaylist(String selectedPlaylist) {
        this.selectedPlaylist = selectedPlaylist;
    }



    public void notify(String message) {
        if (message.compareTo("Successful") == 0)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, "Workout added to the playlist. "));
    }



    public void save(){
        ArrayList<WorkoutTemplate> newPlaylist = new ArrayList<WorkoutTemplate>();
        String[] aux;
        String auxName;
        int auxReps;
        for(String x : target){
            aux = x.split(" ");
            auxName = aux[0];
            auxReps = Integer.parseInt(aux[1].replace("x",""));
            newPlaylist.add(new WorkoutTemplate(auxName,auxReps));
            System.out.println(auxName+"   "+auxReps);
        }

        MongoConnect.updatePlaylistWorkouts(loggedUsername,selectedPlaylist,newPlaylist);
    }

    private String leftValue;
    private String rightValue;
    private ArrayList<String> source;
    private ArrayList<String> target;

    public void initPicklist(){
        setSelectedPlaylist(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("newValue"));
        System.out.println("INIT Picklist   "+ getSelectedPlaylist());

        try {
            source = new ArrayList<String>();
            for (WorkoutTemplate x : MongoConnect.getPlaylistFrom(loggedUsername,selectedPlaylist)){
                source.add(x.getName() + " x" + x.getReps());
            }
            target = new ArrayList<String>();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void pushToRightAll(){
        target.addAll(source);
        source.clear();
    }

    public void pushToRightSelected(){
        if(leftValue != null && leftValue.compareTo("") != 0){
            System.out.println("HERE is leftValue:  ");
            target.add(leftValue);
            source.remove(leftValue);
        }
    }

    public void pushToLeftAll(){
        source.addAll(target);
        target.clear();
    }

    public void pushToLeftSelected(){
        if(rightValue != null&& leftValue.compareTo("") != 0){
            source.add(rightValue);
            target.remove(rightValue);
        }
    }

    public String getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(String leftValue) {
        this.leftValue = leftValue;
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }

    public ArrayList<String> getSource() {
        return source;
    }

    public void setSource(ArrayList<String> source) {
        this.source = source;
    }

    public ArrayList<String> getTarget() {
        return target;
    }

    public void setTarget(ArrayList<String> target) {
        this.target = target;
    }
}
