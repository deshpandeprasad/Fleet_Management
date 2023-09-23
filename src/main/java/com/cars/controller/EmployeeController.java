package com.cars.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.cars.dao.CarDAO;
import com.cars.dao.UserDAO;
import com.cars.pojo.Car;
import com.cars.pojo.User;

@Controller
public class EmployeeController {
	
// #################################### EMPLOYEE DASHBOARD ############################################	
	@GetMapping("/employee-dashboard.htm")
    public String getEmpMenu(Model model) {
		

        return "employee/employee-dashboard";
    }
	
// #################################### CAR CATALOG ############################################	
	
	@GetMapping("/cars.htm")
    public String getCars(Model model,CarDAO cardao,HttpServletRequest request) throws Exception {
		
		HttpSession session=request.getSession();
		List<Car> cars = cardao.getAllCars();
		model.addAttribute("cars", cars);
        return "employee/cars";
    }
	
// #################################### ADD Cars ############################################
	
		@GetMapping("/add-cars.htm")
		public String addCar(ModelMap model,Car car,HttpServletRequest request) {
			// command object
//		model.addAttribute("car", car);
			
		HttpSession session=request.getSession();
		model.addAttribute("car", car);

			// return form view
			return "employee/add-cars";
		}
		
		@PostMapping("/add-cars.htm")
		public String saveCar(@ModelAttribute("car") Car car, BindingResult result, SessionStatus status, CarDAO cardao,Model model) throws Exception {
			
				
				System.out.println(car.getPlate());
				System.out.println(car.getCarname());
				System.out.println(car.getCarbrand());
				
				car.setReadyForPickup(false);
				
				
				if(cardao.plateExists(car.getPlate()))
				{
					String plateExistsErr="plate already exists";
					System.out.println(plateExistsErr);
					model.addAttribute("plateExistsErr",plateExistsErr);
					return "employee/add-cars";
				}
				
				String fileName = "img_" + System.currentTimeMillis() + "" + new Random().nextInt(100000000) + "" + new Random().nextInt(100000000) + ".jpg";
				car.setImagePath(fileName);
				try {
					car.getImageFile().transferTo(new File("src/main/webapp/images/" + fileName));
				} catch (IllegalStateException e1) {
					System.out.println("IllegalStateException: " + e1.getMessage());
				} catch (IOException e1) {
					System.out.println("IOException: " + e1.getMessage());
				}
				
				
				cardao.save(car);
				
				if(result.hasErrors())
				{
					return "employee/add-cars";
				}
				
				status.setComplete(); //mark it complete
				
			
			return "employee/car-add-success";
		}
		
// #################################### CONFIRM EDIT ############################################	
		
		@GetMapping("/confirm-edit.htm")
		public String getConfirmEdit(Model model, HttpServletRequest request, CarDAO cardao, UserDAO userdao)
				throws Exception {
		
			HttpSession session = request.getSession();
			String carid = request.getParameter("carId");

			
			System.out.println("IN confirm edit method");
			long carId = Long.parseLong(carid);
			Car car = cardao.getCarById(carId);

			model.addAttribute(car);
			
			return "employee/confirm-edit";
		}
		
		
		@PostMapping("/confirm-edit.htm")
		public String postConfirmEdit(SessionStatus status,
				CarDAO cardao, HttpServletRequest request, UserDAO userdao) throws Exception {
			
			System.out.println("############### EMPLOYEE: Confirm EDIT Post Mapping ###############");
			HttpSession session = request.getSession();

			String id = request.getParameter("id");
			long carid = Long.parseLong(id);
			
			
			Car car=cardao.getCarById(carid);
			
			String carname=request.getParameter("carname");
			String carbrand=request.getParameter("carbrand");
			String removeReservation=request.getParameter("removeReservation");
			String removeCurrentUser=request.getParameter("removeCurrentUser");
			
						
			car.setCarId(car.getCarId());
			car.setPlate(car.getPlate());
			car.setCarbrand(carbrand);
			car.setCarname(carname);
			car.setBookingStartDate(car.getBookingStartDate());
			car.setBookingEndDate(car.getBookingEndDate());
			car.setReturnDate(car.getReturnDate());
			car.setReadyForPickup(car.getReadyForPickUp());
			
			if(removeReservation !=null) {
				car.setReservedByUser(null);
				car.setBookingStartDate(null);
				car.setBookingEndDate(null);
				car.setReturnDate(null);
			}
			else {
				car.setReservedByUser(car.getReservedByUser());
				car.setBookingStartDate(car.getBookingStartDate());
				car.setBookingEndDate(car.getBookingEndDate());
				car.setReturnDate(car.getReturnDate());
			}
			
			if(removeCurrentUser !=null) {
				car.setTheUser(null);
				car.setBookingStartDate(null);
				car.setBookingEndDate(null);
				car.setReturnDate(null);
			}
			else {
				car.setTheUser(car.getTheUser());
				car.setBookingStartDate(car.getBookingStartDate());
				car.setBookingEndDate(car.getBookingEndDate());
				car.setReturnDate(car.getReturnDate());
			}
			
//			SET IMAGE PATH
			car.setImagePath(car.getImagePath());
			
			cardao.update(car);
			
			status.setComplete();
			
			return "employee/edit-success";
		}
		
// #################################### CONFIRM DELETE ############################################
		
