package fan.core.json;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
/**
 * <p> ##################################################### </p>
 * <p> @描述：封装 JSON 常用操作的工具类 </p>
 * <p> @作者：fancy</p>
 * <p> @邮箱：fancores@163.com </p>
 * <p> @日期：2014-06-19 </p>
 * <p> ##################################################### </p>
 */
public class JsonUtil {
	
	/**
	 * <p> 默认的日期格式 </p>
	 * <p> 2014-06-21 12:09:13 </p>
	 */
	private static final String DATE_FORMAT_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	
	private JsonUtil(){
		
	}
	
	/**
	 * <p> 序列化对象 </p>
	 * <pre>
	 * >>> Person(name, sex)
	 * >>> Person person = new Person("fan", "male");
	 * >>> JsonUtil.toJsonMap("person", person);
	 * >>> output:
	 * >>> --------------------------------------
	 * >>> {"person":{"name":"fan","sex":"male"}}
	 * >>> --------------------------------------
	 * >>> Person p1 = new Person("fan", "male");
	 * >>> Person p2 = new Person("cai", "female");
	 * >>> List personList = new ArrayList();
	 * >>> personList.add(p1);
	 * >>> personList.add(p2);
	 * >>> JsonUtil.toJsonMap("persons", personList);
	 * >>> output:
	 * >>> -----------------------------------------------------------------------
	 * >>> {"persons":[{"name":"fan","sex":"male"},{"name":"cai","sex":"female"}]}
	 * >>> -----------------------------------------------------------------------
	 * </pre>
	 */
	public static <T> String toJsonMap(String key, T value){
		return toJsonMap(key, value, DATE_FORMAT_PATTERN_DEFAULT);
	}

	/**
	 * <p> 序列化对象 </p>
	 * <pre>
	 * >>> Person(name, sex)
	 * >>> Person p1 = new Person("fan", "male");
	 * >>> Person p2 = new Person("cai", "female");
	 * >>> Map map = new HashMap();
	 * >>> map.put("p1", p1);
	 * >>> map.put("p2", p2);
	 * >>> JsonUtil.toJsonMap(map);
	 * >>> output:
	 * >>> ---------------------------------------------------------------------
	 * >>> {"p1":{"name":"fan","sex":"male"},"p2":{"name":"cai","sex":"female"}}
	 * >>> ---------------------------------------------------------------------
	 * </pre>
	 */
	public static <T> String toJsonMap(Map<String, T> keyValueMap){
		return toJsonMap(keyValueMap, DATE_FORMAT_PATTERN_DEFAULT);
	}
	
	/**
	 * <p> 序列化对象 </p>
	 * <pre>
	 * >>> Person(name, sex, birthday)
	 * >>> Person person = new Person("fan", "male", new Date());
	 * >>> JsonUtil.toJsonMap("person", person, "yyyy-MM-dd");
	 * >>> output:
	 * >>> --------------------------------------------------------------
	 * >>> {"person":{"name":"fan","sex":"male","birthday":"2014-06-21"}}
	 * >>> --------------------------------------------------------------
	 * </pre>
	 */
	public static <T> String toJsonMap(String key, T value, String dateFormatPattern){
		Map<String, T> map = new HashMap<String, T>();
		map.put(key, value);
		return toJsonMap(map, dateFormatPattern);
	}
	
	/**
	 * <p> 序列化对象 </p>
	 * <pre>
	 * >>> Person(name, sex, birthday)
	 * >>> Person p1 = new Person("fan", "male", new Date());
	 * >>> Person p2 = new Person("cai", "female", new Date());
	 * >>> Map map = new HashMap();
	 * >>> map.put("p1", p1);
	 * >>> map.put("p2", p2);
	 * >>> JsonUtil.toJsonMap(map, "yyyy-MM-dd");
	 * >>> output:
	 * >>> -------------------------------------------------------------
	 * >>> {
	 * >>>   "p1":{"name":"fan","sex":"male","birthday":"2014-06-21"},
	 * >>>   "p2":{"name":"cai","sex":"female","birthday":"2014-06-21"}
	 * >>> }
	 * >>> -------------------------------------------------------------
	 * </pre>
	 */
	public static <T> String toJsonMap(Map<String, T> keyValueMap, String dateFormatPattern){
		Gson gson = new GsonBuilder().setDateFormat(dateFormatPattern).create();
		return gson.toJson(keyValueMap);
	}
	
