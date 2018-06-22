import java.util.Map;

public class Product {

    private String name;
    private String category;
    private String description;
    private String sex;
    private String color;
    private Map<String, Integer> size;
    private int quantity;

    public Product(String name, String category, String description, String color, String sex, Map<String, Integer> size){
        setName(name);
        setCategory(category);
        setDescription(description);
        setSex(sex);
        setColor(color);
        setSize(size);
    }

    public Product(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Map<String, Integer> getSize() {
        return size;
    }

    public void setSize(Map<String, Integer> size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
