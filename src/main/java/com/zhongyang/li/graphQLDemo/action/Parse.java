package com.zhongyang.li.graphQLDemo.action;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.Scalars.GraphQLFloat;
import static graphql.Scalars.GraphQLBoolean;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.zhongyang.li.graphQLDemo.bean.User;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLSchema;

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

}
