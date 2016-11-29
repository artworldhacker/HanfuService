package com.dexingworld.hanfu.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 校验工具类
 * Created by wangpeng on 2016/11/14.
 */
public class ValidatorUtils {

    private static Validator validator;

    static {
        init();
    }

    private static void init(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static <T> void validator(T t){
        Set violations = validator.validate(t, new Class[0]);
        if(!violations.isEmpty()){
            try {
                throw new Exception(buildErrorMsg(violations));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private static <T> String buildErrorMsg(Set<ConstraintViolation<T>> violations){
        StringBuilder sb = new StringBuilder();
        for(ConstraintViolation constraintViolation : violations){
            sb.append("[").append(constraintViolation.getMessage()).append("]").append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
