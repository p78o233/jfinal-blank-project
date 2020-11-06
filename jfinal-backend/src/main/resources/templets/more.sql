#sql("findTestMore")
  select * from test
#end

#namespace("more")
  #sql("findTestNameSpace")
    select * from test
  #end

  #sql("findTestMore")
    select * from test where name = '123'
  #end

#end