package uv.airlines.app.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Passenger.
 */
@Entity
@Table(name = "passenger")

public class Passenger implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Size(max = 45)
    @Column(name = "lastname", length = 45)
    private String lastname;

    @OneToMany(mappedBy = "passDni")
    private Set<ReservationPassengers> reservationPassengers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not
    // remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Passenger name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public Passenger lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<ReservationPassengers> getReservationPassengers() {
        return reservationPassengers;
    }

    public Passenger reservationPassengers(Set<ReservationPassengers> reservationPassengers) {
        this.reservationPassengers = reservationPassengers;
        return this;
    }

    public Passenger addReservationPassengers(ReservationPassengers reservationPassengers) {
        this.reservationPassengers.add(reservationPassengers);
        reservationPassengers.setPassDni(this);
        return this;
    }

    public Passenger removeReservationPassengers(ReservationPassengers reservationPassengers) {
        this.reservationPassengers.remove(reservationPassengers);
        reservationPassengers.setPassDni(null);
        return this;
    }

    public void setReservationPassengers(Set<ReservationPassengers> reservationPassengers) {
        this.reservationPassengers = reservationPassengers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Passenger)) {
            return false;
        }
        return id != null && id.equals(((Passenger) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Passenger{" + "id=" + getId() + ", name='" + getName() + "'" + ", lastname='" + getLastname() + "'"
                + "}";
    }
}
