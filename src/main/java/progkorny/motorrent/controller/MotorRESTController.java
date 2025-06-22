package progkorny.motorrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.OptimisticLockingFailureException;
import progkorny.motorrent.model.Motor;
import progkorny.motorrent.service.MotorService;

import java.util.List;
// Ez az osztály egy REST API vezérlő (controller), amely a Motork kezeléséért felel.
// Az URL-ek a "/api/Motors" útvonalról érhetők el.
@RestController
@RequestMapping("/api/Motors")
public class MotorRESTController {
    // A Motorkkal kapcsolatos üzleti logikát egy külön szolgáltatás (service) kezeli.
    // Az @Autowired automatikusan betölti a MotorService példányt a Spring konténerből.
    @Autowired
    private MotorService MotorService;
    // GET /api/Motors
    // Minden Motor lekérdezése.
    @GetMapping
    public List<Motor> getAllMotors() {
        return MotorService.getAllMotors();
    }
    // GET /api/Motors/{id}
    // Egy adott Motor lekérdezése azonosító alapján.
    // Ha nem található, 404 NOT FOUND válasszal tér vissza.
    @GetMapping("/{id}")
    public ResponseEntity<Motor> getMotorById(@PathVariable Long id) {
        return MotorService.getMotorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // POST /api/Motors
    // Új Motor létrehozása. A kérés törzsében (body) JSON formátumban küldjük a Motor adatait.
    @PostMapping
    public Motor createMotor(@RequestBody Motor Motor) {
        return MotorService.createMotor(Motor);
    }
    // PUT /api/Motors/{id}
    // Egy meglévő Motor frissítése azonosító alapján.
    // Optimista zárolás esetén 409 Conflict hibaüzenettel tér vissza.
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMotor(@PathVariable Long id, @RequestBody Motor Motor) {
        try {
            Motor updated = MotorService.updateMotor(id, Motor);
            return ResponseEntity.ok(updated);
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.status(409).body("Hiba: A Motort időközben más is módosította.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    // DELETE /api/Motors/{id}
    // Motor törlése azonosító alapján.
    // Ha sikeres → 204 No Content, ha nem található → 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotor(@PathVariable Long id) {
        if (MotorService.deleteMotor(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}