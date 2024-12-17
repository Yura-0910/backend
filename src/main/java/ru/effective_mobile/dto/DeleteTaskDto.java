package ru.effective_mobile.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteTaskDto {
  public long taskId;
  public int senderId; //ID пользователя, отправляющего данные на удаление задачи.
}
