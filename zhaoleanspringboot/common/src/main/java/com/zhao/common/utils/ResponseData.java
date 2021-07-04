package com.zhao.common.utils;

import com.zhao.common.utils.enums.FinalValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {
    /**
     * 状态码 0-成功
     */
    private int ret;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 结果集
     */
    private T data;

    /**
     * 200 请求成功
     */
    public static ResponseData ok(Object data){
        ResponseData result = new ResponseData();
        result.setRet(FinalValue.CODE_OK);
        result.setMsg(FinalValue.OPERATE_SUCCESS);
        result.setData(data);
        return result;
    }


}
