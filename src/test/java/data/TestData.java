package data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {
    public static String fileName = "01.jpg";

    Faker fakerEn = new Faker(new Locale("en-US"));
    Faker fakerRu = new Faker(new Locale("ru"));
    public final String firstName = fakerRu.name().firstName();
    public final String lastName = fakerRu.name().lastName();
    public final String email = fakerEn.internet().emailAddress();
    public final String gender = fakerEn.options().option("Male", "Female", "Other");
    public final String number = String.valueOf(fakerEn.number().randomNumber(10,true));
    public final String invalidNumber = String.valueOf(fakerEn.number().randomNumber(9,false));;
    public final String monthBirthDate = fakerEn.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    public final String yearBirthDate = String.valueOf(fakerEn.number().numberBetween(1989, 2010));
    public final String birthDate = String.valueOf(fakerEn.number().numberBetween(10,30));
    public final String address = fakerRu.address().fullAddress();

    public final String subject = fakerEn.options().option("Maths", "Physics", "Chemistry", "Biology", "English", "Computer Science" , "Economics", "Arts" , "History", "Civics");

    public final String hobby = fakerEn.options().option("Sports", "Reading", "Music");

    public final String state = fakerEn.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");

    public final String city = getRandomCityForState(state);

    public String getRandomCityForState(String state) {
        String value = "";
        switch (state) {
            case "NCR":
                value = fakerEn.options().option("Delhi", "Gurgaon", "Noida");
                break;
            case "Uttar Pradesh":
                value = fakerEn.options().option("Agra", "Lucknow", "Merrut");
                break;
            case "Haryana":
                value = fakerEn.options().option("Karnal", "Panipat");
                break;
            case "Rajasthan":
                value = fakerEn.options().option("Jaipur", "Jaiselmer");
                break;
            default:
                value="";
        }
        return value;
    }

}
