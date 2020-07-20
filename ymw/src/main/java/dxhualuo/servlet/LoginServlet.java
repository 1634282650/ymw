package dxhualuo.servlet;

import dxhualuo.service.UserService;
import entity.EasybuyUser;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.net.http.HttpRequest;

/**
 * @author dx_hualuo
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        Writer out = response.getWriter();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        if(userName == null){
            out.write("{userName:false}");
        }else{
            boolean bl = UserService.checkUserName(userName);
            if(bl){
                if(password == null){
                    out.write("{userName:true,password:false}");
                }
                else{
                    EasybuyUser user = UserService.login(userName, password);
                    if(user != null){
                        session.setAttribute("User",user);
                        out.write("{userName:true,password:true}");
                    }else{
                        out.write("{userName:true,password:false}");
                    }
                }
            }
            else{
                out.write("{userName:false}");
            }
        }
    }
}
