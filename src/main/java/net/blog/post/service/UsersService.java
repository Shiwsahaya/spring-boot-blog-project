package net.blog.post.service;

import net.blog.post.Repository.UsersRepository;
import net.blog.post.model.Category;
import net.blog.post.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepo;

    public List<Users> listAll(){
        return (List<Users>)usersRepo.findAll();
    }

    public void save(Users users) {
        usersRepo.save(users);
    }

    public Users get(int id){
        Optional<Users> result=usersRepo.findById(id);
        return result.get();
    }

    public void delete(int id){
        usersRepo.deleteById(id);
    }
}
