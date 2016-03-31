var path				= require('path'),
	webpack				= require('webpack'),
	CommonsChunkPlugin	= require("webpack/lib/optimize/CommonsChunkPlugin"),
	ExtractTextPlugin	= require("extract-text-webpack-plugin");

var source_dir = __dirname + '/src/main/resources/static/js',
	node_dir = __dirname + '/node_modules';

var config = {
    entry: {
    	app: [source_dir + '/app.render'],
    	vendors: [source_dir + '/vendors']
    },
    resolve: {
    	extensions: ['', '.js', '.jsx', '.css']
    },
    devtool: 'source-map',
    cache: true,
    debug: true,
    output: {
        path: './target/classes/static/js',
        filename: '[name].bundle.js'        
    },
    plugins: [
        new ExtractTextPlugin("../css/[name].css"),
        new CommonsChunkPlugin("vendors", null, true),
    ],
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
            	test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, 
            	loader: "file?name=../css/[name].[ext]" 
            },
			{ 
            	test: /\.(woff|woff2)$/, 
            	loader:"url?prefix=font/&limit=5000&name=../css/[name].[ext]" 
            },
			{ 
            	test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, 
            	loader: "url?limit=10000&mimetype=application/octet-stream&&name=../css/[name].[ext]" 
            },
			{ 
            	test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, 
            	loader: "url?limit=10000&mimetype=image/svg+xml&&name=../css/[name].[ext]" 
            }
        ]
    },
    postcss: function () {
        return [];
    }
};

module.exports = config;