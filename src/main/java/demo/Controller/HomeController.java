package demo.Controller;

import demo.Process.Indexprocess;
import demo.VerificationCode.Captcha;
import demo.VerificationCode.GifCaptcha;
import demo.model.SysPermissionEntity;
import demo.model.UserinfoEntity;
import demo.service.Imp.SysPermissionSerivceImp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by PC on 2017/11/21.
 */
@Controller
public class HomeController {
    org.slf4j.Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Resource
    Indexprocess indexprocess;
    @Resource
    SysPermissionSerivceImp sysPermissionSerivceImp;

    /**
     * 用户添加;
     * 用于权限控制
     *
     * @return
     */
    @RequestMapping("/userInfo/userAdd")
    @RequiresPermissions("perms[userInfo/userAdd]")//权限管理;
    public String userInfoAdd() {
        return "userInfoAdd";
    }

    /**
     * 用户删除;
     *
     * @return
     */
    @RequestMapping("/userInfo/userDel")
    @RequiresPermissions("perms[userInfo/userDel]")//权限管理;
    public String userDel() {
        return "userInfoDel";
    }

    /**
     * 用户列表;
     *
     * @return
     */
    @RequestMapping("/userInfo/userList")
    @RequiresPermissions("userInfo:view")//权限管理;
    public String userList() {
        return "userInfoList";
    }

    @Resource
    StringRedisTemplate redisTemplate01;

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String index(Map<String, Object> map) {
        map.put("path", "userinfo.html");
//        ValueOperations<String, String> opsForValue = redisTemplate01.opsForValue();
//         opsForValue.increment("test", 1);
//        opsForValue.set("test", "0");

        return "index";
    }

    @RequestMapping(value = {"/userinfo"}, method = RequestMethod.GET)
    public String userinfo() {
//        ValueOperations<String, String> opsForValue = redisTemplate01.opsForValue();
//         opsForValue.increment("test", 1);
//        opsForValue.set("test", "0");
//        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        return "/userinfo";
    }

    @RequestMapping(value = {"/clearmy"}, method = RequestMethod.POST)
    public String index(HttpServletRequest request) {
//        ValueOperations<String, String> opsForValue = redisTemplate01.opsForValue();
//         opsForValue.increment("test", 1);
//        opsForValue.set("test", "0");
        // return"/index";
        // ShiroAuthorizationHelper.clearAuthorizationInfo();
        // ShiroAuthorizationHelper.reloadAuthorizing(myShiroRealm);
        return "success";
    }


    @RequestMapping(value = {"/testroot"}, method = RequestMethod.GET)
    public String testroot() {
//        ValueOperations<String, String> opsForValue = redisTemplate01.opsForValue();
//         opsForValue.increment("test", 1);
//        opsForValue.set("test", "0");
        return "forward:testroot2";
    }

    @RequestMapping(value = {"/testroot2"}, method = RequestMethod.GET)
    public String testroot2() {
//        ValueOperations<String, String> opsForValue = redisTemplate01.opsForValue();
//         opsForValue.increment("test", 1);
//        opsForValue.set("test", "0");
        return "/testroot1";
    }

    @RequestMapping(value = {"/clearpermissions"}, method = RequestMethod.POST)
    public String clearpermissions(HttpServletRequest request) {
//        ValueOperations<String, String> opsForValue = redisTemplate01.opsForValue();
//         opsForValue.increment("test", 1);
//        opsForValue.set("test", "0");
        // return"/index";
        // ShiroAuthorizationHelper.clearAuthorizationInfo();
        // ShiroAuthorizationHelper.reloadAuthorizing(myShiroRealm);
        String userName = request.getParameter("userName");
        indexprocess.clearUserPermission(userName);
        return "success";
    }


