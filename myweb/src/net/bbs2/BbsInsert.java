package net.bbs2;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.action.CommandAction;
import net.utility.Utility;

public class BbsInsert implements CommandAction{
  @Override
  public String requestPro(HttpServletRequest req,
                           HttpServletResponse resp) throws Throwable {

    //req.setCharacterEncoding("utf-8"); web.xml 한글 필터 등록되어 있음
    BoardDataBean article=new BoardDataBean(); // DTO
    article.setNum(Integer.parseInt(req.getParameter("num")));
    article.setWriter(req.getParameter("writer"));
    article.setEmail(req.getParameter("email"));
    article.setSubject(req.getParameter("subject"));
    article.setPasswd(req.getParameter("passwd"));
    article.setReg_date(new Timestamp(System.currentTimeMillis()));
    article.setRef(Integer.parseInt(req.getParameter("ref")));
    article.setRe_step(Integer.parseInt(req.getParameter("re_step")));
    article.setRe_level(Integer.parseInt(req.getParameter("re_level")));
    article.setContent(req.getParameter("content"));
    article.setIp(req.getRemoteAddr());
  
    BoardDBBean dao=new BoardDBBean();        //DAO
    dao.insertArticle(article); 
    
    //View페이지 재구성
    
    //1)
    //return "insertProc.jsp";     
    
    //2)
    String root=Utility.getRoot(); // /myweb
    String msg="<meta http-equiv='refresh' content='0;url=" + root + "/bbs2/bbslist.do'>";
    req.setAttribute("msg", msg);
    return "bbsResult.jsp";    
    
    
    
    
    
    
    
    
  }//requestPro() end
  
}//class end