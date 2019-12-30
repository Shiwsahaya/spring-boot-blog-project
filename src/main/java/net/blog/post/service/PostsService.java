package net.blog.post.service;
import net.blog.post.Repository.PostsRepository;
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

    public List<Posts> sortByPublishedDate(){
        return  repo.findAllByOrderByCreatedAtAsc();
    }

    public List<Posts>sortByUpLastUpdatedDate(){
        return repo.findAllByOrderByUpdatedAtDesc();
    }

    public Page<Posts>findAllByPage(Pageable pageable){
        return repo.findAll(pageable);
    }
    public List<Posts>search(String keyword){
        return repo.search(keyword);
    }

}