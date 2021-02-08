package scheduler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitializeServlet extends HttpServlet {

 public void init() throws ServletException {

    try {
        System.out.println("quartz got initialized");

        Class.forName("scheduler.CronScheluder");
       
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }

}