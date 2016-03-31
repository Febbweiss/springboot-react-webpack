"use strict";


import React from 'react';
import ReactDOM from 'react-dom';
import ReactDOMServer from 'react-dom/server';
import App from './app.jsx';
import $ from 'jquery';


require('bootstrap/dist/css/bootstrap.css');
require('../css/comments.css');
require('../css/comments.less');

global.renderClient = function (comments) {
    var data = comments || [];
    ReactDOM.render(
		<App.CommentBox data={data} url="/api/comments" pollInterval={2000}/>,
		document.getElementById('content')
	);
};

global.renderServer = function (comments) {
    var data = Java.from(comments);
    return ReactDOMServer.renderToString(
    	<App.CommentBox data={data} url="/api/comments" pollInterval={2000} />
    );
};

if( !global.nashorn ) {
	renderClient(initialData);
};