package net.blog.post.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private String name;

    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="category_Posts",
            joinColumns=@JoinColumn(name="category_id"),
            inverseJoinColumns=@JoinColumn(name="posts_id")
    )
    @Setter
    @Getter
    private List<Posts> posts;

    public void addPosts(Posts thePosts){
        if(posts==null){
            posts=new ArrayList<>();
        }
        posts.add(thePosts);
    }


}