package ru.effective_mobile.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.effective_mobile.db.entity.Comments;
import ru.effective_mobile.db.entity.Tasks;

@Getter
@Setter
@NoArgsConstructor
public class TaskAndCommentsDto {

  public Tasks task;
  public List<Comments> comments;
}
