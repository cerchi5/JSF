import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;


import org.primefaces.component.panel.Panel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@ManagedBean(name="dashboard")
@SessionScoped
public class Dashboard implements Serializable {

//    private org.primefaces.component.dashboard.Dashboard dash;
//    private FacesContext context;
//    private Application app;

    private DashboardModel dashboardModel;
    private String loggedUsername;
    private DashboardColumn column;
    private String selectedPlaylist;

    public Dashboard() {
        dashboardModel = new DefaultDashboardModel();
        column = new DefaultDashboardColumn();
        loggedUsername = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username").toString();
        setSelectedPlaylist(null);
        initDashboard();
    }

    public void initDashboard(){
        setSelectedPlaylist();
        System.out.println("INIT  " + getSelectedPlaylist());
        String playlistName = selectedPlaylist;
        if(playlistName != null) {
//            ArrayList<WorkoutTemplate> workouts;
//            workouts = MongoConnect.getPlaylistFrom(loggedUsername, playlistName);
//            dashboardModel = new DefaultDashboardModel();
//            column = new DefaultDashboardColumn();
//            try {
//                for (WorkoutTemplate x : workouts)
//                    column.addWidget(x.getName());
//
//                dashboardModel.addColumn(column);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            dashboardModel = new DefaultDashboardModel();
            column = new DefaultDashboardColumn();
            column.addWidget("w2");
            column.addWidget("aaa");
            column.addWidget("w1");
            column.addWidget("w5");
            column.addWidget("w4");
            System.out.println("Ajunge AICIII");
            dashboardModel.addColumn(column);
        }else{
            column.addWidget("w2");
            column.addWidget("aaa");
            column.addWidget("w1");
            column.addWidget("w5");
            column.addWidget("w4");
            System.out.println("A PUS IN DASH");
            dashboardModel.addColumn(column);
        }
    }

    public void updateAndReorderDashboard(DashboardReorderEvent event){
        String itemMoved = event.getWidgetId();
        int newRowIndex = event.getItemIndex();



    }

    public DashboardModel getDashboardModel() {
        return dashboardModel;
    }

    public void setDashboardModel(DashboardModel dashboardModel) {
        this.dashboardModel = dashboardModel;
    }

    public String getSelectedPlaylist() {
        return selectedPlaylist;
    }

    public void setSelectedPlaylist(String selectedPlaylist) {
        this.selectedPlaylist = selectedPlaylist;
    }

    public void setSelectedPlaylist(){
        String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("newValue");
        setSelectedPlaylist(value);
    }
//
//
//    public org.primefaces.component.dashboard.Dashboard getDash() {
//        return dash;
//    }
//
//    public void setDash(org.primefaces.component.dashboard.Dashboard dash) {
//        this.dash = dash;
//    }
}