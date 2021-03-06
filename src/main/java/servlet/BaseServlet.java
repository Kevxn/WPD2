package servlet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

import util.MustacheRenderer;


public class BaseServlet extends HttpServlet {
    @SuppressWarnings("unused")
    static final Logger LOG = LoggerFactory.getLogger(BaseServlet.class);

    public static final String PLAIN_TEXT_UTF_8 = "text/plain; charset=UTF-8";
    public static final String HTML_UTF_8 = "text/html; charset=UTF-8";
    public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");

    private final MustacheRenderer mustache;

    protected BaseServlet() {mustache = new MustacheRenderer();
    }

    protected void issue(String mimeType, int returnCode, byte[] output, HttpServletResponse response) throws IOException {
        response.setContentType(mimeType);
        response.setStatus(returnCode);
        response.getOutputStream().write(output);
    }


    int getInt(HttpServletRequest request, String param) {
        String value = request.getParameter(param);
        return (value == null) ? 0 : Integer.parseInt(value);
    }

    String getString(HttpServletRequest request, String param, String deflt) {
        String value = request.getParameter(param);
        if (value == null) {
            return deflt;
        }
        return value;
    }

}
