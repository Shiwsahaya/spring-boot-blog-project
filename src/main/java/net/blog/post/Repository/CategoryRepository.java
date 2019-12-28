package net.blog.post.Repository;
import net.blog.post.model.Category;
import org.springframework.data.repository.CrudRepository;
public interface CategoryRepository extends CrudRepository<Category, Integer > {
}