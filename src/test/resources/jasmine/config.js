'use strict';

$("body").append("<div id='content'></div>");

$.mockjax({
  url: "/api/comments",
  responseText: []
});