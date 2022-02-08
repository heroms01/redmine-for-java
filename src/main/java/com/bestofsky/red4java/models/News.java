package com.bestofsky.red4java.models;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity(name = "news")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class News {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "title")
    @Length(max = 60)
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "author_id")
    @ColumnDefault("0")
    private Integer authorId;

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @Column(name = "comments_count")
    @ColumnDefault("0")
    private Integer commentsCount;
}