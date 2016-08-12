package com.zhongyang.li.graphQLDemo.action;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLObjectType.newObject;

import java.util.ArrayList;
import java.util.List;

import com.zhongyang.li.graphQLDemo.bean.User;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLSchema;

public abstract class FatherTable {
	
	private String tableName;
    public FatherTable(String tableName){
    	this.tableName=tableName;
    }
	public GraphQLFieldDefinition createField(GraphQLOutputType type) {
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(tableName)
                .argument(getArgs())
                .type(new GraphQLList(type))
                .dataFetcher(environment -> {
                   return getDatas(environment);
                })
                .build();
	}

	public abstract List<GraphQLArgument> getArgs();
	public abstract List<Object> getDatas(DataFetchingEnvironment environment);
}
