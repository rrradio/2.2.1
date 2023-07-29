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

      User Andrey = new User("Andrey", "Andreev", "aAndreev@mail.ru");
      User ALeksey = new User("Leha", "Alekseev", "lAlekseev@mail.ru");
      User Lena = new User("Elena", "Malisheva", "eMalisheva@mail.ru");

       Car Zapor = new Car("ZAPOROJEC", 222);
       Car Jiga = new Car("jiguli", 2109);
       Car bmw = new Car("bmw", 224);

       Andrey.setCar(Zapor);
       Lena.setCar(Jiga);
       ALeksey.setCar(bmw);

      userService.add(Andrey);
      userService.add(Lena);
      userService.add(ALeksey);


      userService.add(new User("Dima", "Nikiforov", "dNikiforov@mail.ru", new Car("Toyota", 111)));
      
      System.out.println(userService.getUserOwnsCar("Toyota", 111));



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Tachka = "+user.getCar());
         System.out.println();
      }

      context.close();
   }
}
