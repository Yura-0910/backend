package ru.effective_mobile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Данные для создания новой задачи")
public class TaskDto {

  public long taskId;
  @Size(min = 3, max = 10, message = "header должен быть не меньше 3, но не больше 10 символов")
  @Schema(description = "Заголовок задачи")
  public String header;
  @Size(min = 15, max = 25,
      message = "description должен быть не меньше 15, но не больше 25 символов")
  @Schema(description = "Описание задачи")
  public String description;
  @Min(value = 1, message = "statusId должен быть больше 0")
  @Schema(description = "id статуса задачи")
  public int statusId;
  @Min(value = 1, message = "priorityId должен быть больше 0")
  @Schema(description = "id приоритета задачи")
  public int priorityId;
  @Min(value = 1, message = "authorId должен быть больше 0")
  @Schema(description = "id автора задачи")
  public int authorId;
  @Min(value = 1, message = "executorId должен быть больше 0")
  @Schema(description = "id исполнителя задачи")
  public int executorId;
}
