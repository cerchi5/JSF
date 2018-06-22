import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

@ManagedBean(name = "Sportswear")
@SessionScoped
public class Sportswear implements Serializable {

    private ArrayList<Product> allProducts;
    private ArrayList<Product> tshirts;
    private ArrayList<Product> pants;
    private ArrayList<Product> shoes;
    private ArrayList<Product> accessories;

    public Sportswear(){
        MongoConnect.getProducts();
        tshirts = new ArrayList<Product>();
        pants = new ArrayList<Product>();
        shoes = new ArrayList<Product>();
        accessories = new ArrayList<Product>();

        for(Product x : allProducts){
            if(x.getCategory().compareTo("tshirt") == 0)
                tshirts.add(x);
            if(x.getCategory().compareTo("pants") == 0)
                pants.add(x);
            if(x.getCategory().compareTo("shoes") == 0)
                shoes.add(x);
            if(x.getCategory().compareTo("accessories") == 0)
                accessories.add(x);
        }
    }

    public void setAllProducts(ArrayList<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public ArrayList<Product> getTshirts() {
        return tshirts;
    }

    public ArrayList<Product> getPants() {
        return pants;
    }

    public ArrayList<Product> getShoes() {
        return shoes;
    }

    public ArrayList<Product> getAccessories() {
        return accessories;
    }
}
