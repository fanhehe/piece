package com.fanhehe.codepiece.controller;

import com.fanhehe.codepiece.util.result.IResult;
import com.fanhehe.codepiece.util.result.InvokeResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	/**
	 * 首页跳到timeline页
	 * @return 跳转到timeline页
	 */
    @RequestMapping(method = RequestMethod.GET, value = "/")
	public String index() {
        return "redirect:/timeline";
	}

	/**
	 * 404 页
	 * @return 404页
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/404")
	public String pageNotFound() {
		return "404";
	}

	/**
	 * 500 页
	 * @return 500页
	 */
	@RequestMapping(method = RequestMethod.GET, value = {"/error", "/500" })
	public String internalError() {
		return "error";
	}

	/**
	 * 500 页
	 * @return 500 API接口
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/api/500")
	public IResult apiInternalError() {
		return InvokeResult.failure("网络异常", 500);
	}
}
