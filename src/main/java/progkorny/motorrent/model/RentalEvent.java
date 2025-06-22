package progkorny.motorrent.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

// Az @Entity annotációval megjelöljük, hogy ez az osztály egy adatbázis-entitás (JPA entitás)
@Entity
public class RentalEvent {

    // Az esemény egyedi azonosítója (manuálisan kell beállítani, mivel nincs @GeneratedValue)
    @Id
    private long id;

    // Több RentalEvent is hivatkozhat egy motorra (ManyToOne kapcsolat)
    @ManyToOne
    @JoinColumn(name = "Motor_rented_id") // Az adatbázisban ez a mező tárolja a hivatkozott motor ID-ját
    private Motor MotorRented;

    // Több RentalEvent is tartozhat egy ügyfélhez
    @ManyToOne
    @JoinColumn(name = "rental_customer_id") // Külső kulcs a customer(id)-ra
    private Customer rentalCustomer;

    // A kölcsönzés kezdő dátuma
    private LocalDate rentalDate;

    // A visszahozás dátuma
    private LocalDate returnDate;

    // A bérlés teljes költsége
    private double totalCost;

    // Meg van-e zárva a bérlés (pl. visszahozták-e a motort)
    private boolean isClosed;

    // --- Getterek és Setterek ---

    public Customer getRentalCustomer() {
        return rentalCustomer;
    }

    public void setRentalCustomer(Customer rentalCustomer) {
        this.rentalCustomer = rentalCustomer;
    }

    public Motor getMotorRented() {
        return MotorRented;
    }

    public void setMotorRented(Motor MotorRented) {
        this.MotorRented = MotorRented;
    }

    // --- Konstruktor Builderrel ---

    // Privát konstruktor, amelyet a Builder használ
    private RentalEvent(Builder builder) {
        this.id = builder.id;
        this.MotorRented = builder.MotorRented;
        this.rentalCustomer = builder.rentalCustomer;
        this.rentalDate = builder.rentalDate;
        this.returnDate = builder.returnDate;
        this.totalCost = builder.totalCost;
        this.isClosed = builder.isClosed;
    }

    // Üres konstruktor a JPA működéséhez szükséges
    public RentalEvent() { }

    // Statikus metódus, amivel a Builder-t indíthatjuk
    public static Builder builder() {
        return new Builder();
    }

    // --- Belső Builder osztály ---
    public static class Builder {
        private long id;
        private Motor MotorRented;
        private Customer rentalCustomer;
        private LocalDate rentalDate;
        private LocalDate returnDate;
        private double totalCost;
        private boolean isClosed;

        public Builder id(long id) { this.id = id; return this; }
        public Builder Motor(Motor Motor) { this.MotorRented = Motor; return this; }
        public Builder customer(Customer customer) { this.rentalCustomer = customer; return this; }
        public Builder rentalDate(LocalDate rentalDate) { this.rentalDate = rentalDate; return this; }
        public Builder returnDate(LocalDate returnDate) { this.returnDate = returnDate; return this; }
        public Builder totalCost(double totalCost) { this.totalCost = totalCost; return this; }
        public Builder isClosed(boolean isClosed) { this.isClosed = isClosed; return this; }

        public RentalEvent build() {
            return new RentalEvent(this);
        }
    }

    // --- Getterek és setterek ---

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Motor getMotor() {
        return MotorRented;
    }

    public void setMotor(Motor Motor) {
        this.MotorRented = Motor;
    }

    public Customer getCustomer() {
        return rentalCustomer;
    }

    public void setCustomerId(Customer customer) {
        this.rentalCustomer = customer;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    // --- toString metódus: naplózáshoz, hibakereséshez hasznos ---
    @Override
    public String toString() {
        return "RentalEvent{" +
                "id=" + id +
                ", car=" + MotorRented +
                ", customer=" + rentalCustomer +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", totalCost=" + totalCost +
                ", isClosed=" + isClosed +
                '}';
    }
}
