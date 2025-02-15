package com.angerbytes.workshopmongo.services;

import com.angerbytes.workshopmongo.domain.User;
import com.angerbytes.workshopmongo.dto.UserDTO;
import com.angerbytes.workshopmongo.repository.UserRepository;
import com.sun.jdi.ObjectCollectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }

    public User findById(String id){
        Optional<User> user = repo.findById(id);
        return user.orElseThrow(() -> new ObjectCollectedException("Objeto não encontrado"));
    }

    public User insert(User obj){
        return repo.insert(obj);
    }

    public User fromDTO(UserDTO obj){
        return new User(obj.getId(), obj.getName(), obj.getEmail());
    }

}
