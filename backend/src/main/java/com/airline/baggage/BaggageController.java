package com.airline.baggage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baggage")
@CrossOrigin(origins = "*")
public class BaggageController {
    private final BaggageRepository repository;

    public BaggageController(BaggageRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Baggage> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Baggage> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Baggage create(@RequestBody Baggage baggage) {
        baggage.setId(null);
        return repository.save(baggage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
