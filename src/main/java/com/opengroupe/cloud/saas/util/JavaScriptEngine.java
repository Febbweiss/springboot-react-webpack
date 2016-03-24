package com.opengroupe.cloud.saas.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScriptEngine {

	private final ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");

	public JavaScriptEngine polyfillToNashorn() {
		this.loadFromClassPath("static/js/nashorn-polyfill.js");
		return this;
	}

	public JavaScriptEngine eval(String script) {
		try {
			this.scriptEngine.eval(script);
		} catch (ScriptException e) {
			throw new IllegalStateException("Failed to eval " + script + "!", e);
		}
		return this;
	}

	public JavaScriptEngine loadFromClassPath(String file) {
		try {
			this.scriptEngine.eval(readFromClassPath(file));
		} catch (ScriptException e) {
			throw new IllegalStateException("Failed to loadFromClassPath " + file + "!", e);
		}
		return this;
	}

	public Object invokeFunction(String functionName, Object... args) {
		try {
			return ((Invocable) this.scriptEngine).invokeFunction(functionName, args);
		} catch (ScriptException | NoSuchMethodException e) {
			throw new IllegalArgumentException("Failed to invoke " + functionName, e);
		}
	}

	public <T> T invokeFunction(String functionName, Function<Object, T> converter, Object... args) {
		return converter.apply(invokeFunction(functionName, args));
	}

	private String readFromClassPath(String path) {
		try (InputStream in = getClass().getClassLoader().getResourceAsStream(path)) {
			if (in == null) {
				throw new IllegalArgumentException(path + " is not found!");
			}
			return copyToString(in, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new IllegalStateException("Failed to read " + path, e);
		}
	}

	private static String copyToString(InputStream in, Charset charset) throws IOException {
		StringBuilder out = new StringBuilder();
		try (InputStreamReader reader = new InputStreamReader(in, charset);) {
			char[] buffer = new char[4096];
			int bytesRead = -1;
			while ((bytesRead = reader.read(buffer)) != -1) {
				out.append(buffer, 0, bytesRead);
			}
			return out.toString();
		}
	}
}
