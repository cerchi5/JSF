import com.mongodb.MongoClient;
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
                    System.out.println("MUIEEE");
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

        System.out.println(new Username().getUsername());

        try{

            MongoCursor<Document> cur = collection.find().iterator();
            while(cur.hasNext()){
                Document doc = cur.next();

                List list = new ArrayList(doc.values());

                workoutList.add(new Template(list.get(0).toString(),list.get(1).toString(),list.get(2).toString(),list.get(3).toString()));
                System.out.println(list.get(1).toString() + " " + list.get(2).toString() + " " +list.get(3).toString());
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

}

