package uv.airlines.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link uv.airlines.app.domain.Agencies} entity.
 */
public class AgenciesDTO implements Serializable {

    private Long id;

    @Size(max = 45)
    private String name;

    @Size(max = 45)
    private String user;

    @Size(max = 45)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AgenciesDTO agenciesDTO = (AgenciesDTO) o;
        if (agenciesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), agenciesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AgenciesDTO{" + "id=" + getId() + ", name='" + getName() + "'" + ", user='" + getUser() + "'"
                + ", password='" + getPassword() + "'" + "}";
    }
}
