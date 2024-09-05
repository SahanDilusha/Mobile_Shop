package controller;

import com.google.gson.Gson;
import dto.Response_DTO;
import entity.Category;
import entity.Color;
import entity.Model;
import entity.Storage;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

@WebServlet(name = "loadFeatures", urlPatterns = {"/loadFeatures"})
public class loadFeatures extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response_DTO response_DTO = new Response_DTO();
        
        Gson gson = new Gson();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria1 = session.createCriteria(Category.class);
        criteria1.addOrder(Order.asc("name"));
        
        List<Category>  categoryList = criteria1.list();
        
        Criteria criteria2 = session.createCriteria(Color.class);
        criteria2.addOrder(Order.asc("name"));
        
        List<Color>  colorList = criteria2.list();
        
        Criteria criteria3 = session.createCriteria(Model.class);
        criteria3.addOrder(Order.asc("name"));
        
        List<Model>  modelList = criteria3.list();
    
        Criteria criteria4 = session.createCriteria(Storage.class);
        criteria3.addOrder(Order.asc("name"));
        
        List<Storage>  storageList = criteria3.list();
        
    }

}
