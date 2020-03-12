package web.resume.filters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ResumeFilter extends AbstractFilter {

    @Value("${application.production}")
    private boolean isProduction;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        request.setAttribute("REQUEST_URL", requestUrl);
        try {
            chain.doFilter(request, response);
        } catch (Throwable throwable) {
            LOGGER.error("Process request failed: " + requestUrl, throwable);
            handleException(throwable, requestUrl, response);
        }
    }

    private void handleException(Throwable throwable, String requestUrl, HttpServletResponse response) throws ServletException, IOException {
        if (isProduction) {
            if ("/error".equals(requestUrl)) {
                throw new ServletException(throwable);
            } else {
                response.sendRedirect("/error?url=" + requestUrl);
            }
        } else {
            throw new ServletException(throwable);
        }
    }

}