	/**
	 * <p> 序列化对象 </p>
	 * <pre>
	 * >>> Person(name, sex, birthday)
	 * >>> Person person = new Person("fan", "male", new Date());
	 * >>> JsonUtil.toJsonMap("person", person, Date.class);
	 * >>> output:
	 * >>> ---------------------------------------
	 * >>> {"person":{"name":"fan","sex":"male"}}
	 * >>> ---------------------------------------
	 * >>> JsonUtil.toJsonMap("person", person, (Object)"sex");
	 * >>> output:
	 * >>> ----------------------------------------------------------
	 * >>> {"person":{"name":"fan","birthday":"2014-06-21 05:30:14"}}
	 * >>> ----------------------------------------------------------
	 * </pre>
	 */
	public static <T> String toJsonMap(String key, T value, T... exclusion){
		return toJsonMap(key, value, DATE_FORMAT_PATTERN_DEFAULT, exclusion);
	}
	
	/**
	 * <p> 序列化对象 </p>
	 * <pre>
	 * >>> Person(name, sex, birthday)
	 * >>> Person p1 = new Person("fan", "male", new Date());
	 * >>> Person p2 = new Person("cai", "female", new Date());
	 * >>> Map map = new HashMap();
	 * >>> map.put("p1", p1);
	 * >>> map.put("p2", p2);
	 * >>> JsonUtil.toJsonMap(map, Date.class);
	 * >>> output:
	 * >>> ---------------------------------------------------------------------
	 * >>> {"p1":{"name":"fan","sex":"male"},"p2":{"name":"cai","sex":"female"}}
	 * >>> ---------------------------------------------------------------------
	 * >>> JsonUtil.toJsonMap(map, (Object)"sex");
	 * >>> output:
	 * >>> --------------------------------------------------------
	 * >>> {
	 * >>>   "p1":{"name":"fan","birthday":"2014-06-21 05:39:43"},
	 * >>>   "p2":{"name":"cai","birthday":"2014-06-21 05:39:43"}
	 * >>> }
	 * >>> --------------------------------------------------------
	 * </pre>
	 */
	public static <T> String toJsonMap(Map<String, T> keyValueMap, T... exclusion){
		return toJsonMap(keyValueMap, DATE_FORMAT_PATTERN_DEFAULT, exclusion);
	}

	/**
	 * <p> 序列化对象 </p>
	 * <pre>
	 * >>> Person(name, sex, birthday)
	 * >>> Person person = new Person("fan", "male", new Date());
	 * >>> JsonUtil.toJsonMap("person", person, "yyyy-MM-dd", Date.class);
	 * >>> output:
	 * >>> ---------------------------------------
	 * >>> {"person":{"name":"fan","sex":"male"}}
	 * >>> ---------------------------------------
	 * >>> JsonUtil.toJsonMap("person", person, "yyyy-MM-dd", (Object)"sex");
	 * >>> output:
	 * >>> -------------------------------------------------
	 * >>> {"person":{"name":"fan","birthday":"2014-06-21"}}
	 * >>> -------------------------------------------------
	 * </pre>
	 */
	public static <T> String toJsonMap(String key, T value, String dateFormatPattern, T... exclusion){
		Map<String, T> map = new HashMap<String, T>();
		map.put(key, value);
		return toJsonMap(map, dateFormatPattern, exclusion);
	}

	/**
	 * <p> 序列化对象 </p>
	 * <pre>
	 * >>> Person(name, sex, birthday)
	 * >>> Person p1 = new Person("fan", "male", new Date());
	 * >>> Person p2 = new Person("cai", "female", new Date());
	 * >>> Map map = new HashMap();
	 * >>> map.put("p1", p1);
	 * >>> map.put("p2", p2);
	 * >>> JsonUtil.toJsonMap(map, "yyyy-MM-dd", Date.class);
	 * >>> output:
	 * >>> ---------------------------------------------------------------------
	 * >>> {"p1":{"name":"fan","sex":"male"},"p2":{"name":"cai","sex":"female"}}
	 * >>> ---------------------------------------------------------------------
	 * >>> JsonUtil.toJsonMap(map, "yyyy-MM-dd", (Object)"sex");
	 * >>> output:
	 * >>> ----------------------------------------------
	 * >>> {
	 * >>>   "p1":{"name":"fan","birthday":"2014-06-21"},
	 * >>>   "p2":{"name":"cai","birthday":"2014-06-21"}
	 * >>> }
	 * >>> ----------------------------------------------
	 * </pre>
	 */
	public static <T> String toJsonMap(Map<String, T> keyValueMap, String dateFormatPattern, T... exclusion){
		Gson gson = new GsonBuilder().setExclusionStrategies(new JsonExclusionStrategy(exclusion)).setDateFormat(dateFormatPattern).create();
		return gson.toJson(keyValueMap);
	}

