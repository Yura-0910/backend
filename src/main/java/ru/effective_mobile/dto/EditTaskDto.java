package ru.effective_mobile.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditTaskDto extends TaskDto{
  public int senderId; //ID пользователя, отправляющего данные на обновление\редактирование
}
