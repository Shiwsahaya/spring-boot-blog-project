package net.blog.post.service;
import net.blog.post.Repository.PostsRepository;
import net.blog.post.model.Category;
import net.blog.post.model.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostsService {

    @Autowired
    private PostsRepository repo;

    public List<Posts> listAll() {
        return (List<Posts>) repo.findAll();
    }



    public void save(Posts posts) {
        repo.save(posts);
    }

    public Posts get(int id) {
        Optional<Posts> result = repo.findById(id);
        return result.get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public Page<Posts> sortByPublishedDate(Pageable pageable){
        return  repo.findAllByOrderByCreatedAtAsc(pageable);
    }

    public Page<Posts>sortByUpLastUpdatedDate(Pageable pageable){
        return repo.findAllByOrderByUpdatedAtDesc(pageable);
    }

    public Page<Posts>findAllByPage(Pageable pageable){
        return repo.findAll(pageable);
    }
    public Page<Posts>search(Pageable pageable,String keyword){
        return repo.search(pageable,keyword);
    }

    public List<Posts>findByCategory(Category category){
        return repo.findByCategories(category);
    }

}