package com.cars.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cars.dao.CarDAO;
import com.cars.dao.UserDAO;
import com.cars.exception.CarException;
import com.cars.pojo.Car;
import com.cars.pojo.User;
import com.cars.service.EmailService;

import com.mysql.cj.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class CustomerController {

	//Send email -- lets' see if this works !!
	 @Autowired
	    private EmailService emailService;
// ################################### DASHBOARD ##########################################################		

	@GetMapping("/customer-dashboard.htm")
	public String getCustomerDashboard(Model model, HttpServletRequest request) {
		return "customer/customer-dashboard";
	}

// ################################### BROWSE CARS ##########################################################	

	@GetMapping("/browse-cars.htm")
	public String getBrowseCars(Model model, CarDAO cardao,UserDAO userdao, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		model.addAttribute("username", username);

		List<Car> cars = cardao.getAllCars();
		model.addAttribute("cars", cars);
		
		
		// CHECK TOTAL NO OF CARS OF USER
		User user=userdao.getUserByUsername(username);
		List<Car> usercars = cardao.getReservedCarsByUser(user);
		usercars.addAll(cardao.getCarsInUserByUser(user));
		System.out.println("usercars:"+usercars.size());
		request.setAttribute("usercars", usercars);
		

		return "customer/browse-cars";
	}

// ################################### MY CARS ##########################################################

	@GetMapping("/my-cars.htm")
	public String getMyCars(Model model, HttpServletRequest request,CarDAO cardao,UserDAO userdao) throws Exception {

		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		
		
		User user=userdao.getUserByUsername(username);
		List<Car> cars=cardao.getCarsInUserByUser(user);
		
		if(cars!=null)
		{
			System.out.println("My Cars exists");
			
		}
		
		model.addAttribute("cars",cars);
//		request.setAttribute("cars",cars);
		return "customer/my-cars";
	}

// ################################### MY RESERVATIONS ##########################################################

	@GetMapping("/my-reservations.htm")
	public String getMyReservations(Model model, HttpServletRequest request,CarDAO cardao,UserDAO userdao) throws Exception {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		
		User user=userdao.getUserByUsername(username);
		List<Car> cars=cardao.getReservedCarsByUser(user);
		
		if(cars!=null)
		{
			System.out.println("My Reservations exists");
			
		}

		model.addAttribute("cars",cars);
//		request.setAttribute("cars",cars);

		return "customer/my-reservations";
	}

// ################################### CONFIRM RESERVATION ##########################################################	

	@GetMapping("/confirm-reservation.htm")
	public String getConfirmReservation(Model model, HttpServletRequest request, CarDAO cardao, UserDAO userdao,SessionStatus status)
			throws Exception {

		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String carid = request.getParameter("carId");

		long carId = Long.parseLong(carid);
		Car car = cardao.getCarById(carId);
		User user = userdao.getUserByUsername(username);
		model.addAttribute("car", car);

		model.addAttribute("username", username);
		
		model.addAttribute("car", car);

		model.addAttribute("username", username);

		System.out.println("userid:" + username);

		LocalDate bookingStartDate = LocalDate.now();
		LocalDate bookingEndDate = LocalDate.now().plusDays(3);
		LocalDate returnDate = LocalDate.now().plusDays(7);

		model.addAttribute("bookingStartDate", bookingStartDate);
		model.addAttribute("bookingEndDate", bookingEndDate);
		model.addAttribute("returnDate", returnDate);

		return "customer/confirm-reservation";

	}

	@PostMapping("/confirm-reservation.htm")
	public String setConfirmReservation(@ModelAttribute("car") Car car, BindingResult result, SessionStatus status,
			CarDAO cardao, HttpServletRequest request, UserDAO userdao) throws Exception {

		System.out.println("############### CUSTOMER: Confirm Reservation Post Mapping ###############");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		System.out.println("Post username:" + username);

		String id = request.getParameter("id");
		long carid = Long.parseLong(id);

		System.out.println("Carid:" + id);
		System.out.println("Carname" + car.getCarname());
		System.out.println("Carbrand" + car.getCarbrand());

		String bookingStartDate = request.getParameter("bookingStartDate");
		System.out.println(bookingStartDate);

		String bookingEndDate = request.getParameter("bookingEndDate");
		System.out.println(bookingEndDate);

		String returnDate = request.getParameter("returnDate");
		System.out.println(returnDate);

		String userid = request.getParameter("username");
		System.out.println(userid);

		User user = userdao.getUserByUsername(username);		

		LocalDate bsd = LocalDate.parse(bookingStartDate);
		LocalDate bed = LocalDate.parse(bookingEndDate);
		LocalDate rd = LocalDate.parse(returnDate);

		car.setCarId(carid);
		car.setPlate(car.getPlate());
		car.setCarbrand(car.getCarbrand());
		car.setCarname(car.getCarname());
		car.setBookingStartDate(bsd);
		car.setBookingEndDate(bed);
		car.setReturnDate(rd);
		car.setReadyForPickup(true);
		car.setReservedByUser(user);
		
		String imagePath=request.getParameter("imagePath");
		System.out.println("IMGWGE PTAH"+car.getImagePath());
		
		
// 	    Image path
		car.setImagePath(imagePath);
		
		

		cardao.update(car);
		//here i am sending mail after car reservation !
		emailService.reservation(user);
		
		
//		@Autowired
//		private EmailSenderService senderService;
//		public static void main(String[] args) {
//			SpringApplication.run(SpringEmailDemoApplication.class, args);
//		}
//		@EventListener(ApplicationReadyEvent.class)
//		public void triggerMail() throws MessagingException {
//			senderService.sendSimpleEmail("toemail@gmail.com",
//					"This is email body",
//					"This is email subject");

//		if (result.hasErrors()) {
//			List<FieldError> errors = result.getFieldErrors();
//		    for (FieldError error : errors ) {
//		        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
//		    }
//			return "customer/my-reservations";
//		}
		status.setComplete(); // mark it complete
		return "customer/reservation-success";
	}
	

}
