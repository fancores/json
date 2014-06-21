package fan.core.json;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
/**
 * <p> ##################################################### </p>
 * <p> @描述：封装与请求相关的 JSON 常用操作的工具类 </p>
 * <p> @作者：fancy </p>
 * <p> @邮箱：fancores@163.com </p>
 * <p> @日期：2014-06-19 </p>
 * <p> ##################################################### </p>
 */
public class JsonHttpUtil {
	
	private JsonHttpUtil(){
		
	}
	
	/** 写出JSON */
	public static void toWriteOut(HttpServletResponse response, String json) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = null;
		try {
			out = response.getWriter();
	        out.write(json);
	        out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null){
				out.close();
			}
		}
	}
	
}