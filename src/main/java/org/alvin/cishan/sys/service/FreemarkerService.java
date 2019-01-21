package org.alvin.cishan.sys.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * 静态生产工具
 */
@Service
public class FreemarkerService {

	private static final String EXTNAME = ".html";

	@Autowired
	private freemarker.template.Configuration freeMarkerConfig;

	/**
	 * 通过freemark 把内容生成html
	 */
	public String parseToHtml(String templateName, Map<String, String> params, String rootPath, String newsUrl, String modelPath) throws IOException, TemplateException {
		String fileName = String.valueOf(System.currentTimeMillis()) + EXTNAME;
		return parseToHtml(templateName, params, rootPath, newsUrl, modelPath, fileName);
	}

	/**
	 * 通过freemark 把内容生成html
	 */
	public String parseToHtml(String templateName, Map<String, String> params, String rootPath, String newsUrl, String modelPath, String fileName) throws IOException, TemplateException {
		StringBuilder path = new StringBuilder();
		path.append(rootPath);
		path.append(modelPath);
		path.append(fileName);

		Path outputFile = Paths.get(path.toString());
		if(!Files.exists(outputFile.getParent())){
			Files.createDirectory(outputFile.getParent());
		}
		Template template = freeMarkerConfig.getTemplate(templateName);
		try (Writer wr = Files.newBufferedWriter(outputFile)) {
			template.process(params, wr);
			wr.flush();
		}

		path = new StringBuilder();
		path.append(newsUrl);
		path.append(modelPath);
		path.append(fileName);

		return path.toString();
	}
}
