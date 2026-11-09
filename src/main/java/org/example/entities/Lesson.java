package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "lessons")
public class Lesson extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private Module module;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content ", nullable = false)
    private String content ;

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private Set<Assignment> assignments;
}
