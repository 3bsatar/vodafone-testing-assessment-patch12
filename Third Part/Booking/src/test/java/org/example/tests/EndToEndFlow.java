package org.example.tests;

import io.restassured.response.Response;
import org.example.apis.BookingApi;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.BookingPojo;
import pojo.GetAllIdsPojo;

import static io.restassured.RestAssured.given;

public class EndToEndFlow extends BaseTest {

    @Test
    public void endToEndFlow() {

        String tokenValue = token;
        System.out.println("Using existing token from BaseTest: " + tokenValue);

        BookingPojo booking = new BookingPojo(
                "Mahmoud",
                "Mesalem",
                250,
                true,
                "2025-11-05",
                "2025-11-10",
                "Breakfast"
        );

        Response createBookingResponse = given()
                .spec(requestSpec)
                .body(booking)
                .when()
                .post(BookingApi.CREATE_BOOKING)
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();

        int bookingId = createBookingResponse.jsonPath().getInt("bookingid");
        Assert.assertTrue(bookingId > 0, "Booking ID should be valid!");
        System.out.println("Booking created successfully. ID = " + bookingId);

        booking.setAdditionalneeds("Lunch");

        Response updateResponse = given()
                .spec(requestSpec)
                .cookie("token", tokenValue)
                .body(booking)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();

        String updatedNeed = updateResponse.jsonPath().getString("additionalneeds");
        Assert.assertEquals(updatedNeed, "Lunch", "Additional needs not updated!");
        System.out.println("Booking updated successfully.");

        // Step 4️⃣: Get updated booking
        Response getResponse = given()
                .spec(requestSpec)
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();

        BookingPojo fetchedBooking = getResponse.as(BookingPojo.class);
        Assert.assertEquals(fetchedBooking.getAdditionalneeds(), "Lunch", "Data mismatch after update!");
        System.out.println("Booking retrieved and verified successfully.");

        Response allIdsResponse = given()
                .spec(requestSpec)
                .when()
                .get(BookingApi.GET_ALL_BOOKINGS)
                .then()
                .statusCode(200)
                .extract().response();

        GetAllIdsPojo[] ids = allIdsResponse.as(GetAllIdsPojo[].class);
        Assert.assertTrue(ids.length > 0, "Booking list should not be empty!");
        System.out.println("Retrieved " + ids.length + " booking IDs successfully.");

        System.out.println("End-to-End flow completed successfully!");
    }
}
