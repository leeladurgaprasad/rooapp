package com.prasad.roostack.check;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lgunti on 013, Dec 13.
 */
public abstract class NavigationCheck {

    protected String exitUrl = null;
    protected String successUrl = null;

    public abstract boolean check(HttpServletRequest request);

    public String getExitUrl() {
        return exitUrl;
    }

    public void setExitUrl(String exitUrl) {
        this.exitUrl = exitUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }
}
