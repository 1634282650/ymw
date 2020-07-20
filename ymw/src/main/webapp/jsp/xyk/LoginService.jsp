<%@ page import="dx.service.UserService" %>
<%@ page import="entity.EasybuyUser" %>
<%@ page contentType="application/json;charset=UTF-8" language="Java" %>
<%
    String userName = request.getParameter("userName");
    String password = request.getParameter("password");
    if(userName == null){
        out.print("{userName:false}");
    }else{
        boolean bl = UserService.checkUserName(userName);
        if(bl){
            if(password == null){
                out.print("{userName:true,password:false}");
            }
            else{
                EasybuyUser user = UserService.login(userName, password);
                if(user != null){
                    session.setAttribute("User",user);
                    out.print("{userName:true,password:true}");
                }else{
                    out.print("{userName:true,password:false}");
                }
            }
        }
        else{
            out.print("{userName:false}");
        }
    }


%>
