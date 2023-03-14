package com.code.exception;

import com.code.myEnum.DaoExceptionStatus;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/2 16:20
 * @email 3349495429@qq.com
 * @className com.code.exception.DaoException
 * @description:
 */
public class DaoException extends ProjectException{

    public DaoException(){}

    public DaoException(String msg){
        super(msg);
    }

    public DaoException(DaoExceptionStatus status,String msg){
        this(status.msg()+msg);
    }

    public static DaoException dataNotFund(){
        return new DaoException(DaoExceptionStatus.WARNING,"数据库中未查到该学号的学生，请重新输入学号再次查询。");
    }
    public static DaoException dataHasExist(){
        return new DaoException(DaoExceptionStatus.WARNING,"该学生已存在，请勿重复添加");
    }
    public static DaoException dataSystemError(String msg){
        return new DaoException(DaoExceptionStatus.ERROR,msg);
    }
}
