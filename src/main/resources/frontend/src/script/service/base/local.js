import Base from './base';
import Config from '../../config';

export default class Local extends Base {

    /**
     * 获取Http请求的scheme, http:// 或者 https://
     * @return String scheme
     */
    static getScheme() {
        return Config.service.local.scheme;
    }

    /**
     * 获取Http请求的endpoint，格式 ip:port
     * @return String endpoint
     */
    static getEndpoint() {
        throw Config.service.local.endpoint;
    }
}
