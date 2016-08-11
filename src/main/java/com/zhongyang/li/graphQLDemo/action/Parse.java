package com.zhongyang.li.graphQLDemo.action;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.zhongyang.li.graphQLDemo.bean.User;

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLOutputType;

public class Parse {
	
	public static GraphQLOutputType beanToType(Class clazz){
		
		Field fs[]=clazz.getDeclaredFields();
		List<GraphQLFieldDefinition> fieldDefinitions=new ArrayList<GraphQLFieldDefinition>();
		for(Field f:fs){
			//System.out.println(f.getType()+":"+f.getName());
			fieldDefinitions.add(treeGrammar(f.getName(),f.getType().toString()));
		}
		String clazzName = clazz.getSimpleName();
		return newObject()
	              .name(clazzName)
	              .fields(fieldDefinitions).build();
	}
    public static GraphQLFieldDefinition treeGrammar(String name,String type){
    	switch (type) {
		case "int":	
			return  newFieldDefinition().name(name).type(GraphQLInt).build();
        case "class java.lang.String":
        	return  newFieldDefinition().name(name).type(GraphQLString).build();
		default:
			return null;
			
		}
    	
    }
//    private GraphQLFieldDefinition createUserField() {
//        return GraphQLFieldDefinition.newFieldDefinition()
//                .name("user")
//                
//                .argument(newArgument().name("id").type(GraphQLInt).build())
//                .type(userType)
//                .dataFetcher(environment -> {
//                    // 获取查询参数
//                    int id = environment.getArgument("id");
//
//                    // 执行查询, 这里随便用一些测试数据来说明问题
//                    User user = new User();
//                    user.setId(id);
//                    user.setAge(id + 15);
//                    user.setSex(id % 2);
//                    user.setName("Name_" + id);
//                    user.setPic("pic_" + id + ".jpg");
//                    return user;
//                })
//                .build();
//    }
//    
    public static GraphQLFieldDefinition createUserField(String query){
    	
    	
    	
    	return null;
    }
	public static  void main(String args[]) {
		//beanToType(com.zhongyang.li.graphQLDemo.bean.User.class);
//        System.out.println(com.zhongyang.li.graphQLDemo.bean.User.class.getSimpleName());
	}
}
