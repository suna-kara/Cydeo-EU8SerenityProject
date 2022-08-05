package eu8.spartan.editor;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanNewBase;
import utilities.SpartanUtil;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.Matchers.*;

@SerenityTest
public class SpartanEditorPostTest extends SpartanNewBase {

    @DisplayName("Editor should be able to POST")
  @Test
    public  void  postSpartanAsEditor(){
        //when you need deserialize or serialize, you dont need to add separate dependency, it comes
        //with serenity
      //create one spartan using util
        Map<String,Object> bodyMap= SpartanUtil.getRandomSpartanMap();
        System.out.println("bodyMap = " + bodyMap);

        //send a post request as editor
        given()
                .auth().basic("editor","editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post("/spartans")
                .then()
                .log().all();

         /*
                status code is 201
                content type is Json
                success message is A Spartan is Born!
                id is not null
                name is correct
                gender is correct
                phone is correct
                check location header ends with newly generated id
         */

        Ensure.that("Status code is 201",x -> x.statusCode(201));
        Ensure.that("Content type is JSON", vR -> vR.contentType(ContentType.JSON));
       Ensure.that("success message is correct",
               thenPart -> thenPart.body("success",is("A Spartan is Born!")));

        Ensure.that("id is not null",
                thenPart -> thenPart.body("data.id",notNullValue()));
                //name is correct
        Ensure.that("name is correct",
                thenPart -> thenPart.body("data.name",is(bodyMap.get("name"))));


        //gender is correct
        Ensure.that("gender is correct",
                thenPart -> thenPart.body("data.gender",is(bodyMap.get("gender"))));

        //phone is correct
        Ensure.that("phone is correct",
                thenPart -> thenPart.body("data.phone",is(bodyMap.get("phone"))));

        //check location header ends with newly generated id
        //get id and save
        String id = lastResponse().jsonPath().getString("data.id");

        Ensure.that("check location header ends with newly generated id",
                vR -> vR.header("Location",endsWith(id))
        );

    }
        /*
            we can give name to each execution using name = ""
            and if you want to get index of iteration we can use {index}
            and also if you to include parameter in your test name
            {0} , {1},{2} --> based on the order you provide as argument.
         */


    }



