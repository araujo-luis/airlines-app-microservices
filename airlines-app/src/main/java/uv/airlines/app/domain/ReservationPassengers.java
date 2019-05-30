package uv.airlines.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ReservationPassengers.
 */
@Entity
@Table(name = "reservation_passengers")

public class ReservationPassengers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "luggages_quanity")
    private Integer luggagesQuanity;

    @Column(name = "priority")
    private Boolean priority;

    @Column(name = "price")
    private Double price;

    @Column(name = "paid")
    private Boolean paid;

    @Size(max = 6)
    @Column(name = "seat_number", length = 6)
    private String seatNumber;

    @ManyToOne
    @JsonIgnoreProperties("reservationPassengers")
    private Reservations reservationId;

    @ManyToOne
    @JsonIgnoreProperties("reservationPassengers")
    private Passenger passDni;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not
    // remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLuggagesQuanity() {
        return luggagesQuanity;
    }

    public ReservationPassengers luggagesQuanity(Integer luggagesQuanity) {
        this.luggagesQuanity = luggagesQuanity;
        return this;
    }

    public void setLuggagesQuanity(Integer luggagesQuanity) {
        this.luggagesQuanity = luggagesQuanity;
    }

    public Boolean getPriority() {
        return priority;
    }

    public ReservationPassengers priority(Boolean priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public ReservationPassengers seatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Reservations getReservationId() {
        return reservationId;
    }

    public ReservationPassengers reservationId(Reservations reservations) {
        this.reservationId = reservations;
        return this;
    }

    public void setReservationId(Reservations reservations) {
        this.reservationId = reservations;
    }

    public Passenger getPassDni() {
        return passDni;
    }

    public ReservationPassengers passDni(Passenger passenger) {
        this.passDni = passenger;
        return this;
    }

    public void setPassDni(Passenger passenger) {
        this.passDni = passenger;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean isPaid() {
        return this.paid;
    }

    public Boolean getPaid() {
        return this.paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReservationPassengers)) {
            return false;
        }
        return id != null && id.equals(((ReservationPassengers) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ReservationPassengers{" + "id=" + getId() + ", luggagesQuanity=" + getLuggagesQuanity() + ", priority="
                + getPriority() + ", seatNumber='" + getSeatNumber() + "'" + "}";
    }
}
