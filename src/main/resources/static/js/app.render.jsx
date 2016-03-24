var renderClient = function (comments) {
    var data = comments || [];
    ReactDOM.render(
		<CommentBox data={data} url="/api/comments" pollInterval={2000}/>,
		document.getElementById('content')
	);
};

var renderServer = function (comments) {
    var data = Java.from(comments);
    return React.renderToString(
    	<CommentBox data={data} url="/api/comments" pollInterval={2000} />
    );
};