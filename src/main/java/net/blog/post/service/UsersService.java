package net.blog.post.service;
import net.blog.post.Repository.UsersRepository;
import net.blog.post.model.UserPrincipal;
import net.blog.post.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Users users=usersRepo.findByName(name);
        if(users==null)
            throw new UsernameNotFoundException("User 404");
        return new UserPrincipal(users);
    }
}
