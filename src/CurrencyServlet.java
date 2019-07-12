import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/currency")
public class CurrencyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("value");
        String currency = request.getParameter("currency");
        String date = request.getParameter("date");
        if (value.equals("")) {
            value = "0";
        }

        if (date.equals("")) {
            date = "latest";
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String curencyExchangeRate = Logic.getRequest(date, currency);
        String plnExchangeRage = Logic.getRequest(date, "PLN");
        double effectiveRate = Logic.getEffectiveRate(plnExchangeRage, curencyExchangeRate);
        double finalValue = Double.parseDouble(value) * effectiveRate;

        writer.println("Kalkulacja kursu");

        writer.println("Ilość PLN: " + value);
        writer.println("<br/>");
        writer.println("Po przeliczeniu na: " + currency);
        writer.println("<br/>");
        writer.println("Przy kursie: " + effectiveRate + "<br/> z dnia : " + date);

        writer.println("<br/>");
        writer.println("Wynosi " + finalValue);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
