package progkorny.motorrent.repository;

import progkorny.motorrent.model.Motor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// A @Repository annotációval jelezzük, hogy ez az osztály egy Spring Data Repository,
// amely az adatbázis műveletekért felelős a Motor entitás számára.
@Repository
public interface MotorRepository extends JpaRepository<Motor, Long> {
    // A JpaRepository biztosítja az alapvető CRUD műveleteket (Create, Read, Update, Delete)
    // és további hasznos metódusokat.
    // A paraméterek:
    // - Motor: az entitás típusa, amellyel dolgozunk
    // - Long: az entitás azonosítójának típusa (Motor.id típusa)
}
