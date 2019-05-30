package uv.airlines.app.service.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A DTO for the {@link uv.airlines.app.domain.Reservations} entity.
 */
public class ReservationsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Date reservationDate;

    private Long agenciesAgencyIdId;

    private Long airportsAirportIdId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Long getAgenciesAgencyIdId() {
        return agenciesAgencyIdId;
    }

    public void setAgenciesAgencyIdId(Long agenciesId) {
        this.agenciesAgencyIdId = agenciesId;
    }

    public Long getAirportsAirportIdId() {
        return airportsAirportIdId;
    }

    public void setAirportsAirportIdId(Long airportsId) {
        this.airportsAirportIdId = airportsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReservationsDTO reservationsDTO = (ReservationsDTO) o;
        if (reservationsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reservationsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReservationsDTO{" + "id=" + getId() + ", reservationDate='" + getReservationDate() + "'"
                + ", agenciesAgencyId=" + getAgenciesAgencyIdId() + ", airportsAirportId=" + getAirportsAirportIdId()
                + "}";
    }
}
