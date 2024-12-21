package ru.effective_mobile.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {

  public long taskId;
  @Size(min = 3, max = 10, message = "header должен быть не меньше 3, но не больше 10 символов")
  public String header;
  @Size(min = 15, max = 25,
      message = "description должен быть не меньше 15, но не больше 25 символов")
  public String description;
  @Min(value = 1, message = "statusId должен быть больше 0")
  public int statusId;
  @Min(value = 1, message = "priorityId должен быть больше 0")
  public int priorityId;
  @Min(value = 1, message = "authorId должен быть больше 0")
  public int authorId;
  @Min(value = 1, message = "executorId должен быть больше 0")
  public int executorId;
}
