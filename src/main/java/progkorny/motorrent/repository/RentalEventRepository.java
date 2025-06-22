package progkorny.motorrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import progkorny.motorrent.model.RentalEvent;

// Ez az interfész a RentalEvent entitás adatbázis műveleteit kezeli.
// A JpaRepository által biztosított CRUD műveleteket használja,
// így nincs szükség manuális implementációra.
public interface RentalEventRepository extends JpaRepository<RentalEvent, Integer> {
    // Paraméterek:
    // RentalEvent: az entitás típusa, amelyhez a repository tartozik
    // Integer: a RentalEvent entitás azonosítójának típusa (id mező típusa)
}
