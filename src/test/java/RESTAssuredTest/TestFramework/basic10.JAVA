package RESTAssuredTest.TestFramework;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


//rahulonlinetutor@gmail.com
//https://mvnrepository.com/artifact/com.github.scribejava/scribejava-core/2.5.3

//https://mvnrepository.com/artifact/com.github.scribejava/scribejava-apis/2.5.3

public class basics9 {

	String ConsumerKey="ztXsFr0ipQTIDue4MD20s4aL3";
	String ConsumerSecret="kuOlC5wB1DZ8ahgOsEos1zLa7C4fLmlpSSB5QEJmFFEX70CT2n";
	String Token="917476985835851778-vOmu4mScebK0zi7hTPXgprO9A7BMLsw";
	String TokenSecret="tCw78M48rJC2mNWpNvZJuXhGUqFgQ8mqowdsjrDbwbQLs";
	String id;
	@Test
	public void getLatestTweet()
	{
		
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
	Response res=	given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
		.queryParam("count", "1")
		.when().get("/home_timeline.json").then().extract().response();
	
	String response =res.asString();
	System.out.println(response);
	JsonPath js= new JsonPath(response);
	
	System.out.println(js.get("text"));
	System.out.println(js.get("id"));
	
	
	}
	@Test
	public void createTweet()
	{
		
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
	Response res=	given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
		.queryParam("status", "I am creating this tweet with Automation")
		.when().post("/update.json").then().extract().response();
	
	String response =res.asString();
	System.out.println(response);
	JsonPath js= new JsonPath(response);
	System.out.println("Below is the tweet added");
	//System.out.println(js.get("text"));
	System.out.println(js.get("id"));
	id=js.get("id").toString();
	
	
	}
	@Test
	public void E2E()
	{
		createTweet();
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
	Response res=	given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
		.when().post("/destroy/"+id+".json").then().extract().response();
	
	String response =res.asString();
	System.out.println(response);
	JsonPath js= new JsonPath(response);
	//System.out.println(js.get("text"));
	System.out.println("Tweet which got deleted with automation is below");
	System.out.println(js.get("text"));
	System.out.println(js.get("truncated"));
	
	
	}
	
	
}
