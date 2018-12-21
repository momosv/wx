//package com.cn.xt.mp.aop;
//
//
//import com.alibaba.fastjson.JSON;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
///**
// * Created by wuwf on 17/4/27.
// * 日志切面
// */
//@Aspect
//@Component
//public class LogAspect {
//
//    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
//
//    @Pointcut("execution(* com.cn.xt.mp.feedback.mobile.*.*(..))")
//    public void webLog() {
//    }
//
//    //环绕通知,环绕增强，相当于MethodInterceptor
//    @Around("webLog()")
//    public Object arround(ProceedingJoinPoint pjp) {
//       /* System.out.println("方法环绕start.....");
//        try {
//            Object o = pjp.proceed();
//            System.out.println("方法环绕proceed，结果是 :" + o);
//            return o;
//        } catch (Throwable e) {
//            e.printStackTrace();
//            return null;
//        }*/
//        return null;
//    }
//
//    @Before("webLog()")
//    public void deBefore(JoinPoint joinPoint) throws Throwable {
//      /*  // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // 记录下请求内容
//        System.out.println("URL : " + request.getRequestURL().toString());
//        System.out.println("HTTP_METHOD : " + request.getMethod());
//        System.out.println("IP : " + request.getRemoteAddr());
//        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//*/
//    }
//
//
//    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
//    @After("webLog()")
//    public void after(JoinPoint jp) {
//
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "webLog()")
//    public void doAfterReturning(Object ret) throws Throwable {
//
//    }
//
//    //后置异常通知
//
//
///**
// * 异常通知 用于拦截异常日志
// *
// * @param joinPoint
// * @param e
// */
//        @AfterThrowing(pointcut = "webLog()", throwing = "e")
//        public void doAfterThrowing (JoinPoint joinPoint, Throwable e){
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//            HttpSession session = request.getSession();
//            //获取请求ip
//            String ip = request.getRemoteAddr();
//            //获取用户请求方法的参数并序列化为JSON格式字符串
//            try {
//                String method = request.getMethod();
//                String param = JSON.toJSONString(request.getParameterMap());
//                System.out.println(param);
//                String beanName = joinPoint.getSignature().getDeclaringTypeName(); //方法所在的类名
//                String methodName = joinPoint.getSignature().getName() + "-" + method;//方法名
//                String uri = request.getRequestURI(); //接口名
//                String url = request.getRequestURL().toString(); //url
///*                OperatorLog operatorLog = new OperatorLog();
//                operatorLog.setExceptionName(e.getClass().getName());
//                operatorLog.setExceptionMsg(e.getMessage());
//                operatorLog.setMethod(methodName);
//                operatorLog.setUrl(url);
//                operatorLog.setIntf(uri);
//                operatorLog.setRequestParam(param);
//                operatorLog.setBeanName(beanName);
//                long beginTime = System.currentTimeMillis();
//                Date date = new Date(beginTime);
//                operatorLog.setRequestTime(date);
//                operatorLog.setRequestIp(ip);
//
//                //保存数据库
//                operatorLogService.save(operatorLog);*/
//                System.out.println("=====异常通知结束=====");
//            } catch (Exception ex) {
//                //记录本地异常日志
//                e.printStackTrace();
//                LOGGER.error("==异常通知异常==");
//            }
//        }
//    }
