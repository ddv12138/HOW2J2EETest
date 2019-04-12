package CRUD.DAO;

import CRUD.JavaBean.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {
    @Select("select * from Product")
    List<Product> ListProduct();

    @Insert("insert into Product(id,name,price,cid) values(#{id},#{name},#{price},#{cid})")
    int Insert(Product p);

    @Delete("delete from Product where id = #{id}")
    int DeleteById(Product p);
}
