package net.blog.post.Repository;
import net.blog.post.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer >, PagingAndSortingRepository<Category,Integer> {
    Optional<Category> findByName(String name);
}