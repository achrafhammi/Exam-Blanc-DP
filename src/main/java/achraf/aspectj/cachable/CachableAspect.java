package achraf.aspectj.cachable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class CachableAspect {
    private Map<String, Object> cache = new HashMap<>();
    @Around("@annotation(Cachable)")
    public Object cacheResult(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        Object[] arguments = joinPoint.getArgs();
        String cacheKey = generateCacheKey(methodName, arguments);
        if (cache.containsKey(cacheKey)) {
            System.out.println("usage de cache");
            return cache.get(cacheKey);
        }
        System.out.println("nouvelle ops, donc cache vide");

        Object result = joinPoint.proceed();
        cache.put(cacheKey, result);
        return result;
    }
    private String generateCacheKey(String methodName, Object[] args) {
        StringBuilder key = new StringBuilder(methodName);
        for (Object arg : args) {
            key.append("|").append(arg);
        }
        return key.toString();
    }
}