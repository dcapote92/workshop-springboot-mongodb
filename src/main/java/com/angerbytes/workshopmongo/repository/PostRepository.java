package com.angerbytes.workshopmongo.repository;

import com.angerbytes.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {

    List<Post> findByTitleContainingIgnoreCase(String text);

    @Query("{'title': { $regex: ?0 , $options: 'i' }}") // ?0 is the parameter position 0,1,2,3...
    List<Post> searchTitle(String text);
}
