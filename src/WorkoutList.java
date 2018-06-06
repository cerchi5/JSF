import com.mongodb.Mongo;

import javax.faces.bean.ManagedBean;
import java.util.ArrayList;

@ManagedBean(name = "WorkoutList")
public class WorkoutList {

    private ArrayList<Template> allWorkouts;

    public WorkoutList(){
        allWorkouts = new ArrayList<Template>();
        setAllWorkouts();
    }

    public ArrayList<Template> getAllWorkouts() {
        return allWorkouts;
    }

    public void setAllWorkouts() {
        this.allWorkouts = MongoConnect.getAllWorkouts();
    }
}
