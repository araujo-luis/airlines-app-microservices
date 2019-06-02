package uv.airlines.app.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Aircrafts.
 */
@Entity
@Table(name = "aircrafts")
public class Aircrafts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 45)
    @Column(name = "number", length = 45)
    private String number;

	@Column(name = "capacity")
    private int capacity;
    
    @Column(name = "seats_taken")
    private int seatsTaken;

    @OneToMany(mappedBy = "aircraft")
    private Set<FlightSchedule> flightSchedules = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not
    // remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public Aircrafts number(String number) {
        this.number = number;
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Aircrafts capacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Set<FlightSchedule> getFlightSchedules() {
        return flightSchedules;
    }
    
    public Integer getSeatsTaken() {
		return seatsTaken;
	}

	public void setSeatsTaken(Integer seatsTaken) {
		this.seatsTaken = seatsTaken;
	}

    public Aircrafts flightSchedules(Set<FlightSchedule> flightSchedules) {
        this.flightSchedules = flightSchedules;
        return this;
    }

    public Aircrafts addFlightSchedule(FlightSchedule flightSchedule) {
        this.flightSchedules.add(flightSchedule);
        flightSchedule.setAircraft(this);
        return this;
    }

    public Aircrafts removeFlightSchedule(FlightSchedule flightSchedule) {
        this.flightSchedules.remove(flightSchedule);
        flightSchedule.setAircraft(null);
        return this;
    }

    public void setFlightSchedules(Set<FlightSchedule> flightSchedules) {
        this.flightSchedules = flightSchedules;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Aircrafts)) {
            return false;
        }
        return id != null && id.equals(((Aircrafts) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Aircrafts{" + "id=" + getId() + ", number='" + getNumber() + "'" + ", capacity=" + getCapacity() + "}";
    }
}
