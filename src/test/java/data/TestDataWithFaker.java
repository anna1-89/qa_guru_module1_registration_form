package data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestDataWithFaker {
    public static String fileNameT = "picture.png";

    Faker fakerEn = new Faker(new Locale("en-US"));
    Faker fakerRu = new Faker(new Locale("ru"));
    public final String firstNameT = fakerRu.name().firstName();
    public final String lastNameT = fakerRu.name().lastName();
    public final String emailT = fakerEn.internet().emailAddress();
    public final String genderT = fakerEn.options().option("Male", "Female", "Other");
    public final String numberT = String.valueOf(fakerEn.number().randomNumber(10,true));
    public final String invalidNumberT = String.valueOf(fakerEn.number().randomNumber(9,false));;
    public final String monthBirthDateT = fakerEn.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    public final String yearBirthDateT = String.valueOf(fakerEn.number().numberBetween(1989, 2010));
    public final String birthDateT = String.valueOf(fakerEn.number().numberBetween(10,30));
    public final String addressT = fakerRu.address().fullAddress();

    public final String subjectT = fakerEn.options().option("Maths", "Physics", "Chemistry", "Biology", "English", "Computer Science" , "Economics", "Arts" , "History", "Civics");

    public final String hobbyT = fakerEn.options().option("Sports", "Reading", "Music");

    public final String stateT = fakerEn.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");

    public final String cityT = getRandomCityForState(stateT);

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
