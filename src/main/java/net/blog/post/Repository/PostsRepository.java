package net.blog.post.Repository;
import net.blog.post.model.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface  PostsRepository extends JpaRepository<Posts, Integer > , PagingAndSortingRepository<Posts,Integer> {
    List<Posts> findAllByOrderByCreatedAtAsc();
    List<Posts>findAllByOrderByUpdatedAtDesc();

    //pagination
    Page<Posts>findAll(Pageable pageable);

}