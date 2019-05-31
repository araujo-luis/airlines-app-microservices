package uv.airlines.app.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Agencies.
 */
@Entity
@Table(name = "agencies")
public class Agencies implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @OneToMany(mappedBy = "agencies")
    private Set<Reservations> reservations = new HashSet<>();

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

    public Agencies name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Reservations> getReservations() {
        return reservations;
    }

    public Agencies reservations(Set<Reservations> reservations) {
        this.reservations = reservations;
        return this;
    }

    public Agencies addReservations(Reservations reservations) {
        this.reservations.add(reservations);
        reservations.setAgenciesAgencyId(this);
        return this;
    }

    public Agencies removeReservations(Reservations reservations) {
        this.reservations.remove(reservations);
        reservations.setAgenciesAgencyId(null);
        return this;
    }

    public void setReservations(Set<Reservations> reservations) {
        this.reservations = reservations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Agencies)) {
            return false;
        }
        return id != null && id.equals(((Agencies) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Agencies{" + "id=" + getId() + ", name='" + getName() + "'" + ", user='" + "'" + "}";
    }
}
