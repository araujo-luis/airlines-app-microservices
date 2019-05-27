package uv.airlines.app.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link uv.airlines.app.domain.FlightSchedule} entity.
 */
public class FlightScheduleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Instant takeoffDate;

    private Instant arrivalDate;

    private Float flightRate;

    private Long aircraftIdId;

    private Long airportTakeoffId;

    private Long airportArrivalId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTakeoffDate() {
        return takeoffDate;
    }

    public void setTakeoffDate(Instant takeoffDate) {
        this.takeoffDate = takeoffDate;
    }

    public Instant getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Instant arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Float getFlightRate() {
        return flightRate;
    }

    public void setFlightRate(Float flightRate) {
        this.flightRate = flightRate;
    }

    public Long getAircraftIdId() {
        return aircraftIdId;
    }

    public void setAircraftIdId(Long aircraftsId) {
        this.aircraftIdId = aircraftsId;
    }

    public Long getAirportTakeoffId() {
        return airportTakeoffId;
    }

    public void setAirportTakeoffId(Long airportsId) {
        this.airportTakeoffId = airportsId;
    }

    public Long getAirportArrivalId() {
        return airportArrivalId;
    }

    public void setAirportArrivalId(Long airportsId) {
        this.airportArrivalId = airportsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FlightScheduleDTO flightScheduleDTO = (FlightScheduleDTO) o;
        if (flightScheduleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), flightScheduleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FlightScheduleDTO{" + "id=" + getId() + ", takeoffDate='" + getTakeoffDate() + "'" + ", arrivalDate='"
                + getArrivalDate() + "'" + ", flightRate=" + getFlightRate() + ", aircraftId=" + getAircraftIdId()
                + ", airportTakeoff=" + getAirportTakeoffId() + ", airportArrival=" + getAirportArrivalId() + "}";
    }
}
