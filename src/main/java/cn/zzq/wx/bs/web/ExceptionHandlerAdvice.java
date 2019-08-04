package cn.zzq.wx.bs.web;

import cn.zzq.wx.common.ResultData;
import cn.zzq.wx.util.AddressUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${err.logging.file}")
    private String errFile;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 拦截所有的Exception
     */
    @ExceptionHandler(value = {Exception.class})
    public void exception(Exception exception, HttpServletResponse response, HttpServletRequest request) {
        try {
            FileWriter fileWriter = new FileWriter(errFile, true);
            fileWriter.write("========================================================================================================================================\n");
            //记录错误时间
            fileWriter.write(sdf.format(new Date()) + "\n");
            //记录用户设备型号
            fileWriter.write(request.getHeader("User-Agent") + "\n");

            String ip = AddressUtil.getRemoteAddress(request);
            String requestURL = request.getRequestURL().toString();
            String url = request.getQueryString() == null ? requestURL + "" : (requestURL + "?" + request.getQueryString());
            //记录请求地址
            fileWriter.write(url + "\n");
            //记录请求ip地址
            fileWriter.write(ip + "\n");
            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                if (paramValues.length == 1) {
                    String paramValue = paramValues[0];
                    if (paramValue.length() != 0) {
                        //记录请求参数
                        fileWriter.write("param: " + paramName + " = " + paramValue + "\n");
                    }
                }
            }
            PrintWriter printWriter = new PrintWriter(fileWriter);
            //把异常信息记录到日志文件中
            exception.printStackTrace(printWriter);
            //关闭writer流
            fileWriter.write("\n");
            fileWriter.close();
            printWriter.close();
        } catch (IOException e) {
            logger.info("I can't open the file " + errFile);
        }
        //控制台打印异常详细信息
        exception.printStackTrace();
        //使用OutputStream流向客户端输出错误信息
        try {
            OutputStream os = response.getOutputStream();
            //通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
            response.setHeader("content-type", "text/html;charset=UTF-8");
            ResultData<Object> resultData = new ResultData<>();
            String e = exception.toString();
            // 异常类型
            resultData.setData("异常类型：" + (e.contains(":") ? e.substring(0, e.indexOf(":")) : e));
            resultData.setCode(555);// 异常信息
            resultData.setMsg("异常信息：" + exception.getMessage());
            String data = JSON.toJSONString(resultData);
            //将字符转换成字节数组，指定以UTF-8编码进行转换
            byte[] dataByteArr = data.getBytes("UTF-8");
            //使用OutputStream流向客户端输出字节数组
            os.write(dataByteArr);
            //关闭输出流
            os.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}