package Controller;

import DAO.ArticleDAO;
import Model.ArticleModel;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author haiva
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    protected void panelRightformation(HttpServletRequest request, HttpServletResponse response, ArticleDAO articleDAO) throws ServletException, IOException {
        try {
            ArticleModel mostRecentNews = articleDAO.getTopArtical(1).get(0);
            String[] word = mostRecentNews.getContent().split(" ");
            String shortContent = "";
            for (int i = 0; i < 30; i++) {
                shortContent += word[i] + " ";
            }
            request.setAttribute("shortContent", shortContent);
            request.setAttribute("recentArticle", articleDAO.getTopArtical(5));
        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArticleDAO articleDAO = new ArticleDAO();
        String id = request.getParameter("id");
        // default id value
        if (id == null) {
            id = "1";
        }
        // reset search result after searched
        if (request.getParameter("searchResult") != null) {
            request.setAttribute("searchResult", null);
        }
        try {
            // view article information
            SimpleDateFormat sdfm = new SimpleDateFormat("dd MMM yyyy - HH:mmaa");
            ArticleModel article = articleDAO.getArticle(Integer.parseInt(id));
            request.setAttribute("article", article);
            request.setAttribute("dateCreated", sdfm.format(article.getDate()));
            // 
            panelRightformation(request, response, articleDAO);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMsg", "ID parameter is not avaiable");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get search keyword
        try {
            String searchKeyword = request.getParameter("searchKeyword");
            if (searchKeyword != null) {
                ArrayList<ArticleModel> searchResult = new ArticleDAO().searchArtical(searchKeyword);
                panelRightformation(request, response, new ArticleDAO());
                request.setAttribute("searchResult", searchResult);
                request.setAttribute("searchKeyword", searchKeyword);
                request.setAttribute("numberOfPage", searchResult.size()/2);
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

}
