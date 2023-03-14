package com.code.myEnum;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/2 16:33
 * @email 3349495429@qq.com
 * @className com.code.myEnum.DaoExceptionStatus
 * @description:
 */
public enum DaoExceptionStatus {
    WARNING("waring-数据库警告:"),ERROR("error-数据库错误:");
    private final String msg;
    private DaoExceptionStatus(String msg){
        this.msg = msg;
    }
   public String msg(){
        return msg;
   }
}
