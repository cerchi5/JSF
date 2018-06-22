import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean(name = "WorkoutSideMenu")
@SessionScoped
public class WorkoutSideMenu implements Serializable{

    private MenuModel model;
    private String loggedUser;
    private String actualName;

    public MenuModel getModel() {
        return model;
    }

    public WorkoutSideMenu(){

        loggedUser = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username").toString();

        model = new DefaultMenuModel();

        DefaultSubMenu addNew = new DefaultSubMenu("Workouts");

        DefaultMenuItem item = new DefaultMenuItem("All");
        item.setCommand("#{WorkoutList.setAllWorkouts}");
        addNew.addElement(item);

        item = new DefaultMenuItem("Chest");
        item.setCommand("#{WorkoutList.categorySelected('chest')}");
        addNew.addElement(item);

        item = new DefaultMenuItem("Biceps");
        item.setCommand("#{WorkoutList.categorySelected('biceps')}");
        addNew.addElement(item);

        item = new DefaultMenuItem("Triceps");
        item.setCommand("#{WorkoutList.categorySelected('triceps')}");
        addNew.addElement(item);

        item = new DefaultMenuItem("Back");
        item.setCommand("#{WorkoutList.categorySelected('back')}");
        addNew.addElement(item);

        item = new DefaultMenuItem("Shoulders");
        item.setCommand("#{WorkoutList.categorySelected('shoulders')}");
        addNew.addElement(item);

        item = new DefaultMenuItem("Legs");
        item.setCommand("#{WorkoutList.categorySelected('legs')}");
        addNew.addElement(item);

        item = new DefaultMenuItem("Cardio");
        item.setCommand("#{WorkoutList.categorySelected('cardio')}");
        addNew.addElement(item);

        model.addElement(addNew);

        addNew = new DefaultSubMenu("My programmes");

        for(PlaylistTemplate x : MongoConnect.getPlaylistsFrom(loggedUser)){
//            setActualName(x.getPlaylistName());
            item = new DefaultMenuItem(x.getPlaylistName());
//            item.setCommand("show_modal_edit("+x+")");
            item.setCommand("#{WorkoutList.executeShowModal('"+ x.getPlaylistName() +"')}");
            addNew.addElement(item);
        }

        model.addElement(addNew);

    }

    public void setActualName(String actualName){
        this.actualName = actualName;
    }

    public String getActualName(){
        return this.actualName;
    }

}
