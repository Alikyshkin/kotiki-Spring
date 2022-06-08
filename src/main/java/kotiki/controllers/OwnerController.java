package kotiki.controllers;

import kotiki.model.dto.*;
import kotiki.model.entity.*;
import kotiki.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    private final KotikiService kotikiService;

    @Autowired
    public OwnerController(OwnerService ownerService, KotikiService kotikiService) {
        this.ownerService = ownerService;
        this.kotikiService = kotikiService;
    }


    @GetMapping(path="/all")
    public List<Owner> findAll() {
        return ownerService.getAll();
    }

    @GetMapping(path="/get/{id}")
    public ResponseEntity<Owner> getById(@PathVariable Integer id) {
        try {
            Owner owner = ownerService.getById(id);
            return new ResponseEntity<>(owner, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/getKotiki/{id}")
    public ResponseEntity<List<Kotiki>> getOwnerKotiki(@PathVariable Integer id) {
        try {
            List<Kotiki> kotiki = kotikiService.getAll();
            List<Kotiki> kotikiFound = kotikiService.findOwnerKotiki(id, kotiki);

            return ResponseEntity.ok().body(kotikiFound);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path="/create")
    public ResponseEntity create(@RequestBody OwnerDto owner) {
        try {
            ownerService.create(owner);
            return new ResponseEntity<>(owner, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<?> update(@RequestBody OwnerDto owner, @PathVariable Integer id) {
        try {
            Owner existOwner = ownerService.getById(id);
            if (existOwner == null){
                return new ResponseEntity<>(MessageFormat
                        .format("Owner with id=", id, " not found"), HttpStatus.NOT_FOUND);
            }
            owner.setId(id);
            ownerService.create(owner);
            return new ResponseEntity<>(owner, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            ownerService.delete(id);
            return ResponseEntity.ok().body("Deleted owner");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
