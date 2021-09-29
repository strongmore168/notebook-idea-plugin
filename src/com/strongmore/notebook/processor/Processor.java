package com.strongmore.notebook.processor;

import com.strongmore.notebook.model.DocumentInfo;

public interface Processor {

  void process(DocumentInfo documentInfo) throws Exception;
}
