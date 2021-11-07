package com.sht.crowd.util;

import java.util.List;

public class Page {

    private Integer pageno;
    private Integer pagesize;
    private List datas;
    private Integer totalsize;
    private Integer totalno;

    public Page(Integer pageno,Integer pagesize){
        if(pageno<=0){
            this.pageno = 0;
        }else{
            this.pageno = pageno;
        }
        if(pagesize<=0){
            this.pagesize = 10;
        }else{
            this.pagesize = pagesize;
        }

        this.pagesize = pagesize;
    }

    public Integer getPageno() {
        return pageno;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public List getDatas() {
        return datas;
    }

    public Integer getTotalsize() {
        return totalsize;
    }

    public Integer getTotalno() {
        return totalno;
    }

    public void setPageno(Integer pageno) {
        this.pageno = pageno;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public void setDatas(List datas) {
        this.datas = datas;
    }

    public void setTotalsize(Integer totalsize) {
        this.totalsize = totalsize;
        this.totalno = (totalsize%pagesize)==0?(totalsize/pagesize):(totalsize/pagesize+1);
    }

    private void setTotalno(Integer totalno) {
        this.totalno = totalno;
    }

    public Integer getStartIndex(){
        return (this.pageno-1)*pagesize;
    }
}
