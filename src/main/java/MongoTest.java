import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

/**
 * Created by alozta on 8/16/16.
 */
public class MongoTest {

    public static void main(String [] args){

        try {
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            DB db = mongoClient.getDB("testdb");     //creates db if not exist
            //boolean auth = db.authenticate("username", "password".toCharArray());     //if password protected

            DBCollection table = db.getCollection("user");  //creates collection if not exist

            //insert
            BasicDBObject document = new BasicDBObject();
            document.put("name", "jessie");
            document.put("age", 28);
            document.put("createdDate", new Date());
            table.insert(document);
            document=new BasicDBObject();
            document.put("name", "james");
            document.put("age", 30);
            document.put("createdDate", new Date());
            table.insert(document);
            //
            //find and display
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("age", "28");
            DBCursor cursor = table.find(searchQuery);

            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            //
            //update
            BasicDBObject query = new BasicDBObject();
            query.put("name", "jessie");
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("age", "29");

            BasicDBObject updateObj = new BasicDBObject();
            updateObj.put("$set", newDocument);
            table.update(query, updateObj);
            //
            //find and display
            BasicDBObject searchQuery2 = new BasicDBObject().append("name", "jessie");
            DBCursor cursor2 = table.find(searchQuery2);

            while (cursor2.hasNext()) {
                System.out.println(cursor2.next());
            }
            //
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
