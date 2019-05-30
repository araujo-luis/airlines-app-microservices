package uv.airlines.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @Temporal(TemporalType.DATE)
    private Date reservationDate;

    @OneToMany(mappedBy = "reservationId")
    private Set<ReservationPassengers> reservationPassengers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("reservations")
    private Agencies agenciesAgencyId;

    @ManyToOne
    @JsonIgnoreProperties("reservations")
    private FlightSchedule flightScheduleId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not
    // remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public Reservations reservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
        return this;
    }

    public void setReservationDate(Date reservationDate) {
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

    public FlightSchedule getFlightScheduleId() {
        return flightScheduleId;
    }

    public void setflightScheduleId(FlightSchedule flightSchedule) {
        this.flightScheduleId = flightSchedule;
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
