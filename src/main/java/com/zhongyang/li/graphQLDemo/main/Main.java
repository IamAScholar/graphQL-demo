package com.zhongyang.li.graphQLDemo.main;

import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLSchema;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhongyang.li.graphQLDemo.action.Parse;
import com.zhongyang.li.graphQLDemo.bean.User;

import static graphql.schema.GraphQLArgument.newArgument;

import graphql.GraphQL;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;

public class Main {
	
	
	
	private GraphQLOutputType userType;

	private void initOutputType() {
	      /**
	       * 会员对象结构
	       */
		userType=Parse.beanToType(com.zhongyang.li.graphQLDemo.bean.User.class);
	}
	   private GraphQLFieldDefinition createUsersField() {
	        return GraphQLFieldDefinition.newFieldDefinition()
	                .name("users")
	                .argument(newArgument().name("page").type(GraphQLInt).build())
	                .argument(newArgument().name("size").type(GraphQLInt).build())
	                .argument(newArgument().name("name").type(GraphQLString).build())
	                .type(new GraphQLList(userType))
	                .dataFetcher(environment -> {
	                    // 获取查询参数
	                    int page = environment.getArgument("page");
	                    int size = environment.getArgument("size");
	                    String name = environment.getArgument("name");

	                    // 执行查询, 这里随便用一些测试数据来说明问题
	                    List<User> list = new ArrayList<User>(6);
	                    for (int i = 0; i < 6; i++) {
	                        User user = new User();
	                        user.setId(i);
	                        user.setAge(i + 15);
	                        user.setSex(i % 2);
	                        user.setName("ll" + "_" + "ll" + "_" + i);
	                        user.setPic("pic_" + i + ".jpg");
	                        list.add(user);
	                    }
	                    return list;
	                })
	                .build();
	    }
    private GraphQLFieldDefinition createUserField() {
        return GraphQLFieldDefinition.newFieldDefinition()
                .name("user")
                
                .argument(newArgument().name("id").type(GraphQLInt).build())
                .type(userType)
                .dataFetcher(environment -> {
                    // 获取查询参数
                    int id = environment.getArgument("id");

                    // 执行查询, 这里随便用一些测试数据来说明问题
                    User user = new User();
                    user.setId(id);
                    user.setAge(id + 15);
                    user.setSex(id % 2);
                    user.setName("Name_" + id);
                    user.setPic("pic_" + id + ".jpg");
                    return user;
                })
                .build();
    }
    private GraphQLSchema schema;

    public GraphQLSchema getSchema() {
		return schema;
	}
	public void setSchema(GraphQLSchema schema) {
		this.schema = schema;
	}
	public Main() {
        initOutputType();
        schema = GraphQLSchema.newSchema().query(newObject()
                .name("GraphQuery")
                .field(createUsersField())
                .field(createUserField())
                .build()).build();
    }
	public static void main(String[] args) {
		 GraphQLSchema schema = new Main().getSchema();

	        String query1 = "{users(page:2,size:5,name:\"john\") {id,name,pic}}";
	        String query2 = "{user(id:4) {id,sex,name,pic}}";
	        String query3 = "{user(id:6) {id,sex,name,pic},users(page:2,size:5,name:\"john\") {id,sex,name,pic}}";

	        Map<String, Object> result1 = (Map<String, Object>) new GraphQL(schema).execute(query1).getData();
	        Map<String, Object> result2 = (Map<String, Object>) new GraphQL(schema).execute(query2).getData();
	        Map<String, Object> result3 = (Map<String, Object>) new GraphQL(schema).execute(query3).getData();
	        
	        // 查询用户列表
	        System.out.println(result1);
	        // 查询单个用户
	        System.out.println(result2);
	        // 单个用户、跟用户列表一起查
	        System.out.println(result3);

	}

}
