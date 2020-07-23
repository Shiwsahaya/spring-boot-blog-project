package net.blog.post.model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, insertable = false)
    @Setter
    @Getter
    private  int id;

    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    private String body;

    @ManyToOne
    @Setter
    @Getter
    private Users authorId;

    @CreationTimestamp
    @Column(updatable = false)
    @Setter
    @Getter
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Setter
    @Getter
    private LocalDateTime updatedAt;

    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="category_Posts",
            joinColumns=@JoinColumn(name="posts_id"),
            inverseJoinColumns=@JoinColumn(name="category_id")
    )
    @Setter
    @Getter
    private List<Category> categories=new ArrayList<>();

    public void addCategory(Category theCategory){
        if(categories==null){
            categories=new ArrayList<>();
        }
        categories.add(theCategory);
    }
}