'use strict';

var AjaxResponses = {
	empty: {
		success: {
			status: 200,
			responseText: '[]'
		}
	},
	submit: {
		success: {
			status: 200,
			responseText: '[{id: 1, author: "Lao Tzu", text: "The journey of a thousand miles begins with one step"}]'
		}
	}
};

var TestUtils = React.addons.TestUtils;

describe('Comments', function() {
	
	var instance,
		container = document.createElement("div");
	
	afterEach(function() {
		if (instance && instance.isMounted()) {
			// Only components with a parent will be unmounted
			ReactDOM.unmountComponentAtNode(ReactDOM.findDOMNode(instance).parentNode);
	    }
	});
	
	describe("rendered without a container reference", function() {
	    beforeEach(function() {
	      instance = TestUtils.renderIntoDocument(<CommentBox url="/api/comments" pollInterval={200000}/>);
	    });

	    it("should render a heading with the given text", function() {
	      var heading = TestUtils.findRenderedDOMComponentWithTag(instance, "h1");
	      expect(ReactDOM.findDOMNode(heading).textContent).toBe("Comments");
	    });
	  });

	  describe("with a container reference required", function() {
	    var inputs,
			form;
		
	    beforeEach(function() {
	      instance = ReactDOM.render(React.createElement(CommentBox, {"url": "/api/comments", "pollInterval": 200000}), container);

	      this.eventSpy = jasmine.createSpy();
	      container.addEventListener("broadcast", this.eventSpy, false);
	      inputs = TestUtils.scryRenderedDOMComponentsWithTag(instance, 'input');
	      form = TestUtils.findRenderedDOMComponentWithTag(instance, 'form')
	    });

	    afterEach(function() {
	      container.removeEventListener("broadcast", this.eventSpy, false);
	    });

	    it("should send comment and retrieve a list", function() {
	    	$.mockjax({
	    		  url: "/api/comments",
	    		  responseText: AjaxResponses.submit.success
	    		});
	    	
	    	var name = inputs[0],
		    	text = inputs[1];
	    	
	    	TestUtils.Simulate.change(name, { target: { value: 'Lao Tzu' } });
	    	TestUtils.Simulate.change(text, { target: { value: 'The journey of a thousand miles begins with one step' } });
	    	
	    	TestUtils.Simulate.submit(form);
	    	var comments = TestUtils.scryRenderedDOMComponentsWithClass(instance, 'comment');
	    	expect(comments.length).toBe(1);
	    	expect(TestUtils.findRenderedDOMComponentWithClass(instance, 'commentAuthor').textContent).toBe('Lao Tzu');
	    	expect(TestUtils.findRenderedDOMComponentWithTag(instance, 'span').textContent.trim()).toBe('The journey of a thousand miles begins with one step');
	    });
	  });
	  
});