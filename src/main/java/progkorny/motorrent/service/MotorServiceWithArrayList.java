package progkorny.motorrent.service;

import progkorny.motorrent.exception.NosuchEntityException;
import progkorny.motorrent.model.Motor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service  // Service réteg komponens, a Spring automatikusan kezeli az életciklust
public class MotorServiceWithArrayList {

    // motork listája, inicializálva 3 előre definiált motorval
    private List<Motor> Motors = new ArrayList<>(List.of(
            createMotor(1L, "Suzuki", 1.0),
            createMotor(2L, "Yamaha", 1.0),
            createMotor(3L, "Honda", 1.0)
    ));

    // Segédfüggvény motor létrehozására adott id, név és hossz alapján
    private Motor createMotor(Long id, String name, double length) {
        Motor Motor = new Motor();
        Motor.setId(id);
        Motor.setName(name);
        Motor.setLength(length);
        // További alapértelmezett értékek beállítása
        Motor.setBrand("Ismeretlen");
        Motor.setModel("Alap");
        Motor.setBuildYear(2000);
        Motor.setDailyRate(100.0);
        Motor.setAvailable(true);
        Motor.setNumberOfSeats(4);
        return Motor;
    }

    // Visszaadja az összes motort listában
    public List<Motor> getAllMotor() {
        return Motors;
    }

    // motor keresése ID alapján, ha nincs ilyen motor, kivételt dob
    public Motor getMotorById(Long id) {
        return Motors.stream()
                .filter(Motor -> Motor.getId().equals(id))  // Szűrés azonosító alapján
                .findFirst()                              // Az első találat
                .orElseThrow(() ->                        // Ha nincs találat, kivétel
                        new NosuchEntityException("There is no Motor with id: " + id));
    }

    // Beszúr egy új motort vagy frissíti a meglévőt azonosító alapján
    public int insertOrUpdateMotor(Motor Motor) {
        // Először törli, ha már van ilyen ID-jű motor a listában
        Motors.removeIf(b -> b.getId().equals(Motor.getId()));
        // Majd hozzáadja az új vagy frissített motort
        Motors.add(Motor);
        // Visszatér a listában lévő motork számával
        return Motors.size();
    }

    // motor törlése ID alapján
    public boolean deleteMotorById(Long id) {
        Optional<Motor> MotorToDelete = Motors.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();

        if (MotorToDelete.isPresent()) {
            Motors.remove(MotorToDelete.get());  // Ha megtalálta, törli
            return true;
        } else {
            // Ha nem található a motor, kivételt dob
            throw new NosuchEntityException("There is no Motor to delete with id: " + id);
        }
    }
}