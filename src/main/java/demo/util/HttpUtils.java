package demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.util.StringUtil;
import demo.model.SysPermissionEntity;
import demo.service.SysPermissionService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 17-11-30
 **/
public class HttpUtils {
    public static boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    // 向客户端输出内容
    public static void sendJsonResponse(ServletResponse response, Object result) {
        try {
            response.getWriter().print(new ObjectMapper().writeValueAsString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化权限
     */
    public static Map<String, String> loadFilterChainDefinitions(SysPermissionService resourcesService) {
        // 权限控制map.从数据库获取
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/Wopop_files/**", "anon");
        filterChainDefinitionMap.put("/ajaxlogin", "anon");
        filterChainDefinitionMap.put("/getGifCode", "anon");
        filterChainDefinitionMap.put("/getGifCode2", "anon");
        filterChainDefinitionMap.put("/webAjax/**", "anon");//通过这个访问的数据都返回
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/testroot", "anon");
        filterChainDefinitionMap.put("/testroot2", "anon");
        List<SysPermissionEntity> resourcesList = resourcesService.queryAllPermission();
        for (SysPermissionEntity resources : resourcesList) {
            if (StringUtil.isNotEmpty(resources.getUrl())) {
                String permission = "perms[" + resources.getUrl() + "]";
                filterChainDefinitionMap.put(resources.getUrl(), permission);
            }
        }
//        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/userinfo", "user");
        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }
}
