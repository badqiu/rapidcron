package com.github.rapidcron.common.util;

public class ProcessOutput {

	private int exitCode;
	private String out;
	private String errOut;

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}

	public String getErrOut() {
		return errOut;
	}

	public void setErrOut(String errOut) {
		this.errOut = errOut;
	}

	public int getExitCode() {
		return exitCode;
	}

	public void setExitCode(int exitCode) {
		this.exitCode = exitCode;
	}
	

}
