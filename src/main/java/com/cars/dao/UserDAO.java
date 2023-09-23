package com.cars.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.cars.exception.CarException;
import com.cars.pojo.Car;
import com.cars.pojo.User;

@Component
public class UserDAO extends DAO {

	public UserDAO() {
	}

	public void save(User user) throws CarException {
		try {
			begin();
			getSession().save(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void update(User user) throws CarException {
		try {
			begin();
			getSession().update(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void delete(User user) throws CarException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public boolean validateCustomer(String username, String password) throws CarException {
		try {

			Query q = getSession()
					.createQuery("from User where username=:username and password=:password and role='customer'");
			q.setString("username", username);
			q.setString("password", password);
			Object obj = q.uniqueResult();
			if (obj == null) {
				return false;
			}

		} catch (HibernateException e) {
//			rollback();
			e.printStackTrace();
		} finally {
			close();
		}

		return true;

	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public boolean validateAdmin(String username, String password) throws CarException {
		try {

			Query q = getSession()
					.createQuery("from User where username=:username and password=:password and role='admin'");
			q.setString("username", username);
			q.setString("password", password);
			Object obj = q.uniqueResult();
			if (obj == null) {
				return false;
			}

		} catch (Exception e) {
//			rollback();
			e.printStackTrace();
		} finally {
			close();
		}

		return true;

	}

	public boolean validateEmployee(String username, String password) throws CarException {
		try {

			Query q = getSession()
					.createQuery("from User where username=:username and password=:password and role='employee'");
			q.setString("username", username);
			q.setString("password", password);
			Object obj = q.uniqueResult();
			if (obj == null) {
				return false;
			}

		} catch (Exception e) {
//			rollback();
//			e.printStackTrace();
			throw new CarException("Employee validation Error: ", e);
		} finally {
			close();
		}

		return true;

	}

	public User getUserById(long userid) throws CarException {

		try {
			begin();
			Query q = getSession().createQuery("from User where userid = :userid");
			q.setLong("userid", userid);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
//			e.printStackTrace();
			throw new CarException("Could not user by Id: ", e);
		} finally {
			close();
		}

	}

	public User getUserByUsername(String username) throws CarException {

		try {
//			begin();
			Query q = getSession().createQuery("from User where username = :username");
			q.setString("username", username);
			User user = (User) q.uniqueResult();
//			commit();
			return user;
		} catch (Exception e) {
			rollback();
			throw new CarException("Could not user by username: ", e);
		} finally {
			close();
		}
	}

	public List<User> getAllUsers() throws CarException {
		List<User> users = new ArrayList<User>();
		try {
			begin();
			Criteria criteria = getSession().createCriteria(User.class);
			users = criteria.list();
			commit();
			return users;
		} catch (Exception e) {
			throw new CarException("Could not list users: ", e);
		} finally {
			close();
		}

	}

	public List<Car> getAllReservedCars() throws CarException {
		List<Car> cars = new ArrayList<Car>();
		try {

			Criteria criteria = getSession().createCriteria(Car.class);
			cars = criteria.list();
//			commit();
			return cars;
		} catch (Exception e) {
			throw new CarException("Could not list all cars: ", e);
		} finally {
			close();
		}

	}
}