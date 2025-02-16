package com.angerbytes.workshopmongo.services;

import com.angerbytes.workshopmongo.domain.Post;
import com.angerbytes.workshopmongo.repository.PostRepository;
import com.sun.jdi.ObjectCollectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id){
        Optional<Post> post = repo.findById(id);
        return post.orElseThrow(() -> new ObjectCollectedException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text){
//        return repo.findByTitleContainingIgnoreCase(text);
        return repo.searchTitle(text);
    }


}
