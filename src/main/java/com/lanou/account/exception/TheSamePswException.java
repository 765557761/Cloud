package com.lanou.account.exception;

/**
 * Created by dllo on 17/10/24.
 */
public class TheSamePswException extends AddException {
    @Override
    public String getMessage(){
        return "DifferentPassword";
    }
}
