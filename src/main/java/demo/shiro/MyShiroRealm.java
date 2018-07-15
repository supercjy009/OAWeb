package demo.shiro;


import demo.model.SysPermissionEntity;
import demo.model.SysRolepermissionEntity;
import demo.model.SysUserroleEntity;
import demo.model.UserinfoEntity;
import demo.service.Imp.*;
import demo.util.MyDES;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by PC on 2017/11/22.
 * 身份校验核心类;
 */
public class MyShiroRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
    @Resource
    UserinfoServiceImp userinfoServiceImp;
    @Resource
    SysUserroleServiceImp sysUserroleServiceImp;
    @Resource
    SysRoleServiceImp sysRoleServiceImp;
    @Resource
    SysRolepermissionServiceImp sysRolepermissionServiceImp;
    @Resource
    SysPermissionSerivceImp sysPermissionSerivceImp;
    // 必须通过bean进行创建对象，不能通过方法调用方式进行创建对象否则为null
    @Resource
    StringRedisTemplate redisTemplate01;
    @Resource
    private RedisSessionDAO redisSessionDAO;

    //用户登录次数计数  redisKey 前缀
    private String SHIRO_LOGIN_COUNT = "shiro_login_count_";

    //用户登录是否被锁定    一小时 redisKey 前缀
    private String SHIRO_IS_LOCK = "shiro_is_lock_";

    /**
     * 认证信息.(身份验证)
     * :
     * Authentication 是用来验证用户身份
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = token.getUsername();


        //访问一次，计数一次
        ValueOperations<String, String> opsForValue = redisTemplate01.opsForValue();
        opsForValue.increment(SHIRO_LOGIN_COUNT + name, 1);
        //计数大于5时，设置用户被锁定一小时
        System.out.println(Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT + name)));
        if (Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT + name)) >= 5) {
            opsForValue.set(SHIRO_IS_LOCK + name, "LOCK");
            redisTemplate01.expire(SHIRO_IS_LOCK + name, 1, TimeUnit.DAYS);
        }
//        if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK + name))) {
//            throw new DisabledAccountException("密码输入错误次数大于5次,请24小时后再试！");
//            // throw new LockedAccountException(); // 帐号锁定
//        }
        // 从数据库获取对应用户名密码的用户
        UserinfoEntity userinfoEntity = userinfoServiceImp.queryUserInfoByusername(name);
        if (null == userinfoEntity) {
            throw new AccountException("帐号或密码不正确！");
        }
        String password = String.valueOf(token.getPassword());
        //密码进行加密处理  明文为  password+盐
        String paw = password + userinfoEntity.getSalt();
        String pawDES = MyDES.encryptBasedDes(paw);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nickname", name);
        map.put("pswd", pawDES);


        if (userinfoEntity.getPassword().equals(pawDES)) {
            //登录成功
            //更新登录时间 last login time
            // user.setLastLoginTime(new Date());
            // sysUserService.updateById(user);
            //清空登录计数
            opsForValue.set(SHIRO_LOGIN_COUNT + name, "0");
        } else {
            throw new AccountException("帐号或密码不正确！");
        }

        // 当验证都通过后，把用户信息放在session里
        // 在存放之前先将之前用户可能存在的信息清除
//        List<Long> userid = new ArrayList<>();
//        userid.add(userinfoEntity.getUid());
//        clearUserCacheByUserId(userid);
//        clearUserAuthByUserId(userid);
        //处理session
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
        Session Subjectsession = SecurityUtils.getSubject().getSession();
        logger.info("myshirorealm的自身sessionid：" + Subjectsession.getId());
        for (Session session : sessions) {
            //清除该用户以前登录时保存的session
            if (null == session)
                continue;
            logger.info("myshirorealm的数据库中存储的sessionid：" + session.getId());
            logger.info(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)));
            if (userinfoEntity.toString().equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
                // 妄图通过sessionid相同的方式骗过去~~~~
                if (!session.getId().equals(Subjectsession.getId())) {
                    sessionManager.getSessionDAO().delete(session);
                }
                // 如果id一个有一个没有说明不同
//                if(session.getAttributeKeys().contains("userSessionId") && SecurityUtils.getSubject().getSession().getAttributeKeys().contains("userSessionId")){
//                    logger.info(session.getAttribute("userSessionId").toString());
//                    logger.info(SecurityUtils.getSubject().getSession().getAttribute("userSessionId").toString());
//                    // 如果id相同说明是同一个session信息，为了避免误删除则需要更新即可，不能将自身删除否则会有异常
//                    if(session.getAttribute("userSessionId").toString().equals(SecurityUtils.getSubject().getSession().getAttribute("userSessionId").toString())){
//                        continue;
//                    }
//                }else{
//                    if(session != Subjectsession){
//                        sessionManager.getSessionDAO().delete(session);
//                    }
//                }
            }
        }


        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", userinfoEntity);
        session.setAttribute("userSessionId", userinfoEntity.getUid());

        // Logger.getLogger(getClass()).info("身份认证成功，登录用户："+name);
        logger.debug("身份认证成功，登录用户：" + name);
        return new SimpleAuthenticationInfo(userinfoEntity, password, getName());
    }

    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*
        * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
        * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
        * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
        * 缓存过期之后会再次执行。
        */
        // System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        logger.debug("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //SysuserinfoEntity userInfo  = (SysuserinfoEntity)principalCollection.getPrimaryPrincipal();
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//     UserInfo userInfo = userInfoService.findByUsername(username)
        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        //UserinfoEntity userinfoEntity = (UserinfoEntity)principalCollection.getPrimaryPrincipal();
        // UserinfoEntity userinfoEntityDB = userinfoServiceImp.queryUserInfoByusername(userinfoEntity.getUsername());

        List<SysUserroleEntity> sysUserroleEntities = sysUserroleServiceImp.queryUserroleByuserid(userinfoEntity.getUid());
        for (SysUserroleEntity sysUserroleEntity : sysUserroleEntities) {
            Long roleid = sysUserroleEntity.getRoleId();
            List<SysRolepermissionEntity> sysRolepermissionEntities = sysRolepermissionServiceImp.queryListSysRolepermissionEntityByroleid(roleid);
            for (SysRolepermissionEntity sysRolepermissionEntity : sysRolepermissionEntities) {
                SysPermissionEntity sysPermissionEntity = sysPermissionSerivceImp.selectByPrimaryKey(sysRolepermissionEntity.getPermissionId());
                // authorizationInfo.addStringPermission(sysPermissionEntity.getPermission());
                authorizationInfo.addStringPermission("perms[" + sysPermissionEntity.getUrl() + "]");
            }
        }
        return authorizationInfo;
    }

    /**
     * 根据userId 清除当前session存在的用户的权限缓存
     *
     * @param userIds 已经修改了权限的userId
     */
    public void clearUserAuthByUserId(List<Long> userIds) {
        if (null == userIds || userIds.size() == 0) return;
        //获取所有session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session : sessions) {
            //获取session登录信息。
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (null != obj && obj instanceof SimplePrincipalCollection) {
                //强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
                //判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if (null != obj && obj instanceof UserinfoEntity) {
                    UserinfoEntity user = (UserinfoEntity) obj;
                    logger.debug("user:" + user);
                    //比较用户ID，符合即加入集合
                    if (null != user && userIds.contains(user.getUid())) {
                        list.add(spc);
                    }
                }
            }
        }
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm) securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection simplePrincipalCollection : list) {
            realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
        }
    }

