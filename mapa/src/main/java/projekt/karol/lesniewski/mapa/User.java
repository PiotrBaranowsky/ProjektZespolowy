package projekt.karol.lesniewski.mapa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Nullable
    Double lat;

    @Nullable
    Double lon;
    String firstName;

    String lastName;

    String phoneNumber;

    String rank;

    Integer brigade;

    String password;

    String email;

    private String role;

    @JsonIgnore
    @OneToMany(mappedBy="user",fetch=FetchType.EAGER)
    private Set<Authority> authorities;

    public User(String email, String firstName, String lastName, String phoneNumber, String rank, Integer brigade, String password, String role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.rank = rank;
        this.brigade = brigade;
        this.password = password;
        this.role = role;
    }

    public User() {

    }

    @Nullable
    public Double getLat() {
        return lat;
    }

    public void setLat(@Nullable Double lat) {
        this.lat = lat;
    }

    @Nullable
    public Double getLon() {
        return lon;
    }

    public void setLon(@Nullable Double lon) {
        this.lon = lon;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getBrigade() {
        return brigade;
    }

    public void setBrigade(Integer brigade) {
        this.brigade = brigade;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
