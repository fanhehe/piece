import HttpUtil  from '../util/httputil';

export default class Base extends HttpUtil {

    /**
     * 获取Http请求的scheme, http:// 或者 https://
     * @return String scheme
     */
    static getScheme() {
        throw new Error('未设置scheme，请重试');
    }

    /**
     * 获取Http请求的endpoint，格式 ip:port
     * @return String endpoint
     */
    static getEndpoint() {
        throw new Error('未设置endpoint，请重试');
    }

    /**
     * 获取Http请求的参数，也是封装统一参数格式的方法
     * @param  Any params 请求参数
     * @return Any        请求参数
     */
    static getParams(params) {
        return { requestData: JSON.stringify(params) };
    }

    /**
     * 业务层很方便的发get请求
     * @param  {String} path    [description] 请求路径
     * @param  {Object} payload [description] 请求路径
     * @return {Object}         [description] 响应结果
     */
    static async doGet(path, payload) {
        return await this.get(path, payload);
    }

    /**
     * 业务层很方便的发post请求
     * @param  {String} path    [description] 请求路径
     * @param  {Object} payload [description] 请求路径
     * @return {Object}         [description] 响应结果
     */
    static async doPost(path, payload) {
        return await this.post(path, payload);
    }
}