		@GetMapping("/confirm-delete.htm")
		public String getConfirmDelete(Model model, HttpServletRequest request, CarDAO cardao, UserDAO userdao)
				throws Exception {
		
			HttpSession session = request.getSession();
			String carid = request.getParameter("carId");

			
			System.out.println("IN confirm delete method");
			long carId = Long.parseLong(carid);
			Car car = cardao.getCarById(carId);

			model.addAttribute(car);
			
			return "employee/confirm-delete";
		}

		@PostMapping("/confirm-delete.htm")
		public String postConfirmDelete(SessionStatus status,
				CarDAO cardao, HttpServletRequest request, UserDAO userdao) throws Exception {
			
			System.out.println("############### EMPLOYEE: Confirm EDIT Post Mapping ###############");
			HttpSession session = request.getSession();

			String id = request.getParameter("id");
			long carid = Long.parseLong(id);
			
			
			Car car=cardao.getCarById(carid);
			
			
			cardao.delete(car);
			
			status.setComplete();
			
			return "employee/delete-success";
		}
		
// #################################### USER RESERVATIONS ############################################	
	@GetMapping("/car-reservations.htm")
    public String getReservations(Model model,CarDAO cardao,HttpServletRequest request,SessionStatus status) throws Exception {
		
//		HttpSession session=request.getSession();
//		String username = request.getParameter("username");
//		model.addAttribute("username", username);
		
		
		LinkedHashMap<Car,String> reservedCars = cardao.getReservedAllCars();
		
		if(reservedCars !=null) {
			System.out.println("Got resreved cars");
		}

		model.addAttribute("cars", reservedCars);
//		request.setAttribute("cars", reservedCars);
        return "employee/car-reservations";
        
    }
	
	


 // #################################### CONFIRM PICKUP ############################################
	
	@GetMapping("/confirm-pickup.htm")
	public String getConfirmPickup(Model model, HttpServletRequest request, CarDAO cardao, UserDAO userdao)
			throws Exception {
		
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String carid = request.getParameter("carId");

		long carId = Long.parseLong(carid);
		Car car = cardao.getCarById(carId);
		User user = userdao.getUserByUsername(username);
//		model.addAttribute("car", car);
//		model.addAttribute("username", username);
		
		model.addAttribute("car", car);
		model.addAttribute("username", username);

		System.out.println("userid:" + username);


		return "employee/confirm-pickup";
		
	}
	
