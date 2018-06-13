import org.omg.PortableInterceptor.SUCCESSFUL;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean(name = "Username")
@SessionScoped

public class Username implements Serializable{

    public String username;
    private String email;
    private String password;
    private String confirmPassword;

    public Username(){
        username = null;
        email = null;
        password = null;
        confirmPassword = null;
    }

    public String validateLogIn() {

//        System.out.println(username + "   " + password);

        if (!MongoConnect.logIn(username, password)){
            System.out.println(false);
            setUsername(null);
            setPassword(null);
            return null;
        }else{

            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("username",username);

        }
        return "";
    }

    public String validateRegister(){

//        System.out.println(username + " " + password + " " + email + " ");

        if(!MongoConnect.register(username,password)){
            System.out.println(false);
            setUsername(null);
            setPassword(null);
            setEmail(null);
            setConfirmPassword(null);
            return null;
        }else{

            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("username",username);

        }

        return "";
    }

    public void setUsername(String username) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username",username);
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return email;

    }
}
