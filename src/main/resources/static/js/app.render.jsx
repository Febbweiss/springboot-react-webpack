"use strict";

var React			= require('react'),
	ReactDOM		= require('react-dom'),
	ReactDOMServer	= require('react-dom/server'),
	CommentBox		= require('./app.jsx').CommentBox;


require('bootstrap/dist/css/bootstrap.css');

global.renderClient = function (comments) {
    var data = comments || [];
    ReactDOM.render(
		<CommentBox data={data} url="/api/comments" pollInterval={2000}/>,
		document.getElementById('content')
	);
};

global.renderServer = function (comments) {
    var data = Java.from(comments);
    return ReactDOMServer.renderToString(
    	<CommentBox data={data} url="/api/comments" pollInterval={2000} />
    );
};

