package controller;
import views.Home;

public class Main {
    public static void main(String[] args) {
        Home home = new Home();
        do {
            home.HomeScreen();
        } while(true);
    }
}
