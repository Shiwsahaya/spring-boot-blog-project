package net.blog.post.service;
import net.blog.post.Repository.CategoryRepository;
import net.blog.post.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;
    public Category get(int id){
        Optional<Category>result=repo.findById(id);
        return result.get();
    }



}