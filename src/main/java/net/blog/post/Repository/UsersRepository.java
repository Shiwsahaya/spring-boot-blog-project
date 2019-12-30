package net.blog.post.Repository;

import net.blog.post.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Integer> {
}
