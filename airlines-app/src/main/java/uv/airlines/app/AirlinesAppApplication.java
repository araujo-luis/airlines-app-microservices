package uv.airlines.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uv.airlines.app.domain.Agencies;
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
		generateReservation(1);
		//testFindReservation();

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
		r.setAgenciesId(new Long(1));
		r.setFlightScheduleId(new Long(13));
		r.setReservationDate(Calendar.getInstance().getTime());

		 ReservationPassengersDTO reservationPassengersDTO = new ReservationPassengersDTO();
		 reservationPassengersDTO.setPaid(true);
		 reservationPassengersDTO.setPassengerId("bvj");
		 reservationPassengersDTO.setLuggagesQuanity(3);
		 reservationPassengersDTO.setPriority(true);
		 reservationPassengersDTO.setSeatNumber("A1");
		 reservationPassengersDTO.setFlightRate(30.00);

		 List<ReservationPassengersDTO> rs = new ArrayList<>();
		 rs.add(reservationPassengersDTO);
		 // That save all without need to use ReservationPassengersService to Save ReservationPassengers
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
		
		// reservationPassengersService.findByFlightPendient(LocalDateTime.now(), new Long(1));

	}

}
