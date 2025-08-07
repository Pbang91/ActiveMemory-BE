package com.example.activememory.global.log.wrapper;

import com.example.activememory.global.log.stream.CachedBodyServletOutputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class CustomResponseWrapper extends HttpServletResponseWrapper {
    private final ByteArrayOutputStream cachedOutputStream = new ByteArrayOutputStream();
    private final ServletOutputStream outputStream;
    private final PrintWriter writer;
    private final HttpServletResponse originalResponse;

    public CustomResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);

        this.originalResponse = response;
        this.outputStream = new CachedBodyServletOutputStream(cachedOutputStream);
        this.writer = new PrintWriter(new OutputStreamWriter(cachedOutputStream));
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    public byte[] getCachedBody() throws IOException {
        if (writer != null) {
            writer.flush();
        } else if (outputStream != null) {
            outputStream.flush();
        }

        return cachedOutputStream.toByteArray();
    }

    @Override
    public void flushBuffer() throws IOException {
        if (!originalResponse.isCommitted()) {
            originalResponse.getOutputStream().write(getCachedBody());
            originalResponse.flushBuffer();
        }
    }
}
