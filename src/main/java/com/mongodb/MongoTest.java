package com.mongodb;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class MongoTest {

	// mongodb://${username}:${password}@${ip}:${port}/${dbname}?authSource=admin&authMechanism=SCRAM-SHA-1
	// mongodb://${username}:${password}@${ip}:${port}
	public static final String URI = "mongodb://sail:sail@10.0.20.103:21000,10.0.20.104:21001,10.0.20.105:21002";

	public static final String DATABASE = "dev_sail";

	public static final String COLLECTION = "stadium";

	public static void main(String[] args) {

		// 连接到集合
		MongoCollection<Document> collection = MongoUtils.connetToCollection(URI, DATABASE, COLLECTION);

		/******************** 操作 ********************/

		// 遍历打印集合信息
//		MongoUtils.findAll(collection);

		// 计算总记录数
		// MongoUtils.count(collection);

		// 依据指定字段名查询文档信息
		 MongoUtils.findByField(collection, "name", "修改测试代码2");

		// 查询指定数值字段某范围之间的数据
		// MongoUtils.findBetween(collection, "code", 2000, 2001, 2);

		// 更新指定字段的第一条文档信息
//		 MongoUtils.updateOneByField(collection, "name", "修改测试代码", "修改测试代码1");

		// 更新指定指字段的所以信息
//		 MongoUtils.updateManyByField(collection, "name", "修改测试代码", "修改测试代码2");

		// 向集合中插入一个文档
		// MongBUtils.insertOne(collection);

	}
}
