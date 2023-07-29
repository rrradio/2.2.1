package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public User getUserOwnsCar(String model, int series) {

      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery
              ("FROM User WHERE car.model = '" + model + "'" + " AND car.series = '" + series + "'");

      try {
         return query.getSingleResult();
      } catch (NoResultException e) {
         System.out.println("пользователя с такой машиной нет в базе данных");
      }

      return null;


   }


   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
