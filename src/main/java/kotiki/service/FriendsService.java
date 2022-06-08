package kotiki.service;

import kotiki.model.dto.*;
import kotiki.model.entity.*;
import kotiki.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FriendsService {

    private final FriendsRepository friendsRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public FriendsService(FriendsRepository friendsRepo, ModelMapper modelMapper) {
        this.friendsRepo = friendsRepo;
        this.modelMapper = modelMapper;
    }

    public List<Friends> getAll() {
        return friendsRepo.findAll();
    }

    public void create(FriendsDto friends) {
        Friends friend = modelMapper.map(friends, Friends.class);
        friendsRepo.save(friend);
    }

    public Friends getById(Integer id) {
        return friendsRepo.findById(id).get();
    }

    public void delete(Integer id) {
        friendsRepo.deleteById(id);
    }

    public List<Integer> findFriends(Integer id, List<Friends> friends) {
        List<Integer> friendsFound = new ArrayList();

        for (Friends friend: friends) {
            if (id == friend.getKotik1()) {
                friendsFound.add(friend.getKotik2());
            }
            if (id == friend.getKotik2()) {
                friendsFound.add(friend.getKotik1());
            }
        }

        Set<Integer> set = new HashSet<>(friendsFound);
        friendsFound.clear();
        friendsFound.addAll(set);

        return friendsFound;
    }
}