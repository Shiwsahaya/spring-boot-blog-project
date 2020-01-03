package net.blog.post.model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
public class UserPrincipal implements UserDetails {

    public UserPrincipal(Users users) {
        this.users = users;
    }

    private Users users;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {

        System.out.println(users.getPassword());
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println(users.getName());
        return users.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
