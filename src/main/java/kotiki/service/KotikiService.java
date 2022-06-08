package kotiki.service;

import kotiki.model.dto.*;
import kotiki.model.entity.*;
import kotiki.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KotikiService {

    private final KotikiRepository kotikiRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public KotikiService(KotikiRepository kotikiRepo, ModelMapper modelMapper) {
        this.kotikiRepo = kotikiRepo;
        this.modelMapper = modelMapper;
    }

    public List<Kotiki> getAll() {
        return kotikiRepo.findAll();
    }

    public void create(KotikiDto kotik) {
        Kotiki kotiki = modelMapper.map(kotik, Kotiki.class);
        kotikiRepo.save(kotiki);
    }

    public Kotiki getById(Integer id) {
        return kotikiRepo.findById(id).get();
    }

    public void delete(Integer id) {
        kotikiRepo.deleteById(id);
    }

    public List<Kotiki> findOwnerKotiki(Integer id, List<Kotiki> kotiki) {
        List<Kotiki> kotikiFound = new ArrayList();

        for (Kotiki kotik: kotiki) {
            if (kotik.getOwnerId() == id) {
                kotikiFound.add(kotik);
            }
        }

        return kotikiFound;
    }

    public List<Kotiki> findByColor(Color color) {
        List<Kotiki> kotiki = kotikiRepo.findAll();
        List<Kotiki> foundKotiki = new ArrayList<>();

        for(Kotiki kotik : kotiki) {
            if(kotik.getKotikColor() == color) {
                foundKotiki.add(kotik);
            }
        }

        return foundKotiki;
    }

    public List<Kotiki> findByBreed(Breed breed) {
        List<Kotiki> kotiki = kotikiRepo.findAll();
        List<Kotiki> foundKotiki = new ArrayList<>();

        for(Kotiki kotik : kotiki) {
            if(kotik.getKotikBreed() == breed) {
                foundKotiki.add(kotik);
            }
        }

        return foundKotiki;
    }
}
