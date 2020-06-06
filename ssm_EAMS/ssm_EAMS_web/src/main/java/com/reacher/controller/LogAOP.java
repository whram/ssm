package com.reacher.controller;

import com.reacher.domain.SysLog;
import com.reacher.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAOP {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;//访问时间
    private Class aClass;//访问类
    private Method method;//访问方法

    //前置通知 主要获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* com.reacher.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//获取当前时间
        aClass = jp.getTarget().getClass(); //具体访问的类
        String methodName = jp.getSignature().getName();//获取访问方法的名称
        Object[] args = jp.getArgs();//获取访问方法的参数

        //获取具体的执行方法的method对象
        if (args == null || args.length==0){
            method = aClass.getMethod(methodName);//只能获取无参的方法
        }else {
            Class[] classes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classes[i] = args[i].getClass();//要保证所有的方法的参数都为对象类型
            }
            method = aClass.getMethod(methodName,classes);
        }
    }

    //后置通知
    @After("execution(* com.reacher.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //获取访问时长
        long time = new Date().getTime() - visitTime.getTime();

        //获取url，通过反射获取
        String url = "";
        if (aClass!=null && method!=null && aClass!=LogAOP.class) {
            //获取类上的RequestMapping的值
            RequestMapping classAnnotation = (RequestMapping) aClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null){
                String classValue = classAnnotation.value()[0];
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String methodValue = methodAnnotation.value()[0];
                    url = classValue+methodValue;

                    //获取访问的ip地址 通过request对象
                    String ip = request.getRemoteAddr();

                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将日志相关信息封装到SysLog中
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setMethod("[类名] "+aClass.getName()+"[方法名]" + method.getName());

                    //调用Service完成操作
                    sysLogService.save(sysLog);
                }
            }
        }


    }

}
