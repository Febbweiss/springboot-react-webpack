package com.opengroupe.cloud.saas.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.opengroupe.cloud.saas.domain.Comment;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class React {

    private ThreadLocal<NashornScriptEngine> engineHolder = new ThreadLocal<NashornScriptEngine>() {
        @Override
        protected NashornScriptEngine initialValue() {
            NashornScriptEngine nashornScriptEngine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
            try {
                nashornScriptEngine.eval(read("static/js/nashorn-polyfill.js"));
                nashornScriptEngine.eval(read("META-INF/resources/webjars/react/0.14.7/react.min.js"));
                nashornScriptEngine.eval(read("META-INF/resources/webjars/marked/0.3.2/marked.js"));
//                nashornScriptEngine.eval(read("classpath:static/js/react-bootstrap.js"));
//                nashornScriptEngine.eval(read("classpath:static/js/comments.js"));
                nashornScriptEngine.eval(read("static/js/app.js"));
                nashornScriptEngine.eval(read("static/js/app.render.js"));
            } catch (ScriptException e) {
                throw new RuntimeException(e);
            }
            return nashornScriptEngine;
        }
    };

    public  String renderCommentBox(List<Comment> comments) {
        try {
            Object html = engineHolder.get().invokeFunction("renderServer", comments);
            return String.valueOf(html);
        }
        catch (Exception e) {
            throw new IllegalStateException("failed to render react component", e);
        }
    }

    private Reader read(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        return new InputStreamReader(in);
    }
}