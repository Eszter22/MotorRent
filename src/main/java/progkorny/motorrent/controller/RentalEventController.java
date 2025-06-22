package progkorny.motorrent.controller;

import progkorny.motorrent.model.RentalEvent;
import progkorny.motorrent.service.RentalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
// Ez a vezérlő a bérlési események (rental events) REST alapú lekérdezéséért felel.
// Az elérési út: /api/rentalevents
@RestController()
@RequestMapping("api/rentalevents")
public class RentalEventController {
    // A rentalEventService osztály példányát a Spring injektálja be automatikusan.
    // Ez az osztály végzi a háttérben a bérlési események adatainak elérését és feldolgozását.
    @Autowired
    private RentalEventService rentalEventService;
    // GET /api/rentalevents
    // Visszaadja az összes bérlési eseményt egy listában.
    @GetMapping()
    public List<RentalEvent> getAllRentalEvents()
    {return rentalEventService.getAllRentalEvents(); }

}