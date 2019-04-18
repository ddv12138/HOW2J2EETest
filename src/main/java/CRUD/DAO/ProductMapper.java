package CRUD.DAO;

import CRUD.JavaBean.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    @Select("select * from Product limit #{limit} offset #{offset}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "price", property = "price"),
            @Result(column = "cid", property = "cid"),
            @Result(column = "cid", property = "category", one = @One(select = "CRUD.DAO.CategoryDao.selectByPrimaryKey"))
    })
    List<Product> ListProduct(Map<String, Object> par);

    @Insert("insert into Product(id,name,price,cid) values(#{id},#{name},#{price},#{cid})")
    int Insert(Product p);

    @Delete("delete from Product where id = #{id}")
    int DeleteById(Product p);

    @Select("select * from Product where id = #{id}")
    Product getById(String id);
}
