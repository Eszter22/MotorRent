package progkorny.motorrent.model;

import jakarta.persistence.*;

// Ez az osztály a "Motor" nevű adatbázistáblát reprezentálja.
// Az @Entity jelzi, hogy ez egy JPA entitás.
@Entity
@Table(name = "motor") // Megadja, hogy az osztály a "Motor" táblához tartozik.
public class Motor {

    // Az elsődleges kulcs (id), amelyet az adatbázis generál automatikusan (AUTO_INCREMENT).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A motor neve nem lehet null.
    @Column(nullable = false)
    private String name;

    // A motor márkája (nem kötelező mező).
    private String brand;

    // A motor hossza (méterben), kötelező mező.
    @Column(nullable = false)
    private double length;

    // A motor modellje (nem kötelező mező).
    private String model;

    // Építési év, az adatbázisban külön megnevezett oszlopnévvel (build_year).
    @Column(name = "build_year", nullable = false)
    private int buildYear;

    // Napi bérlési díj, kötelező mező, adatbázisban
    @Column(name = "daily_rate", nullable = false)
    private double dailyRate;

    // Elérhetőség jelzése (pl. bérelhető-e jelenleg).
    @Column(nullable = false)
    private boolean available;

    // Ülések száma, kötelező mező.
    @Column(name = "number_of_seats", nullable = false)
    private int numberOfSeats;

    // Optimista zároláshoz használt verziómező.
    // Ha más is módosítja az adatot, akkor kivétel dobódik mentéskor.
    @Version
    private Integer version;

    // Üres konstruktor a JPA számára.
    public Motor() {
    }

    // Teljes konstruktor, amely minden kötelező mezőt inicializál.
    public Motor(String name, String brand, double length, String model, int buildYear,
                double dailyRate, boolean available, int numberOfSeats) {
        this.name = name;
        this.brand = brand;
        this.length = length;
        this.model = model;
        this.buildYear = buildYear;
        this.dailyRate = dailyRate;
        this.available = available;
        this.numberOfSeats = numberOfSeats;
    }

    // Getter metódusok – az osztály mezőinek lekérdezésére szolgálnak.
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getLength() {
        return length;
    }

    public String getModel() {
        return model;
    }

    public int getBuildYear() {
        return buildYear;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public Integer getVersion() {
        return version;
    }

    // Setter metódusok – az osztály mezőinek beállítására szolgálnak.
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBuildYear(int buildYear) {
        this.buildYear = buildYear;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
