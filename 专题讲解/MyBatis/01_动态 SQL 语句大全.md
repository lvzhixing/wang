# 动态 SQL 语句大全

读完这篇文章里你能收获到

1. Mybatis动态SQL语句大全
2. Mybatis中如何定义变量
3. Mybatis中如何提取公共的SQL片段

# 1、if语句

需求：根据作者名字和博客名字来查询博客，如果作者名字为空，那么只根据博客名字查询，反之，则根据作者名来查询

```xml
<select id="queryBlogIf" parameterType="map" resultType="blog">
 select * from blog where
 <if test="title != null and title != '' ">
  title = #{title}
 </if>
 <if test="author != null and author != '' ">
  and author = #{author}
 </if>
</select>
```

这样写我们可以看到，如果 author 等于 null，那么查询语句为 `select * from user where title=#{title}`,但是如果title为空呢？那么查询语句为 `select * from user where and author=#{author}`，这是错误的SQL 语句，如何解决呢？请看下面的 where 语句！

# 2、where语句

修改上面的SQL语句：

```xml
<select id="queryBlogIf" parameterType="map" resultType="blog">
 select * from blog
 <where>
  <if test="title != null and title != ''">
   title = #{title}
  </if>
  <if test="author != null and author != ''">
   and author = #{author}
  </if>
 </where>
</select>
```

where 元素只会在子元素返回任何内容的情况下才插入 “WHERE” 子句。而且，若子句的开头为 “AND” 或 “OR”，where 元素也会将它们去除

如果 where 元素与你期望的不太一样，你也可以通过自定义 trim 元素来定制 where 元素的功能，如下：

```xml
<trim prefix="WHERE" prefixOverrides="AND | OR ">
  ...
</trim>
```

prefixOverrides 属性会忽略通过管道分隔的文本序列（**注意此例中的空格也是必要的**）

它的作用是移除所有指定在 prefixOverrides 属性中的内容，并且插入 prefix 属性中指定的内容（注意 prefixOverrides 单词不能写错，包括大小写，否则会报错）

# 3、set语句

同理，上面的对于查询 SQL 语句包含 where 关键字，如果在进行更新操作的时候，含有 set 关键词，我们怎么处理呢？

```xml
<!--注意set是用的逗号隔开-->
<update id="updateBlog" parameterType="map">
 update blog
 <set>
  <if test="title != null and title != '' ">
   title = #{title},
  </if>
  <if test="author != null and author != '' ">
   author = #{author}
  </if>
 </set>
 where id = #{id}
</update>
```

这个例子中，set 元素会动态的在行首插入 SET 关键字，并会删掉额外的逗号（这些逗号是在使用条件语句给列赋值时引入的）

也是可以和 trim 搭配使用的，如下：

```xml
<trim prefix="SET" suffixOverrides=",">
  ...
</trim>
```

# 4、choose语句

有时候，我们不想用到所有的查询条件，只想选择其中的一个，查询条件有一个满足即可，使用 choose 标签可以解决此类问题，类似于 Java 的 switch 语句

```xml
<select id="queryBlogChoose" parameterType="map" resultType="blog">
 select * from blog
 <where>
  <choose>
   <when test="title != null and title != '' ">
    title = #{title}
   </when>
   <when test="author != null and author != '' ">
    and author = #{author}
   </when>
   <otherwise>
    and views = #{views}
   </otherwise>
  </choose>
 </where>
</select>
```

# 5、foreach语句

## 1、新增

批量新增博客信息到数据库：

```xml
<insert id="batchInsertBlog" useGeneratedKeys="true" keyProperty="id">
    insert into blog (title, author, views, status) values
    <foreach collection="blogList" item="blog" separator=",">
        (#{blog.title}, #{blog.author}, #{blog.views}, #{blog.status})
    </foreach>
</insert>
```

mapper中这样写：

```java
int batchInsertBlog(List<Blog> blogList);
```

## 2、删除

```xml
<delete id="batchDeleteByIds">
    DELETE FROM blog WHERE id in
    <foreach collection="ids" item="item" index="index" open="(" separator="," close=")">
        #{item,jdbcType=VARCHAR}
    </foreach>
</delete>
```

mapper中这样写：

```java
int batchDeleteByIds(@Param("ids") List<String> ids);
```

## 3、更新

对所有表都适用，公用的写法：

```xml
<update id="updateTable">
    UPDATE ${tableName}
    SET
    <foreach collection="dataMap" index="key" item="value"  separator="," >
        ${key} = #{value}
    </foreach>
    WHERE
    id = #{id}
</update>
```

mapper中这样写：

```java
 void updateTable(@Param("tableName") String tableName, Param("id") String id, @Param("dataMap") HashMap dataMap);
```

## 4、查询

批量查询博客信息

```xml
<select id="getAllBlog"  resultType="blog">
    SELECT title, author, views, status
    FROM blog
    <if test="blogList != null and blogList.size() > 0">
        WHERE id in
        <foreach collection="blogList" item="item" index="index" open="(" separator="," close=")">
            #{item}
        </foreach>
    </if>
</select>
```

mapper中这样写：

```java
 void getAllBlog(@Param("blogList") List<Long> blogList);
```

# 6、SQL片段

有时候可能某个 sql 语句我们用的特别多，为了增加代码的重用性，简化代码，我们需要将这些代码抽取出来，然后使用时直接调用。

提取SQL片段：

```xml
<sql id="if-title-author">
 <if test="title != null and title != '' ">
  title = #{title}
 </if>
 <if test="author != null and author != '' ">
  and author = #{author}
 </if>
</sql>
```

引用SQL片段：

```xml
<select id="queryBlogIf" parameterType="map" resultType="blog">
 select * from blog
 <where>
  <!-- 引用 sql 片段，如果refid 指定的不在本文件中，那么需要在前面加上 namespace-->
  <include refid="if-title-author"></include>
  <!-- 在这里还可以引用其他的 sql 片段 -->
 </where>
</select>
```

注意事项：

- 最好基于 单表来定义 sql 片段，提高片段的可重用性
- 在 sql 片段中不要包括 where

# 7、bind元素

bind 元素允许你在 OGNL 表达式以外创建一个变量，并将其绑定到当前的上下文。比如：

```xml
<select id="selectBlogsLike" resultType="Blog">
  <bind name="pattern" value="'%' + _parameter.getTitle() + '%'" />
  SELECT * FROM BLOG
  WHERE title LIKE #{pattern}
</select>
```

# 8、总结

相信大家在实际开发中会经常用到上面总结梳理的这些标签元素，确实非常方便，可以大大提高开发效率，希望大家多使用，多练习，熟能生巧























































































































