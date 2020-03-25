package org.fe.ek.test.proj.es.dto.page;

import lombok.Data;
import org.fe.ek.test.model.constant.SysConst;

/**
 * @program: SimpleProj
 * @description: page tool for elastic search
 * the default page number is SysConst.DEFAULT_PAGE_NUM (1),
 * page size must no larger than SysConst.MAX_PAGE_SIZE (100), default value is SysConst.DEFAULT_PAGE_SIZE (10).
 * @author: Wang Zhenhua
 * @create: 2020-03-25
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-03-25
 **/
@Data
public class ElasticPage {

    /**
     * page number
     */
    private int current;

    /**
     * page size, the number of search hits
     */
    private int size;

    public ElasticPage() {
        this.current = SysConst.DEFAULT_PAGE_NUM;
        this.size = SysConst.DEFAULT_PAGE_SIZE;
    }

    public ElasticPage(int current, int size) {
        this.current = current <= 0 ? SysConst.DEFAULT_PAGE_NUM : current;
        this.size = size > SysConst.MAX_PAGE_SIZE ? SysConst.DEFAULT_PAGE_SIZE : size;
    }

    /**
     * calculate the <a href="https://javadoc.io/doc/org.elasticsearch/elasticsearch/2.3.0/org/elasticsearch/search/builder/SearchSourceBuilder.html#from(int)">from</a> value used for elastic search
     * @return
     */
    public int getFrom() {
        return (this.current - 1) * this.size;
    }
}
