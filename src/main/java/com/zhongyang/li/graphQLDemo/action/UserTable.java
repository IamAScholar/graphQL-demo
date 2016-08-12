package com.zhongyang.li.graphQLDemo.action;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;

import java.util.ArrayList;
import java.util.List;

import com.zhongyang.li.graphQLDemo.bean.User;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLArgument;

public class UserTable extends FatherTable {

	public UserTable(String tableName) {
		super(tableName);
	}

	@Override
	public List<GraphQLArgument> getArgs() {
		List<GraphQLArgument> ga= new ArrayList<>();
		ga.add(newArgument().name("id").type(GraphQLInt).build());
		ga.add(newArgument().name("size").type(GraphQLInt).build());
		ga.add(newArgument().name("page").type(GraphQLInt).build());
		ga.add(newArgument().name("name").type(GraphQLString).build());
		return ga;
	}

	@Override
	public List<Object> getDatas(DataFetchingEnvironment environment) {
		int id=environment.getArgument("id");
		int size=environment.getArgument("size");
		int page=environment.getArgument("page");
		String name=environment.getArgument("name");
	    List list = new ArrayList<User>(6);
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
	}

}
