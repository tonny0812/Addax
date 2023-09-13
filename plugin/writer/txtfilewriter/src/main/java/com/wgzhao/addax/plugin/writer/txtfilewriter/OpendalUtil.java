package com.wgzhao.addax.plugin.writer.txtfilewriter;

import org.apache.opendal.BlockingOperator;
import org.apache.opendal.Operator;

import java.util.Map;

public class OpendalUtil {


    public static Operator getOperator(String operatorName)
    {
        Operator operator = null;
        try {
            operator = (Operator) Class.forName(operatorName).newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return operator;
    }

    /**
     * create {@link  org.apache.opendal.Operator}  instance from path string
     * for example , the path is 'dropbox://test/test.txt' , the operatorName is 'dropbox'
     *
     * @param path path string
     *             the path string must be start with 'dropbox://' or 'file://'
     * @return {@link  org.apache.opendal.Operator} instance
     */
    public static BlockingOperator getOperator(String path, Map<String, String> params)
    {
        String schema;
        if (path.contains(":")) {
            schema = path.split(":", 2)[0];
        } else {
            schema = "fs";
        }
        try {
            return new BlockingOperator(schema, params);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
