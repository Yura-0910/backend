package ru.effective_mobile.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

  public String content;
  public long taskId;
  public int userId;
}
