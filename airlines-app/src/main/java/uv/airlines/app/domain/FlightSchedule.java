package uv.airlines.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A FlightSchedule.
 */
@Entity
@Table(name = "flight_schedule")

public class FlightSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "takeoff_date")
    private Instant takeoffDate;

    @Column(name = "arrival_date")
    private Instant arrivalDate;

    @Column(name = "flight_rate")
    private Float flightRate;

    @ManyToOne
    @JsonIgnoreProperties("aircraftId")
    private Aircrafts aircraftId;

    @ManyToOne
    @JsonIgnoreProperties("airportTakeoff")
    private Airports airportTakeoff;

    @ManyToOne
    @JsonIgnoreProperties("airportArrival")
    private Airports airportArrival;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not
    // remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTakeoffDate() {
        return takeoffDate;
    }

    public FlightSchedule takeoffDate(Instant takeoffDate) {
        this.takeoffDate = takeoffDate;
        return this;
    }

    public void setTakeoffDate(Instant takeoffDate) {
        this.takeoffDate = takeoffDate;
    }

    public Instant getArrivalDate() {
        return arrivalDate;
    }

    public FlightSchedule arrivalDate(Instant arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public void setArrivalDate(Instant arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Float getFlightRate() {
        return flightRate;
    }

    public FlightSchedule flightRate(Float flightRate) {
        this.flightRate = flightRate;
        return this;
    }

    public void setFlightRate(Float flightRate) {
        this.flightRate = flightRate;
    }

    public Aircrafts getAircraftId() {
        return aircraftId;
    }

    public FlightSchedule aircraftId(Aircrafts aircrafts) {
        this.aircraftId = aircrafts;
        return this;
    }

    public void setAircraftId(Aircrafts aircrafts) {
        this.aircraftId = aircrafts;
    }

    public Airports getAirportTakeoff() {
        return airportTakeoff;
    }

    public FlightSchedule airportTakeoff(Airports airports) {
        this.airportTakeoff = airports;
        return this;
    }

    public void setAirportTakeoff(Airports airports) {
        this.airportTakeoff = airports;
    }

    public Airports getAirportArrival() {
        return airportArrival;
    }

    public FlightSchedule airportArrival(Airports airports) {
        this.airportArrival = airports;
        return this;
    }

    public void setAirportArrival(Airports airports) {
        this.airportArrival = airports;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlightSchedule)) {
            return false;
        }
        return id != null && id.equals(((FlightSchedule) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FlightSchedule{" + "id=" + getId() + ", takeoffDate='" + getTakeoffDate() + "'" + ", arrivalDate='"
                + getArrivalDate() + "'" + ", flightRate=" + getFlightRate() + "}";
    }
}
