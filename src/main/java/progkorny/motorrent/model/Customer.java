package progkorny.motorrent.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Ez az osztály a "customer" nevű adatbázistábla megfelelője.
// Az @Entity annotációval jelezzük, hogy ez egy JPA entitás.
@Entity
public class Customer {

    // Az elsődleges kulcs mező. Automatikusan generálódik (IDENTITY: pl. MySQL AUTO_INCREMENT).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Ügyfél keresztneve
    private String firstName;

    // Ügyfél vezetékneve
    private String lastName;

    // Email-cím
    private String email;

    // Telefonszám, amit megadott
    private String phoneNumber;

    // Jogosítvány száma
    private String driverLicenseNumber;

    // Országkód, pl. HU, DE, AT stb.
    private String countryCode;

    // Üres konstruktor szükséges a JPA működéséhez (pl. lekérdezéskor)
    public Customer() {}

    // Teljes konstruktor: új ügyfél létrehozásához használható
    public Customer(String firstName, String lastName, String email,
                    String phoneNumber, String driverLicenseNumber, String countryCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.driverLicenseNumber = driverLicenseNumber;
        this.countryCode = countryCode;
    }

    // Getter és setter metódusok — az osztály mezőinek lekérdezésére és módosítására szolgálnak.

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }
    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
