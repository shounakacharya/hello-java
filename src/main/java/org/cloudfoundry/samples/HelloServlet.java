package org.cloudfoundry.samples;

import java.io.IOException;
import java.io.PrintWriter;
import com.lambdaworks.redis.*;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private String mongoOps(){
		try{
			MongoClient mongoClient = MongoClients.create("mongodb://10.0.0.208:27017");
			MongoDatabase database = mongoClient.getDatabase("mydb");
			MongoCollection<Document> collection = database.getCollection("mycollection");
			Document myDoc = collection.find().first();
			return myDoc.toJson();
		}catch(Exception e){
			return "Connection unsuccessful "+e.getMessage();
		}
	}
	private String redisOps(){
		try{
			RedisClient redisClient = new RedisClient(
      			RedisURI.create("redis://10.0.0.208:6379"));
    			RedisConnection<String, String> connection = redisClient.connect();

    			System.out.println("Connected to Redis");
			connection.set("shounak", "acharya");
			String value = connection.get("shounak");
    			connection.close();
    			redisClient.shutdown();
			return value;
		}catch(Exception e){
			return "Redis exception "+e.getMessage();
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setStatus(200);
		PrintWriter writer = response.getWriter();
		//writer.println("Hello from " + System.getenv("VCAP_APP_HOST") + ":" + System.getenv("VCAP_APP_PORT")+" Mongo Connection "+mongoOps());
		//writer.println("Message from redis for key shounak is "+redisOps());
		writer.println("Java Code Succeeded - Update 4");
		writer.close();
	}
}
