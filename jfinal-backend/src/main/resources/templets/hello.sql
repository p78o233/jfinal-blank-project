#sql("findTest")
  select * from test
#end

#sql("findByParamTest")
    select * from test where id = #para(id) and cdNum = #para(cdNum)
#end

#sql("findByParamPage")
    select * from test where score = #para(score) order by id desc
#end
-- 注释
#sql("findByLike")
    select * from test where name like  concat('%',#para(name), '%')
#end

-- 动态查询
#sql("findTestNull")
    select * from test where id = #para(id)
    #if(cdNum!=0)
        and cdNum = #para(cdNum)
    #end
    #if(name != null )
        and name like  concat('%',#para(name), '%')
    #end
#end

-- 使用lastid分页查询数据
#sql("selectPage")
    select * from wl_channel_consumer where 1 = 1
    #if(lastId != 0)
       and id < #para(lastId)
    #end
    order by id desc limit #para(pageSize)
#end