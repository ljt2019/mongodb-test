/*
 * 自己所创建的包名与MongoClient所在的包名一样，因此相当于包下的类直接引用，不需要导包
 * 更多内容请移步网址：http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/quick-tour/
 */
package com.mongodb;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.lte;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class MongoUtils {

	/**
	 * 连接到MongoDB服务器
	 * 
	 * @param uri
	 * @return
	 */
	public static MongoClient connet(String uri) {
		try {
			MongoClient mongoClient = new MongoClient(new MongoClientURI(uri));
			System.out.println("Connnect to MongoDB successfully..." + mongoClient);
			return mongoClient;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return null;
	}

	/**
	 * 连接到MongoDB服务器中的指定集合
	 * 
	 * @param uri
	 *            服务器uri
	 * @param database
	 *            数据库名
	 * @param col
	 *            集合名
	 * @return
	 */
	public static MongoCollection<Document> connetToCollection(String uri, String database, String col) {
		try {
			@SuppressWarnings("resource")
			MongoCollection<Document> collection = new MongoClient(new MongoClientURI(uri)).getDatabase(database)
					.getCollection(col);
			System.out.println("Connnect to MongoDB successfully..." + collection.getNamespace());
			return collection;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return null;
	}

	/**
	 * 检索打印所有文档
	 * 
	 * @param collection
	 */
	public static void findAll(MongoCollection<Document> collection) {
		collection.find().forEach(print());
	}

	/**
	 * 查询文档中第一条数据
	 * 
	 * @param collection
	 */
	public static void findFirst(MongoCollection<Document> collection) {
		System.out.println(collection.find().first().toJson());
	}

	/**
	 * 依据指定字段查询文档中的信息
	 * 
	 * @param collection
	 */
	public static void findByField(MongoCollection<Document> collection, String field, String fieldName) {
		// http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/quick-tour/
		collection.find(eq(field, fieldName)).forEach(print());
		;
	}

	/**
	 * 查询指定数值字段某范围之间的数据
	 * 
	 * @param collection
	 * @param field
	 * @param leftField
	 * @param rightField
	 * @param type
	 */
	public static void findBetween(MongoCollection<Document> collection, String field, int leftField, int rightField,
			int type) {
		switch (type) {
		case 1:
			collection.find(and(gt(field, leftField), lt(field, rightField))).forEach(print());
			break;
		case 2:
			collection.find(and(gte(field, leftField), lt(field, rightField))).forEach(print());
			break;
		case 3:
			collection.find(and(gt(field, leftField), lte(field, rightField))).forEach(print());
			break;
		case 4:
			collection.find(and(gte(field, leftField), lte(field, rightField))).forEach(print());
			break;
		default:
			break;
		}
	}

	/**
	 * 查询数据库记录条数
	 * 
	 * @param collection
	 * @return
	 */
	public static long count(MongoCollection<Document> collection) {
		long count = collection.countDocuments();
		System.out.println("共 " + count + " 条数据");
		return count;

	}

	/**
	 * 文档打印块
	 * 
	 * @return
	 */
	public static Block<Document> print() {
		Block<Document> printBlock = new Block<Document>() {
			public void apply(final Document document) {
				System.out.println(document.toJson());
			}
		};
		return printBlock;
	}

	/**
	 * 向集合中插入一个文档
	 * 
	 * @param collection
	 */
	public static void insertOne(MongoCollection<Document> collection) {
		Document document = new Document("gymId", "0123456789abc")

				// 嵌入文档
				.append("loc", new Document("type", "Point").append("coordinates", Arrays.asList(1, 2)))

				.append("address", "广东省珠海市").append("latitude", 23.1399211512).append("longitude", 113.3319244938665)
				.append("name", "MongoJDBC001").append("id", "0bea170a9a0e11e8b9def48e38c3c5b0")
				.append("endTime", "17:00:00").append("openTime", "00:30:00").append("status", 0);

		collection.insertOne(document);
	}

	/**
	 * 更新指定字段一条文档信息
	 * 
	 * @param collection
	 *            所在的集合
	 * @param field
	 *            字段名
	 * @param originValue
	 *            原始值
	 * @param targetValue
	 *            目标值
	 */
	public static void updateOne(MongoCollection<Document> collection, String field, int originValue, int targetValue) {
		collection.updateOne(eq(field, originValue), new Document("$set", new Document(field, targetValue)));
	}

	public static void updateOneByField(MongoCollection<Document> collection, String field, String originValue,
			String targetValue) {
		collection.updateOne(eq(field, originValue), new Document("$set", new Document(field, targetValue)));
	}

	/**
	 * 更新指定字段多条文档信息
	 * 
	 * @param collection
	 *            所在的集合
	 * @param field
	 *            字段名
	 * @param originValue
	 *            原始值
	 * @param targetValue
	 *            目标值
	 */
	public static void updateManyByField(MongoCollection<Document> collection, String field, int originValue,
			int targetValue) {
		collection.updateMany(eq(field, originValue), new Document("$set", new Document(field, targetValue)));
	}

	public static void updateManyByField(MongoCollection<Document> collection, String field, String originValue,
			String targetValue) {
		collection.updateMany(eq(field, originValue), new Document("$set", new Document(field, targetValue)));
	}

}
