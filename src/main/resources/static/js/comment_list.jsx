import React from 'react';
import Comment from './comment.jsx';

const CommentList = function (props) {
  const commentNodes = props.data.map(function*(comment) {
    return (
      <Comment author={comment.author} key={comment.id}>{comment.text}</Comment>
    );
  });
  return (
    <div className="commentList">
      {commentNodes}
    </div>
  );
};
CommentList.propTypes = {
  data: React.PropTypes.node,
};
