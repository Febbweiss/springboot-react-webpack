var path				= require('path'),
	webpack				= require('webpack'),
	CommonsChunkPlugin	= require("webpack/lib/optimize/CommonsChunkPlugin"),
	ExtractTextPlugin	= require("extract-text-webpack-plugin"),
	HtmlWebpackPlugin	= require('html-webpack-plugin');

var source_dir = __dirname + '/src/main/resources/static',
	node_dir = __dirname + '/node_modules',
	dev_port = 9090;

var config = {
    entry: {
    	app: [source_dir + '/js/app.render'],
    	vendors: [source_dir + '/js/vendors']
    },
    resolve: {
    	extensions: ['', '.js', '.jsx', '.css', '.less']
    },
    devtool: 'source-map',
    cache: true,
    debug: true,
    output: {
        path: __dirname + '/target/classes/static',
        filename: 'js/[name].bundle.js',
        publicPath: '/'
    },
    plugins: [
        new ExtractTextPlugin("css/[name].css"),
        new CommonsChunkPlugin("vendors", null, true),
        new HtmlWebpackPlugin({
        	template: path.join(__dirname, 'src/main/resources/templates/index-template.html'),
        	filename: '../templates/index.html',
        	xhtml: true,
        	hash: true
        })
    ],
    devServer: {
        port: dev_port,
        publicPath: 'http://localhost:' + dev_port + '/'
    },
    module: {
        loaders: [
			{
			    test: /\.jsx?$/,
			    exclude: /(node_modules)/, 
			    loader: 'babel',
			    query: {
			    	cacheDirectory: true,
			        presets: ['es2015', 'react']
			    }
			},
			{ 
				test: /\.css$/, 
				loader: ExtractTextPlugin.extract("style-loader", "css-loader!postcss-loader") 
			},
			{
				test: /\.less$/,
				loader: ExtractTextPlugin.extract('style-loader', 'css-loader?!postcss-loader!less-loader')
			},
			{ 
            	test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, 
            	loader: "file?name=font/[name].[ext]" 
            },
			{ 
            	test: /\.(woff|woff2)$/, 
            	loader:"url?prefix=font/&limit=5000&name=font/[name].[ext]" 
            },
			{ 
            	test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, 
            	loader: "url?limit=10000&mimetype=application/octet-stream&&name=font/[name].[ext]" 
            },
			{ 
            	test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, 
            	loader: "url?limit=10000&mimetype=image/svg+xml&&name=font/[name].[ext]" 
            },
            { 
            	test: /\.html$/, 
            	loader: 'html-loader?minimize=false' 
            }
        ]
    },
    postcss: function () {
        return [];
    },
    htmlLoader: {
    	removeAttributeQuotes: false,
    }
};

var env = process.env.NODE_ENV;

if (env === 'development') {
	config.output.publicPath = 'http://localhost:' + dev_port + '/';
}

module.exports = config;