package com.koreait.pjt.user;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/Profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 프로필 화면 ( 나의 프로필 이미지, 이미지 변경 가능한 화면 )
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (MyUtils.getLoginUser(request) == null || MyUtils.getSessionItem("member_confirm", request) == null
				|| !(boolean) MyUtils.getSessionItem("member_confirm", request)) {
			response.sendRedirect(Const.DEFUALT_PAGE);
			return;
		}
		request.setAttribute("user", UserDAO.selectUser(MyUtils.getLoginUser(request).getI_user()));
		ViewResolver.forwardLoginCheck("user/profile", request, response);
	}

	// 이미지 변경 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserVO param = new UserVO();
		String savePath = getServletContext().getRealPath("img") + "/user/" + MyUtils.getLoginUser(request).getI_user();

		File directory = new File(savePath);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		// System.out.println("savePath: " + savePath);

		int maxFilesize = 10_485_760;
		String fileNm = "";
		// String originFileNm = "";
		String saveFileNm = null;
		String user_pw = "";
		String user_nm = "";
		String user_mail = "";
		String user_id = "";
		
		try {
			MultipartRequest mr = new MultipartRequest(request, savePath, maxFilesize, "UTF-8",
					new DefaultFileRenamePolicy());
			Enumeration files = mr.getFileNames();
			while (files.hasMoreElements()) {
				String key = (String) files.nextElement();
				fileNm = mr.getFilesystemName(key);
				// originFileNm = mr.getOriginalFileName(key);

				System.out.println(key);
				System.out.println(fileNm);
				//System.out.println(originFileNm);
				if( fileNm != null ) {
					String ext = fileNm.substring(fileNm.lastIndexOf("."));
					// System.out.println(ext);
					saveFileNm = UUID.randomUUID() + ext;
					File oldFile = new File(savePath + "/" + fileNm);
					File newFile = new File(savePath + "/" + saveFileNm);
					oldFile.renameTo(newFile);
				}
				user_pw = mr.getParameter("user_pw");
				user_nm = mr.getParameter("user_nm");
				user_mail = mr.getParameter("email");
				user_id = mr.getParameter("user_id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		param = UserDAO.selectUser(MyUtils.getLoginUser(request).getI_user());
		if (user_pw != null && !user_pw.isEmpty()) {
			param.setUser_pw(MyUtils.encryptSHA256(user_pw));
		}
		if (user_nm != null && !user_nm.isEmpty()) {
			param.setUser_nm(user_nm);
		}
		if (user_mail != null && !user_mail.isEmpty()) {
			param.setUser_email(user_mail);
		}
		if (saveFileNm != null) {
			param = MyUtils.getLoginUser(request);
			param.setProfile_img(saveFileNm);
		}
		UserDAO.updateUserProfile(param);
		MyUtils.setSesstionItem("member_confirm", request, false);
		doGet(request, response);

		// response.sendRedirect("/Profile");
	}

}
