package kr.or.ddit.controller.ch13.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.ch13.web.TelegramSendController;
import kr.or.ddit.mapper.LoginMapper;
import kr.or.ddit.mapper.NoticeMapper;
import kr.or.ddit.mapper.ProfileMapper;
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
	
	@Inject
	private ProfileMapper profileMapper;
	
	@Inject
	private PasswordEncoder pe;
	
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
				
				//디버깅용
				System.out.println(endFileName);
				
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
	public ServiceResult updateNotice(HttpServletRequest req, NoticeVO notice) {
		ServiceResult result = null;
		int status = noticeMapper.updateNotice(notice);
		
		if(status > 0) {
			List<NoticeFileVO> list = notice.getNoticeFileList();
			
			try {
				//수정을 위해 새로 추가된 파일 데이터를 먼저 업로드 처리
				noticeFileUpload(list, notice.getBoNo(), req);
				
				//기존 파일들 중 삭제를 원하는 파일 번호를 가져옴
				Integer[] delNoticeNo = notice.getDelNoticeNo();
				
				if(delNoticeNo != null) {
					//삭제를 원하는 파일들을 데이터베이스에서 삭제
					//서버 업로드경로에 업로드 되어있는 파일 데이터를 삭제
					for (int i = 0; i < delNoticeNo.length; i++) {
						NoticeFileVO vo = noticeMapper.selectNoticeFile(delNoticeNo[i]);
						noticeMapper.deleteNoticeFile(delNoticeNo[i]);
						File file = new File(vo.getFileSavepath());
						file.delete();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ServiceResult deleteNotice(HttpServletRequest req, int boNo) {
		ServiceResult result = null;
		
		NoticeVO vo = noticeMapper.selectNotice(boNo);
		//notice를 삭제하기 전 file을 먼저 삭제
		noticeMapper.deleteNoticeFileByNo(boNo);
		
		int status = noticeMapper.deleteNotice(boNo);
		
		if(status > 0) {
			//파일 삭제
			List<NoticeFileVO> list = vo.getNoticeFileList();
			if(list != null && list.size() > 0) {
				String[] filePath = list.get(0).getFileSavepath().split("/");
				
				// \resources\notice\{boNo}
				String path = filePath[0];
				deleteFolder(req, path);
			}
			
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}
	
	public static void deleteFolder(HttpServletRequest req, String path) {
		File folder = new File(path);
		
		if(folder.exists()) {
			File[] files = folder.listFiles();
			
			for (int i = 0; i < files.length; i++) {
				if(files[i].isFile()) {	//폴더안에 있는게 파일 일때
					files[i].delete();
				} else {
					//폴더에 있는게 폴더일때 재귀함수 호출(그 폴더안으로 들어감)
					deleteFolder(req, files[i].getPath());
				}
			}
			
			//안에 파일을 전부 날리고 folder를 지운다
			folder.delete();
		}
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
		//비밀번호 암호화
		memberVO.setMemPw(pe.encode(memberVO.getMemPw()));
		
		int status = loginMapper.signup(memberVO);
		if(status > 0) {
			loginMapper.signupAuth(memberVO.getMemNo());
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

	@Override
	public NoticeFileVO noticeDownload(int fileNo) {
		NoticeFileVO vo = noticeMapper.noticeDownload(fileNo);
		
		if(vo == null) {
			throw new RuntimeException();
		} 
		
		noticeMapper.incrementNoticeDowncount(fileNo);
		
		return vo;
	}

	@Override
	public DDITMemberVO selectMember(DDITMemberVO vo) {
		return profileMapper.selectMember(vo);
	}

	@Override
	public ServiceResult profileUpdate(HttpServletRequest req, DDITMemberVO vo) {
		ServiceResult res = null;
		
		//프로필 이미지
		String uploadPath = req.getServletContext().getRealPath("/resources/profile");
		File file = new File(uploadPath);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String profileImg = "";
		MultipartFile imgFile = vo.getImgFile();
		//프로필 이미지를 변경하면 if문을 타게 된다
		if(imgFile.getOriginalFilename() != null && !imgFile.getOriginalFilename().equals("")) {
			String fileName = UUID.randomUUID().toString();
			fileName += "_" + imgFile.getOriginalFilename();
			uploadPath += "/" + fileName;
			try {
				imgFile.transferTo(new File(uploadPath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			profileImg = "/resources/profile/" + fileName;
		}
		
		vo.setMemProfileImg(profileImg);
		
		int status = profileMapper.profileUpdate(vo);
		
		if(status > 0) {
			res = ServiceResult.OK;
		} else {
			res = ServiceResult.FAILED;
		}
		
		return res;
	}
}
