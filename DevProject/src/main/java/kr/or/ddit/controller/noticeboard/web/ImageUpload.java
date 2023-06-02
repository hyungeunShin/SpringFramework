package kr.or.ddit.controller.noticeboard.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class ImageUpload {
	//CKEDITOR 사용 시 규칙이다
	@RequestMapping(value="/imageUpload")
	public String imageUpload(
			HttpServletRequest req,
			HttpServletResponse res,
			MultipartHttpServletRequest multiFile) throws IOException {
		JsonObject json = new JsonObject();
		PrintWriter pw = null;
		OutputStream out = null;
		long limitSize = 1024 * 1024 * 2; // 2MB
		//upload라는 이름으로 파일이 온다
		MultipartFile file = multiFile.getFile("upload");
		
		if(file != null && file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) {
			if(file.getContentType().toLowerCase().startsWith("image/")) {
				if(file.getSize() > limitSize) {
					JsonObject jsonMsg = new JsonObject();
					JsonArray jsonArr = new JsonArray();
					jsonMsg.addProperty("message", "2MB미만의 이미지만 업로드 가능합니다"); //message라는 키에 에러메시지를 담을수있다.
					jsonArr.add(jsonMsg);
					json.addProperty("uploaded", 0); //실패값
					json.add("error", jsonArr.get(0));
					//실패 시 CKEDIOTOR의 규칙
					
					res.setCharacterEncoding("UTF-8");
					pw = res.getWriter();
					pw.println(json);
				} else { //정상
					try {
						String fileName = file.getName();
						byte[] bytes = file.getBytes();
						String uploadPath = req.getServletContext().getRealPath("/resources/img"); //업로드경로
						
						File uploadFile = new File(uploadPath);
						
						if(!uploadFile.exists()) {
							uploadFile.mkdirs();
						}
						
						fileName = UUID.randomUUID().toString();	//UUID를 활용하여 랜덤으로 발생된 이름을 저장
						uploadPath = uploadPath + "/" + fileName + ".jpg"; // resources/img/UUID.jpg가 완성
						
						out = new FileOutputStream(new File(uploadPath));
						out.write(bytes);  //파일복사
						
						pw = res.getWriter();
						res.setContentType("text/html");
						String fileUrl = req.getContextPath() + "/resources/img/" + fileName + ".jpg";
						
						//성공 시 필수로 3개 모두 다시 내보내줘야 한다.
						json.addProperty("uploaded", 1); //성공값
						json.addProperty("fileName", fileName);
						json.addProperty("url", fileUrl);
						
						pw.println(json);
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if(out != null) {
							out.close();
						}
						if(pw != null) {
							pw.close();
						}
					}
				}
			}
		}
		
		return null;
	}
}
