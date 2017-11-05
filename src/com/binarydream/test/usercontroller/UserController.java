package com.binarydream.test.usercontroller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.*;
import org.apache.commons.io.IOUtils;

import com.binarydream.test.users.User;
import com.binarydream.test.dao.UserDao;;


@WebServlet("/UserController")
@MultipartConfig
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String INSERT = "/creatUser.jsp";
	public static String VIEW = "/userList.jsp";
	private UserDao dao;
 
    public UserController() {
        super();
        dao = new UserDao();
        
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String dispatchedPage = "";
		User user = new User();
		if(action.equalsIgnoreCase("insert")){ //don't use == to compare. == checks if the two objects are same.
			dispatchedPage = INSERT;
		
		}
		else if(action.equalsIgnoreCase("edit")){
			dispatchedPage = INSERT;
			int userId = Integer.parseInt(request.getParameter("userId"));
			user = dao.getUserById(userId);
			request.setAttribute("users", user);
			
			
		}
		else if(action.equalsIgnoreCase("delete")){
			dispatchedPage = VIEW;
			int userId = Integer.parseInt(request.getParameter("userId"));
			dao.deleteUser(userId);
			request.setAttribute("users", dao.getAllUser());
		}
		else{
			dispatchedPage = VIEW;
			request.setAttribute("users", dao.getAllUser());
			
		}
		
		RequestDispatcher view = request.getRequestDispatcher(dispatchedPage);
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		
		Part userImage = request.getPart("userIamge");
		
		
		InputStream imageStream = userImage.getInputStream();
	    byte[] bytes = IOUtils.toByteArray(imageStream);
	    String bsae64image = Base64.getEncoder().encodeToString(bytes);
		user.setUserImage(bsae64image);
		//System.out.println(imageStream.available());
		try{
			 Date dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dob"));
			 user.setDateOfBirth(dateOfBirth);
			
		}catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user.setUserEmail(request.getParameter("email"));
		String userId= request.getParameter("userid");
		if( userId.isEmpty()){
			
			dao.addUser(user);
			
		}else{
			user.setUserId(Integer.parseInt(userId));
			dao.updateUser(user);
		}
		 RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		 request.setAttribute("users", dao.getAllUser());
		 dispatcher.forward(request, response);
		
	}

}
