package com.javaproject.webtodo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaproject.webtodo.dtos.TaskDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    private String description;
    @NotNull
    private Date dueDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    private Task (TaskDTO taskDTO) {
        this.title = taskDTO.title();
        this.description = taskDTO.description();
        this.dueDate = taskDTO.dueDate();
    }
}
