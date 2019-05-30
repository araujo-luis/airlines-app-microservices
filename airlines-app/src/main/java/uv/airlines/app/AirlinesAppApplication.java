package uv.airlines.app;

import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uv.airlines.app.service.AircraftsService;
import uv.airlines.app.service.AirportsService;
import uv.airlines.app.service.dto.AircraftsDTO;
import uv.airlines.app.service.dto.AirportsDTO;

@SpringBootApplication
public class AirlinesAppApplication implements CommandLineRunner {

	@Autowired
	private AirportsService airportsService;

	public static void main(String[] args) {
		SpringApplication.run(AirlinesAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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
		}*/
	}

}