//    /**
//     * 根据userId 清除当前session存在的用户的权限缓存
//     *
//     * @param userIds 已经修改了权限的userId
//     */
//    public void clearUserCacheByUserId(List<Long> userIds) {
//        if (null == userIds || userIds.size() == 0) return;
//        //获取所有session
//        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
//        //定义返回
//        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
//        for (Session session : sessions) {
//            //获取session登录信息。
//            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
//            if (null != obj && obj instanceof SimplePrincipalCollection) {
//                //强转
//                SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
//                //判断用户，匹配用户ID。
//                obj = spc.getPrimaryPrincipal();
//                if (null != obj && obj instanceof UserinfoEntity) {
//                    UserinfoEntity user = (UserinfoEntity) obj;
//                    logger.debug("user:" + user);
//                    //比较用户ID，符合即加入集合
//                    if (null != user && userIds.contains(user.getUid())) {
//                        list.add(spc);
//                    }
//                }
//            }
//        }
//        RealmSecurityManager securityManager =
//                (RealmSecurityManager) SecurityUtils.getSecurityManager();
//        MyShiroRealm realm = (MyShiroRealm) securityManager.getRealms().iterator().next();
//        for (SimplePrincipalCollection simplePrincipalCollection : list) {
//            realm.clearCachedAuthenticationInfo(simplePrincipalCollection);
//        }
//    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
