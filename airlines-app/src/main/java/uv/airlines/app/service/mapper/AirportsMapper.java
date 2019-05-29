package uv.airlines.app.service.mapper;

import uv.airlines.app.domain.*;
import uv.airlines.app.service.dto.AirportsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Airports} and its DTO {@link AirportsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AirportsMapper extends EntityMapper<AirportsDTO, Airports> {

    @Mapping(target = "airportTakeoff", ignore = true)
    @Mapping(target = "airportArrival", ignore = true)
    Airports toEntity(AirportsDTO airportsDTO);

    default Airports fromId(String id) {
        if (id == null) {
            return null;
        }
        Airports airports = new Airports();
        airports.setId(id);
        return airports;
    }
}
