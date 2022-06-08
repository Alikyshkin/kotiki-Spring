package kotiki.service;

import kotiki.model.entity.*;
import kotiki.repository.*;
import kotiki.model.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public OwnerService(OwnerRepository ownerRepo, ModelMapper modelMapper) {
        this.ownerRepo = ownerRepo;
        this.modelMapper = modelMapper;
    }

    public List<Owner> getAll() {
        return ownerRepo.findAll();
    }

    public void create(OwnerDto user) {
        Owner owner = modelMapper.map(user, Owner.class);
        ownerRepo.save(owner);
    }

    public Owner getById(Integer id) {
        return ownerRepo.findById(id).get();
    }

    public void delete(Integer id) {
        ownerRepo.deleteById(id);
    }
}
