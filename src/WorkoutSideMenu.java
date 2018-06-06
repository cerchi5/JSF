import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean(name = "WorkoutSideMenu")
public class WorkoutSideMenu implements Serializable{

    private MenuModel model;

    public MenuModel getModel() {
        return model;
    }

    public WorkoutSideMenu(){
//        System.out.println("MUIEEEE");

        model = new DefaultMenuModel();

        DefaultSubMenu addNew = new DefaultSubMenu("Workouts");

        DefaultMenuItem item = new DefaultMenuItem("Chest");
//        item.setIcon("ui-icon-disk");
        addNew.addElement(item);

        item = new DefaultMenuItem("Biceps");
        addNew.addElement(item);

        item = new DefaultMenuItem("Triceps");
        addNew.addElement(item);

        item = new DefaultMenuItem("Back");
        addNew.addElement(item);

        item = new DefaultMenuItem("Shoulders");
        addNew.addElement(item);

        item = new DefaultMenuItem("Legs");
        addNew.addElement(item);

        item = new DefaultMenuItem("Cardio");
        addNew.addElement(item);

        model.addElement(addNew);

        addNew = new DefaultSubMenu("My programmes");

//        add new workout to user
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

//        MongoConnect.addUserWorkout(session.getAttribute("username").toString(),"ceva ceva",5);


//        item = new DefaultMenuItem("Cardio");
//        addNew.addElement(item);

        model.addElement(addNew);

//        MongoConnect.getAllWorkouts();

    }

}
