package com.qixl.bookshare.Controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qixl.bookshare.Constants;
import com.qixl.bookshare.exception.ParameterException;
import com.qixl.bookshare.exception.ServiceException;
import com.qixl.bookshare.model.User;
import com.qixl.bookshare.service.UserService;
import com.qixl.bookshare.util.StringUtils;
import com.qixl.common.ModelAndView;

public class UserController {
    
    private static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
    private static final String WELCOME_PAGE = "/WEB-INF/jsp/welcome.jsp";
    
    public ModelAndView login(HttpServletRequest request) {

        System.out.println("login...");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.USER);
        ModelAndView modelAndView = new ModelAndView(); 
        System.out.println("get......");
        if(user != null){
            System.out.println("user:"+user);
            modelAndView.setRediret(true);                     //（1）
            modelAndView.setView(request.getContextPath()+"/mybook.action");//（2）
//            response.sendRedirect(request.getContextPath()+"/mybook.action"); //用（1）（2）替换掉本条语句，替代response
        }else{
            System.out.println("nouser");
            String go = request.getParameter("go");
            
            System.out.println("go:..."+go);
            if(StringUtils.isEmpty(go)){
                go = "";
            }
            request.setAttribute("go",go);
            modelAndView.setView("/WEB-INF/jsp/login.jsp");
//            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
        return modelAndView;
    }

    /*
    public void saveLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("saveLogin...");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        System.out.println("userName:"+userName+"password:"+password);
        System.out.println("post......");
        try{
            User user = null;
            UserService uesrService = new UserService();
            user = uesrService.login(userName, password);
           
            System.out.println("user---login");
            //System.out.println(user.toString());
            // user.setPassword("");
            HttpSession session =  request.getSession();
//            System.out.println("session");
            session.setAttribute(Constants.USER, user);
            
            String go = request.getParameter("go");
            String queryString = request.getParameter("queryString");
            if(!StringUtils.isEmpty(queryString)){
                if(queryString.startsWith("#")){
                    queryString = queryString.substring(1);
                }
                go = go + "?" + queryString; 
            }
            System.out.println("loginServlet:go:...."+go);
            if(go== ""){
                System.out.println("go:空;;;;;");
            }
            if(go== null){
                System.out.println("go:null;;;;;");
            }
            if(go == "null"){
                System.out.println("go:"+"null"+";;;;;");
            }
            if(StringUtils.isEmpty(go)){
                System.out.println("loginServlet:1:....");
                response.sendRedirect(request.getContextPath()+"/mybook.action");
            }else{
                System.out.println("loginServlet:2:....");
                response.sendRedirect(request.getContextPath()+"/"+go);
            } 
//           System.out.println(request.getContextPath().toString());
           
            //request.getRequestDispatcher(WELCOME_PAGE).forward(request, response);
        } catch (ParameterException parameterException) {
            Map<String,String> errorField = parameterException.getErrorField();
            request.setAttribute(Constants.ERROR_MESSAGE, errorField);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        } catch (ServiceException serviceException) {
            int code = serviceException.getCode();
            request.setAttribute(Constants.TIP_MESSAGE, serviceException.getMessage()+"["+code+"]");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }*/
    
    
    public ModelAndView saveLogin(HttpServletRequest request) throws IOException, ServletException {
        System.out.println("saveLogin...");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        System.out.println("userName:"+userName+"password:"+password);
        System.out.println("post......");
        ModelAndView modelAndView = new ModelAndView();
        try{
            User user = null;
            UserService uesrService = new UserService();
            user = uesrService.login(userName, password);
           
            System.out.println("user---login");
            //System.out.println(user.toString());
            // user.setPassword("");
            HttpSession session =  request.getSession();
//            System.out.println("session");
            session.setAttribute(Constants.USER, user);
            
            String go = request.getParameter("go");
            String queryString = request.getParameter("queryString");
            if(!StringUtils.isEmpty(queryString)){
                if(queryString.startsWith("#")){
                    queryString = queryString.substring(1);
                }
                go = go + "?" + queryString; 
            }
            System.out.println("loginServlet:go:...."+go);
            if(go== ""){
                System.out.println("go:空;;;;;");
            }
            if(go== null){
                System.out.println("go:null;;;;;");
            }
            if(go == "null"){
                System.out.println("go:"+"null"+";;;;;");
            }
            if(StringUtils.isEmpty(go)){
                System.out.println("loginServlet:1:....");
                modelAndView.setRediret(true);
                modelAndView.setView(request.getContextPath()+"/mybook.action");
                //response.sendRedirect(request.getContextPath()+"/mybook.action");
            }else{
                System.out.println("loginServlet:2:....");
                modelAndView.setRediret(true);
                modelAndView.setView(request.getContextPath()+"/"+go);
                //response.sendRedirect(request.getContextPath()+"/"+go);
            } 
//           System.out.println(request.getContextPath().toString());
           
            //request.getRequestDispatcher(WELCOME_PAGE).forward(request, response);
        } catch (ParameterException parameterException) {
            Map<String,String> errorField = parameterException.getErrorField();
            request.setAttribute(Constants.ERROR_MESSAGE, errorField);
            modelAndView.setView(LOGIN_PAGE);
            //request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        } catch (ServiceException serviceException) {
            int code = serviceException.getCode();
            request.setAttribute(Constants.TIP_MESSAGE, serviceException.getMessage()+"["+code+"]");
            modelAndView.setView(LOGIN_PAGE);
            //request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
        
        return modelAndView;
    }
    
    
    
}
