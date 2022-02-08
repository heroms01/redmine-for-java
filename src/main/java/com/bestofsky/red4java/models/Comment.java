package com.bestofsky.red4java.models;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity(name = "comments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "commented_type")
    @Length(max = 30)
    private String commentedType;

    @Column(name = "commented_id")
    @ColumnDefault("0")
    private Long commentedId;

    @Column(name = "author_id")
    @ColumnDefault("0")
    private Long authorId;

    @Column(name = "content")
    @Lob
    private String content;

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @Column(name = "updated_on")
    private ZonedDateTime updatedOn;
}
