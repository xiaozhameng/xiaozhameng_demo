package com.xiaozhameng.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p>Description: </p>
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author xiaozhameng
 * @version V1.0 2018/4/21
 */
public class CommonUtils {

    public static String mapToQueryString(Map parameters) {
        return mapToQueryString(parameters,"UTF-8");
    }


    public static String mapToQueryString(Map parameters, String charSet) {
        String queryString = "";
        if(parameters != null && !parameters.isEmpty()) {
            Set entrySet = parameters.entrySet();
            Iterator i$ = entrySet.iterator();

            while(i$.hasNext()) {
                Map.Entry entry = (Map.Entry)i$.next();

                try {
                    String e = entry.getKey().toString();
                    Object value = entry.getValue();
                    List values = makeStringList(value);

                    Object v;
//                    for(Iterator i$1 = values.iterator(); i$1.hasNext(); queryString = queryString + e + "=" + URLEncoder.encode(v == null?"":v.toString(), charSet) + "&") {
//                        v = i$1.next();
//                    }
                    for(Iterator i$1 = values.iterator(); i$1.hasNext(); queryString = queryString + e + "=" + (v == null?"":v.toString()) + "&") {
                        v = i$1.next();
                    }
                } catch (Exception var11) {
                    throw new IllegalArgumentException("invalid charset : " + charSet);
                }
            }

            if(queryString.length() > 0) {
                queryString = queryString.substring(0, queryString.length() - 1);
            }
        }

        return queryString;
    }

    public static Map queryStringToMap(String queryString, String charSet) {
        if(queryString == null) {
            throw new IllegalArgumentException("queryString must be specified");
        } else {
            int index = queryString.indexOf("?");
            if(index > 0) {
                queryString = queryString.substring(index + 1);
            }

            String[] keyValuePairs = queryString.split("&");
            HashMap map = new HashMap();
            String[] arr$ = keyValuePairs;
            int len$ = keyValuePairs.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String keyValue = arr$[i$];
                if(keyValue.indexOf("=") != -1) {
                    String[] args = keyValue.split("=");
                    if(args.length == 2) {
                        try {
                            map.put(args[0], URLDecoder.decode(args[1], charSet));
                        } catch (UnsupportedEncodingException var11) {
                            throw new IllegalArgumentException("invalid charset : " + charSet);
                        }
                    }

                    if(args.length == 1) {
                        map.put(args[0], "");
                    }
                }
            }

            return map;
        }
    }

    private static List<String> makeStringList(Object value) {
        if(value == null) {
            value = "";
        }

        ArrayList result = new ArrayList();
        Object obj;
        if(value.getClass().isArray()) {
            for(int var5 = 0; var5 < Array.getLength(value); ++var5) {
                obj = Array.get(value, var5);
                result.add(obj != null?obj.toString():"");
            }

            return result;
        } else {
            Iterator var4;
            if(value instanceof Iterator) {
                var4 = (Iterator)value;

                while(var4.hasNext()) {
                    obj = var4.next();
                    result.add(obj != null?obj.toString():"");
                }

                return result;
            } else if(value instanceof Collection) {
                var4 = ((Collection)value).iterator();

                while(var4.hasNext()) {
                    obj = var4.next();
                    result.add(obj != null?obj.toString():"");
                }

                return result;
            } else if(value instanceof Enumeration) {
                Enumeration enumeration = (Enumeration)value;

                while(enumeration.hasMoreElements()) {
                    obj = enumeration.nextElement();
                    result.add(obj != null?obj.toString():"");
                }

                return result;
            } else {
                result.add(value.toString());
                return result;
            }
        }
    }

}
