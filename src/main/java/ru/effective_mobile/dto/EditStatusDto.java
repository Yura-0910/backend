package ru.effective_mobile.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditStatusDto {
  public long taskId;
  public int statusId;
  public int senderId; //ID пользователя, отправляющего данные на изменение статуса задачи.
}
