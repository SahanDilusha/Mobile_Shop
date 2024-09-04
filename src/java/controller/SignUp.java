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
            response_DTO.setContent("Please enter your first name!");
        } else if (user.getLast_name().isEmpty()) {
            response_DTO.setContent("Please enter your last name!");
        } else if (user.getEmail().isBlank()) {
            response_DTO.setContent("Please enter your email!");
        } else if (!Validations.isEmailValid(user.getEmail())) {
            response_DTO.setContent("Please enter valid email!");
        } else if (user.getPassword().isBlank()) {
            response_DTO.setContent("Please enter your password!");
        } else if (!Validations.isPasswordValid(user.getPassword())) {
            response_DTO.setContent("Please enter valid password!");
        } else {
            Session session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("email", user.getEmail()));

            if (!criteria.list().isEmpty()) {
                response_DTO.setContent("User with this Email already exists");
            } else {
                User userEntity = new User();
                int code = (int) (Math.random() * 1000000);

                userEntity.setFirst_name(user.getFirst_name());
                userEntity.setLast_name(user.getLast_name());
                userEntity.setPassword(user.getPassword());
                userEntity.setVerification(String.valueOf(code));

                Thread t = new Thread() {
                    @Override
                    public void run() {
                        Mail.sendMail("sdilusha34@gmail.com", "Smart Trade Verification", "<h1 style=\"color:red\">" + userEntity.getVerification() + "</h1>");
                    }

                };
                t.start();

                request.getSession().setAttribute("email", user.getEmail());
                session.save(userEntity);
                session.beginTransaction().commit();
                response_DTO.setSuccess(true);
                response_DTO.setContent("Registration Complete");

            }

            session.close();

        }

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(response_DTO));

    }

}
