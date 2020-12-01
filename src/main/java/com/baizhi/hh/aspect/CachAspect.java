package com.baizhi.hh.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

@Configuration
@Aspect
public class CachAspect {
    /**
     * 缓存
     */
    @Resource
    RedisTemplate redisTemplate;

    /**
     * redis   key  value
     * value -(key  hashMap)-
     * hashMap  -(key   value)-
     * key:类+方法名+实参
     * value:查询到的数据
     * -参数-
     * ProceedingJoinPoint:程序的连接点
     * 通过该连接点可以获取当前切入点的一些基本信息
     * 目标对象、方法的名字、方法上的注解、方法上的参数
     * -/面向切面开发/-
     */
    @Around("execution(* com.baizhi.hh.serviceImpl.*.find*(..))")
    public Object addRedis(ProceedingJoinPoint proceedingJoinPoint) {
        StringBuilder stringBuilder = new StringBuilder();
//        序列化解决乱码
        StringRedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
//        获取类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
//        获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        stringBuilder.append(methodName);
//        获取方法上的参数
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            stringBuilder.append(arg);
        }
//        拼接key值
        String key = stringBuilder.toString();
//        ValueOperations forValue = redisTemplate.opsForValue();
        Object result = null;
//        判断key值是否存在于redis
        HashOperations forHash = redisTemplate.opsForHash();
        Boolean aBoolean = forHash.hasKey(className, key);
        if (aBoolean) {
            result = forHash.get(className, key);
        } else {
            try {
//                放行方法(返回数据)
                result = proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            forHash.put(className, key, result);
        }
        return result;
    }

    /**
     * 清除缓存
     * 当程序发生 增删改  操作时
     * 删除存入Redis中的所有key
     * 以保证资源的最新性
     * -/注解开发/-
     */
    @After("@annotation(com.baizhi.hh.annotcation.DelCahe)")
    public void delCache(JoinPoint joinPoint) {
//        获取权限类名
        String className = joinPoint.getTarget().getClass().getName();
//        清除缓存
        redisTemplate.delete(className);
    }
}



