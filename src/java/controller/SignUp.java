package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import org.hibernate.Session;

@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("done");

        Response_DTO response_DTO = new Response_DTO();

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        User_DTO user = gson.fromJson(request.getReader(), User_DTO.class);

        System.out.println(user.getEmail());
        System.out.println(user.getFirst_name());
        System.out.println(user.getLast_name());
        System.out.println(user.getPassword());

        if (user.getFirst_name().isBlank()) {
            response_DTO.setContent("Please enter your first name");
        } else if (user.getLast_name().isEmpty()) {
            response_DTO.setContent("Please enter your last name");
        } else if (user.getEmail().isBlank()) {
            response_DTO.setContent("Please enter your email");
        }else if (user.getEmail()) {
            
        }  else if (user.getPassword().isBlank()) {
            response_DTO.setContent("Please enter your password");
        }else if (user.getPassword()) {
            
        }  else {
            Session session = HibernateUtil.getSessionFactory().openSession();
            User userEntity = new User();
        }

    }

}