	/**
	 * <p> 序列化对象 </p>
	 * <pre>
	 * >>> Person(name, sex)
	 * >>> Person person = new Person("fan", "male");
	 * >>> JsonUtil.toJson(person);
	 * >>> output:
	 * >>> ----------------------------
	 * >>> {"name":"fan","sex":"male"}
	 * >>> ----------------------------
	 * >>> Person p1 = new Person("fan", "male");
	 * >>> Person p2 = new Person("cai", "female");
	 * >>> List personList = new ArrayList();
	 * >>> personList.add(p1);
	 * >>> personList.add(p2);
	 * >>> JsonUtil.toJson(personList);
	 * >>> output:
	 * >>> -----------------------------------------------------------
	 * >>> [{"name":"fan","sex":"male"},{"name":"cai","sex":"female"}]
	 * >>> -----------------------------------------------------------
	 * </pre>
	 */
	public static <T> String toJson(T object){
		return toJson(object, DATE_FORMAT_PATTERN_DEFAULT);
	}

	/**
	 * <p> 序列化对象 </p>
	 * <pre>
	 * >>> Person(name, sex, birthday)
	 * >>> Person person = new Person("fan", "male", new Date());
	 * >>> JsonUtil.toJson(person, "yyyy-MM-dd");
	 * >>> output:
	 * >>> ---------------------------------------------------
	 * >>> {"name":"fan","sex":"male","birthday":"2014-06-21"}
	 * >>> ---------------------------------------------------
	 * </pre>
	 */
	public static <T> String toJson(T object, String dateFormatPattern){
		return new GsonBuilder().setDateFormat(dateFormatPattern).create().toJson(object);
	}
	
	/**
	 * <p> 序列化对象 </p>
	 * <pre>
	 * >>> Person(name, sex, birthday)
	 * >>> Person person = new Person("fan", "male", new Date());
	 * >>> JsonUtil.toJson(person, Date.class);
	 * >>> output:
	 * >>> ---------------------------
	 * >>> {"name":"fan","sex":"male"}
	 * >>> ---------------------------
	 * >>> JsonUtil.toJson(person, (Object)"sex");
	 * >>> output:
	 * >>> -----------------------------------------------
	 * >>> {"name":"fan","birthday":"2014-06-21 05:30:14"}
	 * >>> -----------------------------------------------
	 * </pre>
	 */
	public static <T> String toJson(T object, T... exclusion){
		return toJson(object, DATE_FORMAT_PATTERN_DEFAULT, exclusion);
	}

	/**
	 * <p> 序列化对象 </p>
	 * <pre>
	 * >>> Person(name, sex, birthday)
	 * >>> Person person = new Person("fan", "male", new Date());
	 * >>> JsonUtil.toJson(person, "yyyy-MM-dd", Date.class);
	 * >>> output:
	 * >>> ---------------------------
	 * >>> {"name":"fan","sex":"male"}
	 * >>> ---------------------------
	 * >>> JsonUtil.toJson(person, "yyyy-MM-dd", (Object)"sex");
	 * >>> output:
	 * >>> --------------------------------------
	 * >>> {"name":"fan","birthday":"2014-06-21"}
	 * >>> --------------------------------------
	 * </pre>
	 */
	public static <T> String toJson(T object, String dateFormatPattern, T... exclusion){
		Gson gson = new GsonBuilder().setExclusionStrategies(new JsonExclusionStrategy(exclusion)).setDateFormat(dateFormatPattern).create();
		return gson.toJson(object);
	}
	
	/**
	 * <p> 反序列化POJO </p>
	 * <pre>
	 * >>> ----------------------------------
	 * >>> json = {"name":"fan","sex":"male"}
	 * >>> ----------------------------------
	 * >>> Person(name, sex)
	 * >>> Person person = JsonUtil.fromJson(json, Person.class);
	 * </pre>
	 * 
	 */
	public static <T> T fromJson(String json, Class<T> clazz){
		return fromJson(json, DATE_FORMAT_PATTERN_DEFAULT, clazz);
	}

	
	/**
	 * <p> 反序列化POJO </p>
	 * <pre>
	 * >>> ----------------------------------------------------------
	 * >>> json = {"name":"fan","sex":"male","birthday":"2014-06-21"}
	 * >>> ----------------------------------------------------------
	 * >>> Person(name, sex, birthday)
	 * >>> Person person = JsonUtil.fromJson(json, "yyyy-MM-dd", Person.class);
	 * </pre>
	 * 
	 */
	public static <T> T fromJson(String json, String dateFormatPattern, Class<T> clazz){
		return new GsonBuilder().setDateFormat(dateFormatPattern).create().fromJson(json, clazz);
	}
	
