package kotiki.controllers;

import kotiki.model.dto.*;
import kotiki.model.entity.*;
import kotiki.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kotiki")
public class KotikiController {

    private final KotikiService kotikiService;

    private final FriendsService friendsService;

    @Autowired
    public KotikiController(KotikiService kotikiService, FriendsService friendsService) {
        this.kotikiService = kotikiService;
        this.friendsService = friendsService;
    }

    @GetMapping(path="/all")
    public List<Kotiki> findAll() {
        return kotikiService.getAll();
    }

    @GetMapping(path="/get/{id}")
    public ResponseEntity<Kotiki> getById(@PathVariable Integer id) {
        try {
            Kotiki kotik = kotikiService.getById(id);
            return new ResponseEntity<>(kotik, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/getByColor/{color}")
    public ResponseEntity<List<Kotiki>> getByColor(@PathVariable Color color) {
        try {
            List<Kotiki> kotik = kotikiService.findByColor(color);
            return new ResponseEntity<>(kotik, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/getByBreed/{breed}")
    public ResponseEntity<List<Kotiki>> getByBreed(@PathVariable Breed breed) {
        try {
            List<Kotiki> kotik = kotikiService.findByBreed(breed);
            return new ResponseEntity<>(kotik, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/getFriends/{id}")
    public ResponseEntity<List<Kotiki>> getFriends(@PathVariable Integer id) {
        try {
            List<Friends> friends = friendsService.getAll();
            List<Integer> kotikiId = friendsService.findFriends(id, friends);
            List<Kotiki> kotiki = new ArrayList<>();

            for (Integer ids : kotikiId) {
                kotiki.add(kotikiService.getById(ids));
            }

            return ResponseEntity.ok().body(kotiki);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path="/create")
    public ResponseEntity create(@RequestBody KotikiDto kotik) {
        try {
            kotikiService.create(kotik);
            return new ResponseEntity<>(kotik, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<?> update(@RequestBody KotikiDto kotik, @PathVariable Integer id) {
        try {
            Kotiki existKotik = kotikiService.getById(id);
            if (existKotik == null){
                return new ResponseEntity<>(MessageFormat
                        .format("Kotik with id=", id, " not found"), HttpStatus.NOT_FOUND);
            }
            kotik.setId(id);
            kotikiService.create(kotik);
            return new ResponseEntity<>(kotik, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            kotikiService.delete(id);
            return ResponseEntity.ok().body("Deleted kotik");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
