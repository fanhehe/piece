import url from './url';

export default class HttpUtil {

    /**
     * 获取Http请求的scheme, http:// 或者 https://
     * @return String scheme
     */
    static getScheme() {
        return 'http://';
    }

    /**
     * 获取Http请求的endpoint，格式 ip:port
     * @return String endpoint
     */
    static getEndpoint() {
        throw new Error('类需要被继承');
    }

    /**
     * 获取Http请求的参数，也是封装统一参数格式的方法
     * @param  Any params 请求参数
     * @return Any        请求参数
     */
    static getParams(params) { return params; }

    /**
     * 统一发送get请求方法
     * @param  {String} path    请求路径
     * @param  {Object} params  请求参数
     * @param  {Object} headers 请求首部
     * @param  {Object} options fetch.api 其他可配置信息
     * @return {Object}         调用结果
     */
    static async get(path, params = {}, headers = {}, options = {}) {
        return await this.call(path, this.getParams(params), headers, options, this.Methods.GET);
    }

    /**
     * 统一发送post请求方法
     * @param  {String} path    请求路径
     * @param  {Object} params  请求参数
     * @param  {Object} headers 请求首部
     * @param  {Object} options fetch.api 其他可配置信息
     * @return {Object}         调用结果
     */
    static async post(path, params = {}, headers = {}, options = {}) {
        return await this.call(path, this.getParams(params), headers, options, this.Methods.POST);
    }

    /**
     * 因为json常用，所以多出来一个统一发送post + json格式的请求方法
     * @param  {String} path    请求路径
     * @param  {Object} params  请求参数
     * @param  {Object} headers 请求首部
     * @param  {Object} options fetch.api 其他可配置信息
     * @return {Object}         调用结果
     */
    static async json(path, params = {}, headers = {}, options = {}) {
        return await this.call(path, this.getParams(params), headers, options, this.Methods.JSON);
    }


    /**
     * 通用发送http请求的方法
     * @param  {String} path    请求路径
     * @param  {Object} params  请求参数
     * @param  {Object} headers 请求首部
     * @param  {Object} options fetch.api 其他可配置信息
     * @return {Object}         调用结果
     */
    static async call(path, params = {}, headers = {}, options = {}, method = 'GET') {

        options = Object.assign({
            mode: "cors",
            method: method,
            credentials: 'include',
            headers: Object.assign({}, headers),
        }, options);

        switch(method.toUpperCase()) {
            case this.Methods.GET:
                path = `${path}?${url.queryParams(params)}`;
                break;
            case this.Methods.POST:
                options.body = url.queryParams(params);
                options.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
                break;
            case this.Methods.JSON:
                options.method = this.Methods.POST;
                options.body = JSON.stringify(params);
                options.headers['Content-Type'] = 'application/json';
                break;
            default:
                throw new Error("使用了不支持的方法");
        }

        const result = await Promise
            .race([
                this.makeRequestPromise(path, options),
                this.makeTimeoutPromise(options.timeout || 10000),
                ]);

        return await this.checkResponse(result);
    }

    /**
     * 发送请求核心代码
     * @param  {String} path 请求路径
     * @param  {Object} options 配置信息
     * @return {Promise}        响应结果Promise
     */
    static makeRequestPromise(path, options) {
        return new Promise(resolve => {

            const url = `${this.getScheme()}${this.getEndpoint()}${path}`;

            fetch(url, options)
                .then(async result => { resolve(JSON.parse(await result.text())); })
                .catch(err => (resolve({
                    code: 500,
                    message: '网络异常',
                })));
        });
    }

    static checkResponse (response) {
        return response;
    }

    /**
     * 生成超时时间的Promise
     * @param  {String|Number} timeout 超时时间
     * @return {Promise} 超时Promise
     */
    static makeTimeoutPromise(timeout) {
        return new Promise((r, rj) => {
            setTimeout(() => {
                rj(timeout);
            }, timeout);
        }).catch(timeout => ({
            code: 500,
            message: '访问超时,请稍后再试',
            data: {
                timeout,
            }
        }));
    }
}

HttpUtil.Methods = {
    get: 'GET',
    GET: 'GET',
    post: 'post',
    POST: 'POST',
    json: 'json',
    JSON: 'JSON'
}
