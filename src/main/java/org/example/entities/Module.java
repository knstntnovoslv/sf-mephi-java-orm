package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "modules")
public class Module extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    @Column(nullable = false)
    private String title;

    @Column(name = "order_index")
    private Integer orderIndex;
}
