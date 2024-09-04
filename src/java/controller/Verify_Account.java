package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dto.Response_DTO;
import dto.User_DTO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import model.Validations;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@WebServlet(name = "Verify_Account", urlPatterns = {"/Verify_Account"})
public class Verify_Account extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("done");

        Response_DTO response_DTO = new Response_DTO();

        Gson gson = new Gson();

        JsonObject dto = gson.fromJson(request.getReader(), JsonObject.class);
        
        String verification = dto.get("verification").getAsString();
        
        if (request.getSession().getAttribute("email")!=null) {
            response_DTO.setContent("");
        }else{
            
            
            
        }

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(response_DTO));

    }

}
