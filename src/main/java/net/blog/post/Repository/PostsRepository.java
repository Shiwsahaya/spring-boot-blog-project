package net.blog.post.Repository;
import net.blog.post.model.Category;
import net.blog.post.model.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface  PostsRepository extends JpaRepository<Posts, Integer > , PagingAndSortingRepository<Posts,Integer> {
//    List<Posts>findAllByOrderByUpdatedAtDesc();

    //pagination
    List<Posts>findByCategories(Category category);
    Page<Posts>findAll(Pageable pageable);
    Page<Posts>findAllByOrderByCreatedAtAsc(Pageable pageable);
    Page<Posts>findAllByOrderByUpdatedAtDesc(Pageable pageable);


    @Query(value = "SELECT t FROM Posts t WHERE " +
            "LOWER(t.title) LIKE LOWER(CONCAT('%',:keyword, '%')) OR " +
            "LOWER(t.body) LIKE LOWER(CONCAT('%',:keyword, '%'))")
    public Page<Posts> search(Pageable pageable,@Param("keyword")String keyword);

}