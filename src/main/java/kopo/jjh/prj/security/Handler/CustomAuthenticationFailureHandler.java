package kopo.jjh.prj.security.Handler;
/*
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger logger = LogManager.getLogger(CustomAuthenticationFailureHandler.class);
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String msg = "Invaild Username or Password";

        if(exception instanceof BadCredentialsException){

        }else if(exception instanceof InsufficientAuthenticationException){
            msg = "Invalid Secret Key";
        }

        setDefaultFailureUrl("/login?error=true&exception="+msg);

        super.onAuthenticationFailure(request,response,exception);
        log.info("비밀번호나 아이디오류");
    }
}
as
 */