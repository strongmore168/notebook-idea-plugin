package com.strongmore.notebook.processor;

import com.intellij.ide.fileTemplates.impl.UrlUtil;
import com.strongmore.notebook.model.DocumentInfo;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class MarkDownProcessor implements Processor {

  @Override
  public void process(DocumentInfo documentInfo) throws Exception {
    String templatePath = UrlUtil.loadText(MarkDownProcessor.class.getResource("/ftl/md.ftl"));
    Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
    StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
    stringTemplateLoader.putTemplate("MDTemplate", templatePath);
    configuration.setTemplateLoader(stringTemplateLoader);
    Template template = configuration.getTemplate("MDTemplate");
    BufferedWriter writer = new BufferedWriter(new FileWriter(documentInfo.getFileName()));
    template.process(documentInfo, writer);
  }
}
