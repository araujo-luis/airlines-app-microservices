package uv.airlines.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.github.javafaker.Faker;

import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.remoting.soap.SoapFaultException;

import uv.airlines.app.domain.Agencies;
import uv.airlines.app.domain.Aircrafts;
import uv.airlines.app.domain.FlightSchedule;
import uv.airlines.app.domain.Passenger;
import uv.airlines.app.domain.ReservationPassengers;
import uv.airlines.app.domain.Reservations;
import uv.airlines.app.service.AgenciesService;
import uv.airlines.app.service.AircraftsService;
import uv.airlines.app.service.AirportsService;
import uv.airlines.app.service.FlightScheduleService;
import uv.airlines.app.service.PassengerService;
import uv.airlines.app.service.ReservationPassengersService;
import uv.airlines.app.service.ReservationsService;
import uv.airlines.app.service.dto.AgenciesDTO;
import uv.airlines.app.service.dto.AircraftsDTO;
import uv.airlines.app.service.dto.AirportsDTO;
import uv.airlines.app.service.dto.FlightScheduleDTO;
import uv.airlines.app.service.dto.PassengerDTO;
import uv.airlines.app.service.dto.ReservationPassengersDTO;
import uv.airlines.app.service.dto.ReservationsDTO;
import uv.airlines.app.service.mapper.FlightScheduleMapper;

@SpringBootApplication
public class AirlinesAppApplication implements CommandLineRunner {

	@Autowired
	private AirportsService airportsService;
	@Autowired
	private AircraftsService aircraftsService;

	@Autowired
	private FlightScheduleService flightScheduleService;

	@Autowired
	private PassengerService passengerService;

	@Autowired
	private AgenciesService agenciesService;

	@Autowired
	private ReservationsService reservationsService;
	@Autowired
	private ReservationPassengersService reservationPassengersService;

	FlightScheduleMapper flightScheduleMapper;

