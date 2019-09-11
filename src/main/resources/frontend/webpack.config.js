var path = require('path');
var glob = require('glob');
var webpack = require('webpack');

var entryMap = getEntrys();

module.exports = env => {
    var isProdEnv = env && env.prod;

    return {
        entry: entryMap,
        resolve: {
            extensions: ['.js', '.vue', '.json'],
            modules: ['./src/script', 'node_modules'],
            alias: {
                // vue: isProdEnv ? 'vue/dist/vue.min.js' : 'vue/dist/vue',
            }
        },
        resolveLoader: {
            alias: {
                text: 'text-loader'
            },
            moduleExtensions: [ '-loader' ]
        },
        amd: {
            $: true,
            jQuery: true
        },
        externals: {
            // jquery: 'jQuery',
            // XLSX: 'XLSX',
            // html2canvas: 'html2canvas'
        },
        module: {
            rules: [
                {
                    test: /\.js|.json$/,
                    exclude: /(node_modules|bower_components)/,
                    use: {
                        loader: 'babel-loader'
                    }
                }
            ]
        },
        // 热加载
        // devServer: {
        //     // 表示静态资源服务器的根目录是/static
        //     contentBase: path.join(__dirname, './static/'),
        //     // 表示将打包的静态资源放在 / 目录的'/scripts-build/'中
        //     // 在entry中，结构是 {'app/semLandingHK/hkMian.js':'app/semLandingHK/hkMian.js'}(简称{name:path})
        //     // devServer这里publicPath的作用是：publicPath + path，是页面上访问hkMian.js的路径，即：/scripts-build/app/semLandingHK/hkMian.js
        //     publicPath: '/scripts-build/',
        //     port: 8866,
        //     hot: true,
        //     progress: true,
        //     overlay: true,
        //     inline: true,
        //     compress: true,
        //     proxy: {
        //         '/': {
        //             target: 'http://localhost:8094',
        //             changeOrigin: true,
        //             secure: false
        //         }
        //     },
        //     disableHostCheck: true
        // },

        devtool: isProdEnv ? 'hidden-source-map' : 'source-map',
        plugins: (function() {
            var _plugins = [
                new webpack.DefinePlugin({
                    'process.env.NODE_ENV': isProdEnv
                        ? '"production"'
                        : '"development"'
                }),
            ];


            // _plugins.push(new webpack.HotModuleReplacementPlugin());

            return _plugins;
        })(),
        output: {
            path: path.resolve(__dirname, './dist/script'),
            filename: '[name].js',
            publicPath: '/static/'
        }
    };
};

function getEntrys() {
    var entry = {};
    var rootPath = './src/script/';
    var files = glob.sync('./src/script/main/**/*.js');

    files.forEach(function(item, index) {
        entry[item.substring(rootPath.length, item.lastIndexOf('.js'))] = item;
    });

    return entry;
}
