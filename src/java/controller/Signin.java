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
import model.Mail;
import model.Validations;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@WebServlet(name = "Signin", urlPatterns = {"/Signin"})
public class Signin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("done");

        Response_DTO response_DTO = new Response_DTO();

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        User_DTO user = gson.fromJson(request.getReader(), User_DTO.class);

        if (user.getEmail().isBlank()) {
            response_DTO.setContent("Please enter your email!");
        } else if (!Validations.isEmailValid(user.getEmail())) {
            response_DTO.setContent("Please enter valid email!");
        } else if (user.getPassword().isBlank()) {
            response_DTO.setContent("Please enter your password!");
        } else if (!Validations.isPasswordValid(user.getPassword())) {
            response_DTO.setContent("Please enter valid password!");
        } else {
            Session session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("email", user.getEmail()));
            criteria.add(Restrictions.eq("password", user.getPassword()));

            if (!criteria.list().isEmpty()) {
                User userEntity = (User) criteria.list().get(0);

                if (userEntity.getVerification().equals("Verified")) {
                    session.save(userEntity);
                    session.beginTransaction().commit();
                    response_DTO.setSuccess(true);
                    response_DTO.setContent("Registration Complete");
                } else {
                  
                }

            } else {
                response_DTO.setContent("Invalid details");
            }

            session.close();

        }

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(response_DTO));

    }

}
