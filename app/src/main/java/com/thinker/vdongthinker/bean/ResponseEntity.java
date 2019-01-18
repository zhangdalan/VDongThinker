package com.thinker.vdongthinker.bean;

/**
 * 响应实体类
 * 
 * @author Zjw
 * 
 * @param <T>
 */
public class ResponseEntity<T>
{

	private String code;
	private String msg;
	private T data;

	public ResponseEntity()
	{
		super();
	}

	public ResponseEntity(String code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 请求是否成功
	 * 
	 * @return 请求结果
	 */
	public boolean isSuccess()
	{

		return code.equals("0");
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{

		this.data = data;
	}

}