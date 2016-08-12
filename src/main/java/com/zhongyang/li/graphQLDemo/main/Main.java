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

import com.zhongyang.li.graphQLDemo.action.FatherTable;
import com.zhongyang.li.graphQLDemo.action.Parse;
import com.zhongyang.li.graphQLDemo.action.Query;
import com.zhongyang.li.graphQLDemo.action.UserTable;
import com.zhongyang.li.graphQLDemo.bean.User;

import static graphql.schema.GraphQLArgument.newArgument;

import graphql.GraphQL;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;

public class Main {
	
	
	
	private static GraphQLOutputType userType;

	private static void initOutputType() {
		userType=Parse.beanToType(com.zhongyang.li.graphQLDemo.bean.User.class);
	}

	public static void main(String[] args) {
		    initOutputType();
		    FatherTable ut= new UserTable("users");
	        String query1 = "{users(id:4,page:2,size:5,name:\"john\") {id,name,pic}}";
	        String query2 = "{user(id:6) {id,sex,name,pic}}";
	        String query3 = "{user(id:6) {id,sex,name,pic},users(page:2,size:5,name:\"john\") {id,sex,name,pic}}";
	        Query q= new Query("Hello");
	        
	        System.out.println(q.query(ut.createField(userType), query1));
	        


	}

}
