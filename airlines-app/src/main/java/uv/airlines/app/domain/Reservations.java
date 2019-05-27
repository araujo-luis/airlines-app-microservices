package uv.airlines.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Reservations.
 */
@Entity
@Table(name = "reservations")

public class Reservations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_date")
    private Instant reservationDate;

    @OneToMany(mappedBy = "reservationId")
    private Set<ReservationPassengers> reservationPassengers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("reservations")
    private Agencies agenciesAgencyId;

    @ManyToOne
    @JsonIgnoreProperties("reservations")
    private Airports airportsAirportId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not
    // remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getReservationDate() {
        return reservationDate;
    }

    public Reservations reservationDate(Instant reservationDate) {
        this.reservationDate = reservationDate;
        return this;
    }

    public void setReservationDate(Instant reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Set<ReservationPassengers> getReservationPassengers() {
        return reservationPassengers;
    }

    public Reservations reservationPassengers(Set<ReservationPassengers> reservationPassengers) {
        this.reservationPassengers = reservationPassengers;
        return this;
    }

    public Reservations addReservationPassengers(ReservationPassengers reservationPassengers) {
        this.reservationPassengers.add(reservationPassengers);
        reservationPassengers.setReservationId(this);
        return this;
    }

    public Reservations removeReservationPassengers(ReservationPassengers reservationPassengers) {
        this.reservationPassengers.remove(reservationPassengers);
        reservationPassengers.setReservationId(null);
        return this;
    }

    public void setReservationPassengers(Set<ReservationPassengers> reservationPassengers) {
        this.reservationPassengers = reservationPassengers;
    }

    public Agencies getAgenciesAgencyId() {
        return agenciesAgencyId;
    }

    public Reservations agenciesAgencyId(Agencies agencies) {
        this.agenciesAgencyId = agencies;
        return this;
    }

    public void setAgenciesAgencyId(Agencies agencies) {
        this.agenciesAgencyId = agencies;
    }

    public Airports getAirportsAirportId() {
        return airportsAirportId;
    }

    public Reservations airportsAirportId(Airports airports) {
        this.airportsAirportId = airports;
        return this;
    }

    public void setAirportsAirportId(Airports airports) {
        this.airportsAirportId = airports;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reservations)) {
            return false;
        }
        return id != null && id.equals(((Reservations) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Reservations{" + "id=" + getId() + ", reservationDate='" + getReservationDate() + "'" + "}";
    }
}