	/**
	 * <p> 反序列化Object </p>
	 * <pre>
	 * >>> ------------------------------------------------------------------
	 * >>> json = [{"name":"fan","sex":"male"},{"name":"cai","sex":"female"}]
	 * >>> ------------------------------------------------------------------
	 * >>> Person(name, sex)
	 * >>> List&lt;Person&gt; personList = JsonUtil.fromJson(json, new TypeToken&lt;List&lt;Person&gt;&gt;(){}.getType());
	 * >>> -----------------------------------------------
	 * >>> json = {"person":{"name":"fan","sex":"male"}}
	 * >>> -----------------------------------------------
	 * >>> Map&lt;String, Person&gt; personMap = JsonUtil.fromJson(json, new TypeToken&lt;Map&lt;String, Person&gt;&gt;(){}.getType());
	 * >>> Person person = personMap.get("person");
	 * </pre>
	 * 
	 */
	public static <T> T fromJson(String json, Type type){
		return fromJson(json, DATE_FORMAT_PATTERN_DEFAULT, type);
	}

	/**
	 * <p> 反序列化Object </p>
	 * <pre>
	 * >>> ----------------------------------------------------------
	 * >>> json = 
	 * >>> [
	 * >>>   {"name":"fan","sex":"male","birthday":"2014-06-21"},
	 * >>>   {"name":"cai","sex":"female","birthday":"2014-06-21"}
	 * >>> ]
	 * >>> ----------------------------------------------------------
	 * >>> Person(name, sex, birthday)
	 * >>> List&lt;Person&gt; personList = JsonUtil.fromJson(json, "yyyy-MM-dd", new TypeToken&lt;List&lt;Person&gt;&gt;(){}.getType());
	 * >>> ---------------------------------------------------------------------
	 * >>> json = {"person":{"name":"fan","sex":"male","birthday":"2014-06-21"}}
	 * >>> ---------------------------------------------------------------------
	 * >>> Map&lt;String, Person&gt; personMap = JsonUtil.fromJson(json, "yyyy-MM-dd", new TypeToken&lt;Map&lt;String, Person&gt;&gt;(){}.getType());
	 * >>> Person person = personMap.get("person");
	 * </pre>
	 * 
	 */
	public static <T> T fromJson(String json, String dateFormatPattern, Type type){
		return new GsonBuilder().setDateFormat(dateFormatPattern).create().fromJson(json, type);
	}
	
	/**
	 * <p> 格式化（美化）JSON 串内容 </p>
	 * <pre>
	 * >>> ----------------------------------
	 * >>> {
	 * >>>   "p1": {
	 * >>>     "name": "fan",
	 * >>>     "sex": "male",
	 * >>>     "birthday": "2014-06-21"
	 * >>>   },
	 * >>>   "p2": {
	 * >>>     "name": "cai",
	 * >>>     "sex": "female",
	 * >>>     "birthday": "2014-06-21"
	 * >>>   }
	 * >>> }
	 * >>> ----------------------------------
	 * </pre>
	 */
	public static String formatJson(String json){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(new JsonParser().parse(json));
		return jsonString;
	}
	
	/**
	 * <p> ########################################################### </p>
	 * <p> @描述：过滤类或字段, 防止 JSON 序列化时循环引用引发异常。（内部使用类）</p>
	 * <p> @作者：fancy </p>
	 * <p> @邮箱：fancores@163.com </p>
	 * <p> @日期：2014-06-19 </p>
	 * <p> ########################################################### </p>
	 */
	static class JsonExclusionStrategy implements ExclusionStrategy {
		
		/** 需要过滤的类型或属性的集合 */
		private Object[] exclusion;
		
		public JsonExclusionStrategy(Object[] exclusion){
			this.exclusion = exclusion;
		}

		/**
		 * <p> 过滤属性类型, 防止类型属性序列化 </p>
		 */
		@Override
		public boolean shouldSkipClass(Class<?> skipClass) {
			for(Object obj : exclusion){
				if(obj == skipClass){
					return true;
				}
			}
			return false;
		}
		
		/**
		 * <p> 过滤属性名称, 防止名称属性序列化 </p>
		 */
		@Override
		public boolean shouldSkipField(FieldAttributes skipField) {
			String field = skipField.getName();
			for(Object obj : exclusion){
				if(obj.equals(field)){
					return true;
				}
			}
			return false;
		}

	}
	
}