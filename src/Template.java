
public class Template {

    private String id;
    private String image;
    private String name;
    private String category;

    public Template(){
        setName(null);
        setCategory(null);
        setImage(null);
    }

    public Template(String id, String name, String category, String image){
        setId(id);
        setName(name);
        setCategory(category);
        setImage(image);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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
}
