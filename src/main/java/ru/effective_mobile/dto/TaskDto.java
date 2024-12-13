package ru.effective_mobile.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
  public long taskId;
  public String header;
  public String description;
  public int statusId;
  public int priorityId;
  public int authorId;
  public int executorId;
}
