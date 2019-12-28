package net.blog.post.service;
import net.blog.post.Repository.CategoryRepository;
import net.blog.post.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public List<Category>listAll(){
        return (List<Category>)repo.findAll();
    }

    public void save(Category category) {
        repo.save(category);
    }

    public Category get(int id){
        Optional<Category>result=repo.findById(id);
        return result.get();
    }

    public void delete(int id){
        repo.deleteById(id);
    }

}