export default class Http {
    static call(path, params, headers, method) {
        return fetch(path, {

            headers: headers || {},
            method: method.toLowerCase(),
        }).then(function(response) {
            if (response.ok && response.data) {
                return response.data;
            }
        }).catch(function(err) {
            return
        });
    }

    static request() {
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
    }
}

Http.Methods = {
    get: 'GET',
    GET: 'GET',
    post: 'post',
    POST: 'POST',
    json: 'json',
    JSON: 'JSON'
};
