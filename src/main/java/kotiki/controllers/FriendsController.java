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
@RequestMapping("/friends")
public class FriendsController {

    private final FriendsService friendsService;

    @Autowired
    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @GetMapping(path="/all")
    public List<Friends> findAll() {
        return friendsService.getAll();
    }

    @GetMapping(path="/get/{id}")
    public ResponseEntity<Friends> getById(@PathVariable Integer id) {
        try {
            Friends friends = friendsService.getById(id);
            return new ResponseEntity<>(friends, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path="/create")
    public ResponseEntity<?> create(@RequestBody FriendsDto friends) {
        try {
            friendsService.create(friends);
            return new ResponseEntity<>(friends, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<?> update(@RequestBody FriendsDto friends, @PathVariable Integer id) {
        try {

            Friends existFriends = friendsService.getById(id);
            if (existFriends == null){
                return new ResponseEntity<>(MessageFormat
                        .format("Friend with id=", id, " not found"), HttpStatus.NOT_FOUND);
            }
            friends.setId(id);
            friendsService.create(friends);
            return new ResponseEntity<>(friends, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            friendsService.delete(id);
            return ResponseEntity.ok().body("Deleted friends");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
