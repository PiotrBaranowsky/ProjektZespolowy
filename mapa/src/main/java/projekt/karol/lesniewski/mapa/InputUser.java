package projekt.karol.lesniewski.mapa;

public class InputUser {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String rank;
    private Integer brigade;

    public InputUser(String email, String firstName, String lastName, String password, String phoneNumber, String rank, Integer brigade) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.rank = rank;
        this.brigade = brigade;
        this.password = password;
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
}
