package kr.or.ddit.controller.ch13.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.ch13.web.TelegramSendController;
import kr.or.ddit.mapper.LoginMapper;
import kr.or.ddit.mapper.NoticeMapper;
import kr.or.ddit.vo.DDITMemberVO;
import kr.or.ddit.vo.NoticeFileVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class NoticeServiceImpl implements INoticeService {
	
	@Inject
	private NoticeMapper noticeMapper;
	
	@Inject
	private LoginMapper loginMapper;
	
	TelegramSendController tst = new TelegramSendController();
	
	@Override
	public ServiceResult insertNotice(HttpServletRequest req, NoticeVO notice) {
		ServiceResult result = null;
		int status = noticeMapper.insertNotice(notice);
		if(status > 0) {
			List<NoticeFileVO> noticeFileList = notice.getNoticeFileList();
			noticeFileUpload(noticeFileList, notice.getBoNo(), req);
			
			try {
				tst.sendGet("406호", notice.getBoTitle());
			} catch (Exception e) {
				e.printStackTrace();
			}
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	private void noticeFileUpload(List<NoticeFileVO> noticeFileList, int boNo, HttpServletRequest req) {
		String savePath = "/resources/notice/";
		
		if(noticeFileList != null && noticeFileList.size() > 0) {
			for (NoticeFileVO noticeFileVO : noticeFileList) {
				String saveName = UUID.randomUUID().toString();
				
				//파일명을 설정할 때 원본 파일명의 공백을 '_'로 변경
				saveName = saveName + "_" + noticeFileVO.getFileName().replaceAll(" ", "_");
				String endFileName = noticeFileVO.getFileName().split("\\.")[1]; //확장자 추출
				
				// /resources/notice/1
				// 글번호를 디렉토리로
				String saveLocate = req.getServletContext().getRealPath(savePath + boNo);
				File file = new File(saveLocate);
				
				if(!file.exists()) {
					file.mkdirs();
				}
				
				saveLocate += "/" + saveName;
				
				File saveFile = new File(saveLocate);
				
				noticeFileVO.setBoNo(boNo);
				noticeFileVO.setFileSavepath(saveLocate);
				noticeMapper.insertNoticeFile(noticeFileVO);
				
				try {
					noticeFileVO.getItem().transferTo(saveFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public NoticeVO selectNotice(int boNo) {
		noticeMapper.incrementHit(boNo);
		return noticeMapper.selectNotice(boNo);
	}

	@Override
	public ServiceResult updateNotice(NoticeVO notice) {
		ServiceResult result = null;
		int status = noticeMapper.updateNotice(notice);
		
		if(status > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ServiceResult deleteNotice(int boNo) {
		ServiceResult result = null;
		int status = noticeMapper.deleteNotice(boNo);
		
		if(status > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {
		return noticeMapper.selectNoticeCount(pagingVO);
	}

	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return noticeMapper.selectNoticeList(pagingVO);
	}

	@Override
	public ServiceResult idCheck(String memId) {
		ServiceResult res = null;
		
		DDITMemberVO member = loginMapper.idCheck(memId);
		
		if(member != null) {
			res = ServiceResult.EXIST;
		} else {
			res = ServiceResult.NOTEXIST;
		}
		
		return res;
	}

	@Override
	public ServiceResult signup(HttpServletRequest req, DDITMemberVO memberVO) {
		ServiceResult result = null;
		String uploadPath = req.getServletContext().getRealPath("/resources/profile");
		
		// 프로필 이미지 파일을 업로드할 경로 설정
		req.getServletContext().getRealPath("/resources/profile");
		File file = new File(uploadPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String proFileImg = "";	// memberVO안에 프로필 이미지 경로를 담을 변수
		MultipartFile proFileImgFile = memberVO.getImgFile();
		if(proFileImgFile.getOriginalFilename() != null && !proFileImgFile.getOriginalFilename().equals("")) {
			String fileName = UUID.randomUUID().toString();
			fileName += "_" + proFileImgFile.getOriginalFilename();
			uploadPath += "/" + fileName;
			try {
				proFileImgFile.transferTo(new File(uploadPath));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	// 파일 복사
			proFileImg = "/resources/profile/" + fileName;
			
		}
		
		memberVO.setMemProfileImg(proFileImg);
		
		int status = loginMapper.signup(memberVO);
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public DDITMemberVO loginCheck(DDITMemberVO memberVO) {
		return loginMapper.loginCheck(memberVO);
	}

	@Override
	public String idForget(DDITMemberVO vo) {
		return loginMapper.idForget(vo);
	}

	@Override
	public String pwForget(DDITMemberVO vo) {
		return loginMapper.pwForget(vo);
	}
}
