package com.cars.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.cars.exception.CarException;
import com.cars.pojo.Car;
import com.cars.pojo.User;

@Component
public class CarDAO extends DAO {

	public CarDAO() {
	}
	
	
// ######################################### CRUD OPERATIONS ###########################################	

	public void save(Car car) throws CarException {
		try {
			begin();
			getSession().save(car);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

	public void update(Car car) throws CarException {
		try {
			begin();
			getSession().update(car);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

	public void delete(Car car) throws CarException {
		try {
			begin();
			getSession().delete(car);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}finally {
			close();
		}
	}

// ######################################### get all cars ###########################################		
	
	public List<Car> getAllCars() throws CarException {
		List<Car> cars = new ArrayList<Car>();
		try {
			begin();
			Criteria criteria = getSession().createCriteria(Car.class);
			cars = criteria.list();
			commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return cars;

	}
	
// ######################################### get all reserved cars ###########################################	
	
	public List<Car> getReservedCars() throws CarException {

		List<Car> cars = new ArrayList<Car>();
		List<Car> reservedCars = new ArrayList<Car>();

		try {
			begin();
			Criteria criteria = getSession().createCriteria(Car.class);
			cars = criteria.list();
			commit();
			for (Car car : cars) {
				if (car.getReadyForPickUp() == true) {
					reservedCars.add(car);
				}
			}

		} catch (HibernateException e) {
//			throw new Exception("Could not list reserved cars: ", e);
			e.printStackTrace();

		}finally {
			close();
		}
		return reservedCars;

	}

	public List<Car> getReservedCars1() throws CarException {

		List<Car> reservedCars = new ArrayList<Car>();

		for (Car car : this.getAllCars()) {
			if (car.getReadyForPickUp() == true) {
				reservedCars.add(car);
			}
		}

		return reservedCars;

	}
	
	public LinkedHashMap<Car,String> getReservedAllCars() throws CarException  {
		LinkedHashMap<Car, String> cars = new LinkedHashMap<Car, String>();
		
		for(Car car: this.getAllCars())
		{
			if(car.getReservedByUser()!=null && car.getTheUser()==null)
			{
				String username=car.getReservedByUser().getUsername();
				cars.put(car, username);
			}
		}
		return cars;
	}
	
// ######################################### get cars in use ###########################################	
	
	public LinkedHashMap<Car,String>getAllCarsInUse() throws CarException {
		LinkedHashMap<Car, String> cars = new LinkedHashMap<Car, String>();

		for(Car car : this.getAllCars())
		{
			if(car.getTheUser()!=null && car.getReservedByUser()==null)
			{
				String username=car.getTheUser().getUsername();
				cars.put(car, username);
			}
		}
		return cars;
	}
	
// ######################################### get reserved cars by user ###########################################		
	
	public List<Car> getReservedCarsByUser(User user) throws CarException {

		List<Car> cars=new ArrayList<Car>();
		try {
		begin();
		Query q = getSession().createQuery("from Car where reservedByUser =:user");
		q.setEntity("user", user);
		cars = (List<Car>) q.list();
		commit();
		}
		catch(HibernateException e)
		{
//			throw new Exception("Could not Cars of user: ", e);
			e.printStackTrace();
		}finally {
			close();
		}
		return cars;
	}

// #########################################  cars in use by user ###########################################	
	
	public List<Car> getCarsInUserByUser(User user) throws CarException {

		List<Car> cars=new ArrayList<Car>();
		try {
		begin();
		Query q = getSession().createQuery("from Car where theUser =:user");
		q.setEntity("user", user);
		cars = (List<Car>) q.list();
		commit();
		}
		catch(HibernateException e)
		{
//			throw new Exception("Could not find Cars in use of user: ", e);
			e.printStackTrace();
		}finally {
			close();
		}
		return cars;
	}

// ######################################### cars by id ###########################################	
	
	public Car getCarById(long carId) throws CarException {

//		Car car=new Car();
		try {
			begin();
			Query q = getSession().createQuery("from Car where carId = :carId");
			q.setLong("carId", carId);
			Car car = (Car) q.uniqueResult();
			commit();
			return car;
			
		} catch (HibernateException e) {
			rollback();
			throw new CarException("Could not find car by Id: ", e);
//			e.printStackTrace();
		}finally {
			close();
		}
//		return null;
	}
	
// ######################################### check if plate exists #####################################
	
	public boolean plateExists(String plate) throws CarException {
		
		try {

			Query q = getSession()
					.createQuery("from Car where plate = :plate");
			q.setString("plate", plate);
			Object obj = q.uniqueResult();
			if (obj == null) {
				return false;
			}

		} catch (Exception e) {
			rollback();
			e.printStackTrace();
		} finally {
			close();
		}
		return true;
		
	}
	
}
