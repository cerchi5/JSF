import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class MongoConnect {

    public static boolean logIn(String user, String password){
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("accounts");

        try{

            MongoCursor<Document> cur = collection.find().iterator();
            while(cur.hasNext()){
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                if(list.get(1).toString().compareTo(user) == 0 && list.get(2).toString().compareTo(password) == 0){
                    return true;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        client.close();
        return false;
    }

    public static boolean register(String user, String password){
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("accounts");

//        db insert confirm and email, verify confirm and pass, verify mail

        try{

            MongoCursor<Document> cur = collection.find().iterator();

            boolean ok = true;

            while(cur.hasNext()){
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                if(list.get(1).toString().compareTo(user) == 0 && ok){
                    ok = false;
                }

            }

            if(ok){
                Document doc = new Document();
                doc.append("username", user);
                doc.append("password", password);
                collection.insertOne(doc);
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        client.close();
        return false;
    }

    public static void printAccounts(){
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("accounts");

        try{
            MongoCursor<Document> cur = collection.find().iterator();

            while(cur.hasNext()) {
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                System.out.println(list.get(1) + "   " + list.get(2));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getId(String username){
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("accounts");

        try{

            MongoCursor<Document> cur = collection.find().iterator();
            while(cur.hasNext()){
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                if(list.get(1).toString().compareTo(username) == 0){
                    return list.get(1).toString();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        client.close();
        return null;
    }

    public static boolean validateSubscriber(String emailSubscriber){
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("subscribers");

        System.out.println(emailSubscriber);

        try{

            MongoCursor<Document> cur = collection.find().iterator();
            while(cur.hasNext()) {

                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                if(list.get(1).toString().compareTo(emailSubscriber) == 0){
                    return false;
                }

            }

            Document newSubscriber = new Document();
            newSubscriber.put("email",emailSubscriber);

            collection.insertOne(newSubscriber);

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public static ArrayList<Template> getAllWorkouts(){
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("workoutlist");

        ArrayList<Template> workoutList = new ArrayList<Template>();

//        System.out.println(new Username().getUsername());

        try{

            MongoCursor<Document> cur = collection.find().iterator();
            while(cur.hasNext()){
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                workoutList.add(new Template(list.get(0).toString(),list.get(1).toString(),list.get(2).toString(),list.get(3).toString()));
//                System.out.println(list.get(1).toString() + " " + list.get(2).toString() + " " +list.get(3).toString());
            }

            return workoutList;

        }catch (Exception e){
            e.printStackTrace();
        }

        client.close();
        return null;
    }

    public static boolean addUserWorkout(String userId, String workoutId, int series){
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection(userId);

        try {
                Document doc = new Document();
                doc.append("workout", workoutId);
                doc.append("series", series);
                collection.insertOne(doc);
                return true;

        }catch (Exception e){
            e.printStackTrace();
        }

        client.close();
        return false;

    }

    public static ArrayList<PlaylistTemplate> getPlaylistsFrom(String username){
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("playlists");

        ArrayList<String> playlists = new ArrayList<String>();

        try {

            ArrayList<PlaylistTemplate> playlistTemplateArrayList = new ArrayList<PlaylistTemplate>();

            MongoCursor<Document> cur = collection.find().iterator();
            while (cur.hasNext()) {
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                if(list.get(1).toString().compareTo(username) == 0) {

//                    System.out.println(list.get(1).toString());
//                    System.out.println(list.get(2).toString());
//                    System.out.println(list.get(3).toString());
//
//                    List<Document> workouts = (List<Document>) list.get(4);
//
//                    for (Document x : workouts) {
//                        System.out.println(x.getString("workoutName"));
//                        System.out.println(x.getDouble("reps").intValue());
//                    }

                    String owner = list.get(1).toString();
                    String category = list.get(2).toString();
                    String playlistName = list.get(3).toString();

                    PlaylistTemplate auxPlaylist = new PlaylistTemplate(playlistName,category,owner);

                    List<Document> workouts = (List<Document>) list.get(4);

                    for(Document x : workouts)
                        auxPlaylist.addWorkout(new WorkoutTemplate(x.getString("workoutName"),x.getDouble("reps").intValue()));


                    playlistTemplateArrayList.add(auxPlaylist);

                }

            }

            if(playlistTemplateArrayList.size() < 1)
                return null;
            else return playlistTemplateArrayList;
        }catch (Exception e){
            e.printStackTrace();
        }
         return null;
    }

    public static boolean addWorkoutInPlaylist(String username, String category, String playlist, String workoutName, int reps){
        MongoClient client = new MongoClient("localhost", 27017);

        com.mongodb.client.MongoDatabase database = client.getDatabase("website");

        MongoCollection<Document> collection = database.getCollection("playlists");

        double reps2 = (double) reps;

        try {

            Document initial = new Document("username",username);
            initial.append("category",category);
            initial.append("playlistName",playlist);

            Document toAdd = new Document("workouts", new Document("workoutName",workoutName).append("reps",reps));

            Document update = new Document("$push",toAdd);

            collection.updateOne(initial,update);

        }catch (MongoException e){
            e.printStackTrace();
        }

        return false;
    }

}

