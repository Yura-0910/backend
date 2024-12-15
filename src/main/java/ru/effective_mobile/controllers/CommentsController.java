package ru.effective_mobile.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.effective_mobile.dto.CommentDto;
import ru.effective_mobile.services.CommentsService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CommentsController {
  private CommentsService commentsService;

  /**
   * Добавляем комментарий к любой задаче
   */
  @PostMapping("/addComment")
  public ResponseEntity<String> addComment(@RequestBody CommentDto commentDto) {
    return commentsService.addComment(commentDto);
  }
}
