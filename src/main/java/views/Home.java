package views;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTP;
import service.UserService;
//import jakarta.mail.MessagingException; // Ensure this is the correct import

import java.sql.SQLException;
import java.util.Scanner;

public class Home {
    public void HomeScreen() {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Application!");
        System.out.println("Press 1 to LOGIN");
        System.out.println("Press 2 to SIGN UP");
        System.out.println("Press 0 to EXIT");
        int choice = 0;
        choice = sc.nextInt();
        switch(choice) {
            case 1 -> login();
            case 2 -> signup();
            case 0 -> System.exit(0);            
        }
        
    }

    private void signup() {
        System.out.println("Enter Name :");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("Enter Email :");
        String email = sc.nextLine();
        String generatedOTP = GenerateOTP.generateOTP();
        SendOTP.sendOTP(email , generatedOTP);
        System.out.println("Enter the OTP");
        String otp = sc.nextLine();
        if(otp.equals(generatedOTP)) {
            User user = new User(name , email);
            int response = UserService.saveUser(user);
            switch (response) {
                case 0 -> System.out.println("User registered");
                case 1 -> System.out.println("User already exists");
            }
            System.out.println("Welcome");
        } else {
            System.out.println("WRONG OTP!");
        }
    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter email:");
        String email = sc.nextLine();
        try {
            if(UserDAO.doesExist(email)) {
                String generatedOTP = GenerateOTP.generateOTP();
                SendOTP.sendOTP(email , generatedOTP);
                System.out.println("Enter the OTP");
                String otp = sc.nextLine();
                if(otp.equals(generatedOTP)) {
                    new UserView(email).menu();

                    System.out.println("Welcome to the Logged In");
                } else {
                    System.out.println("WRONG OTP!");
                }
            } else {
                System.out.println("USER DOES NOT EXIST!");
            }
        } catch (SQLException e) {
         e.printStackTrace();
        }
    }
}
