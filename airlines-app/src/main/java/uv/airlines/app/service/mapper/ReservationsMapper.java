package uv.airlines.app.service.mapper;

import uv.airlines.app.domain.*;
import uv.airlines.app.service.dto.ReservationsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reservations} and its DTO
 * {@link ReservationsDTO}.
 */
@Mapper(componentModel = "spring", uses = { AgenciesMapper.class, AirportsMapper.class })
public interface ReservationsMapper extends EntityMapper<ReservationsDTO, Reservations> {

    @Mapping(source = "agenciesAgencyId.id", target = "agenciesAgencyIdId")
    
    ReservationsDTO toDto(Reservations reservations);

    @Mapping(target = "reservationPassengers", ignore = true)
    @Mapping(source = "agenciesAgencyIdId", target = "agenciesAgencyId")
    
    Reservations toEntity(ReservationsDTO reservationsDTO);

    default Reservations fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reservations reservations = new Reservations();
        reservations.setId(id);
        return reservations;
    }
}
