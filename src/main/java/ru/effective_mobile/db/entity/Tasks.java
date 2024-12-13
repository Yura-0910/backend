package ru.effective_mobile.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Getter
@Setter
public class Tasks {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "task_id_pk")
  private long taskId;

  @Column(name = "header")
  private String header;

  @Column(name = "description")
  private String description;

  @Column(name = "status_id_fk")
  private int statusId;

  @Column(name = "priority_id_fk")
  private int priorityId;

  @Column(name = "author_id_fk")
  private int authorId;

  @Column(name = "executor_id_fk")
  private int executorId;
}
