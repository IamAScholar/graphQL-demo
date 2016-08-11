package com.zhongyang.li.graphQLDemo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import graphql.GraphQL;

public class Demo1 {

	public static void main(String[] args) {

//        GraphQLObjectType queryType = newObject()
//                        .name("helloWorldQuery")
//                        .field(newFieldDefinition()
//                                .type(GraphQLString)
//                                .name("hello")
//                                .staticValue("world")
//                                .build())
//                        .build();
    	new GraphQLList(GraphQLString);
    	DataFetcher calculateComplicatedValue = new DataFetcher() { 
    	    @Override		
    	    public  Object get(DataFetchingEnvironment environment) {
    	        // environment.getSource() is the value of the surrounding
    	        // object. In this case described by objectType
    	        List<String> value = new ArrayList<String>();// Perhaps getting from a DB or whatever 
    	        value.add("Hello");
    	        value.add("Hello1");
    	        return value;
    	    }
    	};
    	
	      GraphQLObjectType queryType = newObject()
	      .name("helloWorldQuery")
	      .field(newFieldDefinition()
	              .type(new GraphQLList(GraphQLString))
	              .name("hello")
	              .dataFetcher(calculateComplicatedValue)
	              
	              .build())
	      
	      
	      .build();
	      DataFetcher calculateComplicatedValue1 = new DataFetcher() {
	    	    @Override		
	    	    public  Object get(DataFetchingEnvironment environment) {
	    	        // environment.getSource() is the value of the surrounding
	    	        // object. In this case described by objectType
	    	        List<String> value = new ArrayList<String>();// Perhaps getting from a DB or whatever 
	    	        value.add("Hello11");
	    	        value.add("Hello111");
	    	        return value;
	    	    }
	    	};
	      GraphQLObjectType person = newObject()
	    	      .name("person")
	    	      .field(newFieldDefinition()
	    	              .type(new GraphQLList(GraphQLString))
	    	              .name("hello")
	    	              .dataFetcher(calculateComplicatedValue1)
	    	              //.type(queryType)
	    	              //.name("hello1")
	    	              .build())
	    	      
	    	      .build();
	     
        GraphQLSchema schema = GraphQLSchema.newSchema()
                        //.query(queryType)
                        .query(person)
                        .build();
         Object result = new GraphQL(schema).execute("{person{ hello}").getData();

        System.out.println(result);
        // Prints: {hello=world}
    }
	  private GraphQLFieldDefinition createUsersField() {
		  
		  DataFetcher calculateComplicatedValue1 = new DataFetcher() {
	    	    @Override		
	    	    public  Object get(DataFetchingEnvironment environment) {
	    	        // environment.getSource() is the value of the surrounding
	    	        // object. In this case described by objectType
	    	        List<String> value = new ArrayList<String>();// Perhaps getting from a DB or whatever 
	    	        value.add("Hello11");
	    	        value.add("Hello111");
	    	        return value;
	    	    }
	    	};
		  return GraphQLFieldDefinition.newFieldDefinition().name("person")
	    	
	    	              .type(new GraphQLList(GraphQLString))
	    	            
	    	              .dataFetcher(calculateComplicatedValue1)
	    	              //.type(queryType)
	    	              //.name("hello1")
	    	              .build();
	    	      
	    	      
	  }
}