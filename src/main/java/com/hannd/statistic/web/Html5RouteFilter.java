package com.hannd.statistic.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Filter that distinguishes between client routes and server routes  when you don't use '#' in client routes.
 */
public class Html5RouteFilter extends OncePerRequestFilter {

    private Logger log = LoggerFactory.getLogger(getClass());


    // These are the URIs that should be processed server-side
    private static final Pattern PATTERN = Pattern.compile("^/((api|content|i18n|management|swagger-ui|swagger-resources)/|error|h2-console|swagger-resources|favicon\\.ico|v2/api-docs).*");

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
        throws ServletException, IOException {
        if (isServerRoute(request)) {
            filterChain.doFilter(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/");
            rd.forward(request, response);
        }
    }

    protected static boolean isServerRoute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            String uri = request.getRequestURI();
            if (uri.startsWith("/app")) {
                return true;
            }
            return PATTERN.matcher(uri).matches();
        }
        return true;
    }
}
