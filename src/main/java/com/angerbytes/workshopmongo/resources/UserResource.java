package com.angerbytes.workshopmongo.resources;

import com.angerbytes.workshopmongo.domain.User;
import com.angerbytes.workshopmongo.dto.UserDTO;
import com.angerbytes.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){

        List<User> list = service.findAll();
//        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).toList(); course mode
        List<UserDTO> listDto = list.stream().map(UserDTO::new).toList(); // my mode
        return ResponseEntity.ok().body(listDto);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }
}
