import React from 'react';
import $ from 'jquery';
import CommentForm from './comment_form.jsx';
import CommentList from './comment_list.jsx';

class CommentBox extends React.Component {
  componentDidMount() {
    this.loadCommentsFromServer();
    setInterval(this.loadCommentsFromServer, this.props.pollInterval);
  }
  getInitialStatefunction() {
    return { data: this.props.data || [] };
  }
  loadCommentsFromServer() {
    $.ajax({
      url: this.props.url,
      dataType: 'json',
      cache: false,
      success: function success(data) {
        this.setState({ data });
      }.bind(this),
      error: function error(xhr, status, err) {
        console.error(this.props.url, status, err.toString());
      }.bind(this),
    });
  }
  handleCommentSubmit(comment) {
    const comments = this.state.data;
    const copy = comment;
    copy.id = Date.now();
    const newComments = comments.concat([copy]);
    this.setState({ data: newComments });
    $.ajax({
      url: this.props.url,
      method: 'POST',
      dataType: 'json',
      data: comment,
      success: function success(data) {
        this.setState({ data });
      }.bind(this),
      error: function error(xhr, status, err) {
        this.setState({ data: comments });
        console.error(this.props.url, status, err.toString());
      }.bind(this),
    });
  }
  render() {
    return (
      <div className="commentBox">
        <h1>Comments</h1>
        <CommentList data={this.state.data} />
        <CommentForm onCommentSubmit={this.handleCommentSubmit} />
      </div>
    );
  }
}
CommentBox.propTypes = {
  data: React.PropTypes.shape({
    id: React.PropTypes.number,
    author: React.PropTypes.string,
    text: React.PropTypes.string,
  }),
  pollInterval: React.PropTypes.numbe,
  url: React.PropTypes.string,
};
