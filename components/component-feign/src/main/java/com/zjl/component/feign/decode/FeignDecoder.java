package com.zjl.component.feign.decode;

import feign.Response;
import feign.codec.Decoder;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 * @author zhuojl
 */
@Slf4j
public class FeignDecoder<T extends com.zjl.component.common.model.Response> implements Decoder {

    private final Class<T> resultCls;
    private Decoder decoder;

    public FeignDecoder(Class<T> resultCls, Decoder decoder) {
        this.resultCls = resultCls;
        this.decoder = decoder;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {

        // 如果是子类，则不再需要 后续的 ParameterizedTypeImpl 包装
        if (isResultClsType(type)) {
            return this.decoder.decode(response, type);
        }

        // XXX 注意第三个参数，如果 一个rpc的Response是内部类，需要多次包装，尽量避免吧。。
        Type resultClsType = ParameterizedTypeImpl.make(
                com.zjl.component.common.model.Response.class, new Type[]{type}, null);

        Object obj = decoder.decode(response, resultClsType);
        T result = (T) obj;

        if (Objects.isNull(result)) {
            log.error("result is empty, must be sth wrong");
            return null;
        }

        return result.getData();

    }

    /**
     * type 是否是 resultCls 的子类
     */
    private boolean isResultClsType(Type type) {
        // 当方法签名的返回参数没有使用泛型时，type是Class
        if (type instanceof Class && resultCls.isAssignableFrom((Class) type)) {
            return true;
        }

        // 当方法签名的返回参数是泛型时，type是ParameterizedType
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            if (rawType instanceof Class && resultCls.isAssignableFrom((Class) rawType)) {
                return true;
            }
        }

        return false;
    }

}
