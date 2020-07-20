<%@ page import="dxhualuo.io.Path" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/7/19
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link type="text/css" rel="stylesheet" href="/css/style.css" />
    <!--[if IE 6]>
    <script src="/js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('div, ul, img, li, input, a');
    </script>
    <![endif]-->
    <script type="text/javascript" src="/js/jquery-1.11.1.min_044d0927.js"></script>
    <script type="text/javascript" src="/js/jquery.bxslider_e88acd1b.js"></script>
    <script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>
    <script type="text/javascript" src="/js/select.js"></script>
    <script type="text/javascript" src="/js/lrscroll.js"></script>
    <script type="text/javascript" src="/js/iban.js"></script>
    <script type="text/javascript" src="/js/fban.js"></script>
    <script type="text/javascript" src="/js/f_ban.js"></script>
    <script type="text/javascript" src="/js/mban.js"></script>
    <script type="text/javascript" src="/js/bban.js"></script>
    <script type="text/javascript" src="/js/hban.js"></script>
    <script type="text/javascript" src="/js/tban.js"></script>
    <script type="text/javascript" src="/js/lrscroll_1.js"></script>
    <title>易买网登录</title>
    <script>
        //当前页面常用元素
        function topLoginLinkClick() {
            alert("您当前就在登录页面。请完善信息！");
        }
        //提交方法
        function submitFunction() {
            let userName = $("#userName").val();
            let password = $("#password").val();
            if(userName != null && userName.replace(/^\s+|\s+$/g,"").length !== 0){
                userName = userName.replace(/^\s+|\s+$/g,"");
                if(password != null && password.replace(/^\s+|\s+$/g,"").length !== 0){
                    password = password.replace(/^\s+|\s+$/g,"");
                    $.post("/loginServlet",{"userName":userName,"password":password},function (data) {
                        alert(data);
                    });
                    <%--$.ajax({--%>
                    <%--    url:"localhost:8081/loginServlet",--%>
                    <%--    dataType:"application/json",--%>
                    <%--    type:"POST",--%>
                    <%--    data:{"userName":userName,"password":password},--%>
                    <%--    success:function(userInfo){--%>
                    <%--        if(userInfo.userName === true && userInfo.password === true){--%>
                    <%--            window.location.href = "<%=Path.getSrcPath()%>/jsp/xyj/Index.jsp";--%>
                    <%--        }else if(userInfo.userName === false){--%>
                    <%--            alert("用户名不存在或者有误，请重新输入！");--%>
                    <%--        }else if(userInfo.password === false){--%>
                    <%--            alert("密码错误，请重试！")--%>
                    <%--        }--%>
                    <%--    }--%>
                    <%--});--%>
                }
                else{
                    alert("密码不能为空和空白字符");
                }
            }else{
                alert("用户名不能为空和空白字符");
            }
        }
        //回车提交
        function enterCommit(key){
            let keyNum;
            if(window.event) // 辣鸡IE
            {
                keyNum = key.keyCode;
            }
            else if(key.which)
            {
                keyNum = key.which;
            }
            //按下回车键
            if(keyNum === 13){
                submitFunction();
            }
        }
    </script>
</head>
<body>
<!--Begin Header Begin-->
<div class="soubg">
    <div class="sou">
        <span class="fr">
        	<span class="fl">你好，请<a href="" onclick="topLoginLinkClick()">登录</a>&nbsp; <a href="/jsp/xyk/Register.jsp" style="color:#ff4e00;">免费注册</a>&nbsp; </span>
            <span class="fl">|&nbsp;关注我们：</span>
            <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
            <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="/images/s_tel.png" align="absmiddle"  alt=""/></a></span>
        </span>
    </div>
</div>
<!--End Header End-->
<!--Begin Login Begin-->
<div class="log_bg">
    <div class="top">
        <div class="logo"><a href="/jsp/xyj/Index.jsp"><img src="/images/logo.png"  alt=""/></a></div>
    </div>
    <div class="login">
        <div class="log_img"><img src="/images/l_img.png" width="611" height="425" /></div>
        <div class="log_c">
            <form action="/jsp/xyk/LoginService.jsp" method="post">
                <table border="0" style="width:370px; font-size:14px; margin-top:30px;" cellspacing="0" cellpadding="0">
                    <tr height="50" valign="top">
                        <td width="55">&nbsp;</td>
                        <td>
                            <span class="fl" style="font-size:24px;">登录</span>
                            <span class="fr">还没有商城账号，<a href="/jsp/xyk/Register.jsp" style="color:#ff4e00;">立即注册</a></span>
                        </td>
                    </tr>
                    <tr height="70">
                        <td>用户名</td>
                        <td><input type="text" value="" class="l_user" id="userName" onkeydown="return enterCommit(event)" name="userName" /></td>
                    </tr>
                    <tr height="70">
                        <td>密&nbsp; &nbsp; 码</td>
                        <td><input type="password" value="" class="l_pwd" onkeydown="return enterCommit(event)" id="password" name="password" /></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td style="font-size:12px; padding-top:20px;">
                	<span style="font-family:'宋体',serif;" class="fl">
                    	<label class="r_rad"><input type="checkbox" /></label><label class="r_txt">请保存我这次的登录信息</label>
                    </span>
                            <span class="fr"><a href="#" style="color:#ff4e00;">忘记密码</a></span>
                        </td>
                    </tr>
                    <tr height="60">
                        <td>&nbsp;</td>
                        <td><input type="button" value="登录" onclick="submitFunction()" class="log_btn"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<!--End Login End-->
<!--Begin Footer Begin-->
<div class="btmbg">
    <div class="btm">
        备案/许可证编号：蜀ICP备12009302号-1-www.dingguagua.com   Copyright © 2015-2018 尤洪商城网 All Rights Reserved. 复制必究 , Technical Support: Dgg Group <br />
        <img src="/images/b_1.gif" width="98" height="33"  alt=""/><img src="/images/b_2.gif" width="98" height="33"  alt=""/><img src="/images/b_3.gif" width="98" height="33"  alt=""/><img src="/images/b_4.gif" width="98" height="33"  alt=""/><img src="/images/b_5.gif" width="98" height="33"  alt=""/><img src="/images/b_6.gif" width="98" height="33"  alt=""/>
    </div>
</div>
<!--End Footer End -->

</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>

