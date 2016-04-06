import React from 'react';
import ReactDOM from 'react-dom';
import ReactDOMServer from 'react-dom/server';
import CommentBox from './comment_box.jsx';

import 'bootstrap/dist/css/bootstrap.css';
import '../css/comments.css';
import '../css/comments.less';

global.renderClient = function renderClient(comments) {
  const data = comments || [];
  ReactDOM.render(
	<CommentBox data={data} url="/api/comments" pollInterval={2000} />,
	document.getElementById('content')
  );
};

global.renderServer = function renderServer(comments) {
  const data = Java.from(comments);
  return ReactDOMServer.renderToString(
	<CommentBox data={data} url="/api/comments" pollInterval={2000} />
  );
};

if (!global.nashorn) {
  global.renderClient(global.initialData);
}
