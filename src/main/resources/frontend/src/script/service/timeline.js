export {};
import Local from './base/local';

const requestPath = {
    TimelineList: '/api/timeline/list/',
};

export default class Timeline extends Base {

    /**
     * 获取Http请求的scheme, http:// 或者 https://
     * @return String scheme
     */
    static async getTimelineList() {
        return await this.get(requestPath.TimelineList);
    }

}
