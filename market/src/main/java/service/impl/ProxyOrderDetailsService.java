package service.impl;

import infrastructure.annotation.Log;
import lombok.*;
import model.OrderDetails;
import service.api.crud.OrderDetailsService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProxyOrderDetailsService implements OrderDetailsService {

    private final OrderDetailsService orderDetailsService;

    public ProxyOrderDetailsService(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @SneakyThrows
    @Override
    public OrderDetails save(OrderDetails entity) {
        Object[] args = new Object[]{entity};
        return log(OrderDetails.class, args).getResult();
    }

    @Override
    public OrderDetails update(OrderDetails entity) {
        Object[] args = new Object[]{entity};
        return log(OrderDetails.class, args).getResult();
    }

    @Override
    public Optional<OrderDetails> getById(UUID uuid) {
        Object[] args = new Object[]{uuid};
        OrderDetails details = log(OrderDetails.class, args).getResult();
        return Optional.ofNullable(details);
    }

    @Override
    public List<OrderDetails> getAll() {
        return log(OrderDetails.class).getResultlist();
    }

    @Override
    public void delete(OrderDetails entity) {
        Object[] args = new Object[]{entity};
        log(OrderDetails.class, args);
    }


    private boolean checkOnLogAnnotationPresence(Method method) {
        return Arrays.stream(method.getAnnotations())
                .anyMatch(annotation -> annotation.annotationType().equals(Log.class));
    }

    private Method getProxiedMethodByCallerMethodName(Object... args) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement currentElement = stackTrace[3];
        String callerMethodName = currentElement.getMethodName();

        Class<?>[] parameterTypes = Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);

        try {
            return orderDetailsService.getClass().getMethod(callerMethodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> Result<T> log(Class<T> returnClazzType, Object... args) {

        Method proxiedMethod = getProxiedMethodByCallerMethodName(args);
        Object result = null;

        if (checkOnLogAnnotationPresence(proxiedMethod)) {
            proxiedMethod.setAccessible(true);
            String originClassName = proxiedMethod.getDeclaringClass().getName();
            String originMethodName = proxiedMethod.getName();
            System.out.println("Start invoke method %s from %s class".formatted(originMethodName, originClassName));
            long startTime = System.currentTimeMillis();
            try {
                result = proxiedMethod.invoke(orderDetailsService, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            long wastedTime = System.currentTimeMillis() - startTime;
            System.out.println("The method %s has been successfully completed; Wasted time: %d millis"
                    .formatted(originMethodName, wastedTime));
            proxiedMethod.setAccessible(false);
        } else {
            try {
                result = proxiedMethod.invoke(orderDetailsService, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        if (result instanceof List) {
            return new Result<>((List<T>) result);
        }
        return new Result<>(returnClazzType.cast(result));
    }
}


@Getter
@Setter
class Result<T> {
    private T result;
    private List<T> resultlist;

    public Result(T result) {
        this.result = result;
    }

    public Result(List<T> resultlist) {
        this.resultlist = resultlist;
    }
}
