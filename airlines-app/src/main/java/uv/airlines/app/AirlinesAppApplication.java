package uv.airlines.app;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uv.airlines.app.service.AgenciesService;
import uv.airlines.app.service.AircraftsService;
import uv.airlines.app.service.AirportsService;
import uv.airlines.app.service.FlightScheduleService;
import uv.airlines.app.service.PassengerService;
import uv.airlines.app.service.ReservationsService;
import uv.airlines.app.service.dto.AgenciesDTO;
import uv.airlines.app.service.dto.AircraftsDTO;
import uv.airlines.app.service.dto.AirportsDTO;
import uv.airlines.app.service.dto.FlightScheduleDTO;
import uv.airlines.app.service.dto.PassengerDTO;
import uv.airlines.app.service.dto.ReservationsDTO;

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

	public static void main(String[] args) {
		SpringApplication.run(AirlinesAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// generateAirport(20);
		// generateAircraft(20);
		// generateScheduleFlight(20);
		// generatePassenger(20);
		// generateAgencies(10);
		// generateReservation(1);

	}

	public void generateAirport(int quantity) {
		Faker faker = new Faker();


		/*for (int i = 0; i < 20; i++) {

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
			flightScheduleDTO.setAircraftId(Long.valueOf(faker.number().numberBetween(2, 23)));
			flightScheduleDTO
					.setAirportArrivalId(faker.options().option("aw82", "fy19", "fz92", "jw93", "bx38", "aw82"));
			flightScheduleDTO
					.setAirportTakeoffId(faker.options().option("gc55", "wt47", "tw16", "qq96", "sk28", "of61"));
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
		ReservationsDTO reservationsDTO = new ReservationsDTO();
		// reservationsDTO.setAgenciesAgencyIdId(new Long(1));
		// reservationsDTO.setAirportsAirportIdId(new Long(82));
		reservationsDTO.setReservationDate(Calendar.getInstance().getTime());
		reservationsService.save(reservationsDTO);

		}*/
	}

}
