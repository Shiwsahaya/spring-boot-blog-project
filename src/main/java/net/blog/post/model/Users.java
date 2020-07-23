package net.blog.post.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private int id;

    @OneToMany(mappedBy = "authorId")
    @Setter
    @Getter
    private List<Posts> posts=new ArrayList<>();

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String email;

    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    private String role;


}