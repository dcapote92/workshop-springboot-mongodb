package com.angerbytes.workshopmongo.repository;

import com.angerbytes.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {

    List<Post> findByTitleContainingIgnoreCase(String text);

    @Query("{'title': { $regex: ?0 , $options: 'i' }}")
        // logic -> title contains text ignoring case
        // where 'title' is the field, $regex is the regular expression (text)
        // ?0 is the parameter position 0,1,2,3... and 'i'  ignore case
    List<Post> searchTitle(String text);

    @Query("{ $and: [" +
                "{ date: { $gte: ?1 } }," +
                "{ date: { $lte: ?2 } }," +
                "{ $or: [" +
                    "{ title: { $regex: ?0, $options: 'i' } }," +
                    "{ body: { $regex: ?0, $options: 'i' } }," +
                    "{ 'comments.text': { $regex: ?0, $options: 'i' } }" +
                 "]}" +
            "]}")
        // logic -> (
        //      date >= minDate && date <= maxDate &&
        //      (title contains text || body contains text || comments.text contains text)
        //      )
        // where $and == && , $or == || , $gte == greater than or equal , $lte == less than or equal

    List<Post> fullSearch(String text, Date minDate, Date maxDate);

}
