package com.my.study.config.dynamicDataSource;

public class DynamicDataSource  {
   /* @Override
    protected Object determineCurrentLookupKey() {
        try {
            Field targetF = this.getClass().getSuperclass().getDeclaredField("targetDataSources");
            targetF.setAccessible(true);
            Map<Object, Object> targetV = (Map<Object, Object>) targetF.get(this);

            String ds = DynamicDataSourceContextHolder.getDataSourceType();

          *//*  if (ds != null){
                System.out.println("操作的数据源是： "
                        + ds + "->url:" + ((DruidDataSource) targetV.get(ds)).getUrl());
            }*//*

            return DynamicDataSourceContextHolder.getDataSourceType();

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "ds1";
    }*/
}
