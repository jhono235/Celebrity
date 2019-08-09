package com.example.celebrity;

public class Celebs {

    private static boolean favorite;



    private String firstName;
    private String lastName;
    private static String primaryKey;






    public Celebs(){

    }

    public Celebs(String firstName, String lastName, boolean favorite) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.favorite = favorite;
    }

    public static String getPrimaryKey() {
        return primaryKey;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static boolean isFavorite() {
        return favorite;
    }

    public static void setFavorite(boolean favorite) {
        Celebs.favorite = favorite;
    }


    public static void setKey(String primaryKey) {
        Celebs.primaryKey = primaryKey;
    }
}