    /**
     * 退出
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> logout() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            //退出
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("msg", "hhhh");
        return modelAndView;
    }

    @RequestMapping(value = "/login1", method = RequestMethod.GET)
    public ModelAndView login1() {
        ModelAndView modelAndView = new ModelAndView("login1");
        modelAndView.addObject("msg", "hhhh");
        return modelAndView;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> insert(@RequestParam Map<String, String> map) {
        SysPermissionEntity sysPermissionEntity = new SysPermissionEntity();
        sysPermissionEntity.setAvailable(Boolean.valueOf(map.get("Available")));
        sysPermissionEntity.setName(map.get("Name"));
        sysPermissionEntity.setParentId(Long.valueOf(map.get("ParentId")));
        sysPermissionEntity.setParentIds(map.get("ParentIds"));
        sysPermissionEntity.setPermission(map.get("Permission"));
        sysPermissionEntity.setResourceType(map.get("ResourceType"));
        sysPermissionEntity.setUrl(map.get("Url"));
        logger.info("HomeController insert");
        sysPermissionSerivceImp.insertSelective(sysPermissionEntity);
        Map<String, String> mapOut = new HashMap<String, String>();
        mapOut.put("state", "success");
        return mapOut;
    }

    /**
     * 登录提交地址和applicationontext-shiro.xml配置的loginurl一致。 (配置文件方式的说法)
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/ajaxlogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, @RequestParam Map<String, String> map) {
        System.out.println("HomeController.login()");
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        logger.info("info");
        logger.error("err");
        HttpSession session1 = request.getSession(true);
        System.out.println(session1.getId());
        String username = (String) map.get("uname");
        String password = (String) map.get("password");
        String vcode = (String) map.get("vcode");
        boolean bReme = false;
        if (map.containsKey("rememberMe")) {
            //bReme = "on".equals(map.get("rememberMe"));
            String remeberMe = map.get("rememberMe");
            bReme = Boolean.valueOf(remeberMe);
        }
        UsernamePasswordToken token = new UsernamePasswordToken();
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        System.out.println(session1.getId());
        String v = (String) session.getAttribute("_code");
//        if(v == null || !v.equals(vcode)){
//            token.clear();
//            resultMap.put("status", 400);
//            return resultMap;
//        }
        String e = (String) request.getAttribute("shiroLoginFailure");
        // System.out.println("exception=" + exception);
        String msg = "";
        try {
            // if(subject.isAuthenticated())
//            if(!subject.isAuthenticated()){
            //subject.logout();
            token = new UsernamePasswordToken(username, password, bReme);
            subject.login(token);
//            }
        } catch (Exception exception) {
            // 如果为AuthenticationException异常有可能可能是因为密码不正确，特别是密码使用md5或者其它的加密算法加密之后，更是如此。
            if (exception != null) {
                if (UnknownAccountException.class.getName().equals(exception)) {
                    System.out.println("UnknownAccountException -- > 账号不存在：");
                    msg = "UnknownAccountException -- > 账号不存在：";
                } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                    System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                    msg = "IncorrectCredentialsException -- > 密码不正确：";
                } else if ("kaptchaValidateFailed".equals(exception)) {
                    System.out.println("kaptchaValidateFailed -- > 验证码错误");
                    msg = "kaptchaValidateFailed -- > 验证码错误";
                } else if ("UsernameNotFoundException".equals(exception)) {
                    System.out.println("UsernameNotFoundException -- > 用户找不到");
                    msg = "UsernameNotFoundException -- > 用户找不到";
                } else if ("BadCredentialsException".equals(exception)) {
                    System.out.println("UsernameNotFoundException -- > 坏的凭据");
                    msg = "UsernameNotFoundException -- > 坏的凭据";
                } else if ("ExcessiveAttemptsException".equals(exception) || "org.apache.shiro.authc.ExcessiveAttemptsException".equals(exception)) {
                    System.out.println("ExcessiveAttemptsException -- > 失败登录过多");
                    msg = "ExcessiveAttemptsException -- > 失败登录过多";
                } else {
                    msg = exception.getMessage();
                    System.out.println("else -- >" + exception);
                }
                resultMap.put("status", 400);
                resultMap.put("message", msg);
                // mapOut.put("msg", msg);
            }
            token.clear();
            return resultMap;
        }
        resultMap.put("status", 200);
        resultMap.put("message", "登录成功");
        msg = "sucess";
        // 此方法不处理登录成功,由shiro进行处理.
        return resultMap;
    }

    /**
     * 获取验证码（Gif版本）
     *
     * @param response
     */
    @RequestMapping(value = "getGifCode", method = RequestMethod.GET)
    @ResponseBody
    public void getGifCode(HttpServletResponse response, HttpServletRequest request, Map<String, Object> map) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            GifCaptcha captcha = new GifCaptcha(146, 33, 4);
            //输出
            char[] c = captcha.alphas();
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(true);
            System.out.println(session.getId());

            //HttpSession httpSession = request.getSession(true);
            String code = (String) session.getAttribute("_code");
            System.out.println(code);
            //存入Session
            session.setAttribute("_code", captcha.text().toLowerCase());
            code = (String) session.getAttribute("_code");
            map.put("_code", code);
            captcha.out(c, response.getOutputStream());
            System.out.println(code);
        } catch (Exception e) {
            // LoggerUtils.fmtError(getClass(),e, "获取验证码异常：%s",e.getMessage());
            System.out.println("获取验证码异常：" + e.getMessage());
        }
    }

    /**
     * 获取验证码（Gif版本）
     *
     * @param response
     */
    @RequestMapping(value = "getGifCode2", method = RequestMethod.GET)
    @ResponseBody
    public void getGifCode2(HttpServletResponse response, HttpServletRequest request, Map<String, Object> map) {
        try {
            System.out.println("getGifCode2");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            GifCaptcha captcha = new GifCaptcha(146, 33, 4);
            //输出

            captcha.out(response.getOutputStream());
            //char [] c = ;
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(true);
            System.out.println(session.getId());

            //HttpSession httpSession = request.getSession(true);
            String code = (String) session.getAttribute("_code");
            System.out.println(code);
            //存入Session
            session.setAttribute("_code", captcha.text().toLowerCase());
            code = (String) session.getAttribute("_code");
            map.put("_code", code);
            System.out.println(code);
        } catch (Exception e) {
            // LoggerUtils.fmtError(getClass(),e, "获取验证码异常：%s",e.getMessage());
            System.out.println("获取验证码异常：" + e.getMessage());
        }
    }
}
