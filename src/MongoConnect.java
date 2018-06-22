import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import javax.print.Doc;
import javax.servlet.http.HttpSession;
import java.util.*;

import static java.util.Arrays.asList;

public class MongoConnect {

    public static boolean logIn(String user, String password) {
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("accounts");

        try {

            MongoCursor<Document> cur = collection.find().iterator();
            while (cur.hasNext()) {
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                if (list.get(1).toString().compareTo(user) == 0 && list.get(2).toString().compareTo(password) == 0) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        client.close();
        return false;
    }

    public static boolean register(String user, String password) {
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("accounts");

//        db insert confirm and email, verify confirm and pass, verify mail

        try {

            MongoCursor<Document> cur = collection.find().iterator();

            boolean ok = true;

            while (cur.hasNext()) {
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                if (list.get(1).toString().compareTo(user) == 0 && ok) {
                    ok = false;
                }

            }

            if (ok) {
                Document doc = new Document();
                doc.append("username", user);
                doc.append("password", password);
                collection.insertOne(doc);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        client.close();
        return false;
    }

    public static void printAccounts() {
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("accounts");

        try {
            MongoCursor<Document> cur = collection.find().iterator();

            while (cur.hasNext()) {
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                System.out.println(list.get(1) + "   " + list.get(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getId(String username) {
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("accounts");

        try {

            MongoCursor<Document> cur = collection.find().iterator();
            while (cur.hasNext()) {
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                if (list.get(1).toString().compareTo(username) == 0) {
                    return list.get(1).toString();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        client.close();
        return null;
    }

    public static boolean validateSubscriber(String emailSubscriber) {
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("subscribers");

        System.out.println(emailSubscriber);

        try {

            MongoCursor<Document> cur = collection.find().iterator();
            while (cur.hasNext()) {

                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                if (list.get(1).toString().compareTo(emailSubscriber) == 0) {
                    return false;
                }

            }

            Document newSubscriber = new Document();
            newSubscriber.put("email", emailSubscriber);

            collection.insertOne(newSubscriber);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public static ArrayList<Template> getAllWorkouts() {
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("workoutlist");

        ArrayList<Template> workoutList = new ArrayList<Template>();

//        System.out.println(new Username().getUsername());

        try {

            MongoCursor<Document> cur = collection.find().iterator();
            while (cur.hasNext()) {
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                workoutList.add(new Template(list.get(0).toString(), list.get(1).toString(), list.get(2).toString(), list.get(3).toString()));
//                System.out.println(list.get(1).toString() + " " + list.get(2).toString() + " " +list.get(3).toString());
            }

            return workoutList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        client.close();
        return null;
    }

    public static ArrayList<Template> getWorkoutsByCategory(String category) {
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("workoutlist");

        ArrayList<Template> workoutList = new ArrayList<Template>();


        try {

            MongoCursor<Document> cur = collection.find().iterator();
            while (cur.hasNext()) {
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                if (category.compareTo(list.get(2).toString()) == 0) {
                    workoutList.add(new Template(list.get(0).toString(), list.get(1).toString(), list.get(2).toString(), list.get(3).toString()));
                }
            }
            return workoutList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        client.close();
        return null;
    }

    public static boolean addUserWorkout(String userId, String workoutId, int series) {
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection(userId);

        try {
            Document doc = new Document();
            doc.append("workout", workoutId);
            doc.append("series", series);
            collection.insertOne(doc);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        client.close();
        return false;

    }

    public static ArrayList<WorkoutTemplate> getPlaylistFrom(String username, String selectedPlaylist) {
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("playlists");

        try {

            MongoCursor<Document> cur = collection.find().iterator();

            ArrayList<WorkoutTemplate> result;

            while (cur.hasNext()) {
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                if (list.get(1).toString().compareTo(username) == 0 && list.get(3).toString().compareTo(selectedPlaylist) == 0) {

                    result = new ArrayList<WorkoutTemplate>();

                    List<Document> workouts = (List<Document>) list.get(4);

                    for (Document x : workouts) {
                        result.add(new WorkoutTemplate(x.getString("workoutName"), x.getDouble("reps").intValue()));
//                        System.out.println(x.getString("workoutName") + "  !!!!   " + x.getDouble("reps").intValue());
                    }

                    return result;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<PlaylistTemplate> getPlaylistsFrom(String username) {
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("playlists");

//        boolean ok = false;

        try {

            ArrayList<PlaylistTemplate> playlistTemplateArrayList = new ArrayList<PlaylistTemplate>();

            MongoCursor<Document> cur = collection.find().iterator();
            while (cur.hasNext()) {
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                if (list.get(1).toString().compareTo(username) == 0) {

                    String owner = list.get(1).toString();
                    String category = list.get(2).toString();
                    String playlistName = list.get(3).toString();

                    PlaylistTemplate auxPlaylist = new PlaylistTemplate(playlistName, category, owner);


                    if(list.size() == 5) {

                        if (list.get(4) != null) {
                            List<Document> workouts = (List<Document>) list.get(4);

                            for (Document x : workouts)
                                auxPlaylist.addWorkout(new WorkoutTemplate(x.getString("workoutName"), x.getDouble("reps").intValue()));
                        }
                    }else auxPlaylist.setWorkouts(null);
                    playlistTemplateArrayList.add(auxPlaylist);
                }
            }
            if (playlistTemplateArrayList.size() < 1)
                return null;
            else return playlistTemplateArrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createPlaylist(String username, String playlistName){
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("playlists");

        Document newPlaylist = new Document();

        newPlaylist.append("username",username);
        newPlaylist.append("category","mixed");
        newPlaylist.append("playlistName",playlistName);
        Map workouts = Collections.emptyMap();
        newPlaylist.append("workouts", Collections.emptyMap());
        collection.insertOne(newPlaylist);

//        Document initial = new Document("username",username);
//        initial.append("playlistName",playlistName);
//
//        List<Document> workouts = Collections.emptyList();
//        Document toAdd = new Document("workouts",workouts);
//        Document update = new Document("$set",toAdd);
//        collection.updateOne(initial,update);

    }

    public static boolean addWorkoutInPlaylist(String username, String category, String playlist, String workoutName, int reps) {

        try {
            MongoClient client = new MongoClient("localhost", 27017);

            com.mongodb.client.MongoDatabase database = client.getDatabase("website");

            MongoCollection<Document> collection = database.getCollection("playlists");

            double reps2 = (double) reps;

            Document initial = new Document("username", username);
            initial.append("playlistName", playlist);

            Document toAdd = new Document("workouts", new Document("workoutName", workoutName).append("reps", reps2));

            Document update = new Document("$push", toAdd);

            collection.updateOne(initial, update);

        } catch (MongoException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void updatePlaylistWorkouts(String username, String playlistName, ArrayList<WorkoutTemplate> newPlaylist) {
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("playlists");

        try {

            Document initial = new Document("username", username);
            initial.append("playlistName", playlistName);

            Document newElement, update;

            for (int i = 0; i < newPlaylist.size(); i++) {
                if (i == 0) {
                    List<Document> workouts = new ArrayList<Document>();
                    newElement = new Document("workoutName", newPlaylist.get(i).getName()).append("reps", (double) newPlaylist.get(i).getReps());

                    workouts.add(newElement);


                    update = new Document("$set", new Document("workouts", workouts));
                    collection.updateOne(initial, update);
                } else {
                    newElement = new Document("workoutName", newPlaylist.get(i).getName()).append("reps", (double) newPlaylist.get(i).getReps());
                    update = new Document("$push", new Document("workouts", newElement));
                    collection.updateOne(initial, update);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void updateReps(String username, String playlistName, String workoutName, int newReps){
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("playlists");

        try {
            Document initial = new Document("username", username);
            initial.append("playlistName", playlistName);
            initial.append("workouts.workoutName",workoutName);

            Document update = new Document("$set",new Document("workouts.$.reps",(double) newReps));

            collection.updateOne(initial,update);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void removeWorkout(String username, String playlistName, String workoutName){
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("playlists");

        try {
            Document initial = new Document("username", username);
            initial.append("playlistName", playlistName);

            Document update = new Document("$pull",new Document("workouts",new Document("workoutName",workoutName)));

            collection.updateOne(initial,update);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getProducts(){
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("products");

        try {

            Product aux;
            ArrayList<Product> products = new ArrayList<Product>();

            MongoCursor<Document> cur = collection.find().iterator();
            while (cur.hasNext()) {
                Document doc = cur.next();

                List list = new ArrayList(doc.values());
                aux = new Product();

                aux.setName(list.get(1).toString());
                System.out.println(list.get(1).toString());
                products.add(aux);
            }

//            return products;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        client.close();
//        return null;
    }

    public static ArrayList<Product> getAllProducts(){
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("products");

        try{

            Product aux;
            ArrayList<Product> products = new ArrayList<Product>();

            MongoCursor<Document> cur = collection.find().iterator();
            while (cur.hasNext()) {
                Document doc = cur.next();

                List list = new ArrayList(doc.values());
                aux = new Product();

                aux.setName(list.get(1).toString());
                aux.setDescription(list.get(2).toString());
                aux.setSex(list.get(3).toString());
                aux.setColor(list.get(4).toString());
                aux.setCategory(list.get(6).toString());
                System.out.println(list.get(1).toString() + "  " + list.get(5));

                if(aux.getCategory().compareTo("accesories") == 0){
                    aux.setQuantity(Integer.parseInt(list.get(5).toString()));
//                }else{
//                    if(aux.getCategory().compareTo("shoes") == 0 && aux.getSex().compareTo("female") == 0){
//                        Document x = (Document) list.get(5);
//                        Map<String,Integer> map = new HashMap<String, Integer>();
//                        map.put("36", x.getDouble("36").intValue());
//                        map.put("37", x.getDouble("37").intValue());
//                        map.put("38", x.getDouble("38").intValue());
//
//                        aux.setSize(map);
//                    } else if(aux.getCategory().compareTo("shoes") == 0 && aux.getSex().compareTo("male") == 0){
//                        Document x = (Document) list.get(5);
//                        Map<String,Integer> map = new HashMap<String, Integer>();
//                        map.put("41", (int) Double.parseDouble(x.get(0).toString()));
//                        map.put("42", (int) Double.parseDouble(x.get(1).toString()));
//                        map.put("43", (int) Double.parseDouble(x.get(2).toString()));
//                        System.out.println(Double.parseDouble(x.get(0).toString()));
//                        System.out.println(Double.parseDouble(x.get(1).toString()));
//                        System.out.println(Double.parseDouble(x.get(2).toString()));
//
//                        aux.setSize(map);
//                    } else{
//                        Document x = (Document) list.get(5);
//                        Map<String,Integer> map = new HashMap<String, Integer>();
//                        map.put("S", x.getDouble("S").intValue());
//                        map.put("M", x.getDouble("M").intValue());
//                        map.put("L", x.getDouble("L").intValue());
//
//                        aux.setSize(map);
//                    }
                }

                products.add(aux);

                System.out.println(list.get(1) + " ... " + list.get(5));

            }

            return products;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

}


