package progkorny.motorrent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progkorny.motorrent.model.Motor;
import progkorny.motorrent.repository.MotorRepository;

import java.util.List;
import java.util.Optional;

@Service  // Service réteg komponensként jelöli az osztályt, a Spring kezeli az életciklust
public class MotorService {

    @Autowired  // Automatikusan beszúrja a MotorRepository példányt (dependency injection)
    private MotorRepository MotorRepository;

    // Összes Motor lekérdezése az adatbázisból
    public List<Motor> getAllMotors() {
        return MotorRepository.findAll();
    }

    // Egy Motor lekérdezése ID alapján, Optional típusban visszatérve (lehet üres)
    public Optional<Motor> getMotorById(Long id) {
        return MotorRepository.findById(id);
    }

    // Új Motor létrehozása és mentése az adatbázisba
    public Motor createMotor(Motor Motor) {
        return MotorRepository.save(Motor);
    }

    @Transactional  // Tranzakciókezelés: az egész metódus vagy végrehajtódik, vagy nem
    public Motor updateMotor(Long id, Motor updatedMotor) {
        // Megkeressük az adott ID-jű Motort, ha nincs, RuntimeException dobódik
        Motor existing = MotorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Motor not found"));

        // Optimistic Locking: a @Version mező használatával automatikus versenykezelés a frissítések között

        // A létező Motor adatainak frissítése az új adatokkal
        existing.setName(updatedMotor.getName());
        existing.setBrand(updatedMotor.getBrand());
        existing.setLength(updatedMotor.getLength());
        existing.setModel(updatedMotor.getModel());
        existing.setBuildYear(updatedMotor.getBuildYear());
        existing.setDailyRate(updatedMotor.getDailyRate());
        existing.setAvailable(updatedMotor.isAvailable());
        existing.setNumberOfSeats(updatedMotor.getNumberOfSeats());
        existing.setVersion(updatedMotor.getVersion()); // Verzió mező frissítése, hogy az optimista zárolás működjön

        // Mentés az adatbázisba, frissített entitás visszaadása
        return MotorRepository.save(existing);
    }

    // Motor törlése ID alapján, visszatérési érték jelzi, sikerült-e a törlés
    public boolean deleteMotor(Long id) {
        if (MotorRepository.existsById(id)) {  // Ellenőrizzük, hogy létezik-e a Motor
            MotorRepository.deleteById(id);     // Törlés adatbázisból
            return true;                       // Törlés sikeres
        } else {
            return false;                      // Nem találtuk a Motort, törlés nem történt
        }
    }
}
