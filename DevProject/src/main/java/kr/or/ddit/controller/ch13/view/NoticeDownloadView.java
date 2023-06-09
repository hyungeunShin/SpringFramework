package kr.or.ddit.controller.ch13.view;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.view.AbstractView;

public class NoticeDownloadView extends AbstractView {
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) model.get("noticeFileMap");
		
		File saveFile = new File(map.get("fileSavepath").toString());
		String fileName = (String) map.get("fileName");
		String agent = request.getHeader("User-Agent");
		
		//한글깨짐 방지
		if(StringUtils.containsIgnoreCase(agent, "msie") || StringUtils.containsIgnoreCase(agent, "trident")) {
			fileName = URLEncoder.encode(fileName, "UTF-8");	 //IE, Chrome
		} else {
			fileName = new String(fileName.getBytes(), "ISO-8859-1");	// Firefox, chrome
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setHeader("Content-Length", map.get("fileSize").toString());
		
		try(OutputStream os = response.getOutputStream()) {
			FileUtils.copyFile(saveFile, os);	//파일 다운로드
		}
	}
}
