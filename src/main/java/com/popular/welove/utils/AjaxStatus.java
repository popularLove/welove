package com.popular.welove.utils;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultDefaultValueProcessor;

import java.util.HashMap;
import java.util.Map;


public class AjaxStatus extends HashMap<String, Object> implements Map<String, Object> {
	private static final long serialVersionUID = -4266740045263457771L;
	/** 执行状态：表示执行操作的方法是否有异常【是false，否true】。 */
	public static final String SUCCESS = "success";
	/** 返回信息：表示执行结果的提示信息。 */
	public static final String DESC = "desc";
	/** 返回数据：其它可返回的数据集合。 */
	public static final String DATA = "data";

	public static JsonConfig CONFIG = new JsonConfig();
	static {
		CONFIG.registerDefaultValueProcessor(Integer.class, new DefaultDefaultValueProcessor() {
			@SuppressWarnings("rawtypes")
			public Object getDefaultValue(Class type) {
				return null;
			}
		});
		CONFIG.registerDefaultValueProcessor(Double.class, new DefaultDefaultValueProcessor() {
			@SuppressWarnings("rawtypes")
			public Object getDefaultValue(Class type) {
				return null;
			}
		});
		CONFIG.registerDefaultValueProcessor(Float.class, new DefaultDefaultValueProcessor() {
			@SuppressWarnings("rawtypes")
			public Object getDefaultValue(Class type) {
				return null;
			}
		});
	}

	public AjaxStatus() {
		super();
		this.setSuccess(true);
		this.setDesc("成功");
	}

	public boolean isSuccess() {
		return (Boolean) this.get(SUCCESS);
	}

	public void setSuccess(boolean success) {
		this.put(SUCCESS, success);
	}

	public String getDesc() {
		return (String) this.get(DESC);
	}

	public void setDesc(String desc) {
		this.put(DESC, desc);
	}

	public Object getData() {
		return this.get(DATA);
	}

	public void setData(Object data) {
		this.put(DATA, data);
	}

	/**
	 * <p>
	 * 设置“执行状态”，并设置提示信息
	 * </p>
	 *
	 * @param success 执行状态
	 * @param desc 提示信息
	 */
	public void setSuccess(boolean success, String desc) {
		this.put(SUCCESS, success);
		setDesc(desc);
	}

	/**
	 * 获取实例
	 *
	 * @return 实例
	 */
	public static AjaxStatus instance() {
		return new AjaxStatus();
	}

	/**
	 * 成功
	 *
	 * @param desc 提示信息
	 * @return JSONObject String
	 */
    public static JSONObject success(String desc) {
        return success(desc, null);
    }

	/**
	 * 成功
	 *
	 * @param desc 提示信息
	 * @param data 数据
	 * @return JSONObject String
	 */
    public static JSONObject success(String desc, Object data) {
        AjaxStatus retObj = new AjaxStatus();

        retObj.setSuccess(true, desc);
        if (null != data) {
            retObj.setData(data);
        }
        return JSONObject.fromObject(retObj, CONFIG);
    }

	/**
	 * 失败
	 *
	 * @param desc 提示信息
	 * @return JSONObject String
	 */
    public static JSONObject fail(String desc) {
	    return fail(desc, null);
    }


	/**
	 * 失败
	 *
	 * @param desc 提示信息
	 * @param data 数据
	 * @return JSONObject String
	 */
	public static JSONObject fail(String desc, Object data) {
		AjaxStatus retObj = new AjaxStatus();

		retObj.setSuccess(false, desc);
		if (null != data) {
			retObj.setData(data);
		}

		return JSONObject.fromObject(retObj, CONFIG);
	}
}
