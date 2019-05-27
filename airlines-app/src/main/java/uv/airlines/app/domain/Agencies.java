package uv.airlines.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

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

    @Size(max = 45)
    @Column(name = "jhi_user", length = 45)
    private String user;

    @Size(max = 45)
    @Column(name = "jhi_password", length = 45)
    private String password;

    @OneToMany(mappedBy = "agenciesAgencyId")

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

    public String getUser() {
        return user;
    }

    public Agencies user(String user) {
        this.user = user;
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public Agencies password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "Agencies{" + "id=" + getId() + ", name='" + getName() + "'" + ", user='" + getUser() + "'"
                + ", password='" + getPassword() + "'" + "}";
    }
}
