package capstone.sangcom.config.interceptor;

import capstone.sangcom.config.auth.AuthConstants;
import capstone.sangcom.config.auth.JwtManager;
import capstone.sangcom.controller.api.response.common.FailHeader;
import capstone.sangcom.entity.JwtUser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginApiInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader(AuthConstants.AUTH_HEADER);
        if (header != null) {
            String token = JwtManager.getTokenFromHeader(header);
            if (JwtManager.isValidToken(token)) {
                JwtUser user = JwtManager.getUserFromToken(token);
                request.setAttribute("user", user);

                return true;
            }
        }

        FailHeader.setResponse(response);

        return false;
    }
}
