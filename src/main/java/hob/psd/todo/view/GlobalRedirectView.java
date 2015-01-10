package hob.psd.todo.view;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.view.RedirectView;

import static hob.psd.todo.constants.CommonConstants.*;

public class GlobalRedirectView extends RedirectView {

    @Override
    protected void sendRedirect(HttpServletRequest request,
                                HttpServletResponse response, String targetUrl,
                                boolean http10Compatible) throws IOException {
        String queryParam = null;

        if (StringUtils.isNotBlank((String) request.getAttribute(QUERY_PARAM))) {
            queryParam = (String) request.getAttribute(QUERY_PARAM);
        }

        if (StringUtils.isNotBlank((String) request.getAttribute("targetUrl"))) {
            targetUrl = (String) request.getAttribute("targetUrl");
        } else {
            targetUrl = request.getContextPath() + request.getServletPath() + "/" + targetUrl;
        }

        if (StringUtils.isNotBlank(queryParam)) {
            targetUrl = targetUrl + queryParam;
        }


        super.sendRedirect(request, response, targetUrl, http10Compatible);
    }
}
