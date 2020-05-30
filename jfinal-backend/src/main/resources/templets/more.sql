#sql("findTestMore")
  select * from test
#end

#namespace("more")
  #sql("findTestNameSpace")
    select * from test
  #end
#end