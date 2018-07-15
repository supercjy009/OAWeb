package demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.dto.ResultDto;
import demo.util.HttpUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gongxufan
 * @date 17-11-29
 **/
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 在这里进行验证码的校验
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
//
//        // 取出验证码
//        String validateCode = (String) session.getAttribute("validateCode");
//        // 取出页面的验证码
//        // 输入的验证和session中的验证进行对比
//        String randomcode = httpServletRequest.getParameter("randomcode");
//        if (randomcode != null && validateCode != null && !randomcode.equals(validateCode)) {
//            // 如果校验失败，将验证码错误失败信息，通过shiroLoginFailure设置到request中
//            httpServletRequest.setAttribute("shiroLoginFailure", "kaptchaValidateFailed");//自定义登录异常
//            // 拒绝访问，不再校验账号和密码
//            return true;
//        }

        //判断请求是否是登录请求
        if (this.isLoginRequest(request, response)) {
            //判断请求是否是post方法
            if (this.isLoginSubmission(request, response)) {
                //执行登录验证
                return this.executeLogin(request, response);

            } else {//如果是get方法则会返回true,跳转到登陆页面
                return true;
            }
        } else {
            if (HttpUtils.isAjax(request)) {
                response.setContentType("application/json;charset=utf-8");
                HttpUtils.sendJsonResponse(response, ResultDto.timeOut("您尚未登录或登录时间过长,请重新登录."));
            } else {
                this.saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }
    }


}