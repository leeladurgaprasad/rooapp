package hob.psd.todo.interceptor;

import hob.psd.todo.check.NavigationCheck;
import hob.psd.todo.constants.CommonConstants;
import hob.psd.todo.exception.ExitUrlRequiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lgunti on 013, Dec 13.
 */
public class ConfigurableInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(ConfigurableInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("processing pre interceptor checks ");
        boolean isRedirectExit = processNavigationCheck(request, response, preNavigationChecks);
        logger.debug("processing pre interceptor checks completed");
        return isRedirectExit;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        processNavigationCheck(request,response,postNavigationChecks);
        return;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    private boolean processNavigationCheck(HttpServletRequest request, HttpServletResponse response, List<NavigationCheck> navigationChecks) throws Exception{
        if(null != navigationChecks) {
            for (NavigationCheck navigationCheck : navigationChecks) {
                boolean isCheckPass = navigationCheck.check(request);
                if (isCheckPass) {
                    if (null != navigationCheck.getSuccessUrl()) {
                        response.sendRedirect(request.getContextPath()+navigationCheck.getSuccessUrl());
                        return false;
                    }
                } else {
                    if (null != navigationCheck.getExitUrl()) {
                        String redirectUrl  = request.getContextPath()+navigationCheck.getExitUrl();
                        String queryParam = (String)request.getAttribute(CommonConstants.QUERY_PARAM);
                        if(null != queryParam) {
                            redirectUrl = redirectUrl + queryParam;
                        }
                        response.sendRedirect(redirectUrl);
                        return false;
                    } else {
                        // throw new ExitUrlRequiredException("exit url is required for check" + navigationCheck);
                    }
                }
            }
        }
        return true;
    }

    public List<NavigationCheck> getPreNavigationChecks() {
        return preNavigationChecks;
    }

    public void setPreNavigationChecks(List<NavigationCheck> preNavigationChecks) {
        this.preNavigationChecks = preNavigationChecks;
    }

    public List<NavigationCheck> getPostNavigationChecks() {
        return postNavigationChecks;
    }

    public void setPostNavigationChecks(List<NavigationCheck> postNavigationChecks) {
        this.postNavigationChecks = postNavigationChecks;
    }

    private List<NavigationCheck> preNavigationChecks;
    private List<NavigationCheck> postNavigationChecks;
}
