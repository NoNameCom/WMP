package web.mentor.ru.model;

import javax.persistence.*;

@Entity
@Table(name = "forms")
public class Form {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "country_residence", nullable = false)
    private String countryResidence;
    @Column(name = "city_residence", nullable = false)
    private String cityResidence;
    @Column(name = "communication")
    private String communication;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "form")
    private Account account;

    public Form() {
    }

    public Form(String firstName, String lastName, String middleName, Integer age, String countryResidence, String cityResidence, String communication, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.age = age;
        this.countryResidence = countryResidence;
        this.cityResidence = cityResidence;
        this.communication = communication;
        this.account = account;
    }

    public Form(Long id, String firstName, String lastName, String middleName, Integer age, String countryResidence, String cityResidence, String communication, Account account) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.age = age;
        this.countryResidence = countryResidence;
        this.cityResidence = cityResidence;
        this.communication = communication;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountryResidence() {
        return countryResidence;
    }

    public void setCountryResidence(String countryResidence) {
        this.countryResidence = countryResidence;
    }

    public String getCityResidence() {
        return cityResidence;
    }

    public void setCityResidence(String cityResidence) {
        this.cityResidence = cityResidence;
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Form form = (Form) object;

        if (!firstName.equals(form.firstName)) return false;
        if (!lastName.equals(form.lastName)) return false;
        if (middleName != null ? !middleName.equals(form.middleName) : form.middleName != null) return false;
        if (!age.equals(form.age)) return false;
        if (!countryResidence.equals(form.countryResidence)) return false;
        if (!cityResidence.equals(form.cityResidence)) return false;
        return communication.equals(form.communication);

    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + age.hashCode();
        result = 31 * result + countryResidence.hashCode();
        result = 31 * result + cityResidence.hashCode();
        result = 31 * result + communication.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Form{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                ", countryResidence='" + countryResidence + '\'' +
                ", cityResidence='" + cityResidence + '\'' +
                ", communication='" + communication + '\'' +
                '}';
    }
}
