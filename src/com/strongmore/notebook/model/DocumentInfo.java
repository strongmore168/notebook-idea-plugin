package com.strongmore.notebook.model;

import java.util.List;
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
public class DocumentInfo {

  private String title;
  private String fileName;
  private List<NoteInfo> noteInfoList;

}
