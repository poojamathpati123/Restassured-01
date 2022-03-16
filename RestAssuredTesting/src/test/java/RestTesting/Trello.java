package RestTesting;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;



public class Trello {
  public static String id;
	public static String baseurl="https://api.trello.com/";
	@Test(priority=0)
	public void createBoard() {
		//i want to fetch the base url so that i can add other thig to that base url
		RestAssured.baseURI = baseurl;
		//In restAssured we have these keywords
		//given:pre condition includes like parameter,header,authorizaAtion
		//when:condition im testing like resource//i have to provide the nme//that is called resource/1/board
		//then:post condition:is something like response check the response
		
		Response response=given().queryParam("name", "pooja1 moolya board")
		.queryParam("key", "ef662f35181f0a136b4d69ec154657b5")
		.queryParam("token","884d5a6a131b989266e049e2df8680f691c2945b3aac0ca36e7269fa8e1e4f58")
		.header("Content-Type","application/json")
		
		.when()
		.post("/1/board/")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
	
	
		
		String jsonresponse = response.asString();
		//i want to convert the response in to json format
		//why do i use jsonpath to convert the string in to a json format
		JsonPath js = new JsonPath(jsonresponse);
		
		//nw i have to fetch the id
		 id = js.get("id");
		

		
	}
			
	
	@Test(priority = 1)
	public void getBoard()
	{
		RestAssured.baseURI = baseurl;
		
		
	
	Response response =	given()
		.queryParam("key", "ef662f35181f0a136b4d69ec154657b5")
		.queryParam("token", "884d5a6a131b989266e049e2df8680f691c2945b3aac0ca36e7269fa8e1e4f58")
		.header("Content-Type","application/json")
		
		.when()
		.get("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();	
		String jsonresponse = response.asString();
		
		System.out.println(jsonresponse);
	}
		
	
	@Test(priority = 2)
	public void DeleteBoard()
	{
		RestAssured.baseURI = baseurl;
		
		
	
	Response response =	given()
		.queryParam("key", "ef662f35181f0a136b4d69ec154657b5")
		.queryParam("token", "884d5a6a131b989266e049e2df8680f691c2945b3aac0ca36e7269fa8e1e4f58")
		.header("Content-Type","application/json")
		
		.when()
		.delete("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();	
		String jsonresponse = response.asString();
		
		System.out.println(jsonresponse);
	}
		
	}


