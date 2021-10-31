package Jasper.SideProject.SpringSecurity.Util;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

//统一返回结果的类
@Data
public class R {

  private Boolean success;

  private Integer code;

  private String message;

  private Map<String, Object> data = new HashMap<String, Object>();

  //把构造方法私有
  private R() {}

  //成功静态方法
  public static R ok() {
      R r = new R();
      r.setSuccess(true);
      r.setCode(2000);
      r.setMessage("My Customized Spring Security good");
      return r;
  }

  //失败静态方法
  public static R error() {
      R r = new R();
      r.setSuccess(false);
      r.setCode(2001);
      r.setMessage("My Customized Spring Security fail");
      return r;
  }

  public R success(Boolean success){
      this.setSuccess(success);
      return this;
  }

  public R message(String message){
      this.setMessage(message);
      return this;
  }

  public R code(Integer code){
      this.setCode(code);
      return this;
  }

  public R data(String key, Object value){
      this.data.put(key, value);
      return this;
  }

  public R data(Map<String, Object> map){
      this.setData(map);
      return this;
  }
}