	@PostMapping("/confirm-pickup.htm")
	public String setConfirmReservation(SessionStatus status,
			CarDAO cardao, HttpServletRequest request, UserDAO userdao) throws Exception {
		
		System.out.println("############### EMPLOYEE: Confirm Pickup Post Mapping ###############");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		System.out.println("Post username:" + username);
		User user = userdao.getUserByUsername(username);

		String id = request.getParameter("id");
		long carid = Long.parseLong(id);
		
//		String bookingStartDate = request.getParameter("bookingStartDate");
//		System.out.println(bookingStartDate);
//
//		String bookingEndDate = request.getParameter("bookingEndDate");
//		System.out.println(bookingEndDate);
//
//		String returnDate = request.getParameter("returnDate");
//		System.out.println(returnDate);
//
//		String userid = request.getParameter("username");
//		System.out.println(userid);
		
		Car car=cardao.getCarById(carid);
		
		System.out.println(car.getCarId());
		System.out.println(car.getCarname());
		System.out.println(car.getCarbrand());
		System.out.println(car.getPlate());
		System.out.println(car.getBookingStartDate());
//		System.out.print
		
		
		
		car.setCarId(car.getCarId());
		car.setPlate(car.getPlate());
		car.setCarbrand(car.getCarbrand());
		car.setCarname(car.getCarname());
		car.setBookingStartDate(car.getBookingStartDate());
		car.setBookingEndDate(car.getBookingEndDate());
		car.setReturnDate(car.getReturnDate());
		car.setReadyForPickup(false);
		car.setReservedByUser(null);
		car.setTheUser(user);
		
//		SET IMAGE PATH
		car.setImagePath(car.getImagePath());
			
		cardao.update(car);
		
		status.setComplete();
		return "employee/pickup-success";
	}
	
	
// #################################### RETURNS ############################################
	
	@GetMapping("/car-returns.htm")
    public String getCarReturns(Model model,CarDAO cardao,HttpServletRequest request) throws Exception {
		
		HttpSession session=request.getSession();
		String username = request.getParameter("username");
		model.addAttribute("username", username);
		
		LinkedHashMap<Car,String> carsInUse = cardao.getAllCarsInUse();
//		List<Car> carsInUse=cardao.getAllCarsInUse();
		
		if(carsInUse !=null) {
			System.out.println("Got all cars in use");
		}

//		model.addAttribute("cars", carsInUse);
		model.addAttribute("cars", carsInUse);
        return "employee/car-returns";
    }
	
	// #################################### CONFIRM RETURN ############################################
	
		@GetMapping("/confirm-return.htm")
		public String getConfirmReturn(Model model, HttpServletRequest request, CarDAO cardao, UserDAO userdao)
				throws Exception {
			
			HttpSession session = request.getSession();
			String username = request.getParameter("username");
			String carid = request.getParameter("carId");

			long carId = Long.parseLong(carid);
			Car car = cardao.getCarById(carId);
			User user = userdao.getUserByUsername(username);

			
			request.setAttribute("car", car);
			request.setAttribute("username", username);

			System.out.println("userid:" + username);


			return "employee/confirm-return";
			
		}
		
		@PostMapping("/confirm-return.htm")
		public String postConfirmReturn(SessionStatus status,
				CarDAO cardao, HttpServletRequest request, UserDAO userdao) throws Exception {
			
			System.out.println("############### EMPLOYEE: Confirm RETURN Post Mapping ###############");
			HttpSession session = request.getSession();
			String username = request.getParameter("username");
			System.out.println("Post username:" + username);
			User user = userdao.getUserByUsername(username);

			String id = request.getParameter("id");
			long carid = Long.parseLong(id);
			
//			String bookingStartDate = request.getParameter("bookingStartDate");
//			System.out.println(bookingStartDate);
	//
//			String bookingEndDate = request.getParameter("bookingEndDate");
//			System.out.println(bookingEndDate);
	//
//			String returnDate = request.getParameter("returnDate");
//			System.out.println(returnDate);
	//
//			String userid = request.getParameter("username");
//			System.out.println(userid);
			
			Car car=cardao.getCarById(carid);
			
			System.out.println(car.getCarId());
			System.out.println(car.getCarname());
			System.out.println(car.getCarbrand());
			System.out.println(car.getPlate());
			System.out.println(car.getBookingStartDate());
//			System.out.print
			
			
			car.setCarId(car.getCarId());
			car.setPlate(car.getPlate());
			car.setCarbrand(car.getCarbrand());
			car.setCarname(car.getCarname());
			car.setBookingStartDate(null);
			car.setBookingEndDate(null);
			car.setReturnDate(null);
			car.setReadyForPickup(false);
			car.setReservedByUser(null);
			car.setTheUser(null);
			
//			SET IMAGE PATH
			car.setImagePath(car.getImagePath());
			
			cardao.update(car);
			
			status.setComplete();
			return "employee/return-success";
		}

}

