package com.biocome.platform.wechatapplet.vo.duanxin;

import com.biocome.platform.common.util.ValidateUtils;

import java.io.Serializable;

public class IacsResp
  implements Serializable
{
  private String result;
  private String message;
  private String errorcode;
  
  public String getResult()
  {
    return ValidateUtils.isEmpty(this.result) ? "0" : this.result;
  }
  
  public void setResult(String result)
  {
    this.result = result;
  }
  
  public String getMessage()
  {
    return ValidateUtils.isEmpty(this.message) ? "" : this.message;
  }
  
  public void setMessage(String message)
  {
    this.message = message;
  }
  
  public String getErrorcode()
  {
    return ValidateUtils.isEmpty(this.errorcode) ? "" : this.errorcode;
  }
  
  public void setErrorcode(String errorcode)
  {
    this.errorcode = errorcode;
  }
}