	public static void main(String[] args) {
		SpringApplication.run(AirlinesAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// generateAirport(20);
		// generateAircraft(20);
		// generatePassenger(20);
		// generateAgencies(10);
		// generateScheduleFlight(20);
		// generateReservation(1);
		// testFindReservation();

		addSeatAutomatic();

	}

	public void generateAirport(int quantity) {
		Faker faker = new Faker();

		for (int i = 0; i < 20; i++) {

			AirportsDTO airportsDTO = new AirportsDTO();
			airportsDTO.setContinent(faker.address().countryCode());
			airportsDTO.setCoordinates(faker.address().longitude() + ", " + faker.address().latitude());
			airportsDTO.setElevation(faker.number().randomDigit());
			airportsDTO.setGpscode(faker.bothify("??##"));
			airportsDTO.setCountry(faker.address().countryCode());
			airportsDTO.setRegion(faker.address().zipCode());
			airportsDTO.setLocalcode(faker.bothify("??##"));
			airportsDTO.setMunipality(faker.address().cityName());
			airportsDTO.setName(faker.beer().name());
			airportsDTO.setType(faker.options().option("plane", "heli"));
			airportsDTO.setId(faker.bothify("??##"));
			airportsService.save(airportsDTO);

		}

	}

	public void generateAircraft(int quantity) {
		Faker faker = new Faker();

		for (int i = 0; i < quantity; i++) {
			AircraftsDTO aircrafts = new AircraftsDTO();
			aircrafts.setNumber(faker.bothify("##??"));
			aircrafts.setCapacity(Integer.parseInt(faker.options().option("60", "80", "100")));
			aircraftsService.save(aircrafts);
		}

	}

	public void generateScheduleFlight(int quantity) {

		Faker faker = new Faker();

		for (int i = 0; i < quantity; i++) {
			FlightScheduleDTO flightScheduleDTO = new FlightScheduleDTO();
			flightScheduleDTO.setAircraftId(Long.valueOf(faker.number().numberBetween(1, 20)));
			flightScheduleDTO
					.setAirportArrivalId(faker.options().option("bq60", "ek41", "fr34", "hi01", "hq21", "in88"));
			flightScheduleDTO
					.setAirportTakeoffId(faker.options().option("rj42", "ne16", "ru63", "oa04", "qp25", "mr10"));
			flightScheduleDTO.setFlightRate(Float.valueOf(faker.commerce().price()));
			flightScheduleDTO.setTakeoffDate(faker.date().past(7, TimeUnit.DAYS));
			flightScheduleDTO.setArrivalDate(faker.date().future(7, TimeUnit.DAYS));
			flightScheduleService.save(flightScheduleDTO);
		}
	}

	public void generatePassenger(int quantity) {
		Faker faker = new Faker();

		for (int i = 0; i < quantity; i++) {
			PassengerDTO passengerDTO = new PassengerDTO();
			passengerDTO.setId(faker.bothify("???"));
			passengerDTO.setName(faker.name().firstName());
			passengerDTO.setLastname(faker.name().lastName());
			passengerService.save(passengerDTO);
		}
	}

	public void generateAgencies(int quantity) {
		Faker faker = new Faker();

		for (int i = 0; i < quantity; i++) {
			AgenciesDTO agenciesDTO = new AgenciesDTO();
			agenciesDTO.setName(faker.company().name());
			agenciesService.save(agenciesDTO);
		}

	}

	public void generateReservation(int quantity) {

		ReservationsDTO r = new ReservationsDTO();
		r.setAgenciesId(new Long(2));
		r.setFlightScheduleId(new Long(11));
		r.setReservationDate(Calendar.getInstance().getTime());

		List<String> passengers = Arrays.asList("azt", "cwv", "czg");

		List<ReservationPassengersDTO> rs = new ArrayList<>();

		for (int i = 0; i < passengers.size(); i++) {
			ReservationPassengersDTO reservationPassengersDTO = new ReservationPassengersDTO();
			reservationPassengersDTO.setPaid(true);
			reservationPassengersDTO.setPassengerId(passengers.get(i));
			reservationPassengersDTO.setLuggagesQuanity(Faker.instance().number().numberBetween(1, 5));
			reservationPassengersDTO.setPriority(true);
			reservationPassengersDTO.setSeatNumber("C" + (i + 1));
			reservationPassengersDTO.setFlightRate(Double.valueOf(Faker.instance().commerce().price(30.00, 100.00)));
			rs.add(reservationPassengersDTO);
		}

		// That save all without need to use ReservationPassengersService to Save
		// ReservationPassengers
		reservationsService.saveAll(r, rs);

	}

	public void payReservation() {
		// Reservation Id & Passenger Id
		Optional<FlightScheduleDTO> flight = flightScheduleService.findOne(new Long(13));
		Optional<ReservationPassengersDTO> oldReservationPassengersDTO = reservationPassengersService
				.findOne(new Long(5));
		ReservationPassengersDTO reservationPassengersDTO = oldReservationPassengersDTO.get();
		reservationPassengersDTO.setPaid(true);
		reservationPassengersService.save(reservationPassengersDTO);
	}

	public void testFindReservation() {

		// reservationPassengersService.changeSeat("azt", new Long(4), "5");

		// Boolean isPaid = reservationPassengersService.payReservation("azt", new
		// Long(4));
		// System.out.println("Ha sido pagado?" + isPaid);

		// reservationPassengersService.findByFlightPendient(LocalDateTime.now(), new
		// Long(1));

	}

	public void addSeatAutomatic() {

		Optional<FlightScheduleDTO> flightScheduleDTO = flightScheduleService.findOne(new Long(13));
		Optional<AircraftsDTO> aircraft = aircraftsService.findOne(flightScheduleDTO.get().getAircraftId());
		List<ReservationPassengersDTO> listOfSeatBusy = reservationPassengersService.getBusySeat(new Long(13),
				new Long(1));
		List<ReservationPassengersDTO> PassengerWithoutSeat = reservationPassengersService
				.getPassengersWithoutSeat(new Long(13), new Long(1));
		int PlaneCapacity = aircraft.get().getCapacity();
		ArrayList<Integer> seatNumber = new ArrayList<>();
		HashMap<String, ArrayList<Integer>> seatOfPlane = new HashMap();
		Integer seatFreeCount = 0;
		// Un avión puede tener entre 4,6 columnas
		Integer rows = PlaneCapacity <= 60 ? PlaneCapacity / 4 : PlaneCapacity / 6;
		for (int i = 1; i <= rows; i++) {
			seatNumber.add(i);
		}

		if (PlaneCapacity <= 60) {
			seatOfPlane.put("A", seatNumber);
			seatOfPlane.put("B", seatNumber);
			seatOfPlane.put("C", seatNumber);
			seatOfPlane.put("D", seatNumber);
		} else {
			seatOfPlane.put("A", seatNumber);
			seatOfPlane.put("B", seatNumber);
			seatOfPlane.put("C", seatNumber);
			seatOfPlane.put("D", seatNumber);
			seatOfPlane.put("E", seatNumber);
			seatOfPlane.put("F", seatNumber);
		}

		for (ReservationPassengersDTO rp : listOfSeatBusy) {
			String column = rp.getSeatNumber().substring(0, 1);
			String row = rp.getSeatNumber().substring(1);

			ArrayList<Integer> seatFree = (ArrayList<Integer>) seatOfPlane.get(column).stream()
					.filter(s -> s != Integer.parseInt(row)).collect(Collectors.toList());

			seatOfPlane.put(column, seatFree);
			seatFreeCount = seatFreeCount + seatFree.size();
			if (seatFreeCount > PassengerWithoutSeat.size()) {
				break;
			}
		}

		int end = PassengerWithoutSeat.size();
		Integer index = 0;

		for (Map.Entry<String, ArrayList<Integer>> entry : seatOfPlane.entrySet()) {
			String col = entry.getKey();
			ArrayList<Integer> r = entry.getValue();
			for (Integer rowPlane : r) {
				ReservationPassengersDTO passenger = PassengerWithoutSeat.get(index);
				passenger.setSeatNumber(col + rowPlane);
				System.out.println("Entrada de valor a reservacion pasajero !!!!!");
				reservationPassengersService.save(passenger);
				index = index + 1;
				if (index > end)
					break;
			}
			if (index > end)
				break;
		}
	}
}
