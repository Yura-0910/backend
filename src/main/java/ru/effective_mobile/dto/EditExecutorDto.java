package ru.effective_mobile.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditExecutorDto {
  public long taskId;
  public int executorId;
  public int senderId; //ID пользователя, отправляющего данные на изменение исполнителя задачи.
}
