package progkorny.motorrent.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// Ez a vezérlő felelős a HTML nézetek (weboldalak) kiszolgálásáért.
// Nem REST API, hanem klasszikus MVC Controller, amely sablonfájlokat szolgál ki.
@Controller
public class ViewController {
    // GET /
    // A főoldal megjelenítése.
    // A visszatérési érték egy sablonfájl neve (templates/index.html).
    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html
    }
    // GET /delete
    // A motor törléséhez tartozó oldal megjelenítése.
    // Pl. form vagy lista törléshez.
    @GetMapping("/delete")
    public String deleteMotorPage() {
        return "delete-motor"; // templates/delete-motor.html
    }
    // GET /upload
    // Új motor feltöltéséhez (regisztrálásához) tartozó oldal.
    @GetMapping("/upload")
    public String uploadMotorPage() {
        return "upload-motor"; // templates/upload-motor.html
    }
    // GET /upload-renter
    // Új ügyfél (bérlő) feltöltésének oldala.
    @GetMapping("/upload-renter")
    public String uploadCustomerPage() {
        return "upload-renter"; // templates/upload-renter.html
    }
}
