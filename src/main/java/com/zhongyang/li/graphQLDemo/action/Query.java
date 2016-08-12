package com.zhongyang.li.graphQLDemo.action;

import static graphql.schema.GraphQLObjectType.newObject;

import graphql.GraphQL;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLSchema;

public class Query {
	private GraphQLSchema schema;
	private String queryName;
	public Query(String queryName){
		this.queryName=queryName;
	}
	public Object query(GraphQLFieldDefinition field,String query){
		schema=GraphQLSchema.newSchema().query(newObject()
                .name(queryName)
                .field(field)
                .build()).build();
		
		return  new GraphQL(schema).execute(query).getData();
	}
}
