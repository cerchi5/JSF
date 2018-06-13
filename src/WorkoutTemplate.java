
public class WorkoutTemplate {

    private String name;
    private int reps;

    public WorkoutTemplate(String name, int reps){
        setName(name);
        setReps(reps);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
