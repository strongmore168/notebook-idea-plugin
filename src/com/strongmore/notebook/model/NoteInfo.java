package com.strongmore.notebook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class NoteInfo {

  private String title;
  private String content;
  private String selectedText;
  private String fileName;
  private String fileType;

  public String getFileType() {
    return fileName.substring(fileName.lastIndexOf(".") + 1);
  }
}
