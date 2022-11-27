package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      // _____люди с машинами:______
      userService.add(new User("Azat", "Yulm", "A@icloud.com"
              ,new Car("Mazda",6)));
      userService.add(new User("Zaur", "Tregulov", "Zaur@icloud.com"
              ,new Car("BMW",530)));
      userService.add(new User("Nail", "Alishev", "Nail@icloud.com"
              ,new Car("Lada",21010)));
      userService.add(new User("Semion", "Fomichiov", "Nail@icloud.com"
              ,new Car("Porche",911)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " +user.getUsersCar());
         System.out.println();
      }

      System.out.println("Поиск авто по модели и серии");

      System.out.println(userService.getUserByCar("Porche", 911));


      context.close();
   }
}
